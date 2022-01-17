import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
import java.nio.file.*;

public class mainmenu
{
    private static ArrayList<user> UserData = new ArrayList<user>();
    //private static ArrayList<administrator> AdminData = new ArrayList<administrator>(); 
    public static void main(String[] args)
    {
        administrator newadmin = new administrator("Kian", "Parnis", "107601L", "kian.parnis.20@um.edu.mt"); //creating an admin
        basemenu(newadmin);
    }
    
    //Method to validate correctness of crypto
    public static void ViewCrypto(boolean isForViewing)
    { 
        crypto c = new crypto();
        LinkedHashMap<String, Double> hc = c.getCrypto();
        int counter =1;
        
        for(String key: hc.keySet())
        {
            if(isForViewing==true)
            {
                System.out.println("   |  " + key + " price per " +hc.get(key) + " €  |");
            }
            else
            {
                System.out.println(counter + ". " + key + " " +hc.get(key)+ " €");
                counter ++;
            }            
        }
        System.out.println(" ");
            return;
        
    }

    //Method that will retreve each order and print it line by line
    public static void printOrderBook()
    {
        Path file = Paths.get("orderbook.txt");
        try(BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8))
        {
            String line = null;
            while((line = reader.readLine()) != null)
            {
                System.out.println(line);
            }
        }    
        catch (IOException x) {
            System.err.println(x);
        }

    }
    //Main system method which tests the functioanlly of depositing money to the system, placing a market or sell order and bringing up the orderbook
    public static void system(user Trader)
    {
        Scanner cc = new Scanner(System.in);
        
        System.out.println("Please pick what you would like to do:\n");
        System.out.println("1) View OrderBook");
        System.out.println("2) Place Order");
        System.out.println("3) View current Crypto prices");
        System.out.println("4) Deposit money to system");
        System.out.println("5) Check available balance");
        System.out.println("6) Log Out");        
       

        int user_choice = -2;
        user_choice = cc.nextInt();
        switch(user_choice)
        {   
            
            case 1: //view OrderBook
                printOrderBook();
                system(Trader);
                break;
            case 2:
                OrderSubMenu(Trader);
                system(Trader);
                break;
            case 3:
                ViewCrypto(true);
                system(Trader);
                break;
            case 4:
                System.out.println("Enter an amount to deposit: ");
                int user_amount = cc.nextInt();
                Trader.depositMoney(user_amount);
                system(Trader);
                break;
            case 5:
                System.out.println("----------------------");
                System.out.println("Balance:");
                System.out.println(Trader.retrieveBalance());
                System.out.println("----------------------");
                system(Trader);
                break;
            case 6:
                System.out.println("Fair well!");
                cc.close();
                System.exit(0);
    
            default:
                System.out.println("Invalid choice");
                system(Trader);
                break;
        }

        cc.close();
    }

    //Method which tests out the retrieval of avaiable crypto currencies and sets them
    public static void CryptoMenu(crypto cry)
    {
        System.out.println("Please choose crypto currency:");

        ViewCrypto(false);
        Scanner ss = new Scanner(System.in);
        
        LinkedHashMap<String, Double> hc = cry.getCrypto();
        int choice = ss.nextInt();
        if ((choice-1) >= hc.size() && (choice-1) <= hc.size())
        {
            System.out.println("Error: Not a valid choice");
            System.exit(-1);
        }
        cry.setCryptoName((hc.keySet().toArray()[choice-1]).toString());
        cry.setCryptoValue(Double.parseDouble((hc.values().toArray()[choice-1]).toString()));

        return;
    }

    //method which will test the limit and market classes
    public static void OrderSubMenu(user tr)
    {
        Scanner rr = new Scanner(System.in);
        System.out.println("Please pick what you would like to do:\n");
        System.out.println("1) Limit Order");
        System.out.println("2) Market Order");
        System.out.println("3) Cancel Order");
        System.out.println("4) Go Back");
              

        int user_choice = -2;
        user_choice = rr.nextInt();
        switch(user_choice)
        {   
            case 1:
                limit l = new limit(); 
                crypto cr = new crypto();  
                CryptoMenu(cr);

                System.out.println("Pick Buy or Sell");
                switch(l.AskBuySell())
                {
                    case 1:
                        l.setbuySell("Buy");                   
                        break;
                    case 2:
                        l.setbuySell("Sell");
                        break;                
                    default:
                        System.out.println("Invalid Choice");
                        return;
                }
                            
                System.out.println("Would you like to bid or ask?");
                switch(l.AskBidOrAskPrice())
                {
                    case 1: //Bid
                        l.setbidAsk("Bid");
                        break;
                    case 2: //Ask Price
                        l.setbidAsk("Ask");
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        return;               
                }
                
                l.setQuantity(l.returnQuantity());
                l.confirmLimit(l, cr, tr);
                break;

            case 2:
                market mar = new market();     
                crypto crc = new crypto();  
                CryptoMenu(crc); 
                System.out.println("Pick buy or sell");      
                switch(mar.AskBuySell())
                {
                    case 1:
                        mar.setbuySell("Buy");                   
                        break;
                    case 2:
                        mar.setbuySell("Sell");
                        break;                
                    default:
                        System.out.println("Invalid Choice");
                        return;
                }
                
                mar.setQuantity(mar.returnQuantity()); 
                mar.confirmMarket(mar, crc, tr);            
                break;
            case 3:
                CancelOrder Order = new CancelOrder();
                System.out.println("Enter OrderID to cancel");
                rr.nextLine();
                String OrderID = rr.nextLine();
                Order.removeOrder(OrderID, tr);
                break;   
            case 4:
                System.out.println(" ");
                return;
            default:
                System.out.println("Invalid choice");
                OrderSubMenu(tr);
                break;
        }

       // cc.close();
    }
