package module1

//Задание 1
fun main(args: Array<String>) {
    println("hello Kotlin")

    // 1 Создайте переменную со значением null типа Any
    var any: Any? = null
    println(any)


    //2 Переопределите значение ранее созданной переменной на любую цифру

    any = 10
    println(any)


    //3 Создайте переменную, не указывая ее тип и присвойте ей значение из ранее созданной переменной
    //деленную на 2,5

    val anyVar = any / 2.5
    println(anyVar)

    //4 Напишите в комментарии какого типа последняя переменная
    //double
    println(anyVar::class)
}



import json
import pathlib
import difflib
from gigachat import GigaChat

# ================= CONFIG =================

ROOT = pathlib.Path.cwd().resolve()

GIGACHAT_API_TOKEN = "ВСТАВЬ_СЮДА_ТОКЕН"
GIGACHAT_MODEL = "GigaChat-Pro"

EXCLUDED_DIRS = {
    ".git", "node_modules", "venv", ".venv", "__pycache__",
    "dist", "build", "target", ".idea", ".gradle"
}

MAX_FILE_CHARS = 30000
MAX_LIST_ITEMS = 500

giga = GigaChat(
    base_url="",
    auth_url="",
    credentials=GIGACHAT_API_TOKEN,
    verify_ssl_certs=False,
    scope="GIGACHAT_API_CORP",
    timeout=320,
)

SYSTEM = """
Ты локальный coding agent уровня Devin-lite/Codex-lite.

Ты работаешь с проектом через инструменты:
- project_overview
- list_files
- read_file
- search
- propose_write

Главные правила:
1. Не выдумывай содержимое файлов.
2. Перед изменением файла обязательно прочитай его.
3. Никогда не меняй файл без propose_write.
4. Сначала составь короткий план, потом действуй.
5. Не трогай файлы вне рабочей директории.
6. Для вызова инструмента отвечай строго JSON:
{
  "tool": "read_file",
  "args": {
    "path": "src/main/java/App.java"
  }
}
7. Если инструмент не нужен — отвечай обычным текстом.
8. Когда правишь файл, отправляй полный новый content в propose_write.
9. Предпочитай минимальные изменения.
"""

# ================= SAFE PATH =================

def safe_path(file_path: str) -> pathlib.Path:
    resolved = (ROOT / file_path).resolve()
    if not str(resolved).startswith(str(ROOT)):
        raise ValueError("Запрещен доступ вне рабочей директории")
    return resolved


def is_excluded(path: pathlib.Path) -> bool:
    return any(part in EXCLUDED_DIRS for part in path.parts)


# ================= TOOLS =================

def project_overview() -> str:
    files = []
    markers = []

    for name in [
        "pom.xml",
        "build.gradle",
        "settings.gradle",
        "package.json",
        "requirements.txt",
        "pyproject.toml",
        "README.md",
    ]:
        if (ROOT / name).exists():
            markers.append(name)

    for p in ROOT.rglob("*"):
        if is_excluded(p):
            continue
        if p.is_file():
            files.append(p)

    extensions = {}
    for f in files:
        ext = f.suffix or "[no_ext]"
        extensions[ext] = extensions.get(ext, 0) + 1

    top_ext = sorted(extensions.items(), key=lambda x: x[1], reverse=True)[:20]

    return (
        f"ROOT: {ROOT}\n"
        f"Project markers: {', '.join(markers) or 'not found'}\n"
        f"Files count: {len(files)}\n"
        f"Top extensions:\n"
        + "\n".join(f"{ext}: {count}" for ext, count in top_ext)
    )


def list_files(path: str = ".") -> str:
    root = safe_path(path)
    result = []

    for p in root.rglob("*"):
        if is_excluded(p):
            continue

        rel = p.relative_to(ROOT)
        result.append(("DIR  " if p.is_dir() else "FILE ") + str(rel))

        if len(result) >= MAX_LIST_ITEMS:
            result.append("...результат обрезан")
            break

    return "\n".join(result)


def read_file(path: str) -> str:
    file = safe_path(path)

    if not file.is_file():
        return f"Файл не найден: {path}"

    text = file.read_text(encoding="utf-8", errors="replace")

    if len(text) > MAX_FILE_CHARS:
        return text[:MAX_FILE_CHARS] + "\n\n...файл обрезан"

    return text


