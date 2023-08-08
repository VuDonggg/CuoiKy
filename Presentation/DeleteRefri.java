package CuoiKy.Presentation;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CuoiKy.Presistence.RefrigerationDAO;

public class DeleteRefri implements ActionListener {
    private RefrigerationWarehouse refrigerationWarehouse;

    public DeleteRefri(RefrigerationWarehouse refrigerationWarehouse) {
        this.refrigerationWarehouse = refrigerationWarehouse;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int selectedRow = refrigerationWarehouse.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(refrigerationWarehouse, "Chọn sản phẩm để xóa");
                return;
            }

            int idToDelete = Integer.parseInt(refrigerationWarehouse.getTable().getValueAt(selectedRow, 0).toString());

            boolean check = RefrigerationDAO.deleteRefrigeration(idToDelete);
            RefrigerationDAO.Notification(check, "Xóa thành công", "Xóa thất bại");

            refrigerationWarehouse.UploadTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(refrigerationWarehouse, "Error: " + ex.getMessage());
        }
    }
}
