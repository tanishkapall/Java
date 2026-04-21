import java.sql.*;

public class MenuItemDAO {

    // Insert 10 MenuItems
    public static void insertMenuItems() {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO MenuItem(Name, Price, ResId) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            for (int i = 1; i <= 10; i++) {
                ps.setString(1, (i % 2 == 0) ? "Pizza " + i : "Burger " + i);
                ps.setDouble(2, i * 50); // 50,100,150...
                ps.setInt(3, (i % 10) + 1); // map to restaurants
                ps.executeUpdate();
            }

            System.out.println("Inserted 10 Menu Items\n");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Print ResultSet in tabular format
    public static void printTable(ResultSet rs) throws Exception {
        System.out.printf("%-5s %-15s %-10s %-5s\n", "ID", "Name", "Price", "ResId");
        System.out.println("-------------------------------------------");

        while (rs.next()) {
            System.out.printf("%-5d %-15s %-10.2f %-5d\n",
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getDouble("Price"),
                    rs.getInt("ResId"));
        }
    }

    // SELECT price <=100
    public static void selectPriceLessThan100() {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM MenuItem WHERE Price <= 100";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("Menu Items with Price <= 100:");
            printTable(rs);
            System.out.println();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT items in Cafe Java
    public static void selectFromCafeJava() {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT m.* FROM MenuItem m " +
                         "JOIN Restaurant r ON m.ResId = r.Id " +
                         "WHERE r.Name = 'Cafe Java'";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("Menu Items in Cafe Java:");
            printTable(rs);
            System.out.println();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE price <=100 → 200
    public static void updatePrice() {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE MenuItem SET Price = 200 WHERE Price <= 100";
            Statement st = con.createStatement();
            int rows = st.executeUpdate(sql);

            System.out.println("Updated Rows: " + rows + "\n");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE where name starts with P
    public static void deleteItems() {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM MenuItem WHERE Name LIKE 'P%'";
            Statement st = con.createStatement();
            int rows = st.executeUpdate(sql);

            System.out.println("Deleted Rows: " + rows + "\n");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}