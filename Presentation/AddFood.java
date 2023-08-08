package CuoiKy.Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import CuoiKy.Domain.Model.ConstructorFood;
import CuoiKy.Presistence.FoodDAO;

public class AddFood implements ActionListener {
    private FoodWarehouse Food;

    public AddFood(FoodWarehouse Food) {
        this.Food = Food;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int id = Integer.parseInt(Food.getIdTextField().getText());
            String name = Food.getNameTextField().getText();
            double price = Double.parseDouble(Food.getPriceTextField().getText());
            int inStock = Integer.parseInt(Food.getInStockTextField().getText());
            Date dateOfManu = Food.getDateOfManuChooser().getDate();
            Date dateOfExp = Food.getDateOfExpChooser().getDate();

            ConstructorFood food = new ConstructorFood(id, name, price, inStock, dateOfManu, dateOfExp);

            boolean Check = FoodDAO.insertFood(food);
            FoodDAO.Notification(Check, "Thêm thành công", "Thêm thất bại");

            Food.UploadTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Food, "Error: " + ex.getMessage());
        }
    }

}
