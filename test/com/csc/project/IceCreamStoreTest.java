package com.csc.project;

import com.csc.project.data.Item;
import com.csc.project.data.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class IceCreamStoreTest {

    private final Item[] testItems = {
            new Item("item 1", 1.00),
            new Item("item 2", 2.00),
            new Item("item 3", 3.00),
            new Item("item 4", 4.00),
    };
    private final User[] testsUsers = {
            new User("user1", "validPassWord")
    };
    private IceCreamStore testStore;

    @BeforeEach
    void setUp() {
        testStore = new IceCreamStore(testsUsers, testItems);
    }

    @Test
    void loginValidUsernameAndPasswordReturnsTrue() {
        boolean result = testStore.login("user1", "validPassWord");
        assertTrue(result);
    }

    @Test
    void login_ValidUsernameAndInvalidPasswordReturnsFalse() {
        boolean result = testStore.login("user1", "invalidPassWord");
        assertFalse(result);
    }

    @Test
    void login_InvalidUsernameAndValidPasswordReturnsFalse() {
        boolean result = testStore.login("user2", "validPassWord");
        assertFalse(result);
    }

    @Test
    void getItemNames_ReturnsCorrectArrayOfItemNames() {
        String[] items = testStore.getItemNames();
        assertArrayEquals(new String[]{"item 1", "item 2", "item 3", "item 4"}, items);
    }

    @Test
    void getItemPrice_ReturnsCorrectPriceForExistingItems() {
        double price1 = testStore.getItemPrice("item 1");
        assertEquals(1.00, price1);

        double price2 = testStore.getItemPrice("item 2");
        assertEquals(2.00, price2);
    }

    @Test
    void getItemPrice_ReturnsZeroForMissingItems() {
        double price1 = testStore.getItemPrice("item 99");
        assertEquals(0, price1);
    }


    @Test
    void calculateCost() {
        String[] itemNames = {"item 1", "item 2"};
        var result = testStore.calculateCost(itemNames);
        assertEquals((1.00 + 2.0), result);
    }

    @Test
    void calculateTax() {
        String[] itemNames = {"item 3", "item 4"};
        var result = testStore.calculateTax(itemNames);
        assertEquals((3.00 + 4.00) * 0.08, result);
    }

    @Test
    void calculateTotal_UsingUniqueItems() {
        String[] itemNames = {"item 1", "item 2", "item 3", "item 4"};
        var result = testStore.calculateTotal(itemNames);
        double expected = (1.00 + 2.00 + 3.00 + 4.00) * 1.08;
        assertEquals(expected , result);
    }

    @Test
    void calculateTotal_UsingDuplicatedItems() {
        String[] itemNames = {"item 2", "item 2", "item 3", "item 3", "item 4"};
        var result = testStore.calculateTotal(itemNames);
        double expected = (2.00 + 2.00 + 3.00 + 3.00 + 4.00) * 1.08;
        assertEquals(expected , result);
    }
}