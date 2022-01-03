import java.io.Serializable;
//import java.util.Scanner;

public abstract class Ordertype implements Serializable
{
    protected double quantity;

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

    //Enter quantity in main menu
}
