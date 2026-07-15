#include <iostream>
#include <string>
using namespace std;

// Make a bunch of animals that polymorph off of a base animal

class Animal {
    public:
        string name;
        string gender;
        int age;
        string location;

        // Constructors
        Animal(){
            name = "";
            gender = "";
            age = 0;
            location = "";
        }

        Animal(string gName, string mOrF, int old, string whereAt){
            name = gName;
            gender = mOrF;
            age = old;
            location = whereAt;
        }

        //Getters
        string getName(){
            return name;
        }

        string getGender(){
            return gender;
        }

        string getLocation(){
            return location;
        }

        int getAge(){
            return age;
        }

        //Setters
        void setName(string newName){
            name = newName;
        }

        void setGender(string newGender){
            gender = newGender;
        }

        void setLocation(string newLocation){
            location = newLocation;
        }
        
        void setAge(int newAge){
            age = newAge;
        }

        // Functions
        virtual void makeNoise() {
            cout << "A noise" << "\n";
        }

        virtual void birthday() {
            ++age;
        }

        virtual void displayMe(){
            cout << name << " is in " << location << "\n";
        }
};

class Pig : public Animal{
    public:
        bool coveredInMud = false;

        //Constructor
        Pig(string gName, string mOrF, int old, string whereAt) : Animal(gName, mOrF, old, whereAt){
        }

        //Getter
        bool getDirty(){
            return coveredInMud;
        }

        //Functions
        void makeNoise() {
            cout << "Oink Oink" << "\n";
        }

        void displayMe(){
            cout << name << " the pig is in " << location << "\n";
        }

        void rolledInMud(){
            coveredInMud = true;
        }

        void cleanedPig(){
            coveredInMud = false;
        }
};

class Cow : public Animal{
    public:
        bool producingMilk;

        //constructor
        Cow(string gName, int old, string whereAt, bool milkYN) : Animal(gName, "Female", old, whereAt), producingMilk(milkYN) {

        }

        //Remove Setter for Gender as only female cows exist
        void setGender(){
        }

        void makeNoise() {
            cout << "Moooo" << "\n";
        }

        void displayMe(){
            cout << name << " the cow is in " << location << "\n";
        }

    bool canBeMilked(){
        return producingMilk;
    }

};

class Bull : public Animal{
    public:
        //constructor
        Bull(string gName, int old, string whereAt) : Animal(gName, "Male", old, whereAt){
        }

        //Remove Setter for Gender as only amel bulls exist
        void setGender(){
        }

        void makeNoise() {
            cout << "Mroooo" << "\n";
        }

        void displayMe(){
            cout << name << " the bull is in " << location << "\n";
        }

};

int main(){

    Cow betsy = Cow("Betsy", 2, "Barn", true);
    Cow bea = Cow("Bea", 4, "Field", false);
    Bull ben = Bull("Ben", 5, "Vet");
    Pig poppy = Pig("Poppy", "Male", 3, "Pig Pen");
    Pig ploppy = Pig("Ploppy", "Female", 3, "Pig Pen");
    Animal theAnimal = Animal("It", "Maybe", 0, "Gone");

    betsy.displayMe();
    betsy.makeNoise();
    cout << betsy.getAge() << "\n";
    betsy.birthday();
    cout << betsy.getAge() << "\n";
    cout << betsy.canBeMilked() << "\n";
    cout << betsy.getGender() << "\n";
    cout << betsy.getLocation() << "\n";
    cout << betsy.getName() << "\n";
    cout << "\n";

    bea.displayMe();
    bea.makeNoise();
    cout << bea.canBeMilked() << "\n";
    cout << "\n";

    ben.displayMe();
    ben.makeNoise();
    cout << ben.getAge() << "\n";
    cout << ben.getGender() << "\n";
    cout << ben.getLocation() << "\n";
    cout << ben.getName() << "\n";
    cout << "\n";

    poppy.displayMe();
    poppy.makeNoise();
    cout << poppy.getAge() << "\n";
    poppy.birthday();
    cout << poppy.getAge() << "\n";
    cout << poppy.getDirty() << "\n";
    poppy.rolledInMud();
    cout << poppy.getDirty() << "\n";
    poppy.cleanedPig();
    cout << poppy.getDirty() << "\n";
    cout << poppy.getGender() << "\n";
    cout << poppy.getLocation() << "\n";
    cout << poppy.getName() << "\n";
    cout << "\n";

    ploppy.displayMe();
    ploppy.makeNoise();
    cout << ploppy.getAge() << "\n";
    cout << ploppy.getGender() << "\n";
    cout << ploppy.getLocation() << "\n";
    cout << ploppy.getName() << "\n";
    cout << "\n";

    theAnimal.displayMe();
    theAnimal.makeNoise();
    cout << theAnimal.getAge() << "\n";
    cout << theAnimal.getGender() << "\n";
    cout << theAnimal.getLocation() << "\n";
    cout << theAnimal.getName() << "\n";
    cout << "\n";

    Animal* animals[4];

    animals[0] = &betsy;
    animals[1] = &ben;
    animals[2] = &poppy;
    animals[3] = &theAnimal;

    for (int i = 0; i < 4; i++)
    {
        animals[i]->displayMe();
        animals[i]->makeNoise();
    }

    return 0;
};
