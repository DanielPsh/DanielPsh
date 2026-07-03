import turtle as t
import random

t.bgcolor("black")
t1 = t.Turtle()
t2 = t.Turtle()
t1.speed(0)
t2.speed(0)
#------
def diyTree():
    qty=3
    t1.color("green")
    for x in range(qty):
        t1.color("green")
        t1.begin_fill()
        t1.fd(200)
        t1.left(360/qty)
        t1.end_fill()
        
        

diyTree()
