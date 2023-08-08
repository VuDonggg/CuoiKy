package CuoiKy.Presentation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import CuoiKy.Presistence.Connect;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class RefrigerationWarehouse extends JPanel {
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
    private JTextField wattageTextField;
    private JComboBox<String> guaranteeComboBox;

    public void UploadTable() {
        try {
            String[] sql = { "Id", "Name", "Price", "Instock", "Wat", "Guarantee" };
            DefaultTableModel model = new DefaultTableModel(sql, 0);

            Connection connection = Connect.getConnection();
            String query = "Select * FROM DienMay";
            PreparedStatement ps = connection.prepareStatement(query);
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
            table.setModel(model);
            idTextField.setText("");
            nameTextField.setText("");
            priceTextField.setText("");
            inStockTextField.setText("");
            wattageTextField.setText("");
            guaranteeComboBox.getSelectedItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RefrigerationWarehouse() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã sản phẩm");
        tableModel.addColumn("Tên hàng");
        tableModel.addColumn("Số lượng tồn");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Công suất");
        tableModel.addColumn("Thời gian bảo hành");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(10, 2));
        idTextField = new JTextField();
        nameTextField = new JTextField();
        inStockTextField = new JTextField();
        priceTextField = new JTextField();
        wattageTextField = new JTextField();

        guaranteeComboBox = new JComboBox<>();
        guaranteeComboBox.addItem("");
        guaranteeComboBox.addItem("6 months");
        guaranteeComboBox.addItem("8 months");
        guaranteeComboBox.addItem("12 months");
        guaranteeComboBox.addItem("18 months");
        guaranteeComboBox.addItem("24 months");

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
        inputPanel.add(new JLabel("Công suất:"));
        inputPanel.add(wattageTextField);
        inputPanel.add(new JLabel("Thời gian bảo hành:"));
        inputPanel.add(guaranteeComboBox);

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
        addButton.addActionListener(new AddRefri(this));
        editButton.addActionListener(new EditRefri(this));
        findButton.addActionListener(new FindRefri(this));
        deleteButton.addActionListener(new DeleteRefri(this));
        add(inputPanel, BorderLayout.SOUTH);
        UploadTable();

    }

    private void handleTableRowSelection() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            idTextField.setText(table.getValueAt(selectedRow, 0).toString());
            nameTextField.setText(table.getValueAt(selectedRow, 1).toString());
            priceTextField.setText(table.getValueAt(selectedRow, 2).toString());
            inStockTextField.setText(table.getValueAt(selectedRow, 3).toString());
            wattageTextField.setText(table.getValueAt(selectedRow, 4).toString());

            String selectedGuarantee = table.getValueAt(selectedRow, 5).toString();
            int index = 0;
            switch (selectedGuarantee) {
                case "6 months":
                    index = 0;
                    break;
                case "8 months":
                    index = 1;
                    break;
                case "12 months":
                    index = 2;
                    break;
                case "18 months":
                    index = 3;
                    break;
                case "24 months":
                    index = 4;
                    break;
                default:
                    index = 0;
            }
            guaranteeComboBox.setSelectedIndex(index);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Refrigeration Warehouse");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setContentPane(new RefrigerationWarehouse());
            frame.setVisible(true);
        });
    }

    public JTextField getIdTextField() {
        return idTextField;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getPriceTextField() {
        return priceTextField;
    }

    public JTextField getInStockTextField() {
        return inStockTextField;
    }

    public JTable getTable() {
        return table;
    }

    public JTextComponent getWattageTextField() {
        return wattageTextField;
    }

    public JComboBox<String> getGuaranteeComboBox() {
        return guaranteeComboBox;
    }
}
