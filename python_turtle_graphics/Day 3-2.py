import turtle as t
import random

t.bgcolor("black")
t1 = t.Turtle()
t2 = t.Turtle()
t1.speed(0)
t2.speed(0)
#------
qty=6
t1.color("purple")
for x in range(qty):
    t1.fd(100)
    t1.left(360/qty)
    
#------
t2.color("pink")
for y in range(30):
    t2.fd(100)
    t2.left(200)

    
    
