package CuoiKy.Domain.Model;

public class ConstructorTemp {
    private int id;
    private String name;
    private double price;
    private int instock;

    public ConstructorTemp(int id, String name, double price, int instock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.instock = instock;

    }

    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return double return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return int return the instock
     */
    public int getInstock() {
        return instock;
    }

    /**
     * @param instock the instock to set
     */
    public void setInstock(int instock) {
        this.instock = instock;
    }

}
