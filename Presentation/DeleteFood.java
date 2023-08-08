package CuoiKy.Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import CuoiKy.Presistence.FoodDAO;

public class DeleteFood implements ActionListener {
    private FoodWarehouse foodWarehouse;

    public DeleteFood(FoodWarehouse foodWarehouse) {
        this.foodWarehouse = foodWarehouse;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int selectedRow = foodWarehouse.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(foodWarehouse, "Chọn sản phẩm để xóa");
                return;
            }

            int idToDelete = Integer.parseInt(foodWarehouse.getTable().getValueAt(selectedRow, 0).toString());

            boolean check = FoodDAO.deleteFood(idToDelete);
            FoodDAO.Notification(check, "Xóa thành công", "Xóa thất bại");

            foodWarehouse.UploadTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(foodWarehouse, "Error: " + ex.getMessage());
        }
    }
}
