def count_letters(given_string):
    given_string.strip
    given_string.lower
    character_array = []
    count_array = []
    for i in range(97, 97+26):
        character = chr(i)
        count = given_string.count(character)
        if count > 0:
            #print(f"Found {character}\n")
            character_array.append(character)
            count_array.append(count)

    for i in range(len(character_array)):
        print(f"{character_array[i]} appears {count_array[i]} times")


#main
if __name__ == "__main__":
    y = True;
    while y == True:
        x = input("Enter the 1 for user input else enter 0: ")
        try:
            x = int(x)
            if x == 1 or x == 0:
                y = False
            else:
                print("Invalid number")
        except:
            print("Wrong input, please try again.")

    z = ""
    print("got here")
    if x == 1:
        z = input("Enter the 1 for user input else enter 0: ")
    else:
        z = "powwow pzazz kakkak"
    print("make call with " + z)
    count_letters(z)
    