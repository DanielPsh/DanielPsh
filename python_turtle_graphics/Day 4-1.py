import turtle as t
import random
t.speed(0)


t1=t.Turtle()
t2=t.Turtle()
t1.speed(0)
t2.speed(0)

#----------
t.fd(100)
t.left(120)
t.fd(100)
t.left(120)
t.fd(100)
t.up()
#----------
t.goto(50,85)
t.left(120)
t.down()
t.fd(50)
t.left(120)
t.fd(100)
t.left(120)
t.fd(100)
t.left(120)
t.fd(100)
t.up()
#-----------
t.goto(50,0)
t.down()
t.left(270)
t.fd(50)
t.left(90)
t.fd(5)
t.left(90)
t.fd(50)

#-------------
def diy3():
    x1=random.randint(-20,80)
    y1=random.randint(0,120)
    t1.up()
    t1.goto(x1,y1)
    t1.color("pink")
    t1.down()
    for x in range(5):
        t1.begin_fill()
        t1.fd(15)
        t1.left(140)
        t1.end_fill()
 #--------------
diy3()
diy3()
diy3()
diy3()
diy3()
diy3()
