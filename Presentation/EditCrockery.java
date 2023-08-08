package CuoiKy.Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import CuoiKy.Presistence.CrockeryDAO;
import CuoiKy.Domain.Model.ConstructorCrockery;

public class EditCrockery implements ActionListener {
    private CrockeryWarehouse crockeryWarehouse;

    public EditCrockery(CrockeryWarehouse crockeryWarehouse) {
        this.crockeryWarehouse = crockeryWarehouse;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int selectedRow = crockeryWarehouse.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(crockeryWarehouse, "Chọn sản phẩm để chỉnh sửa");
                return;
            }

            int id = Integer.parseInt(crockeryWarehouse.getTable().getValueAt(selectedRow, 0).toString());
            int idFromTextField = Integer.parseInt(crockeryWarehouse.getIdTextField().getText());

            if (id != idFromTextField) {
                JOptionPane.showMessageDialog(crockeryWarehouse,
                        "Không thể thay đổi ID, Cập nhật thất bại",
                        "Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String name = crockeryWarehouse.getNameTextField().getText();
            double price = Double.parseDouble(crockeryWarehouse.getPriceTextField().getText());
            int inStock = Integer.parseInt(crockeryWarehouse.getInStockTextField().getText());
            String inforProducer = crockeryWarehouse.getInforproduTextField().getText();
            Date dayAdd = crockeryWarehouse.getDateChooser().getDate();

            ConstructorCrockery crockery = new ConstructorCrockery(id, name, price, inStock, inforProducer, dayAdd);

            boolean check = CrockeryDAO.editCrockery(crockery);
            CrockeryDAO.Notification(check, "Cập nhật thành công", "Cập nhật thất bại");
            crockeryWarehouse.UploadTabel();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(crockeryWarehouse, "Error: " + ex.getMessage());
        }
    }
}
