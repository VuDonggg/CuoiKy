package CuoiKy.Domain.Model;

import java.util.Date;

public class ConstructorCrockery extends ConstructorTemp {
    private String inforProducer;
    private Date dayAdd;

    public ConstructorCrockery(int id, String name, double price, int instock, String inforProducer, Date dayAdd) {
        super(id, name, price, instock);
        this.inforProducer = inforProducer;
        this.dayAdd = dayAdd;
    }

    public String getInforProducer() {
        return inforProducer;
    }

    public void setInforProducer(String inforProducer) {
        this.inforProducer = inforProducer;
    }

    public Date getDayAdd() {
        return dayAdd;
    }

    public void setDayAdd(Date dayAdd) {
        this.dayAdd = dayAdd;
    }

}
