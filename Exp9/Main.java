public class Main {
    public static void main(String[] args) {

        // Insert data
        RestaurantDAO.insertRestaurants();
        MenuItemDAO.insertMenuItems();

        // SELECT
        MenuItemDAO.selectPriceLessThan100();
        MenuItemDAO.selectFromCafeJava();

        // UPDATE
        MenuItemDAO.updatePrice();

        // Verify update
        MenuItemDAO.selectPriceLessThan100();

        // DELETE
        MenuItemDAO.deleteItems();

        // Final state
        MenuItemDAO.selectPriceLessThan100();
    }
}
