# Project NAME: TamJai Ordering System using Python
# used logic: if-loops, while-loops, for-loops, functions, math
# Edited by danielpark1030@gmail.com

import math

# from PIL import Image
# myImage = Image.open(
#     '/Users/sanghyunpark/DanielPsh/python_projects/tamjai_logo.jpg')
# myImage.show()

# variables
price = 0
order_list = []
# Toppings Vegetables $+4
TOPPINGS_VEG = [
    ("", )
]

# Toppings Meats $+8
TOPPINGS_MT = [
    ("", )
]

# Drinks
DRINKS = [
    ("Milk Tea", 18),
    ("Lemon Water", 18),
    ("Watercress", 16),
    ("Coke with Lemon and Salty Lemon", 18),
    ("Coffee", 18),
    ("Ovaltine", 18),
    ("Coke with Lemon", 18),
    ("Soy Milk", 16),
    ("Yuan Yang", 16),
    ("Horlicks", 16),
    ("Sprite with Lemon", 18),
    ("Soft Drinks", 10),
    ("Lemon Tea", 18),
    ("Honey with Lemon", 16),
    ("Sprite with Salted Lime", 18),
    ("Iced Water / Ice", 10),
]

# Appetizers
APPETIZER = [
    ("TuFei Chicken Wings", 25),
    ("Mala Chicken Wings", 25)
    ("Chicken Wings with Herb", 25)
    ("TuFei Chicken Wings & Drumsticks", 25)
    ("Roasted Pork Intestines", 28)
    ("Hong Kong Style Curry Fish & Fish Tofu", 12)
    ("Fresh Vegetables (Lettuce)", 16)
    ("Fresh Vegetables (Chinese Chives)", 16)
    ("Fresh Vegetables (Bean Sprout)", 16)
    ("Fresh Vegetables (Napa Cabbage)", 16)
    ("Minced Pork", 12)
]

# Cold Appetizer
COLD_APPETIZER = [
    ("Sliced Pork Belly with Mashed Garlic", 30)
    ("Pig’s Ear in Mala Sauce", 30)
    ("Homemade Pickles", 12)
    ("Pink Fungus with Cucumber and Lemon", 25)
    ("Century Egg in Sichuan Style", 30)
    ("Black Fungus in Special Chill Sauce", 25)
    ("Cucumber in Special Chill Sauce", 25)
    ("Shredded Pork Tripe", 30)
    ("Pig’s Oviduct in Mala Sauce", 30)
]


ICE_SURCHARGE = 2

# adding order list with the price


def add_to_order(item_name, item_price):
    # Add an item to the order list and update the total price.
    global price
    order_list.append({"name": item_name, "price": item_price})
    price += item_price
    print(f"Ordered {item_name}")

# display order list with the price


def show_order_list():
    # Display all items in the current order.
    print("\n========= YOUR ORDER =========")
    if not order_list:
        print("No items ordered yet.")
    else:
        for i, item in enumerate(order_list, 1):
            print(f"  {i}. {item['name']} - ${item['price']}")
        print(f"\nTotal: ${price}")
    print("==============================\n")


# Order drink cold or hot


def order_drink(drink_name, base_price):
    # Iced Water / Ice has no temperature option.
    if drink_name == "Iced Water / Ice":
        add_to_order(drink_name, base_price)
        return True

    print(
        f"[1] ICE (${base_price + ICE_SURCHARGE})\n[2] HOT (${base_price})\n")
    temp_choice = int(input("Enter your choice: "))
    if temp_choice == 1:
        add_to_order(f"COLD {drink_name}", base_price + ICE_SURCHARGE)
        return True
    elif temp_choice == 2:
        add_to_order(f"HOT {drink_name}", base_price)
        return True

    print("INVALID Choice. ENTER AGAIN\n")
    return False

# Menu


