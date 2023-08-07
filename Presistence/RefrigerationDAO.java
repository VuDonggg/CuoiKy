package CuoiKy.Presistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import CuoiKy.Domain.Model.ConstructorRefrigeration;

public class RefrigerationDAO {

    public static void Notification(boolean Check, String successMessage, String errorMessage) {
        if (Check) {
            JOptionPane.showMessageDialog(null, successMessage, "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, errorMessage, "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static boolean insertRefrigeration(ConstructorRefrigeration refrigeration) throws Exception {
        String query = "INSERT INTO DienMay (id, name, price, instock, wattage, guarantee) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = Connect.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, refrigeration.getId());
            ps.setString(2, refrigeration.getName());
            ps.setDouble(3, refrigeration.getPrice());
            ps.setInt(4, refrigeration.getInstock());
            ps.setDouble(5, refrigeration.getWattage());
            ps.setString(6, refrigeration.getGuarantee());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean editRefrigeration(ConstructorRefrigeration refrigeration) throws Exception {
        String query = "UPDATE DienMay SET name=?, price=?, instock=?, wattage=?, guarantee=? WHERE id=?";
        try (Connection connection = Connect.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, refrigeration.getName());
            ps.setDouble(2, refrigeration.getPrice());
            ps.setInt(3, refrigeration.getInstock());
            ps.setDouble(4, refrigeration.getWattage());
            ps.setString(5, refrigeration.getGuarantee());
            ps.setInt(6, refrigeration.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteRefrigeration(int id) throws Exception {
        String query = "DELETE FROM DienMay WHERE id=?";
        try (Connection connection = Connect.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static DefaultTableModel findRefrigeration(String searchText) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã sản phẩm");
        model.addColumn("Tên hàng");
        model.addColumn("Số lượng tồn");
        model.addColumn("Đơn giá");
        model.addColumn("Công suất");
        model.addColumn("Thời gian bảo hành");

        if (searchText != null && !searchText.trim().isEmpty()) {
            try {
                Connection connection = Connect.getConnection();
                String query = "SELECT * FROM DienMay WHERE name LIKE ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, "%" + searchText + "%");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(rs.getString("id"));
                    vector.add(rs.getString("name"));
                    vector.add(rs.getString("price"));
                    vector.add(rs.getString("instock"));
                    vector.add(rs.getString("wattage"));
                    vector.add(rs.getString("guarantee"));
                    model.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return model;
    }
}
