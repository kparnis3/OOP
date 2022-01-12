import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.io.*;
import java.util.*;


public class Order
{

    String OrderType, bidask, userBuySell, userSell, crypto, User, OrderID;
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
        sortOrderBook();
    }

    Order(String OrderID, String OrderType, double quantity, String userBuySell, String bidask, String crypto, double amount, String User)
    {   this.OrderID = OrderID;
        this.OrderType = OrderType;
        this.quantity = quantity;
        this.bidask = bidask;
        this.userBuySell = userBuySell;
        this.amount = amount;
        this.crypto = crypto;
        this.User = User;
    }



    //If orderbook doesn't exist create, if exists append new order to file
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

        } 
        catch(IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try
        {
            FileWriter myWriter = new FileWriter("orderbook.txt", true);
            int ID = GenerateID();
            myWriter.write(ID + " " + OrderType + " " + quantity + " " + userBuySell + " " + bidask + " " + crypto + " " + amount + " " + User + "\n");
            myWriter.close();
            System.out.println("Order has been added to the OrderBook");

        } 
        catch(IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }

    public int GenerateID()
    {
        Random rand = new Random();
        int max = 10000;
        int min = 0;
        return rand.nextInt(max - min) + min;
    } 

    //sorts the OrderBook according to price
    public static void sortOrderBook()
    {
        Path file = Paths.get("orderbook.txt");
        try(BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8))
        {
            ArrayList<Order> Orders = new ArrayList<Order>();
            String currentLine = reader.readLine();
            while(currentLine!=null)
            {
            
            String[] info = currentLine.split(" ");
            
            String ID = info[0];
            String OrderType = info[1];
            double quantity = Double.valueOf(info[2]);
            String bidask = info[3];
            String userBuySell = info[4];
            double amount = Double.valueOf(info[6]);
            String crypto = info[5];
            String User = info[7];

            Orders.add(new Order(ID,OrderType,quantity,bidask,userBuySell,crypto,amount,User));
            currentLine = reader.readLine();
            }
            Collections.sort(Orders, new sorting());

            FileWriter w = new FileWriter("orderbook.txt"); //clear text file
            w.close();

            try
            {

            FileWriter writer = new FileWriter("orderbook.txt", true);
               for(Order order: Orders)
               {
                 writer.write(order.OrderID + " " + order.OrderType + " " + order.quantity + " " + order.userBuySell + " " + order.bidask + " " + order.crypto + " " + order.amount + " " + order.User + "\n");
               }
               reader.close();
               writer.close();
            }  

            catch (IOException x) 
            {   
                System.err.println(x);
            }
            
        }    
        catch (IOException x) {
            System.err.println(x);
        }        
    }

}