//imports
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;
import java.lang.Enum;

enum Rarity{
    common, uncommon, rare, legendary
}

enum DmgType{
    physical, fire, poison, cold, psychic, acid, dark, light
}

enum EquipSlot{
    armor, weapon, chest, head, boots, cloak, ring, main_hand, off_hand, necklace
}

enum Potion_types{
    health, mana
}



public class RPG_Combat_Sim {
    public static final int MAX_POTION_STACK_SIZE = 99;
    public static final int MAX_EQUIPMENT_STACK_SIZE = 1;

    public record damage_record(int dmg_num, DmgType type_of_dmg){}

    public static void main(String[] args) throws IOException{
        
    }

    public boolean see_if_crit(){
        int min = 1;
        int max = 20;
        Random r = new Random();
        boolean retboolean = false;
        if(max == (r.nextInt(max - min + 1) + min)){
            retboolean = true;
        }
        return retboolean;
    }

    class Item{
        // vars
        private String name; // Name of item
        private Rarity item_Rarity; // The rarity of the item
        private int max_stack; //Max amount of an item

        // Constructor
        public Item(String givenName, Rarity givenRarity, int max_stack){
            name = givenName;
            item_Rarity = givenRarity;
            this.max_stack = max_stack;
        }

        //Getters
        public String getItemName(){
            return name;
        }
        public Rarity getRarity(){
            return item_Rarity;
        }
        public int getMaxStackSize(){
            return max_stack;
        }

        //Setter
        public void setItemName(String newName){
            name = newName;
        }
        public void setItemRarity(Rarity newRarity){
            item_Rarity = newRarity;
        }
        public void setMaxStackSize(int newMax){
            max_stack = newMax;
        }

        //Functions
        public String getItemDescription(){
            String firstString = "Item: ";
            String secondString = " Rarity: ";
            firstString = firstString.concat(name);
            secondString = secondString.concat(item_Rarity.toString());
            return firstString.concat(secondString);
        }

    }

    class Equippible extends Item{
        //vars
        private EquipSlot slot_it_takes;

        //Constructor
        public Equippible(String givenName, Rarity givenRarity, EquipSlot slot_it_will_take){
            super(givenName, givenRarity, MAX_EQUIPMENT_STACK_SIZE);
            is_equipped = false;
            slot_it_takes = slot_it_will_take;
        }

        //getters and setters
        public EquipSlot getSlot(){
            return slot_it_takes;
        }
        public void setNewSlot(EquipSlot newSlot){
            slot_it_takes = newSlot;
        }

    }

    class Weapon extends Equippible{
        //vars
        private int attack_stat; //The weapons attack stat
        private DmgType weapon_dmg_type; //Weapons damage type

        //constructor
        public Weapon(String givenName, Rarity givenRarity, EquipSlot slot_it_will_take, int attack_dmg, DmgType type_dmg){
            super(givenName, givenRarity, slot_it_will_take);
            attack_stat = attack_dmg;
            weapon_dmg_type = type_dmg;
        }

        //getters
        public int get_weapon_attack(){
            return attack_stat;
        }
        public DmgType get_weapon_damage_type(){
            return weapon_dmg_type;
        }

        //setters
        public void set_weapon_attack(int newAtk){
            attack_stat = newAtk;
        }
        public void set_weapon_damage_type(DmgType newType){
            weapon_dmg_type = newType;
        }

        //functions
        public String getItemDescription(){
            String baseDescription = super.getItemDescription();
            return baseDescription + " Attack: " + attack_stat + " Type: " + weapon_dmg_type;
        }

        public damage_record weapon_attack_action(int user_Attack){
            boolean did_crit = see_if_crit();
            int total_power = user_Attack + attack_stat;
            if(did_crit){
                total_power = 2*total_power;
            }
            return new damage_record(total_power, weapon_dmg_type);
        }
    }

    class Bow extends Weapon{
        //vars
        private int amount_ammo;

        // Constructor
        public Bow(String givenName, Rarity givenRarity, EquipSlot slot_it_will_take, int attack_dmg, DmgType type_dmg, int ammo){
            super(givenName, givenRarity, slot_it_will_take, attack_dmg, type_dmg);
            amount_ammo = ammo;
        }

        //Getter and Setter
        public int getAmountAmmo(){
            return amount_ammo;
        }
        public void setAmountAmmo(int newAmmo){
            amount_ammo = newAmmo;
        }

        //function
        public damage_record weapon_attack_action(int user_Attack){
            damage_record retRecord = new damage_record(0, DmgType.physical);
            if(amount_ammo > 0){
                retRecord = super.weapon_attack_action(user_Attack);
                amount_ammo = amount_ammo - 1;
                System.out.println(amount_ammo + " ammo left");
            }
            else{
                System.out.println("No ammo left nothing fired");
            }

            return retRecord;
        }

        public void displayAmmo(){
            System.out.println(amount_ammo + " ammo left");
        }

    }

