import turtle as t
import random

t1=t.Turtle()
t2=t.Turtle()
t1.shape("turtle")
t1.color("green")
t2.shape("turtle")
t2.color("green")
a1=random.randint(1,10)
b1=random.randint(1,10)

t1.speed(a1)
t2.speed(b1)

y=0

x1=random.randint(0,0)
y1=random.randint(0,200)
t2.up()
t2.goto(50,0)
t2.down()
x2=random.randint(50,50)
y2=random.randint(0,200)
while True:
    if y==200:
        break
    t1.goto(x1,y1)
    t2.goto(x2,y2)
    
