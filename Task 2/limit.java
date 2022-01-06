import java.util.Scanner;

public class limit extends Ordertype
{   
    protected String bidAsk;

    public void setbidAsk(String ba)
    {
        this.bidAsk = ba;
    }

    public String getbidAsk()
    {
        return bidAsk;
    }

    public int AskBuySell()
    {
        Scanner ss = new Scanner(System.in);
        System.out.println("1. Buy");
        System.out.println("2. Sell");
        return ss.nextInt();        
    }

    public double returnQuantity()
    {
        Scanner ss = new Scanner(System.in);
        System.out.println("Please enter quantity");
        return ss.nextInt();
        // return 1;        
    }
    
    public int AskBidOrAskPrice()
    {
        Scanner ss = new Scanner(System.in);
        System.out.println("1. Bid");
        System.out.println("2. Ask Price");
        return ss.nextInt();        
    }
}
