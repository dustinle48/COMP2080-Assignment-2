import java.util.Scanner;

public class Main {
    static int getInteger(Scanner sc, String message) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print(message);
        }
        return sc.nextInt();
    }

    static double getDouble(Scanner sc, String message) {
        System.out.print(message);
        while (!sc.hasNextDouble()) {
            sc.nextLine();
            System.out.print(message);
        }
        return sc.nextDouble();
    }

    static void addWeapons(ArrayManager h, Scanner sc) {
        System.out.println("***** WELCOME TO THE WEAPON CREATING MENU *****");
        
        String weaponName;
        int weaponRange;
        int weaponDamage;
        double weaponWeight;
        double weaponCost;
        int quantity;

        System.out.print("Please enter the NAME of the Weapon ('end' to quit):");
        weaponName = sc.next();
        while (weaponName.compareTo("end") != 0) {
            int result = h.find(weaponName);
            if (result != -1) {
                System.out.println("This weapon is already in the shop.");
                quantity = getInteger(sc,"Please enter the quantity to add in stock (0 to cancel):");
                h.add(result, quantity);
                System.out.println("Quantity added!");
                System.out.println("---------------");
                System.out.println("Please enter the NAME of another Weapon ('end' to quit):");
                weaponName = sc.next();
            } else {
                weaponRange = getInteger(sc,"Please enter the Range of the Weapon (0-10):"); 
                weaponDamage = getInteger(sc,"Please enter the Damage of the Weapon:"); 
                weaponWeight = getDouble(sc,"Please enter the Weight of the Weapon (in pounds):");
                weaponCost = getDouble(sc,"Please enter the Cost of the Weapon:");
                Weapon w = new Weapon(weaponName, weaponRange, weaponDamage, weaponWeight, weaponCost);
                quantity = getInteger(sc,"Please enter the quantity in stock:"); 
                h.create(w,quantity);
                System.out.println("New weapon created!");
                System.out.println("----------------");
                System.out.println("Please enter the NAME of another Weapon ('end' to quit):");
                weaponName = sc.next();
            }
        }
    }

    static void deleteWeapons(ArrayManager h, Scanner sc) {
        System.out.println("***** WELCOME TO THE WEAPON DELETING MENU *****");

        String weaponName;

        System.out.print("Please enter the NAME of the Weapon you want to delete ('end' to quit):");
        weaponName = sc.next();
        while (weaponName.compareTo("end") != 0) {
            int result = h.find(weaponName);
            if (result != -1) {
                h.delete(result);
                System.out.println("Weapon deleted!");
                System.out.println("---------------");
                System.out.println("Please enter the NAME of another Weapon you want to delete ('end' to quit):");
                weaponName = sc.next();
            } else {
                System.out.println("This weapon is not available in the shop!");
                System.out.println("-----------------------------------------");
                System.out.println("Please enter the NAME of the Weapon you want to delete ('end' to quit):");
                weaponName = sc.next();
            }
        }
    }

    static void showRoomMenu(ArrayManager ht,Player p) {
        System.out.println("***** WELCOME TO THE SHOWROOM *****");
        ht.printTable();
        System.out.println("You have "+p.money+" Gold.");
        System.out.println("Please select a weapon to buy('end' to quit):");
    }

    static void showRoom(ArrayManager ht, Player p, Scanner sc) {
        String choice;
        showRoomMenu(ht,p);
        choice = sc.next();
        while (choice.compareTo("end") != 0 && !p.inventoryFull()) {
            ShopItem si = ht.get(choice);
            if (si != null) {
                if (p.money > si.item.cost) {
                    if (!p.overweight(si.item.weight)) {
                        p.buy(si.item);
                        p.withdraw(si.item.cost);
                        si.numberInStock--;
                    } else {
                        System.out.println("You cannot carry more!");
                        System.out.println("----------------------");
                    }
                } else {
                    System.out.println("You don't have enough Gold!");
                    System.out.println("----------------------");
                } 
            } else {
                System.out.println(choice+" isn't available in the shop!");
                System.out.println("---------------------------------");
            }
            showRoomMenu(ht,p);
            choice = sc.next();
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter Player name:");
        String pname = sc.next();

        Player pl = new Player(pname,45);
        ArrayManager ht = new ArrayManager(80);

        while (0 < 1) {
            System.out.println("***** GAME MENU *****");
            System.out.println("1) Add items to the shop");
            System.out.println("2) Delete items from the shop");
            System.out.println("3) Buy items from the shop");
            System.out.println("4) View backpack");
            System.out.println("5) View player");
            System.out.println("6) Exit");
            String choice;
            choice = sc.next();
            
            if (choice.equals("1")) {
                addWeapons(ht,sc);
            }
            else if (choice.equals("2")) {
                deleteWeapons(ht, sc);
            }
            else if (choice.equals("3")) {
                showRoom(ht, pl, sc);
            }
            else if (choice.equals("4")) {
                System.out.println("------------------");
                System.out.println("View your backpack");
                System.out.println("------------------");
                pl.printBackpack();
            }
            else if (choice.equals("5")) {
                System.out.println("-------------------");
                System.out.println("View your character");
                System.out.println("-------------------");
                pl.printCharacter();
                System.out.println("-------------------");
            }
            else if (choice.equals("6")) {
                System.out.println("--------------------------------");
                System.out.println("Thank you for playing this game!");
                System.out.println("--------------------------------");
                break;
            }
            else {
                System.out.println("----------------------------------");
                System.out.println("Invalid command, please try again!");
                System.out.println("----------------------------------");
            }
        }
    }
}