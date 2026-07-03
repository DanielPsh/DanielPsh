import time

input("press enter...")
start = time.time()

input("press enter again after 20sec...")
end = time.time()

et = end-start
print("Real time: ", et, "Second")
print("Difference :", abs(et-20), "Second")

###
print("Start: ",start)
print("end: ",end)
