package CuoiKy.Domain.Model;

public class ConstructorRefrigeration extends ConstructorTemp {
    private double wattage;
    private String guarantee;

    public ConstructorRefrigeration(int id, String name, double price, int instock, double wattage, String guarantee) {
        super(id, name, price, instock);
        this.wattage = wattage;
        this.guarantee = guarantee;
    }

    /**
     * @return double return the wattage
     */
    public double getWattage() {
        return wattage;
    }

    /**
     * @param wattage the wattage to set
     */
    public void setWattage(double wattage) {
        this.wattage = wattage;
    }

    /**
     * @return String return the guarantee
     */
    public String getGuarantee() {
        return guarantee;
    }

    /**
     * @param guarantee the guarantee to set
     */
    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

}
