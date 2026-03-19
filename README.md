# DanielPsh — Code Portfolio

This repository is my personal code portfolio and classwork archive.

## Highlights

- **COMP2322**
  - `Polyu/COMP2322/Lab05/MyHTTPServer1.py`: simple socket-based HTTP server
- **COMP2432**
  - `Polyu/COMP2432/Project/robot_blaze1/`: Rust project (Cargo)
  - `Polyu/COMP2432/lab02*/`, `lab03/`, `lec03/`, `lec04/`: Rust labs + shell scripts

## How to run (examples)

### Python HTTP server (COMP2322 Lab05)

```bash
python3 "Polyu/COMP2322/Lab05/MyHTTPServer1.py"
```

Then in another terminal:

```bash
curl "http://127.0.0.1:8000/"
```

### Rust projects (Cargo)

```bash
cd "Polyu/COMP2432/Project/robot_blaze1"
cargo run
```

## Notes

- macOS `.DS_Store` and nested `.git` folders are intentionally ignored/removed to keep the repo clean for GitHub.
