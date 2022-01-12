import java.io.Serializable;
//import java.util.Scanner;

public abstract class Ordertype implements Serializable
{
    protected double quantity;
    protected String buySell;

    public void setbuySell(String bs)
    {
        this.buySell = bs;
    }

    public String getbuySell()
    {
        return buySell;
    }

    public void setQuantity(Double Q)
    {
        this.quantity = Q;
    }

    public double getQuantity()
    {
        return quantity;
    }

    public abstract int AskBuySell();

    public abstract double returnQuantity();

}
