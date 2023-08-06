package CuoiKy.Domain.Model;

public class ConstructorFood extends ConstructorTemp {
    private String DOM;
    private String EXP;

    public ConstructorFood(int id, String name, double price, int instock, String DOM, String EXP) {
        super(id, name, price, instock);
    }

    /**
     * @return String return the DOM
     */
    public String getDOM() {
        return DOM;
    }

    /**
     * @param DOM the DOM to set
     */
    public void setDOM(String DOM) {
        this.DOM = DOM;
    }

    /**
     * @return String return the EXP
     */
    public String getEXP() {
        return EXP;
    }

    /**
     * @param EXP the EXP to set
     */
    public void setEXP(String EXP) {
        this.EXP = EXP;
    }

}
