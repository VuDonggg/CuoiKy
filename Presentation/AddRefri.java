package CuoiKy.Presentation;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CuoiKy.Domain.Model.ConstructorRefrigeration;
import CuoiKy.Presistence.RefrigerationDAO;

public class AddRefri implements ActionListener {
    private RefrigerationWarehouse refrigerationWarehouse;

    public AddRefri(RefrigerationWarehouse refrigerationWarehouse) {
        this.refrigerationWarehouse = refrigerationWarehouse;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int id = Integer.parseInt(refrigerationWarehouse.getIdTextField().getText());
            String name = refrigerationWarehouse.getNameTextField().getText();
            double price = Double.parseDouble(refrigerationWarehouse.getPriceTextField().getText());
            int inStock = Integer.parseInt(refrigerationWarehouse.getInStockTextField().getText());
            double wattage = Double.parseDouble(refrigerationWarehouse.getWattageTextField().getText());

            String guarantee = "";
            int selectedGuaranteeIndex = refrigerationWarehouse.getGuaranteeComboBox().getSelectedIndex();
            if (selectedGuaranteeIndex > 0) {
                guarantee = refrigerationWarehouse.getGuaranteeComboBox().getSelectedItem().toString();
            } else {
                JOptionPane.showMessageDialog(refrigerationWarehouse, "Vui lòng chọn thời gian bảo hành", "Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            ConstructorRefrigeration refrigeration = new ConstructorRefrigeration(id, name, price, inStock, wattage,
                    guarantee);

            boolean check = RefrigerationDAO.insertRefrigeration(refrigeration);
            RefrigerationDAO.Notification(check, "Thêm thành công", "Thêm thất bại");
            refrigerationWarehouse.UploadTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(refrigerationWarehouse, "Error: " + ex.getMessage());
        }
    }
}
