package CuoiKy.Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CuoiKy.Presistence.CrockeryDAO;

public class FindCrockery implements ActionListener {
    private CrockeryWarehouse crockeryWarehouse;

    public FindCrockery(CrockeryWarehouse crockeryWarehouse) {
        this.crockeryWarehouse = crockeryWarehouse;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String searchText = JOptionPane.showInputDialog(crockeryWarehouse,
                "Nhập tên sản phẩm để tìm kiếm: ");
        DefaultTableModel model = CrockeryDAO.findCrockery(searchText);
        crockeryWarehouse.getTable().setModel(model);
    }
}
