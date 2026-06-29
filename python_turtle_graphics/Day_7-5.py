num = {}

index = 0
sum = 0
while True:
    num[index]=int(input("Enter a Number: "))
    print(num)
    sum = sum + num[index]
    index = index + 1
    if index > 5:
        break
    

print("End of While")
print("sum: ", sum)