//Base menu created to test out the function and interaction between the administraor class and the user class
    public static void basemenu(administrator adm)
    {
        System.out.println("Please choose:\n1) Register\n2) Login\n3) Exit");
        Scanner sc = new Scanner(System.in);
        int user_choice = -2;
        user_choice = sc.nextInt();
        sc.nextLine();
    
    
    switch(user_choice) 
    {   
        case 1:
            //registering
            String name, surname, idcard, email;
            System.out.println("Enter your name");
            name = sc.nextLine();
            System.out.println("Enter your surname");
            surname = sc.nextLine();
            System.out.println("Enter your idcard number");
            idcard = sc.nextLine();
            System.out.println("Enter your email address");
            email = sc.nextLine();

            user newuser = new user(name, surname, idcard, email);
        
            UserData.add(newuser);
            saveExternally("trader.data", UserData);
            basemenu(adm);

        case 2:
            //simple login system implementation
            getData("trader.data");

            String pass, user;
            System.out.println("Enter username");
            user = sc.nextLine();
            System.out.println("Enter password");
            pass = sc.nextLine();
                     
            for(user Trader: UserData)
            {
            
             if (Trader.retrieveUsername().equals(user))
             {
                while(!Trader.retrievePassword().equals(pass))
                {
                    System.out.println("Password is incorrect, please try again.");
                    pass = sc.nextLine();
                }


                System.out.println("login successfull\n");
                String adm_choice;
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Admin please write 'approve' if you would like the user to use the system.");
                
                adm_choice = sc.nextLine();
                //approval of system is tested here
                if (adm_choice.equals("approve"))
                {  
                    adm.approveRequest(Trader);
                }
                else
                {
                    adm.declineRequest(Trader);
                }   
                System.out.println("-------------------------------------------------------------------------------\n");

                if(Trader.retrieveapproval())
                {
                    System.out.println("You can now use the system " +Trader.retrieveName()+ " " +Trader.retrieveSurname()+",");
                    system(Trader);
                }
                else
                {
                    System.out.println(Trader.retrieveName()+ " " +Trader.retrieveSurname()+" you havent been approved to use the system!");
                    System.exit(0);
                } 
                 

             }
            }

            System.out.println("Username not registered!");
            basemenu(adm);
            break;
             
        case 3:
            System.exit(0);
        default:
            System.out.println("Invalid Choice");   
            basemenu(adm);    
    }
    sc.close();
    }

//Method which will get the users data
    public static void getData(String nameOfFile)
    {
        try
        {
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(nameOfFile));
            UserData = (ArrayList<user>) file.readObject(); 
            file.close();
        }
        catch(FileNotFoundException exp)
        {
            System.out.println("Error: file not found");
            exp.printStackTrace();
        }
        catch(ClassNotFoundException exp)
        {
            System.out.println("Error: class not found");
            exp.printStackTrace();
        }
        catch(IOException exp)
        {
            System.out.println("Error: IOException");
            exp.printStackTrace();
        }

    }

//Method which will save the user's data externally
    public static void saveExternally(String nameOfFile, Object obj)
    {
        try
        {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(nameOfFile));
            file.writeObject(obj);
            file.close();
        }

        catch (FileNotFoundException exp)
        {
            System.out.println("Error: file not found");
            exp.printStackTrace();
        }

        catch (IOException exp)
        {
            System.out.println("Error: IOException");
            exp.printStackTrace();
        }
    }
}