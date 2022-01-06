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
                if(user_amount <= 90000.0 || 0 >= user_amount)
                {
                    Trader.depositMoney(user_amount);
                }
                else
                {
                    System.out.println("Not a valid amount!");
                } 
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

    public static void OrderSubMenu(user tr)
    {
        Scanner rr = new Scanner(System.in);
        System.out.println("Please pick what you would like to do:\n");
        System.out.println("1) Limit Order");
        System.out.println("2) Market Order");
        System.out.println("3) Go Back");
              

        int user_choice = -2;
        user_choice = rr.nextInt();
        switch(user_choice)
        {   
            case 1:
                limit l = new limit(); 
                crypto cr = new crypto();  
                CryptoMenu(cr);
                int buySell = l.AskBuySell(); 

                System.out.println("Pick Buy or Sell");
                if (buySell==1)
                {
                    l.setbuySell("Buy");                   
                }
                else if(buySell==2) 
                {
                    l.setbuySell("Sell");                
                }
                else
                {   
                    System.out.println("Invalid Choice");
                    return;
                }              

                switch(l.AskBidOrAskPrice())
                {
                    case 1: //Bid
                        l.setbidAsk("Bid");
                        break;
                    case 2: //Ask Price
                        l.setbidAsk("Ask");
                        break;
                    default:
                        return;               
                }
                
                l.setQuantity(l.returnQuantity());
                System.out.println(" ");
                System.out.println("Order: ");
                System.out.println(" Bid/Ask: "+ l.getbidAsk() + "\n Quantity: " + l.getQuantity()+ "\n Buy/Sell: "+ l.getbuySell() + "\n Crypto: " + cr.ChosenCryptoName + "\n Cost: " + l.getQuantity()*cr.ChosenCryptoValue + " €"); //save as market 
                System.out.println("Proceed with order? (Enter: yes or no) "); //yes -> orderbook (take from account validation)
                rr.nextLine(); //catch newline
                String choice = rr.nextLine();
                
                switch(choice)
                {
                    case "yes": 
                        
                        if(l.getbuySell().equals("Buy"))
                        {
                            if(tr.retrieveBalance() >= l.getQuantity()*cr.ChosenCryptoValue)
                            {
                                tr.depositMoney(-l.getQuantity()*cr.ChosenCryptoValue);
                                Order or = new Order("limit", l.getQuantity(), l.getbuySell(), l.getbidAsk(),cr.ChosenCryptoName, l.getQuantity()*cr.ChosenCryptoValue, tr.retrieveUsername() );

                            }
                            else
                            {
                                System.out.println("Not enough money");
                            }
                            //place order
                        }
                        else //sell
                        {
                                Order or = new Order("limit", l.getQuantity(),  l.getbuySell(), l.getbidAsk(), cr.ChosenCryptoName, l.getQuantity()*cr.ChosenCryptoValue, tr.retrieveUsername() );
                        }

                        break;
                    case "no": //no
                        break;
                    default:
                        return;               
                }
                //OrderBook b = new ORDERBOOK()          
                break;

            case 2:
                market o = new market();     
                crypto crc = new crypto();  
                CryptoMenu(crc); 
                System.out.println("Pick buy or sell");      
                int buySellInt = o.AskBuySell(); //buySell  = 1- Buy //2=Sell
                if (buySellInt==1) {
                    o.setbuySell("Buy");                   
                }
                else if(buySellInt==2) 
                {
                    o.setbuySell("Sell");               
                }
                else
                {
                    return;
                }
                o.setQuantity(o.returnQuantity()); 
                System.out.println(" ");
                System.out.println("Order: ");
                System.out.println(" Quantity: " + o.getQuantity()+ "\n Buy/Sell: "+ o.getbuySell() + "\n Crypto: " + crc.ChosenCryptoName + "\n Cost: " + o.getQuantity() * crc.ChosenCryptoValue + " €"); //save as market 
                System.out.println("Proceed with order? "); //yes -> orderbook (take from account validation)
                
                rr.nextLine();
                String choice2 = rr.nextLine();
                switch(choice2)
                {
                    case "yes": 
                        
                        if(o.getbuySell().equals("Buy"))
                        {
                            if(tr.retrieveBalance() >= o.getQuantity()*crc.ChosenCryptoValue)
                            {
                                tr.depositMoney(-o.getQuantity()*crc.ChosenCryptoValue);
                                Order or = new Order("market", o.getQuantity(), o.getbuySell(), "null" , crc.ChosenCryptoName, o.getQuantity()*crc.ChosenCryptoValue, tr.retrieveUsername() );

                            }
                            else
                            {
                                System.out.println("Not enough money");
                            }
                            //place order
                        }
                        else //sell
                        {
                            Order or = new Order("market", o.getQuantity(),  o.getbuySell(), "null" , crc.ChosenCryptoName, o.getQuantity()*crc.ChosenCryptoValue, tr.retrieveUsername() );
                        }

                        break;
                    case "no": //no
                        System.out.println("Order declined");
                        break;
                    default:
                        return;    
                }               
                break;
            case 3:
                System.out.println(" ");
                //cc.close();
                return;
            default:
                System.out.println("Invalid choice");
                OrderSubMenu(tr);
                break;
        }

       // cc.close();
    }

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
            //login system
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
             else
             {
                 System.out.println("Username not registered!");
                 basemenu(adm);
                 break;
             }
            }
            break;
        case 3:
            System.exit(0);
        default:
            System.out.println("Invalid Choice");   
            basemenu(adm);    
    }
    sc.close();
    }

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