print(type(5))
print(type(range(5)))

# primitives are numbers, strings, booleans
# iterables are lists, tuples, sets, dictionaries, ranges

for x in range(5):
    print(x)  # x is iterable

print("\n")

for x in "Python":
    print(x)  # x is iterable

print("\n")

for x in [1, 2, 3, 4, 5]:
    print(x)  # x is iterable

print("\n")

shopping_cart = ["apple", "banana", "orange"]

for item in shopping_cart:
    print(item)  # item is iterable