def search(query: str, path: str = ".") -> str:
    root = safe_path(path)
    matches = []

    for p in root.rglob("*"):
        if is_excluded(p):
            continue

        if not p.is_file():
            continue

        try:
            text = p.read_text(encoding="utf-8", errors="replace")
        except Exception:
            continue

        if query in text:
            rel = str(p.relative_to(ROOT))
            lines = text.splitlines()

            hit_lines = []
            for i, line in enumerate(lines, start=1):
                if query in line:
                    hit_lines.append(f"{rel}:{i}: {line.strip()}")
                    if len(hit_lines) >= 5:
                        break

            matches.extend(hit_lines)

        if len(matches) >= 100:
            matches.append("...результат обрезан")
            break

    return "\n".join(matches) or "Ничего не найдено"


def propose_write(path: str, content: str) -> str:
    file = safe_path(path)

    old_content = ""
    if file.exists():
        old_content = file.read_text(encoding="utf-8", errors="replace")

    diff = difflib.unified_diff(
        old_content.splitlines(keepends=True),
        content.splitlines(keepends=True),
        fromfile=f"a/{path}",
        tofile=f"b/{path}",
    )

    diff_text = "".join(diff)

    print("\n========== DIFF ==========")
    print(diff_text if diff_text.strip() else "[нет изменений]")
    print("==========================\n")

    if not diff_text.strip():
        return "Изменений нет"

    confirm = input(f"Применить изменения к {path}? Напиши yes: ").strip().lower()

    if confirm != "yes":
        return "Изменения отклонены пользователем"

    file.parent.mkdir(parents=True, exist_ok=True)
    file.write_text(content, encoding="utf-8")

    return f"Файл изменен: {path}"


TOOLS = {
    "project_overview": lambda args: project_overview(),
    "list_files": lambda args: list_files(args.get("path", ".")),
    "read_file": lambda args: read_file(args["path"]),
    "search": lambda args: search(args["query"], args.get("path", ".")),
    "propose_write": lambda args: propose_write(args["path"], args["content"]),
}

# ================= MODEL =================

def extract_json(text: str):
    try:
        return json.loads(text)
    except Exception:
        pass

    start = text.find("{")
    end = text.rfind("}")

    if start == -1 or end == -1:
        return None

    try:
        return json.loads(text[start:end + 1])
    except Exception:
        return None


def ask_model(messages):
    try:
        response = giga.chat(
            messages,
            model=GIGACHAT_MODEL
        )
        return response.choices[0].message.content
    except Exception as e:
        return f"Ошибка вызова модели: {e}"


# ================= CLI =================

def main():
    if not GIGACHAT_API_TOKEN or GIGACHAT_API_TOKEN == "ВСТАВЬ_СЮДА_ТОКЕН":
        raise RuntimeError("Не задан GIGACHAT_API_TOKEN в коде")

    messages = [
        {"role": "system", "content": SYSTEM}
    ]

    print("GigaChat Code Agent NEXT запущен")
    print(f"ROOT: {ROOT}")
    print(f"MODEL: {GIGACHAT_MODEL}")
    print("exit — выход\n")

    while True:
        user_input = input("> ").strip()

        if user_input.lower() in {"exit", "quit"}:
            break

        messages.append({
            "role": "user",
            "content": user_input
        })

        for _ in range(12):
            answer = ask_model(messages)
            tool_call = extract_json(answer)

            if not tool_call or "tool" not in tool_call:
                print("\n" + answer + "\n")
                messages.append({
                    "role": "assistant",
                    "content": answer
                })
                break

            tool_name = tool_call["tool"]
            args = tool_call.get("args", {})

            if tool_name not in TOOLS:
                result = f"Неизвестный инструмент: {tool_name}"
            else:
                print(f"\n[tool] {tool_name} {args if tool_name != 'propose_write' else {'path': args.get('path')}}")
                try:
                    result = TOOLS[tool_name](args)
                except Exception as e:
                    result = f"Ошибка инструмента: {e}"

            messages.append({
                "role": "assistant",
                "content": answer
            })

            messages.append({
                "role": "user",
                "content": f"Результат инструмента {tool_name}:\n{result}"
            })


if __name__ == "__main__":
    main()




