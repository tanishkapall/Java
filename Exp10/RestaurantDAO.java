import java.sql.*;
import java.util.*;

public class RestaurantDAO {

    // INSERT
    public static void insertRestaurant(String name, String address) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO Restaurant(Name, Address) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT ALL
    public static List<RestaurantModel> selectAll() {
        List<RestaurantModel> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM Restaurant";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new RestaurantModel(
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getString("Address")
                ));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public static void updateRestaurant(int id, String name, String address) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE Restaurant SET Name=?, Address=? WHERE Id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setInt(3, id);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public static void deleteRestaurant(int id) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "DELETE FROM Restaurant WHERE Id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}