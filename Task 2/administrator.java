public class administrator extends person
{
    public administrator(String name, String surname, String idcard, String email)
    {
       super(name, surname, idcard, email);
    }

 public void approveRequest(user new_user)//yet to implement
 {
        new_user.approved = true;
        System.out.println(new_user.retrieveUsername()+" has been approved");
 } 

 public void declineRequest(user new_user)
 {      
        new_user.approved = false;
        System.out.println(new_user.retrieveUsername()+" has been declined");
 }

}
