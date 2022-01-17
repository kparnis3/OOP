
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

    public void confirmMarket(market o, crypto crc, user tr)
    {
        Scanner rr = new Scanner(System.in);
        System.out.println(" ");
        System.out.println("Order: ");
        System.out.println("Quantity: " + o.getQuantity()+ "\n Buy/Sell: "+ o.getbuySell() + "\n Crypto: " + crc.ChosenCryptoName + "\n Cost: " + o.getQuantity() * crc.ChosenCryptoValue + " €"); //save as market 
        System.out.println("Proceed with order? (Enter: yes or no) ");
                
        String choice2 = rr.nextLine();
        switch(choice2)
        {
            case "yes": 
                        
                if(o.getbuySell().equals("Buy"))
                {
                    if(tr.retrieveBalance() >= o.getQuantity()*crc.ChosenCryptoValue)
                    {
                        tr.setBalance(-o.getQuantity()*crc.ChosenCryptoValue);
                        Order or = new Order("market", o.getQuantity(), o.getbuySell(), "null" , crc.ChosenCryptoName, o.getQuantity()*crc.ChosenCryptoValue, tr.retrieveUsername());
                    }
                    else
                    {
                        System.out.println("Not enough money");
                    }
                
                }
                else //sell
                {
                     Order or = new Order("market", o.getQuantity(),  o.getbuySell(), "null" , crc.ChosenCryptoName, o.getQuantity()*crc.ChosenCryptoValue, tr.retrieveUsername());
                }

                break;

            case "no": //no
                System.out.println("Order declined");
                break;

            default:
                return;    
        }        
    }
}
