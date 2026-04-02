public class IceCreamStore {
    private final double taxRate = 0.08;

    private User[] users = {
            new User("Baskin", "100 Chambers")
    };

    private Item[] items = {

    };

    public String[] getItemNames() {
        throw new RuntimeException();
    }

    public double getItemPrice(String itemName) {
        throw new RuntimeException();
    }

    public double getSubCost(String[] itemNames) {
        throw new RuntimeException();
    }

    public double getTaxCost(String[] itemNames) {
        throw new RuntimeException();
    }

    public double getTotalCost(String[] itemNames) {
        throw new RuntimeException();
    }

    public void addItem(String itemName){

    }

    public void removeItem(String itemName){

    }
}
