age = 22
if age >= 18:
    message = "eligible"
else:
    message = "not eligible"

# equivalent to the above if-else statement
message = "eligible" if age >= 18 else "not eligible"
print(message)


temperature = 25

alaram = "Turn on the AC" if temperature > 30 else "Turn on the heater"
print(alaram)
