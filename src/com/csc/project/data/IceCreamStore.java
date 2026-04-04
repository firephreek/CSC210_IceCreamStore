package com.csc.project.data;

import com.csc.project.models.Item;
import com.csc.project.models.User;

public class IceCreamStore implements Store {
    private static final Item EMPTY_ITEM = new Item("", 0.0);
    private final double TAX_RATE = 0.08;

    final private User[] users;
    final private Item[] storeItems;

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

    @Override
    public boolean login(String username, String password) {
        for (User user : this.users) {
            if (user.getUsername().equalsIgnoreCase(username.trim())) {
                return user.validatePassword(password);
            }
        }
        return false;
    }

    @Override
    public String[] getItemNames() {
        String[] names = new String[storeItems.length];

        for (int i = 0; i < storeItems.length; i++) {
            names[i] = storeItems[i].getName();
        }

        return names;
    }

    @Override
    public double getItemPrice(String itemName) {
        for (Item item : storeItems) {
            if (item.getName().equalsIgnoreCase(itemName.trim())) {
                return item.getCost();
            }
        }

        return EMPTY_ITEM.getCost();
    }

    @Override
    public double calculateCost(String[] itemNames) {
        double total = 0.0;
        for (String itemName : itemNames) {
            Item item = getItem(itemName);
            total += item.getCost();
        }

        return total;
    }

    @Override
    public double calculateTax(String[] itemNames) {
        return calculateCost(itemNames) * TAX_RATE;
    }

    @Override
    public double calculateTotal(String[] itemNames) {
        return calculateCost(itemNames) + calculateTax(itemNames);
    }

    @Override
    public Item getItem(String itemName) {
        for (Item storeItem : this.storeItems) {
            if (storeItem.getName().equalsIgnoreCase(itemName.trim())) {
                return storeItem;
            }
        }
        return EMPTY_ITEM;
    }

    @Override
    public Item[] getItems() {
        return this.storeItems;
    }
}
