def middleChunck(input_array):
    if (len(input_array) % 2) == 0:
        print("Array/list size is even")
        return [0]

    return input_array[((len(input_array)//2) - 1):((len(input_array)//2) + 2)]


def getUsernameAndFlip(log_string):
    sections = log_string.split("-")
    username = sections[1]
    return username[::-1]

def getItemsOnPage(item_list, items_per_page, page_number):
    if not item_list:
        print("No item list given")
        return []

    ending_item = items_per_page*page_number

    if len(item_list) <= ending_item - items_per_page:
        print("Out of page bounds")
        return []

    return item_list[((items_per_page*(page_number - 1))):ending_item]


#main
if __name__ == "__main__":
    odd_array = [10,20,30,40,50,60,70,80,90]
    even_array = [10,20,30,40,50,60]
    print(middleChunck(odd_array))
    print(middleChunck(even_array))

    string1 = "404-Alice-12:00PM"
    string2 = "402-RomanJ-08:24AM"
    print(getUsernameAndFlip(string1))
    print(getUsernameAndFlip(string2))

    items = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J']
    items2 = ['1', '2', '3', '4', 'A', 'B', 'C', 'D', '11', '22', '33', '44', 'a', 'b', 'c', 'd']
    print(getItemsOnPage(items, 4, 1))
    print(getItemsOnPage(items, 4, 2))
    print(getItemsOnPage(items, 4, 3))
    print(getItemsOnPage(items, 4, 4))
    print(getItemsOnPage(items2, 4, 1))
    print(getItemsOnPage(items2, 4, 2))
    print(getItemsOnPage(items2, 4, 3))
    print(getItemsOnPage(items2, 4, 4))
    print(getItemsOnPage(items2, 4, 5))