    class Staff extends Weapon{
        //vars
        private int cost_per_shot; //Amount of mana each attack costs

        // Constructor
        public Staff(String givenName, Rarity givenRarity, EquipSlot slot_it_will_take, int attack_dmg, DmgType type_dmg, int mana_cost){
            super(givenName, givenRarity, slot_it_will_take, attack_dmg, type_dmg);
            cost_per_shot = mana_cost;
        }

        //Getter and Setter
        public int getCostPerShot(){
            return cost_per_shot;
        }
        public void setCostPerShot(int newCostPer){
            cost_per_shot = newCostPer;
        }

        //function
        public damage_record weapon_attack_action(int user_attack){
            retRecord = super.weapon_attack_action(2*user_attack);
            return retRecord;
        }

    }

    class Armor extends Equippible{
        //var
        private int item_defence;
        private boolean equipped;

        //constructor
        public Armor(String givenName, Rarity givenRarity, EquipSlot slot_it_will_take, int def_value){
            super(givenName, givenRarity, slot_it_will_take);
            item_defence = def_value;
            equipped = false;
        }

        //Getter and Setter
        public int getArmorDef(){
            return item_defence;
        }
        public void setArmorDef(int newDef){
            item_defence = newDef;
        }

        //Function
    }

    class Potion extends Item{
        //vars
        private Potion_types restore_type;
        private int amount_restored;

        //constructor
        public Potion(String givenName, Rarity givenRarity, Potion_types type_given, int restorable){
            super(givenName, givenRarity, MAX_POTION_STACK_SIZE);
            restore_type = type_given;
            amount_restored = restorable;
        }

        //setters and getters
        public void setPotionRestoreType(Potion_types newPotion){
            restore_type = newPotion;
        }
        public void setRestoreAmount(int newRestore){
            amount_restored = newRestore;
        }
        public Potion_types getPotionRestoreType(){
            return restore_type;
        }
        public int getRestoreAmount(){
            return amount_restored;
        }

        //functions
        public void usePotion(Entity potion_target){ 
            if(restore_type == Potion_types.health){
                potion_target.take_healing(amount_restored);
            }
            else{
                potion_target.resotre_mana(amount_restored);
            }
        }
    }

    //Classes that will be used for composition and implements
    public class ItemStack {
        private Item item;
        private int quantity;
        private final int maxStackSize;

        public ItemStack(Item item, int quantity) {
            this.item = item;
            this.quantity = quantity;
            this.maxStackSize = item.getDefaultMaxStack();
        }

        // Functions
        //This returns leftovers if over stack size
        public int addQuantity(int amount) {
            int newTotal = this.quantity + amount;
            if (newTotal > maxStackSize) {
                this.quantity = maxStackSize;
                return newTotal - maxStackSize;
            }
            this.quantity = newTotal;
            return 0;
        }

        // Getters and setters
        public int getQuantity(){
            return this.quantity;
        }
        public Item getItem(){
            return this.item;
        }

        public void setItem(Item newItem){
            this.item = newItem;
        }
        public void setQuantity(int newQuantity){
            this.quantity = newQuantity;
        }
        
    }   

    class Inventory{
        //Vars
        private List<ItemStack> items; //List of items

        //constructor
        public Inventory() {
            this.items = new ArrayList<>();
        }

        //Getter and Setter
        public List<ItemStack> getItemStack(){
            return items;
        }
        public void setItemStack(List<ItemStack> newList){
            this.items = newList;
        }

        //function add/remove items
        public void addItem(Item newItem, int amount){
            //see if we have it in the inventory already and not max stack
            for (ItemStack stack : items) {
                //find item and see if we can add to it
                if(stack.getItem().equals(newItem) && stack.getQuantity() < stack.getMaxStackSize()){
                    amount = stack.addQuantity(amount);
                    // see if we added all the items
                    if (amount == 0){
                        return;
                    }
                }
            }

            //we still have items left over make a new stack (minecraft style)
            while (amount > 0) {
                int amountForNewStack = Math.min(amount, newItem.getDefaultMaxStack());
                items.add(new ItemStack(newItem, amountForNewStack));
                amount -= amountForNewStack;
            }
        }

        public void removeItem(Item usedItem, int amount){
            for (ItemStack stack : items) {
                //find item and see if we can add to it
                int stackAmount = stack.getQuantity();
                if(stack.getItem().equals(usedItem) && stackAmount > 0){
                    if(stackAmount >= amount){
                        stack.setQuantity(stackAmount - amount);
                        amount = 0;
                    }
                    else{
                        amount = amount - stackAmount;
                        stack.setQuantity(stackAmount - amount);
                    }
                }
            }
            if(amount > 0){
                System.err.println("Not enough to remove "); //We should never hit this
            }
        }

        //function to display entire inventory
        public void displayInentory(){
            System.out.println("Items in Inventory");
            for (ItemStack stack : items){
                Item tempItem = stack.getItem();
                System.out.println(tempItem.getItemName() + stack.getQuantity());
            }
        }
    }

