#input
name=input("Enter your name: ")
age=input("Enter your age: ")
gender=input("Enter your gender: ")
height=input("Enter your height: ")
weight=input("Enter your weight: ")

cheight=int(height)
cweight=int(weight)

#output
print("Your name is: ", name)
print("Your age is: ", age)
print("Your gender is(ex.Male/Female): ", gender)
print("Your height is: ", height)
print("Your weight is: ", weight)

print("Height =", cheight, "Weight =", cweight)
BMI=int((cweight/(cheight*cheight))*10000)
print("BMI =", BMI)

if(BMI < 30):
    print("Normal")

if(BMI == 25):
    print("Normal")

if(BMI > 30):
    print("Overweight")





