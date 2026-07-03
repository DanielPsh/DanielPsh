import turtle as t
t.shape("turtle")
t.speed(10)

#module
def makeCircle():
    t.color("red")
    t.begin_fill()
    t.circle(50)
    t.end_fill()

def move(x,y):
    t.up()
    t.goto(x,y)
    t.down()

def makeHouse():

    
    t.left(90)
    t.fd(100)
    t.left(90)
    t.fd(100)
    t.left(90)
    t.fd(100)
    t.left(90)
    t.fd(100)
    t.left(90)
    t.fd(100)

    t.left(60)
    t.fd(58)
    t.left(60)
    t.fd(58)


#call
makeHouse()
move(100,0)
t.left(150)
makeHouse()
move(-100,0)
t.left(150)
makeHouse()
move(0,100)
t.left(150)
makeHouse()
move(0,-100)
t.left(150)
makeHouse()


