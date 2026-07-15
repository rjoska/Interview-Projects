#include <iostream>
#include <fstream>
#include <string>
using namespace std;

double const sm_pot_weight = 0.1;
double const md_pot_weight = 0.2;
double const lg_pot_weight = 0.4;
double const quest_item_weight = 0.0;
int const helm_armor = 0;
int const body_armor = 1;
int const shield_armor = 2;
int const hp_pot = 1;
int const mana_pot = 2;


class Item {
    protected:
        string name;
        double weight;
        string rarity;
        bool is_Enchanced;
        bool equiped;
    public:
        //Constructor
        Item(){
            name = "";
            rarity = "";
            weight = 0.0;
            is_Enchanced = false;
            equiped = false;
        }

        Item(string gName, double gWeight, string gRarity, bool YorN){
            name = gName;
            weight = gWeight;
            rarity = gRarity;
            is_Enchanced = YorN;
            equiped = false;
        }

        //Getters
        string getItemName(){
            return name;
        }
        string getItemRarity(){
            return rarity;
        }
        double getItemWeight(){
            return weight;
        }
        bool getEnhanced(){
            return is_Enchanced;
        }
        bool getEquiped(){
            return equiped;
        }

        //Setters
        void setItemName(string newName){
            name = newName;
        }
        void setItemRarity(string newRarity){
            rarity = newRarity;
        }
        void setItemWeight(double newWeight){
            weight = newWeight;
        }
        void setEnhanced(bool newEnhanced){
            is_Enchanced = newEnhanced;
        }
        void setEquiped(bool newEquiped){
            equiped = newEquiped;
        }

        //Functions
        virtual void displayItem(){
            cout << name << " (" << rarity << ")\n";
        }
        virtual void enhanceItem(){
            if(is_Enchanced == false){
                is_Enchanced = true;
                cout << name << " has been enchanced\n";
            } else{
                cout << "Item already enhanced \n";
            }
        }
        virtual void equipItem(){
            cout << "Items of type \"Item\" can not be equiped\n";
        }

        //destructor
        virtual ~Item() = default;
};

class Armor : public Item{
    int armor_position; //0 = helm, 1 = body, 2 = shield
    int defence_value;
    public:
        //Constructor
        Armor(string gName, double gWeight, string gRarity, bool YorN, int position, int defVal) : Item(gName, gWeight, gRarity, YorN), armor_position(position), defence_value(defVal){
        }

        //Getters
        int getArmorPos(){
            return armor_position;
        }
        int getDefenceValue(){
            return defence_value;
        }

        //Setters
        void setArmorPos(int newPos){
            armor_position = newPos;
        }
        void setDefenceValue(int newDef){
            defence_value = newDef;
        }

        //Functions
        void displayItem() override{
            string ending = ")\n";
            if(equiped){
                ending = ") (Equipped)\n";
            }
            cout << name << " (" << rarity << ending;
        }
        void enhanceItem() override{
            if(is_Enchanced == false){
                is_Enchanced = true;
                defence_value = defence_value + 5;
                cout << name << " has been enchanced\n";
            } else{
                cout << "Item already enhanced \n";
            }
        }
        void equipItem() override{
            if(equiped){
                //done
                cout << "Already equipped \n";
            } else{
                equiped = true;
                cout << name << " is now equipped\n";
            }
        }

        //destructor
        ~Armor() = default;
};

class Weapon : public Item{
    int damage_value;
    string damage_type;
    public:
        //Constructor
        Weapon(string gName, double gWeight, string gRarity, bool YorN, int damVal, string damType) : Item(gName, gWeight, gRarity, YorN), damage_value(damVal), damage_type(damType){
        }

        //Getters
        int getDamageVal(){
            return damage_value;
        }
        string getDamageType(){
            return damage_type;
        }
        //Setters
        void setDamageVal(int newVal){
            damage_value = newVal;
        }
        void setDamageType(string newType){
            damage_type = newType;
        }

        //Functions
        void displayItem() override{
            string ending = ")\n";
            if(equiped){
                ending = ") (Equipped)\n";
            }
            cout << name << " (" << rarity << ending;
        }
        void enhanceItem() override{
            if(is_Enchanced == false){
                is_Enchanced = true;
                damage_value = damage_value + 5;
                cout << name << " has been enchanced\n";
            } else{
                cout << "Item already enhanced \n";
            }
        }
        void equipItem() override{
            if(equiped){
                //done
                cout << "Already equipped \n";
            } else{
                equiped = true;
                cout << name << " is now equipped\n";
            }
        }

