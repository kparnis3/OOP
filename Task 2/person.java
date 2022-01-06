import java.io.Serializable;
import java.util.Scanner;

public abstract class person implements Serializable
{
    String name, surname, idcard, email;
    person(String name, String surname, String idcard, String email)
    {
            this.name = name;
            this.surname = surname;
            this.idcard = idcard;
            this.email = email;
    }
    
    protected String userName;
    protected String password;      
    protected double balance;

    public void depositMoney(double amount)
    {
        balance+=amount;
    }

    public double retrieveBalance()
    {
        return balance;
    }


    public String retrieveUsername()
    {
        return userName;
    }

    public String retrievePassword()
    {
        return password;
    }

    public String retrieveName()
    {
        return name;
    }

    public String retrieveSurname()
    {
        return surname;
    }

    public void register()
    {   
        balance = 0; //starting account should have 0 balance
        Scanner ss = new Scanner(System.in);
        System.out.println("Please enter your username");
        userName = ss.nextLine();
        
        String firstcheck, secondcheck;
        System.out.println("Please enter your password");
        
        firstcheck = ss.nextLine();
        System.out.println("Enter your password again to confirm");
        secondcheck = ss.nextLine();
        
 
        if (firstcheck.equals(secondcheck))
        {
            password = firstcheck;
        }
        else
        {
            System.out.println("Passwords dont match please try again.");
            register();
        }

       // ss.close(); 
    }
}
