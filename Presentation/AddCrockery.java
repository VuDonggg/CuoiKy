package CuoiKy.Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CuoiKy.Domain.Model.ConstructorCrockery;
import CuoiKy.Presistence.CrockeryDAO;

public class AddCrockery implements ActionListener {
    private CrockeryWarehouse crockeryWarehouse;

    public AddCrockery(CrockeryWarehouse crockeryWarehouse) {
        this.crockeryWarehouse = crockeryWarehouse;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int id = Integer.parseInt(crockeryWarehouse.getIdTextField().getText());
            String name = crockeryWarehouse.getNameTextField().getText();
            double price = Double.parseDouble(crockeryWarehouse.getPriceTextField().getText());
            int inStock = Integer.parseInt(crockeryWarehouse.getInStockTextField().getText());
            String inforProducer = crockeryWarehouse.getInforproduTextField().getText();
            java.util.Date utilDate = crockeryWarehouse.getDateChooser().getDate();
            java.sql.Date dayAdd = new java.sql.Date(utilDate.getTime());

            ConstructorCrockery crockery = new ConstructorCrockery(id, name, price, inStock, inforProducer, dayAdd);

            boolean Check = CrockeryDAO.insertCrockery(crockery);
            CrockeryDAO.Notification(Check, "Thêm thành công", "Thêm thất bại");
            crockeryWarehouse.UploadTabel();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(crockeryWarehouse, "Error: " + ex.getMessage());
        }
    }
}