        //destructor
        ~Weapon() = default;
};

class QuestItem : public Item{
    public:
        //Constructor
        QuestItem(string gName, string gRarity) : Item(gName, quest_item_weight, gRarity, false){
        }

        //Functions
        void equipItem() override{
            cout << "Items of type \"Quest Item\" can not be equiped\n";
        }
        void enhanceItem() override{
            cout << "Can't enhance quest items \n";
        }

        //Destructor
        ~QuestItem() = default;
};

class Potion : public Item{
    int potion_type; // 1 = hp, 2 = mana
    int potion_size; // 0 = small, 1 = md, 2 = large
    int quantity;

    public:
        //Constructor
        Potion(string gName, double gWeight, string gRarity, bool YorN, int typeOfPot, int sizeOfPot, int amount) : Item(gName, gWeight, gRarity, YorN), potion_type(typeOfPot), potion_size(sizeOfPot), quantity(amount){
        }

        //Getters
        int getPotionSize(){
            return potion_size;
        }
        int getPotionType(){
            return potion_type;
        }
        int getPotionAmount(){
            return quantity;
        }
        //Setters
        void setPotionSize(int newSize){
            potion_size = newSize;
        }
        void setPotionType(int newType){
            potion_type = newType;
        }
        void setPotionAmount(int newAmount){
            quantity = newAmount;
        }

        //Functions
        void displayItem() override{
            cout << quantity << " " << name << " (" << rarity << ")\n";
        }
        void enhanceItem() override{
            cout << "Can't enhance potions";
        }
        void equipItem() override{
            cout << "Items of type \"Potion\" can not be equiped\n";
        }
        void usePotion(){
            if(quantity > 0){
                string restored = "";
                int amount = 0;
                if(potion_type == hp_pot){
                    restored = "Health";
                } else{
                    restored = "Mana";
                }

                if(potion_size == 0){
                    amount = 5;
                } else if(potion_size == 1){
                    amount = 10;
                } else{
                    amount = 20;
                }

                cout << "Potion used " << amount << " " << restored << " restored \n";
                --quantity;

            } else{
                cout << "No potions of thus type left \n";
            }
        }

        //destructor
        ~Potion() = default;
};

int main(){

    // Base Item
    Item junk("Broken Stick", 1.2, "Common", false);

    // Armor
    Armor helmet("Iron Helmet", 5.0, "Uncommon", false, helm_armor, 15);

    // Weapon
    Weapon sword("Steel Sword", 7.5, "Rare", false, 25, "Slash");

    // Quest Item
    QuestItem relic("Ancient Relic", "Legendary");

    // Potions
    Potion hpSmall("Small Health Potion",
                   sm_pot_weight,
                   "Common",
                   false,
                   hp_pot,
                   0,
                   3);

    Potion manaLarge("Large Mana Potion",
                     lg_pot_weight,
                     "Rare",
                     false,
                     mana_pot,
                     2,
                     2);

    cout << "===== Display Items =====\n";

    junk.displayItem();
    helmet.displayItem();
    sword.displayItem();
    relic.displayItem();
    hpSmall.displayItem();
    manaLarge.displayItem();

    cout << "\n===== Equip Tests =====\n";

    junk.equipItem();
    helmet.equipItem();
    helmet.equipItem();      // Already equipped
    sword.equipItem();
    relic.equipItem();
    hpSmall.equipItem();

    cout << "\n===== Display After Equipping =====\n";

    helmet.displayItem();
    sword.displayItem();

    cout << "\n===== Enhancement Tests =====\n";

    junk.enhanceItem();
    junk.enhanceItem();

    helmet.enhanceItem();
    helmet.enhanceItem();

    sword.enhanceItem();
    sword.enhanceItem();

    relic.enhanceItem();
    hpSmall.enhanceItem();

    cout << "\n===== Potion Tests =====\n";

    hpSmall.usePotion();
    manaLarge.usePotion();

    cout << "\n===== Getter Tests =====\n";

    cout << "Helmet Defense: "
         << helmet.getDefenceValue() << endl;

    cout << "Sword Damage: "
         << sword.getDamageVal() << endl;

    cout << "HP Potions Left: "
         << hpSmall.getPotionAmount() << endl;

    cout << "\n===== Polymorphism Test =====\n";

    Item* inventory[5];

    inventory[0] = &helmet;
    inventory[1] = &sword;
    inventory[2] = &relic;
    inventory[3] = &hpSmall;
    inventory[4] = &junk;

    for(int i = 0; i < 5; i++)
    {
        inventory[i]->displayItem();
    }

    return 0;
};
