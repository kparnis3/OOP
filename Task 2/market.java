
import java.util.Scanner;
public class market extends Ordertype
{
    public double returnQuantity()
    {
        Scanner ss = new Scanner(System.in);
        System.out.println("Please enter quantity");
        return ss.nextInt();
      
    }

    public int AskBuySell()
    {
        Scanner tt = new Scanner(System.in);
        System.out.println("1. Buy");
        System.out.println("2. Sell");
        return tt.nextInt();
        
    }
}
