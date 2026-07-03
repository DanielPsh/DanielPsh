import turtle as t
import random

#Setup 'Enemy'
te=t.Turtle()
te.shape("turtle")
te.color("red")
te.speed(0)
te.up()
te.goto(0,200)

#Setup 'Food'
ts=t.Turtle()
ts.shape("circle")
ts.color("green")
ts.speed(0)
ts.up()
ts.goto(0,-200)

td=t.Turtle()
td.shape("circle")
td.color("green")
td.speed(0)
td.up()
td.goto(100,-200)

tf=t.Turtle()
tf.shape("circle")
tf.color("black")
tf.speed(0)
tf.up()
tf.goto(-100,-200)


#Setup Functions
def turn_up():
    t.setheading(90)
def turn_down():
    t.setheading(270)
def turn_left():
    t.setheading(180)
def turn_right():
    t.setheading(0)

def play():
    t.forward(10)
    ang=te.towards(t.pos())
    te.setheading(ang)
    te.forward(8)
    if t.distance(ts)<12:
        start_x=random.randint(-230,230)
        start_y=random.randint(-230,230)
        ts.goto(start_x,start_y)
        t.color("blue")
        t.write("Good!", False, "center", ("",15))
    if t.distance(td)<12:
        start_x1=random.randint(-230,230)
        start_y1=random.randint(-230,230)
        td.goto(start_x1,start_y1)
        t.color("blue")
        t.write("Good!", False, "center", ("",15))
    if t.distance(tf)<12:
        start_x2=random.randint(-230,230)
        start_y2=random.randint(-230,230)
        tf.goto(start_x2,start_y2)
        t.color("red")
        t.write("Bad!", False, "center", ("",15))
    if t.distance(te)>12:
        t.ontimer(play,100)
    
        

        
#Setup Controls
t.setup(500,500)
t.shape("turtle")
t.color("black")
t.speed(0)
t.up()
t.onkeypress(turn_up,"Up")
t.onkeypress(turn_left,"Left")
t.onkeypress(turn_right,"Right")
t.onkeypress(turn_down,"Down")
t.listen()
play()


    
