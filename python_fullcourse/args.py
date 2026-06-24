def multiply(*numbers):
    total = 1
    for number in numbers:
        total *= number
    return total


print(multiply(2, 3, 4, 5))

# () -> tuple (collection of objects) -> iterable
# [] -> list (collection of objects)

numbers = [int(x) for x in input("Enter a numbers: ").split(",")]
print(multiply(*numbers))
