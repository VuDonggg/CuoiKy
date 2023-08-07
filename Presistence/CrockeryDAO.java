package CuoiKy.Presistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import CuoiKy.Domain.Model.ConstructorCrockery;

public class CrockeryDAO {
    public static void Notification(boolean Check, String successMessage, String errorMessage) {
        if (Check) {
            JOptionPane.showMessageDialog(null, successMessage, "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, errorMessage, "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static boolean insertCrockery(ConstructorCrockery crockery) throws Exception {
        String query = "INSERT INTO SanhSu (id, name, price, instock, inforprodu, dayadd) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = Connect.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, crockery.getId());
            ps.setString(2, crockery.getName());
            ps.setDouble(3, crockery.getPrice());
            ps.setInt(4, crockery.getInstock());
            ps.setString(5, crockery.getInforProducer());

            java.util.Date utilDate = crockery.getDayAdd();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps.setDate(6, sqlDate);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteCrockery(int id) throws Exception {
        String query = "DELETE FROM SanhSu WHERE id = ?";
        try (Connection connection = Connect.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean editCrockery(ConstructorCrockery crockery) throws Exception {
        String query = "UPDATE SanhSu SET name=?, price=?, instock=?, inforprodu=?, dayadd=? WHERE id=?";
        try (Connection connection = Connect.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, crockery.getName());
            ps.setDouble(2, crockery.getPrice());
            ps.setInt(3, crockery.getInstock());
            ps.setString(4, crockery.getInforProducer());

            java.util.Date utilDate = crockery.getDayAdd();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps.setDate(5, sqlDate);

            ps.setInt(6, crockery.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static DefaultTableModel findCrockery(String searchText) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã sản phẩm");
        model.addColumn("Tên hàng");
        model.addColumn("Số lượng tồn");
        model.addColumn("Đơn giá");
        model.addColumn("Thông tin nhà sản xuất");
        model.addColumn("Ngày nhập kho");

        if (searchText != null && !searchText.trim().isEmpty()) {
            try {
                Connection connection = Connect.getConnection();
                String query = "SELECT * FROM SanhSu WHERE name LIKE ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, "%" + searchText + "%");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(rs.getString("id"));
                    vector.add(rs.getString("name"));
                    vector.add(rs.getString("price"));
                    vector.add(rs.getString("instock"));
                    vector.add(rs.getString("inforprodu"));
                    vector.add(rs.getString("dayadd"));
                    model.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return model;
    }
}
