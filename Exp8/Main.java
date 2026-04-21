import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        // Get Singleton instance
        InventoryManager manager = InventoryManager.getInstance();

        // Add New Products
        manager.addProduct(new NewProduct("Laptop"));
        manager.addProduct(new NewProduct("Mobile"));

        // Add Legacy Products using Adapter
        LegacyItem item1 = new LegacyItem(101, "Old Keyboard");
        LegacyItem item2 = new LegacyItem(102, "Old Mouse");

        manager.addProduct(new ProductAdapter(item1));
        manager.addProduct(new ProductAdapter(item2));

        // Iterate using Iterator
        Iterator<Product> iterator = manager.returnInventory();

        System.out.println("Inventory Details:\n");

        while (iterator.hasNext()) {
            Product product = iterator.next();
            product.displayDetails();
        }
    }
}
