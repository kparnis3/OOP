import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

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

                break;
            case 2:
                OrderSubMenu();
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
                System.out.println("Balance:");
                System.out.println(Trader.retrieveBalance());
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
        ViewCrypto(false);
        Scanner ss = new Scanner(System.in);
        System.out.println("Please choose crypto currency:");

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

    public static void OrderSubMenu()
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
                int buySell = l.AskBuySell(); //buySell  = 1- Buy //2=Sell
                String buySellStr =""; 

                if (buySell==1)
                {
                    buySellStr="Buy";                    
                }
                else if(buySell==2) 
                {
                    buySellStr="Sell";                    
                }
                else
                {   
                    System.out.println("Invalid Choice");
                    return;
                }              
                String askBidStr =""; 

                switch(l.AskBidOrAskPrice())
                {
                    case 1: //Bid
                        askBidStr="Bid";
                        break;
                    case 2: //Ask Price
                        askBidStr="Ask";
                        break;
                    default:
                        return;               
                }
                
                l.setQuantity(l.returnQuantity());
                System.out.println(" ");
                System.out.println("Order: ");
                System.out.println(" Bid/Ask: "+ askBidStr + "\n Quantity: " + l.getQuantity()+ "\n Buy/Sell: "+ buySellStr + "\n Crypto: " + cr.ChosenCryptoName + "\n Cost: " + l.getQuantity()*cr.ChosenCryptoValue + " €"); //save as market 
                System.out.println("Proceed with order? "); //yes -> orderbook (take from account validation)
                
                //OrderBook b = new ORDERBOOK()          
                break;

            case 2:
                market o = new market();     
                crypto crc = new crypto();  
                CryptoMenu(crc);           
                int buySellInt = o.AskBuySell(); //buySell  = 1- Buy //2=Sell
                String buySellMarketStr =""; 
                if (buySellInt==1) {
                    buySellMarketStr="Buy";                    
                }
                else if(buySellInt==2) 
                {
                    buySellMarketStr="Sell";                    
                }
                else
                {
                    return;
                }
                o.setQuantity(o.returnQuantity()); 
                System.out.println(" ");
                System.out.println("Order: ");
                System.out.println(" Quantity: " + o.getQuantity()+ "\n Buy/Sell: "+ buySellMarketStr + "\n Crypto: " + crc.ChosenCryptoName + "\n Cost: " + o.getQuantity() * crc.ChosenCryptoValue + " €"); //save as market 
                System.out.println("Proceed with order? "); //yes -> orderbook (take from account validation)
                               
                break;
            case 3:
                System.out.println(" ");
                //cc.close();
                return;
            default:
                System.out.println("Invalid choice");
                OrderSubMenu();
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