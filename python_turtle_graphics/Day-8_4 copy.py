in_qty = {}
in_pizza = {}
count = 0;
pizza_name = {'','Pepperoni Pizza','Bulgogi Pizza','Hawaian Pizza','Cheese Pizza'}
pizza_price = {0,80,100,85,75}

def order():
    print("...PizzaCold...")
    print("...Order menu...")
    print("1.Pepperoni Pizza      80 RMB")
    print("2.Bulgogi Pizza        100 RMB")
    print("3.Hawaian Pizza        85 RMB")
    print("4.Cheese Pizza         75 RMB")

def view():
    print("...View menu...")
    
while True:
    print("...Welcome to PizzaCold!!...")
    print("1.Order 2.Check 0.End")
    menu = int(input())
    if menu < 0 or menu > 2:
        print("Invalid Choice")
        continue

    if menu == 1:
        order()
        in_pizza[count] = int(input("Enter your Pizza Choice...: "))
        in_qty [count]= int(input("Enter Quantity...: "))
        print("Pizza Choice: ", in_pizza)
        print("Quantity: ", in_qty)
        count = count + 1
        
    if menu == 2:
        print("Num   Pizza Choice   Quant   Price")
        for x in range(count):
            print(x+1, "     ", in_pizza, "       ", in_qty[x], "   ", pizza_price)
    if menu == 0:
        print("Bye...Ending...")
        break
