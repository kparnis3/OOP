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

    public void confirmLimit(limit l, crypto crc, user tr)
    {
        Scanner rr = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("Order: ");
        System.out.println(" Bid/Ask: "+ l.getbidAsk() + "\n Quantity: " + l.getQuantity()+ "\n Buy/Sell: "+ l.getbuySell() + "\n Crypto: " + crc.ChosenCryptoName + "\n Cost: " + l.getQuantity()*crc.ChosenCryptoValue + " €"); //save as market 
        System.out.println("Proceed with order? (Enter: yes or no) "); //yes -> orderbook (take from account validation)
        String choice = rr.nextLine();
                
        switch(choice)
        {
            case "yes": 
                        
            if(l.getbuySell().equals("Buy"))
            {
                if(tr.retrieveBalance() >= l.getQuantity()*crc.ChosenCryptoValue)
                {
                    tr.depositMoney(-l.getQuantity()*crc.ChosenCryptoValue);
                    Order or = new Order("limit", l.getQuantity(), l.getbuySell(), l.getbidAsk(),crc.ChosenCryptoName, l.getQuantity()*crc.ChosenCryptoValue, tr.retrieveUsername());

                }
                else
                {
                    System.out.println("Not enough money");
                }
            }
            else //sell
            {
                Order or = new Order("limit", l.getQuantity(),  l.getbuySell(), l.getbidAsk(), crc.ChosenCryptoName, l.getQuantity()*crc.ChosenCryptoValue, tr.retrieveUsername());
            }

                        break;
                    case "no": //no
                        break;
                    default:
                        return;               
                }       
            
    }
}
