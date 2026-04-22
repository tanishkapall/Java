import java.sql.*;
import java.util.*;

public class MenuItemDAO {

    // INSERT
    public static void insertItem(String name, double price, int resId) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO MenuItem(Name, Price, ResId) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, resId);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT ALL
    public static List<MenuItemModel> selectAll() {
        List<MenuItemModel> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM MenuItem";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new MenuItemModel(
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getDouble("Price"),
                    rs.getInt("ResId")
                ));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public static void updateItem(int id, String name, double price, int resId) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE MenuItem SET Name=?, Price=?, ResId=? WHERE Id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, resId);
            ps.setInt(4, id);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public static void deleteItem(int id) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "DELETE FROM MenuItem WHERE Id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}