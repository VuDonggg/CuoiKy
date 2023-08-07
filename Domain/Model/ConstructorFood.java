package CuoiKy.Domain.Model;

import java.util.Date;

public class ConstructorFood extends ConstructorTemp {
    private Date DOM;
    private Date EXP;

    public ConstructorFood(int id, String name, double price, int instock, Date DOM, Date EXP) {
        super(id, name, price, instock);
        this.DOM = DOM;
        this.EXP = EXP;
    }

    public Date getDOM() {
        return DOM;
    }

    public void setDOM(Date DOM) {
        this.DOM = DOM;
    }

    public Date getEXP() {
        return EXP;
    }

    public void setEXP(Date EXP) {
        this.EXP = EXP;
    }

}
