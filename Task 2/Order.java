import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Order
{

    String OrderType, bidask, userBuySell, userSell, crypto, User;
    double amount, quantity;

    Order(String OrderType, double quantity, String userBuySell, String bidask, String crypto, double amount, String User)
    {
        this.OrderType = OrderType;
        this.quantity = quantity;
        this.bidask = bidask;
        this.userBuySell = userBuySell;
        this.amount = amount;
        this.crypto = crypto;
        this.User = User;
        setOrder();
    }

    public void setOrder()
    {
        try
        {
            File myObj = new File("orderbook.txt");
            if(myObj.createNewFile())
            {
                System.out.println("OrderBook created: " +myObj.getName());
            }
            else
            {
                System.out.println("OrderBook Found");
            }

        } catch(IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try
        {
            FileWriter myWriter = new FileWriter("orderbook.txt", true);
            myWriter.write(OrderType + " " + quantity + " " + userBuySell + " " + bidask + " " + crypto + " " + amount + " " + User + "\n");
            myWriter.close();
            System.out.println("Order has been added to the OrderBook");

        } catch(IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }
}