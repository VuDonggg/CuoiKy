package CuoiKy.Presentation;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CuoiKy.Domain.Model.ConstructorRefrigeration;
import CuoiKy.Presistence.RefrigerationDAO;

public class EditRefri implements ActionListener {
    private RefrigerationWarehouse refrigerationWarehouse;

    public EditRefri(RefrigerationWarehouse refrigerationWarehouse) {
        this.refrigerationWarehouse = refrigerationWarehouse;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int selectedRow = refrigerationWarehouse.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(refrigerationWarehouse, "Chọn sản phẩm để chỉnh sửa");
                return;
            }

            int id = Integer.parseInt(refrigerationWarehouse.getTable().getValueAt(selectedRow, 0).toString());
            int idFromTextField = Integer.parseInt(refrigerationWarehouse.getIdTextField().getText());

            if (id != idFromTextField) {
                JOptionPane.showMessageDialog(refrigerationWarehouse, "Không thể thay đổi ID, cập nhật thất bại",
                        "Message", JOptionPane.ERROR_MESSAGE);
                return;
            }

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

            boolean check = RefrigerationDAO.editRefrigeration(refrigeration);
            RefrigerationDAO.Notification(check, "Cập nhật thành công", "Cập nhật thất bại");
            refrigerationWarehouse.UploadTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(refrigerationWarehouse, "Error: " + ex.getMessage());
        }
    }
}
