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
    t1.fd(300)
    t1.left(120)
    t1.fd(300)
    t1.left(120)
    t1.fd(300)
    t1.left(120)
    t1.up()
    t1.goto(0,-150)
    t1.down()
    t1.up()
    t1.goto(0,-300)
    t1.down()
   
#------
def diyStar():
    x1 = random.randint(0,200)
    y1 = random.randint(0,150)
    t2.goto(x1,y1)
    t2.color("pink")
    for y in range(12):
        t2.fd(100)
        t2.left(200)

#------
#main

diyTree()
diyTree()
diyTree()
diyStar()

    
