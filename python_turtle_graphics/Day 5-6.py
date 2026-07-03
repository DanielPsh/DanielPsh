import turtle as t
import random

t1=t.Turtle()
t2=t.Turtle()
t1.shape("turtle")
t1.color("green")
t2.shape("turtle")
t2.color("red")
a1=random.randint(1,10)
b1=random.randint(1,10)

print("a1=", a1, "b1=", b1)
#t1.speed(a1)
#t2.speed(b1)

y1=0
y2=0
x1=0
x2=50
t2.up()
t2.goto(50,0)
t2.down()

while True:
    if y1>200 or y2>200:
        break
    y1=y1+a1
    y2=y2+b1
    t1.goto(x1,y1)
    t2.goto(x2,y2)
    y=y+1
