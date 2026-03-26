# DanielPsh — Portfolio

This repository is my portfolio and coursework archive, containing assignments, lab work, and small projects across multiple courses. It includes Java, Python networking, MIPS assembly, and Rust implementations, along with supporting scripts and tests—reflecting my progress and growing experience throughout my studies.

## Table of contents

- [Repo structure](#repo-structure)
- [Courses](#courses)
  - [COMP2011](#comp2011)
  - [COMP2021](#comp2021)
  - [COMP2322](#comp2322)
  - [COMP2421](#comp2421)
  - [COMP2432](#comp2432)
- [Quick “save to GitHub”](#quick-save-to-github)
- [Notes](#notes)

## Repo structure

- `Polyu/COMP2011/`: Java coursework
- `Polyu/COMP2021/`: Java OOP coursework (assignments + tests)
- `Polyu/COMP2322/`: Python networking labs (TCP/UDP, simple HTTP server)
- `Polyu/COMP2421/`: MIPS assembly exercises
- `Polyu/COMP2432/`: Rust labs + small scripts + project work

## Courses

### COMP2011

**What you’ll find**

- `Polyu/COMP2011/Assignment1/`: Java assignment code
- `Polyu/COMP2011/Assignment2/`: Java assignment code

**How to run (generic Java CLI)**

From the repo root:

```bash
cd "Polyu/COMP2011/Assignment1"
javac *.java
java SimpleArray_236
```

If a folder has multiple packages, compile from the package root with `javac` and run the correct main class.

### COMP2021

**What you’ll find**

- `Polyu/COMP2021/Assignment1/`
  - `src/`: Java source
  - `test/`: unit tests (JUnit)
- `Polyu/COMP2021/Assignment2/`
  - `src/`: Java source
  - `test/`: unit tests (JUnit)

**How to run (IntelliJ recommended)**

- Open `Polyu/COMP2021/Assignment1` or `Polyu/COMP2021/Assignment2` as a project.
- Run tests from the `test/` folder.

**How to run (CLI, if you prefer)**

If you have JDK installed, you can compile and run from the `src/` root package directory. (JUnit tests require adding JUnit jars to the classpath.)

### COMP2322

**Highlights**

- `Polyu/COMP2322/Lab05/MyHTTPServer1.py`: simple socket-based HTTP server
- `Polyu/COMP2322/Lab05/MyHTTPServer2.py`: another HTTP server variant
- `Polyu/COMP2322/Lab05/MyHTTPServer3.py`: another HTTP server variant
- `Polyu/COMP2322/Lab05/MyHTTPServer4.py`: another HTTP server variant
- `Polyu/COMP2322/socket_lab/`: TCP client/server exercises
- `Polyu/COMP2322/Lab04/Q3/`, `Polyu/COMP2322/Lab04/Q4/`: TCP lab questions
- `Polyu/COMP2322/tcp.py`, `Polyu/COMP2322/udp.py`: TCP/UDP exercises

**How to run**

Python HTTP server:

```bash
python3 "Polyu/COMP2322/Lab05/MyHTTPServer1.py"
```

Test it:

```bash
curl "http://127.0.0.1:8000/"
```

### COMP2421

**What you’ll find**

- `Polyu/COMP2421/*.s`: MIPS assembly exercises and examples
- `Polyu/COMP2421/MIPS/`: additional MIPS files

**How to run**

Use a MIPS simulator such as **MARS** or **QtSpim**:

- Open a `.s` file (example: `Polyu/COMP2421/Example.s`)
- Assemble + run inside the simulator

### COMP2432

**Highlights**

- `Polyu/COMP2432/Project/robot_blaze1/`: Rust project (Cargo)
- `Polyu/COMP2432/lab02/`, `lab03/`: Rust labs (Cargo)
- `Polyu/COMP2432/lab02_OptionalExercise/`: Rust optional exercises (Cargo)
- `Polyu/COMP2432/lec03/`, `lec04/`: lecture work + small `.sh` scripts
- `Polyu/COMP2432/Assign/`: data/scripts for assignments

**How to run (Rust / Cargo)**

```bash
cd "Polyu/COMP2432/Project/robot_blaze1"
cargo run
```

## Quick “save to GitHub”

If you want “cloud backup” after you add/edit files:

```bash
bash scripts/push.sh "your commit message"
```

## Notes

- macOS `.DS_Store`, Rust `target/`, and editor swap files are ignored to keep the repo clean for GitHub.
