#List
name = {}
eng = {}
math = {}
for x in range(2):
    name[x] =int(input("input name: "))
for y in range(2):
    eng[y] =int(input("input eng: "))
for z in range(2):
    math[z] =int(input("input math: "))

#Output 
for x in range(2):
    print("name[",x, "]: ", name[x])
for y in range(2):
    print("eng[",y, "]: ", eng[y])
for z in range(2):
    print("math[",z, "]: ", math[z])