while True:
    # Welcome Page
    print("====================================")
    print("======= WELCOME TO TAMJAI ==========")
    print("====================================\n")

    # user input to choose their options
    print("[1] Make your own TAMJAI\n[2] Choose TAMJAI\n[3] SIDES\n[4] PAY BILL\n[5] EXIT")
    user_input = int(input("Enter your choice: "))
    # Make your own TAMJAI
    if user_input == 1:
        print("===============================")
        print("========= MIX & MATCH =========")
        print("===============================\n")
    elif user_input == 2:
        print("================================")
        print("====== TAMJAI's SIGNATURE ======")
        print("================================\n")
    elif user_input == 3:
        print("================================")
        print("======== SIDES & DRINKS ========")
        print("================================\n")
        print("[1] SIDES\n[2] DRINKS\n[3] Back To Home\n")
        user_input_sd = int(input("Enter your choice: "))
        if user_input_sd == 1:
            print("===============")
            print("==== SIDES ====")
            print("===============\n")
            print(
                "[1] Chicken Wing($25)\n[2] Pig Ears($23)\n[3] Fungues($21)\n[4] Cucumber($18)\n[5] Salad($13)\n[6] Back\n")
            user_input_sides = int(input("Enter your choice: "))
            if user_input_sides == 1:
                print("[1] Pepper flavour($25)\n[2] Mala flavour($25)\n")
                user_input_sides_chicken = int(input("Enter your choice: "))
                if user_input_sides_chicken == 1:
                    add_to_order("(PEPPER) Chicken", 25)
                elif user_input_sides_chicken == 2:
                    add_to_order("(MALA) Chicken", 25)
            elif user_input_sides == 2:
                print("[1] Spicy($23)\n[2] No Spicy($23)\n")
                user_input_sides_pe = int(input("Enter your choice: "))
                if user_input_sides_pe == 1:
                    add_to_order("(SPICY) Pig Ear", 23)
                elif user_input_sides_pe == 2:
                    add_to_order("(NO SPICY) Pig Ear", 23)
            elif user_input_sides == 3:
                print("[1] Spicy($21)\n[2] No Spicy($21)\n")
                user_input_sides_fg = int(input("Enter your choice: "))
                if user_input_sides_fg == 1:
                    add_to_order("(SPICY) Fungues", 21)
                elif user_input_sides_fg == 2:
                    add_to_order("(NO SPICY) Fungues", 21)
            elif user_input_sides == 4:
                add_to_order("Cucumber", 18)
            elif user_input_sides == 5:
                add_to_order("Salad", 13)
            elif user_input_sides == 6:
                continue
            else:
                print("INVALID Choice. ENTER AGAIN\n")
            show_order_list()
        elif user_input_sd == 2:
            print("================")
            print("==== DRINKS ====")
            print("================\n")
            # print("[1] Milk Tea\n[2] Lemon Tea\n[3] Coffee\n[4] Back\n")
            for i, (drink_name, drink_price) in enumerate(DRINKS, 1):
                if drink_name == "Iced Water / Ice":
                    print(f"[{i}] {drink_name} (${drink_price})")
                else:
                    # print(
                    #     f"[{i}] {drink_name.strip()} (HOT ${drink_price} / ICE ${drink_price + ICE_SURCHARGE})")
                    clean_name = drink_name.strip()
                    prices = f"(HOT ${drink_price} / ICE ${drink_price + ICE_SURCHARGE})"

                    print(f"[{i:<2}] {clean_name:<35} {prices}")

            print(f"[{len(DRINKS) + 1}] Back\n")
            user_input_drinks = int(input("Enter your choice: "))
            # if user_input_drinks == 1:
            #     print("[1] ICE($18)\n[2] HOT($16)\n")
            #     user_input_drinks_mt = int(input("Enter your choice: "))
            #     if user_input_drinks_mt == 1:
            #         add_to_order("COLD Milk Tea", 18)
            #     elif user_input_drinks_mt == 2:
            #         add_to_order("HOT Milk Tea", 16)
            # elif user_input_drinks == 2:
            #     print("[1] ICE($18)\n[2] HOT($16)\n")
            #     user_input_drinks_lt = int(input("Enter your choice: "))
            #     if user_input_drinks_lt == 1:
            #         add_to_order("COLD Lemon Tea", 18)
            #     elif user_input_drinks_lt == 2:
            #         add_to_order("HOT Lemon Tea", 16)
            # elif user_input_drinks == 3:
            #     print("[1] ICE($18)\n[2] HOT($16)\n")
            #     user_input_drinks_co = int(input("Enter your choice: "))
            #     if user_input_drinks_co == 1:
            #         add_to_order("COLD Coffee", 18)
            #     elif user_input_drinks_co == 2:
            #         add_to_order("HOT Coffee", 16)
            # elif user_input_drinks == 4:
            if 1 <= user_input_drinks <= len(DRINKS):
                drink_name, drink_price = DRINKS[user_input_drinks - 1]
                if order_drink(drink_name, drink_price):
                    show_order_list()
            elif user_input_drinks == len(DRINKS) + 1:
                continue
            else:
                print("INVALID Choice. ENTER AGAIN\n")
        elif user_input_sd == 3:
            continue  # back to the first if loop
        else:
            print("INVALID Choice. ENTER AGAIN\n")
    elif user_input == 4:
        if not order_list:
            print("No Ordered Made Yet...")
            continue
        else:
            print("==============================")
            print("============ BILL ============")
            print("==============================\n")
            show_order_list()
            print("Thank You! See You Again!")
            break
    elif user_input == 5:
        if price > 0:
            print(f"Have to pay your bill! The total amount is {price}!")
            continue
        else:
            print("SEE YOU AGAIN!")
        break
    else:
        print("INVALID Choice. ENTER AGAIN\n")