    class Equipment{
        //vars armor, weapon, chest, head, boots, cloak, ring, main_hand, off_hand, necklace
        private boolean armor_is_equipped;
        private Armor armor_on;
        private boolean weapon_is_equipped;
        private Weapon weapon_on;

        //constructor
        Equipment(){
            armor_is_equipped = false;
            weapon_is_equipped = false;
        }

        //getters
        public boolean getIsArmorEquipped(){
            return armor_is_equipped;
        }
        public boolean getIsWeaponEquipped(){
            return weapon_is_equipped;
        }
        public Weapon getWeaponEquipped(){
            return weapon_on;
        }
        public Armor getArmorEquipped(){
            return armor_on;
        }

        //setters
        public void setIsArmorEquipped(boolean newStatus){
            this.armor_is_equipped = newStatus;
        }
        public void setIsWeaponEquipped(boolean newStatus){
            this.weapon_is_equipped = newStatus;
        }
        public void setWeaponEquipped(Weapon newWeapon){
            weapon_on = newWeapon;
        }
        public void getArmorEquipped(Armor newArmor){
            armor_on = newArmor;
        }

        //function to equip and unequip items
        
    }



    //Entity, Enemy, and Player classes
    abstract class Entity{
        //variables
        private String name; // Name of entity
        private int health_points; // Current health of entity
        private int mana_points; // Current mana points of entity
        private int defence_value; // Defence Value of entity
        private int attack_value; // Attack power of entity with main attack
        private int speed; // Speed of entity to determine turn order
        private boolean is_Dead; // Is it alive or dead
        private int max_health; //Max HP
        private int max_mana; // Max mana
        private DmgType type_of_damage; //type of damage entity does (no weapon)

        //constructor
        public Entity(String given_Name, int hp, int mp, int def, int atk, int spd){
            name = given_Name;
            health_points = hp;
            mana_points = mp;
            defence_value = def;
            attack_value = atk;
            speed = spd;
            is_Dead = false;
            max_health = hp;
            max_mana = mp;
            type_of_damage = DmgType.physical;
        }

        //Setters
        public void setName(String newName) {
            name = newName;
        }

        public void setHP(int hp) {
            health_points = hp;
        }

        public void setMP(int mp) {
            mana_points = mp;
        }

        public void setDef(int newDef) {
            defence_value = newDef;
        }

        public void setAtk(int newAttack) {
            attack_value = newAttack;
        }

        public void setSpd(int newSpd) {
            speed = newSpd;
        }

        public void setIsDead(boolean dead) {
            is_Dead = dead;
        }

        public void setMaxMana(int newMaxMana) {
            max_mana = newMaxMana;
        }

        public void setMaxHp(int newMaxHp) {
            max_health = newMaxHp;
        }

        public void setNewDamageType(DmgType newTypeDmg){
            type_of_damage = newTypeDmg;
        }

        //getters
        public String getName() {
            return name;
        }

        public int getHP() {
            return health_points;
        }

        public int getMP() {
            return mana_points;
        }

        public int getDef() {
            return defence_value;
        }

        public int getAtk() {
            return attack_value;
        }

        public int getSpd() {
            return speed;
        }

        public boolean getIsDead() {
            return is_Dead;
        }

        public int getMaxMana() {
            return max_mana;
        }

        public int getMaxHp() {
            return max_health;
        }

        public DmgType getNewDamageType(){
            return type_of_damage;
        }

        //functions
        public damage_record attack_action(){
            boolean did_crit = see_if_crit();
            int dmg_val = attack_value;
            if(did_crit){
                dmg_val = (2*dmg_val);
            }
            return new damage_record(dmg_val, type_of_damage);
        }

        public void take_damage(damage_record recordedDMG){
            int damage = recordedDMG.dmg_num();
            health_points = health_points - damage;
            System.out.println(name + " is at " + health_points + " health");
        }

        public void take_healing(int healed){
            if(health_points < max_health){ //see if healing needed
                health_points = health_points + healed;
                if(health_points > max_health){ //ensure no overheal
                    health_points = max_health;
                }
            }
            System.out.println(name + " is at " + health_points + " health");
        }

        public void take_mana(int mana_used){
            mana_points = mana_points - mana_used;
            System.out.println(name + " is at " + mana_points + " mana");
        }

        public void resotre_mana(int mana_gained){
            if(mana_points < max_mana){ //see if mana needed
                mana_points = mana_points + mana_gained;
                if(mana_points > max_mana){ //ensure no overheal
                    mana_points = max_mana;
                }
            }
            System.out.println(name + " is at " + mana_points + " mana");
        }

    }

    class Player extends Entity{
        //Vars
        //Needs to have an inventory
        //needs to have equipment that handels what is equipped

        //constructor
        Player(String given_Name, int hp, int mp, int def, int atk, int spd){
            super(given_Name, hp, mp, def, atk, spd);
        }
    }

    


} //end class
