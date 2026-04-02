import java.util.ArrayList;
import java.util.List;

public class IceCreamStore {
    private final double TAX_RATE = 0.08;

    final private User[] users;
    final private Item[] storeItems;
    private List<Item> cartItems = new ArrayList<>();

    public IceCreamStore(User[] users, Item[] storeItems) {
        this.storeItems = storeItems;
        this.users = users;
    }

    public IceCreamStore() {
        this(
                new User[]{
                        new User("Baskin", "100 Chambers")
                },
                new Item[]{
                        new Item("Ice Cream", 3.75),
                        new Item("Soda", 0.75),
                        new Item("Froyo", 2.75),
                        new Item("Sundae", 5.75),
                });
    }

    public boolean login(String username, String password) {
        for (User user : this.users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user.validatePassword(password);
            }
        }
        return false;
    }

    public String[] getItemNames() {
        String[] names = new String[storeItems.length];

        for (int i = 0; i < storeItems.length; i++) {
            names[i] = storeItems[i].name;
        }

        return names;
    }

    public double getItemPrice(String itemName) {
        for (Item item : storeItems) {
            if (item.name.equalsIgnoreCase(itemName)) {
                return item.cost;
            }
        }

        return -1;
    }

    public double calculateCost(Item[] items) {
        double total = 0.0;
        for (Item item : items) {
            total += item.cost;
        }

        return total;
    }

    public double calculateTax(Item[] items) {
        return calculateCost(items) * TAX_RATE;
    }

    public double getTotalCost(Item[] items) {
        return calculateCost(items) + calculateTax(items);
    }

    public void addItem(String itemName) {
        Item item = getItem(itemName);
        if (item != null) {
            this.cartItems.add(item);
        }
    }

    private Item getItem(String itemName) {
        for (Item storeItem : this.storeItems) {
            if (storeItem.name.equalsIgnoreCase(itemName)) {
                return storeItem;
            }
        }
        return null;
    }

    public void removeItem(String itemName) {
        for (Item cartItem : this.cartItems) {
            if(cartItem.name.equalsIgnoreCase(itemName)){
                this.cartItems.remove(cartItem);
            }
        }
    }

    public void clearCart() {
        this.cartItems = new ArrayList<>();
    }
}
