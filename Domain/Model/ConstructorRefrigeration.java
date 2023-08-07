package CuoiKy.Domain.Model;

public class ConstructorRefrigeration extends ConstructorTemp {
    private double wattage;
    private String guarantee;

    public ConstructorRefrigeration(int id, String name, double price, int instock, double wattage, String guarantee) {
        super(id, name, price, instock);
        this.wattage = wattage;
        this.guarantee = guarantee;
    }

    public double getWattage() {
        return wattage;
    }

    public void setWattage(double wattage) {
        this.wattage = wattage;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

}
