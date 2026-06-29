#Module Diy
#psh
#2022.03.08

import turtle as t
import random

t.bgcolor("black")
t1 = t.Turtle()
t2 = t.Turtle()
t1.speed(0)
t2.speed(0)
#------
def diyTree():
    t1.color("green")
    for x in range(3):
        t1.begin_fill()
        t1.fd(300)
        t1.left(360/3)
        t1.fd(300)
        t1.left(360/3)
        t1.fd(300)
        t1.left(360/3)
        t1.end_fill()
    
    
#------
def diyStar():
    x1 = random.randint(120,200)
    y1 = random.randint(-250,200)
    t2.up()
    t2.goto(x1,y1)
    t2.down()
    t2.color("pink")
    for y in range(12):
        t2.begin_fill()
        t2.fd(25)
        t2.left(200)
        t2.end_fill()

#------
#main

diyTree()
t1.up()
t1.goto(0,-150)
t1.down()
diyTree()
t1.up()
t1.goto(0,-300)
t1.down()
diyTree()

t1.color("brown")
t1.begin_fill()
t1.up()
t1.fd(100)
t1.down()
t1.right(90)
t1.fd(100)
t1.left(90)
t1.fd(100)
t1.left(90)
t1.fd(100)
t1.end_fill()
diyStar()
diyStar()
diyStar()
diyStar()
diyStar()
diyStar()
diyStar()


