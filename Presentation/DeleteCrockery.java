package CuoiKy.Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CuoiKy.Presistence.CrockeryDAO;

public class DeleteCrockery implements ActionListener {
    private CrockeryWarehouse crockeryWarehouse;

    public DeleteCrockery(CrockeryWarehouse crockeryWarehouse) {
        this.crockeryWarehouse = crockeryWarehouse;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int selectedRow = crockeryWarehouse.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(crockeryWarehouse, "Chọn sản phẩm để xóa");
                return;
            }

            int id = Integer.parseInt(crockeryWarehouse.getTable().getValueAt(selectedRow, 0).toString());

            boolean check = CrockeryDAO.deleteCrockery(id);
            CrockeryDAO.Notification(check, "Xóa thành công", "Xóa thất bại");
            crockeryWarehouse.UploadTabel();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(crockeryWarehouse, "Error: " + ex.getMessage());
        }
    }
}
