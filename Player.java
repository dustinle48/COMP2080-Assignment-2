class Player {
    public String name;
    public Weapon[] backpack;
    public int numItems;
    public double money;

    public Player(String n, double m) {
        name = n;
        money = m;
        numItems = 0;
        backpack = new Weapon[30];
    }

    public void buy(Weapon w) {
        System.out.println(w.weaponName+" bought...");
        int hv = w.weaponName.hashCode() % 30;
        if (backpack[hv] == null) {
            backpack[hv] = w;
        } else {
            for (int j = 0; j < 30; j++) {
                int newhv = (hv + j * j) % 30;
                if (backpack[newhv] == null) {
                    backpack[newhv] = w;
                    break;
                }
            }
        }
        numItems++;
    }

    public void withdraw(double amt) {
        money = money - amt;
    }

    public boolean overweight(double weight) {
        double total = 0;
        for (int i = 0; i < 30; i++) {
            if (backpack[i] != null) {
                total += backpack[i].weight;
            }
        }
        return (total + weight > 30);
    }

    public boolean inventoryFull() {
        return (numItems == 30);
    }

    public void printCharacter() {
        System.out.println(" Name:"+name+"\n Money:"+money);
        printBackpack();
    }

    public void printBackpack() {
        System.out.println(" "+name+", you own "+numItems+" Weapons:");
        for (int x = 0; x < 30; x++) {
            if (backpack[x] != null) {
                System.out.println(" "+backpack[x].weaponName);
            }
        }
        System.out.println();
    }
}
