package CuoiKy.Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import CuoiKy.Domain.Model.ConstructorFood;
import CuoiKy.Presistence.FoodDAO;

public class EditFood implements ActionListener {
    private FoodWarehouse foodWarehouse;

    public EditFood(FoodWarehouse foodWarehouse) {
        this.foodWarehouse = foodWarehouse;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int selectedRow = foodWarehouse.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(foodWarehouse, "Chọn sản phẩm để chỉnh sửa");
                return;
            }

            int id = Integer.parseInt(foodWarehouse.getTable().getValueAt(selectedRow, 0).toString());
            int idFromTextField = Integer.parseInt(foodWarehouse.getIdTextField().getText());

            if (id != idFromTextField) {
                JOptionPane.showMessageDialog(foodWarehouse, "Không thể thay đổi ID, cập nhật thất bại", "Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String name = foodWarehouse.getNameTextField().getText();
            double price = Double.parseDouble(foodWarehouse.getPriceTextField().getText());
            int inStock = Integer.parseInt(foodWarehouse.getInStockTextField().getText());
            Date dateOfManu = foodWarehouse.getDateOfManuChooser().getDate();
            Date dateOfExp = foodWarehouse.getDateOfExpChooser().getDate();

            ConstructorFood food = new ConstructorFood(id, name, price, inStock, dateOfManu, dateOfExp);

            boolean check = FoodDAO.editFood(food);
            FoodDAO.Notification(check, "Cập nhật thành công", "Cập nhật thất bại");

            foodWarehouse.UploadTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(foodWarehouse, "Error: " + ex.getMessage());
        }
    }
}
