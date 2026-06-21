def greet(name):
    print(f"Hi {name}")


# 1 - Perform a task
# 2 - Return a value


round(1.9)  # built-in function


def get_greeting(name):
    return f"Hi {name}"


get_greeting("SangHyun")  # calling a function
message = get_greeting("SangHyun")
print(message)
file = open("content.txt", "r")
file.write(message)
