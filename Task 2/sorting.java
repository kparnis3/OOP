import java.util.Comparator;


public class sorting implements Comparator<Order> 
{
    
    public int compare(Order price1, Order price2)
    {
        if(price2.amount > price1.amount) return -1;
        if(price1.amount > price2.amount) return 1;
        return 0;
    }

}
