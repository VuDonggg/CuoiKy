package CuoiKy.Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import CuoiKy.Presistence.FoodDAO;

public class FindFood implements ActionListener {
    private FoodWarehouse foodWarehouse;

    public FindFood(FoodWarehouse foodWarehouse) {
        this.foodWarehouse = foodWarehouse;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String searchText = JOptionPane.showInputDialog(foodWarehouse, "Nhập vào tên để tìm kiếm:");
        DefaultTableModel model = FoodDAO.findFood(searchText);
        foodWarehouse.getTable().setModel(model);
    }
}
