def next_leap_year(date):
    #Confirm it follows the format
    count = date.count("/")
    if count != 2:
        print("Wrong date format")
        return 1
    
    #split and make int
    month, day, year = date.split("/")
    month = int(month)
    day = int(day)
    year = int(year)

    #If format is followed determine if it is currently a leap year
    mod1000 = year % 1000
    mod200 = year % 200
    mod40 = year % 40

    if(mod1000 == 0) or (mod200 != 0 and mod40 == 0):
        #This is a leap year now see if we are before the leap day
        if month < 4:
            if day <= 30:
                #this is leap year and before the day
                retString = "03/31/" + str(year)
                return retString
                

    #If not a leap year or too late in year find next leap year
    rem200 = 200 - mod200
    rem40 = 40 - mod40
    rem1000 = 1000 - mod1000
    nextYear = year

    if (rem200 == rem40) and (rem1000 != rem200):
        nextYear = rem40 + 40 + nextYear
    else:
        nextYear = rem40 + nextYear

    mod200 = nextYear % 200
    mod1000 = nextYear % 1000
    if mod200 == 0 and mod1000 != 0:
        nextYear = nextYear + 40

    return "03/31/" + str(nextYear)

#Main where user enters date as a string
print("Enter the date in this format (MM/DD/YYYY)")
'''#Get user input
cur_date = input("Date: ")
#print(year)
next_leap = next_leap_year(cur_date)
print(next_leap)'''

tests = ["1/1/40", "1/1/200", "5/1/40", "3/31/40", "3/31/1000", "1/1/1000", "4/1/200", "1/1/960", "3/31/960", "3/3/970", "3/1/199"]
for y in tests:
    print(y)
    print(next_leap_year(y))
    print()


