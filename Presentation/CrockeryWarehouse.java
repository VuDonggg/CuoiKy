package CuoiKy.Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import CuoiKy.Domain.Model.ConstructorCrockery;
import CuoiKy.Presistence.Connect;
import CuoiKy.Presistence.CrockeryDAO;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CrockeryWarehouse extends JPanel {
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
    private JTextField inforproduTextField;
    private JDateChooser dateChooser;

    public void UploadTabel() {
        try {
            String[] sql = { "Id", "Name", "Price", "Instock", "Infor Producer", "Day Add" };
            DefaultTableModel model = new DefaultTableModel(sql, 0);

            Connection connection = Connect.getConnection();
            String query = "Select * FROM SanhSu";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vector<String> vector = new Vector<String>();
                vector.add(rs.getString("id"));
                vector.add(rs.getString("name"));
                vector.add(rs.getString("price"));
                vector.add(rs.getString("instock"));
                vector.add(rs.getString("inforprodu"));
                vector.add(rs.getString("dayadd"));
                model.addRow(vector);
            }
            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleTableRowSelection() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            idTextField.setText(table.getValueAt(selectedRow, 0).toString());
            nameTextField.setText(table.getValueAt(selectedRow, 1).toString());
            priceTextField.setText(table.getValueAt(selectedRow, 2).toString());
            inStockTextField.setText(table.getValueAt(selectedRow, 3).toString());
            inforproduTextField.setText(table.getValueAt(selectedRow, 4).toString());

            String dateString = table.getValueAt(selectedRow, 5).toString();
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                dateChooser.setDate(date);
            } catch (Exception ex) {

            }
        }
    }

    public CrockeryWarehouse() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã sản phẩm");
        tableModel.addColumn("Tên hàng");
        tableModel.addColumn("Số lượng tồn");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Thông tin nhà sản xuất");
        tableModel.addColumn("Ngày nhập kho");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(10, 2));
        idTextField = new JTextField();
        nameTextField = new JTextField();
        inStockTextField = new JTextField();
        priceTextField = new JTextField();
        inforproduTextField = new JTextField();

        dateChooser = new JDateChooser();
        dateChooser.setDate(new Date());

        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        findButton = new JButton("Find");

        inputPanel.add(new JLabel("Mã sản phẩm:"));
        inputPanel.add(idTextField);
        inputPanel.add(new JLabel("Tên hàng:"));
        inputPanel.add(nameTextField);
        inputPanel.add(new JLabel("Số lượng tồn:"));
        inputPanel.add(inStockTextField);
        inputPanel.add(new JLabel("Đơn giá:"));
        inputPanel.add(priceTextField);
        inputPanel.add(new JLabel("Thông tin nhà sản xuất:"));
        inputPanel.add(inforproduTextField);
        inputPanel.add(new JLabel("Ngày nhập kho:"));
        inputPanel.add(dateChooser);

        inputPanel.add(addButton);
        inputPanel.add(editButton);
        inputPanel.add(deleteButton);
        inputPanel.add(findButton);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    handleTableRowSelection();
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    String name = nameTextField.getText();
                    double price = Double.parseDouble(priceTextField.getText());
                    int inStock = Integer.parseInt(inStockTextField.getText());
                    String inforProducer = inforproduTextField.getText();
                    Date dayAdd = dateChooser.getDate();

                    ConstructorCrockery crockery = new ConstructorCrockery(id, name, price, inStock, inforProducer,
                            dayAdd);

                    boolean Check = CrockeryDAO.insertCrockery(crockery);
                    CrockeryDAO.Notification(Check, "Thêm thành công", "Thêm thất bại");
                    UploadTabel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CrockeryWarehouse.this, "Error: " + ex.getMessage());
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(CrockeryWarehouse.this, "Chọn sản phẩm để xóa");
                        return;
                    }

                    int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

                    boolean Check = CrockeryDAO.deleteCrockery(id);
                    CrockeryDAO.Notification(Check, "Xóa thành công", "Xóa thất bại");
                    UploadTabel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CrockeryWarehouse.this, "Error: " + ex.getMessage());
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(CrockeryWarehouse.this, "Chọn sản phẩm để chỉnh sửa");
                        return;
                    }

                    int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                    String name = nameTextField.getText();
                    double price = Double.parseDouble(priceTextField.getText());
                    int inStock = Integer.parseInt(inStockTextField.getText());
                    String inforProducer = inforproduTextField.getText();
                    Date dayAdd = dateChooser.getDate();

                    ConstructorCrockery crockery = new ConstructorCrockery(id, name, price, inStock, inforProducer,
                            dayAdd);

                    boolean Check = CrockeryDAO.editCrockery(crockery);
                    CrockeryDAO.Notification(Check, "Cập nhật thành công", "Cập nhật thất bại");
                    UploadTabel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CrockeryWarehouse.this, "Error: " + ex.getMessage());
                }
            }
        });

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = JOptionPane.showInputDialog(CrockeryWarehouse.this,
                        "Nhập tên sản phẩm để tìm kiếm: ");
                DefaultTableModel model = CrockeryDAO.findCrockery(searchText);
                table.setModel(model);
            }
        });

        add(inputPanel, BorderLayout.SOUTH);
        UploadTabel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Crockery Warehouse");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setContentPane(new CrockeryWarehouse());
            frame.setVisible(true);
        });
    }
}
