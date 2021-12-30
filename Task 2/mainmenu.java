import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class mainmenu
{
    private static ArrayList<user> UserData = new ArrayList<user>();
    // private static ArrayList<administrator> AdminData = new ArrayList<administrator>(); 
    public static void main(String[] args)
    {
        administrator newadmin = new administrator("Kian", "Parnis", "107601L", "kian.parnis.20@um.edu.mt"); //creating an admin
        basemenu(newadmin);
    }

    public static void system(user Trader)
    {
        Scanner cc = new Scanner(System.in);
        System.out.println("You can now use the system " +Trader.retrieveName()+ " " +Trader.retrieveSurname()+",");
        System.out.println("Please pick what you would like to do:\n");
        System.out.println("5) Log Out");
        int user_choice = -2;
        user_choice = cc.nextInt();
        switch(user_choice)
        {   
            case 5:
                System.out.println("Fair well!");
                cc.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice");
                break;
        }

        cc.close();
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