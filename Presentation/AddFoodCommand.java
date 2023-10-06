package CuoiKy.Presentation;

import java.sql.Date;

import javax.swing.JOptionPane;

import CuoiKy.Domain.Model.ConstructorFood;
import CuoiKy.Presistence.FoodDAO;

public class AddFoodCommand implements FoodCommand {
    private FoodWarehouse foodWarehouse;

    public AddFoodCommand(FoodWarehouse foodWarehouse) {
        this.foodWarehouse = foodWarehouse;
    }

    @Override
    public void execute() {
        try {
            int id = Integer.parseInt(foodWarehouse.getIdTextField().getText());
            String name = foodWarehouse.getNameTextField().getText();
            double price = Double.parseDouble(foodWarehouse.getPriceTextField().getText());
            int inStock = Integer.parseInt(foodWarehouse.getInStockTextField().getText());
            Date dom = (Date) foodWarehouse.getDateOfManuChooser().getDate();
            Date exp = (Date) foodWarehouse.getDateOfExpChooser().getDate();

            ConstructorFood food = new ConstructorFood(id, name, price, inStock, dom, exp);

            boolean check = FoodDAO.insertFood(food);
            FoodDAO.Notification(check, "Thêm thành công", "Thêm thất bại");
            foodWarehouse.UploadTable();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(foodWarehouse, "Error: " + ex.getMessage());
        }
    }
}
