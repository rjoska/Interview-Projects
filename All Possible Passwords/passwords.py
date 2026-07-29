#So my thinking goes, make all possible combinations of lowercase then capitals only then do the combination

def findAllPassOfSize(size):
    if size > 26 or size < 1:
        print("Passwords are out of range")
        return
    total_printed = 0

    def recursiveFindNextInput(current_pass, starting_index):
        nonlocal total_printed

        #default case aka we made a large enough password
        if len(current_pass) == size:
            print(current_pass)
            total_printed += 1
            return

        #recursive case aka we need to go longer
        space_left_to_fill = size - len(current_pass)
        for i in range(starting_index, 26):
            if 26-i < space_left_to_fill:
                break

            lower_char = chr(97 + i)
            upper_char = chr(65 + i)
            recursiveFindNextInput(current_pass + lower_char, i+1)
            recursiveFindNextInput(current_pass + upper_char, i+1)

    print(f"All passwords of length {size}")
    recursiveFindNextInput("", 0)
    print(f"\nTotal valid passwords printed: {total_printed}")


#main
if __name__ == "__main__":
    y = True;
    while y == True:
        x = input("Enter the password size: ")
        try:
            x = int(x)
            y = False
        except:
            print("Wrong input, please try again.")

    findAllPassOfSize(x)
