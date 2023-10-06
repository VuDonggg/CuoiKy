package CuoiKy.Presentation;

import javax.swing.*;

public class WareHouseView extends JFrame {
    public WareHouseView() {
        setTitle("Warehouse Management");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Crockery Warehouse", new CrockeryWarehouse());
        tabbedPane.addTab("Food Warehouse", new FoodWarehouse());
        tabbedPane.addTab("Refrigeration Warehouse", new RefrigerationWarehouse());

        add(tabbedPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WareHouseView());
        // Liem dang co gang de khong rot mon nhaaa
    }
}
