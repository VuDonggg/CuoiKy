package CuoiKy.Presentation;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

import CuoiKy.Presistence.RefrigerationDAO;

public class FindRefri implements ActionListener {
    private RefrigerationWarehouse refrigerationWarehouse;

    public FindRefri(RefrigerationWarehouse refrigerationWarehouse) {
        this.refrigerationWarehouse = refrigerationWarehouse;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String searchText = JOptionPane.showInputDialog(refrigerationWarehouse, "Enter the name to search for:");
        DefaultTableModel model = RefrigerationDAO.findRefrigeration(searchText);
        refrigerationWarehouse.getTable().setModel(model);
    }
}
