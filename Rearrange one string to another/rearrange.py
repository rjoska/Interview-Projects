def transform_one_string_into_another(first, second):
    first = first.upper()
    second = second.upper()
    if first == second:
        print("Strings already match")
        return

    if(len(first) != len(second)):
        print("Invalid strings one string is larger than the other")
        return

    if sorted(first) != sorted(second):
        print(f"Error: '{first}' and '{second}' do not contain the same characters.")
        print("Transformation is impossible using only rearrangements.\n")
        return

    #First Print to display
    print(f"Transforming {first} into {second}")
    print("-----------------------------------------")

    num_tranformations = 0

    #else
    i = 0 #index we are currently looking at
    for x in second: #x is the char we are currently looking for in spot i
        if first == second:
            break

        if(x != first[i]): #see if the chars are the same
            x_spot_in_first = first.find(x, i) #find x in first after index i
            old_char_in_spot_i = first[i] #store the char from spot i
            first_list = list(first)
            first_list[i] = x
            first_list[x_spot_in_first] = old_char_in_spot_i
            first = "".join(first_list) #first after swap
            print(f"{i+1} = {first}")
            num_tranformations += 1
            
        i += 1

    print(f"Total transformations = {num_tranformations}")
    return
    


if __name__ == "__main__":
    string1 = "GUM"
    string2 = "MUG"

    string3 = "Conversationalists"
    string4 = "Conservationalists"

    string5 = "basiparachromatin"
    string6 = "marsipobranchiata"
    string7 = "marsipobranchiatt"

    transform_one_string_into_another(string1, string2)
    print()
    transform_one_string_into_another(string3, string4)
    print()
    transform_one_string_into_another(string3, string3)
    print()
    transform_one_string_into_another(string3, string1)
    print()
    transform_one_string_into_another(string5, string6)
    print()
    transform_one_string_into_another(string5, string7)