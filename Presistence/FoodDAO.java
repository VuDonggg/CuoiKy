package CuoiKy.Presistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import CuoiKy.Domain.Model.ConstructorFood;

public class FoodDAO {
    public static void Notification(boolean Check, String successMessage, String errorMessage) {
        if (Check) {
            JOptionPane.showMessageDialog(null, successMessage, "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, errorMessage, "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static boolean insertFood(ConstructorFood food) throws Exception {
        String query = "INSERT INTO ThucPham (id, name, price, instock, dom, exp) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = Connect.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, food.getId());
            ps.setString(2, food.getName());
            ps.setDouble(3, food.getPrice());
            ps.setInt(4, food.getInstock());

            java.util.Date utilDate = food.getDOM();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps.setDate(5, sqlDate);

            java.util.Date utilDateExp = food.getEXP();
            java.sql.Date sqlDateExp = new java.sql.Date(utilDateExp.getTime());
            ps.setDate(6, sqlDateExp);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean editFood(ConstructorFood food) throws Exception {
        String query = "UPDATE ThucPham SET name=?, price=?, instock=?, dom=?, exp=? WHERE id=?";
        try (Connection connection = Connect.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, food.getName());
            ps.setDouble(2, food.getPrice());
            ps.setInt(3, food.getInstock());

            java.util.Date utilDate = food.getDOM();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps.setDate(4, sqlDate);

            java.util.Date utilDateExp = food.getEXP();
            java.sql.Date sqlDateExp = new java.sql.Date(utilDateExp.getTime());
            ps.setDate(5, sqlDateExp);

            ps.setInt(6, food.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteFood(int id) throws Exception {
        String query = "DELETE FROM ThucPham WHERE id = ?";
        try (Connection connection = Connect.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static DefaultTableModel findFood(String searchText) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã sản phẩm");
        model.addColumn("Tên hàng");
        model.addColumn("Số lượng tồn");
        model.addColumn("Đơn giá");
        model.addColumn("Ngày sản xuất");
        model.addColumn("Ngày hết hạn");

        if (searchText != null && !searchText.trim().isEmpty()) {
            try {
                Connection connection = Connect.getConnection();
                String query = "SELECT * FROM ThucPham WHERE name LIKE ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, "%" + searchText + "%");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(rs.getString("id"));
                    vector.add(rs.getString("name"));
                    vector.add(rs.getString("price"));
                    vector.add(rs.getString("instock"));
                    vector.add(rs.getString("dom"));
                    vector.add(rs.getString("exp"));
                    model.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return model;
    }
}
