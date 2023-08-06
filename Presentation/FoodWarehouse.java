package CuoiKy.Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import CuoiKy.Presistence.Connect;

public class FoodWarehouse extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton findButton;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField inStockTextField;
    private JTextField priceTextField;
    private JDateChooser dateOfManuChooser;
    private JDateChooser dateOfExpChooser;

    public void UploadTable() {
        try {
            String[] sql = { "Id", "Name", "Price", "Instock", "DOM", "EXP" };
            DefaultTableModel model = new DefaultTableModel(sql, 0);

            Connection connection = Connect.getConnection();
            String query = "Select * FROM ThucPham";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vector<String> vector = new Vector<String>();
                vector.add(rs.getString("id"));
                vector.add(rs.getString("name"));
                vector.add(rs.getString("price"));
                vector.add(rs.getString("instock"));
                vector.add(rs.getString("dom"));
                vector.add(rs.getString("exp"));
                model.addRow(vector);
            }
            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FoodWarehouse() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã sản phẩm");
        tableModel.addColumn("Tên hàng");
        tableModel.addColumn("Số lượng tồn");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Ngày sản xuất");
        tableModel.addColumn("Ngày hết hạn");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(10, 2));
        idTextField = new JTextField();
        nameTextField = new JTextField();
        inStockTextField = new JTextField();
        priceTextField = new JTextField();
        dateOfManuChooser = new JDateChooser();
        dateOfExpChooser = new JDateChooser();
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        findButton = new JButton("Find");

        dateOfManuChooser.setDateFormatString("dd/MM/yyyy"); // Format the date to display
        JTextFieldDateEditor editor1 = (JTextFieldDateEditor) dateOfManuChooser.getComponent(1);
        editor1.setEditable(false); // Prevent manual editing

        dateOfExpChooser.setDateFormatString("dd/MM/yyyy");
        JTextFieldDateEditor editor2 = (JTextFieldDateEditor) dateOfExpChooser.getComponent(1);
        editor2.setEditable(false);

        inputPanel.add(new JLabel("Mã sản phẩm:"));
        inputPanel.add(idTextField);
        inputPanel.add(new JLabel("Tên hàng:"));
        inputPanel.add(nameTextField);
        inputPanel.add(new JLabel("Số lượng tồn:"));
        inputPanel.add(inStockTextField);
        inputPanel.add(new JLabel("Đơn giá:"));
        inputPanel.add(priceTextField);
        inputPanel.add(new JLabel("Ngày sản xuất:"));
        inputPanel.add(dateOfManuChooser);
        inputPanel.add(new JLabel("Ngày hết hạn:"));
        inputPanel.add(dateOfExpChooser);

        inputPanel.add(addButton);
        inputPanel.add(editButton);
        inputPanel.add(deleteButton);
        inputPanel.add(findButton);

        add(inputPanel, BorderLayout.SOUTH);
        UploadTable();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Food Warehouse");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setContentPane(new FoodWarehouse());
            frame.setVisible(true);
        });
    }
}
