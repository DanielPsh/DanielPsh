import turtle as t
import random

#define module
def turn_up():
    t.left(2)

def turn_down():
    t.right(2)

def fire():
    ang=t.heading()
    while t.ycor()>0:
       t.forward(15)
       t.right(5)

    d=t.distance(target,0)
    t.sety(random.randint(10,100))

    if d<25:
        t.color("blue")
        t.write("Good!", False, "center", ("",15))
    else:
        t.color("red")
        t.write("Bad!", False, "center", ("",15))
        
    t.color("black")
    t.goto(-200,10)
    t.setheading(ang)
#main
t.goto(-300,0)
t.down()
t.goto(300,0)

target = random.randint(50,150)
print(target)
t.pensize(10)
t.color("green")
t.up()
t.goto(target-25,2)
t.down()
t.goto(target+25,2)

t.color("black")
t.up()
t.goto(-200,10)
t.setheading(20)

#Key control
t.onkeypress(turn_up, "Up")
t.onkeypress(turn_down, "Down")
t.onkeypress(fire, "space")
t.listen()
