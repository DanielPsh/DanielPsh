#Module
def order():
    print("Order menu...")
def view():
    print("View menu...")
    
while True:
    print("1.Enter 2.Check 0.End")
    menu = int(input())
    if menu < 0 or menu > 2:
        print("Invalid Choice")
        continue

    if menu == 1:
        order()
    if menu == 2:
        view()
    if menu == 0:
        print("Bye...Ending...")
        break
