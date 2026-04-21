import java.sql.Connection;
import java.sql.PreparedStatement;

public class RestaurantDAO {

    public static void insertRestaurants() {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO Restaurant(Name, Address) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            for (int i = 1; i <= 10; i++) {
                if (i == 1) {
                    ps.setString(1, "Cafe Java"); // important for query
                } else {
                    ps.setString(1, "Restaurant " + i);
                }
                ps.setString(2, "Address " + i);
                ps.executeUpdate();
            }

            System.out.println("Inserted 10 Restaurants\n");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}