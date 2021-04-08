public class ArrayManager {
    
    int maxItems;    // records the max size of the table
    int numItems;       // records number of items in the list
    ShopItem[] table; //hashtable itself

    public ArrayManager(int size) {
        maxItems = size;
        numItems = 0;
        table = new ShopItem[maxItems];
    }

    public void add(int loc, int quantity) {
        table[loc].numberInStock += quantity;
    }

    public void create(Weapon item, int quantity) {
        if (numItems < maxItems) {
            int hv = item.weaponName.hashCode() % maxItems;
            if (table[hv] == null) {
                table[hv] = new ShopItem(item, quantity);
            } else {
                for (int j = 0; j < maxItems; j++) {
                    int newhv = (hv + j * j) % maxItems;
                    if (table[newhv] == null) {
                        table[newhv] = new ShopItem(item, quantity);
                        break;
                    }
                }
            }
            numItems++;
        }
    }

    public void delete(int loc) {
        table[loc] = null;
    }

    public int find(String name) {
        for (int loc = 0; loc < maxItems; loc++) {
            if (table[loc] != null && name.compareTo(table[loc].item.weaponName) == 0) {
                return loc;
            }
        }
        return -1;
    }

    public ShopItem get(String name) {
        for (int loc = 0; loc < maxItems; loc++) {
            if (table[loc] != null && name.compareTo(table[loc].item.weaponName) == 0) {
                return table[loc];
            }
        }
        return null;
    }

    public void printTable() {
        for (int x = 0; x < maxItems; x++) {
            if (table[x] != null && table[x].numberInStock > 0) {
                System.out.println("Name: " +table[x].item.weaponName+" -- Damage: "+table[x].item.damage+" -- Cost: "+table[x].item.cost+" -- Weight: "+table[x].item.weight+" -- Quantity in stock: "+table[x].numberInStock);
            }
        }
    }
}