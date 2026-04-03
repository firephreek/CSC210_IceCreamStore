package com.csc.project.data;

public interface Store {
    boolean login(String username, String password);

    String[] getItemNames();

    double getItemPrice(String itemName);

    double calculateCost(String[] itemNames);

    double calculateTax(String[] itemNames);

    double calculateTotal(String[] itemNames);

    Item getItem(String itemName);

    Item[] getItems();
}
