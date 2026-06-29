import turtle as t
t.speed(0)

for x in range (100):
    if x%5 == 0:
        t.color("red")
    if x%5 == 1:
        t.color("orange")
    if x%5 == 2:
        t.color("yellow")
    if x%5 == 3:
        t.color("green")
    if x%5 == 4:
        t.color("blue")

    t.fd(100)
    t.left(130)
