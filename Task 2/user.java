public class user extends person
{
  public user(String name, String surname, String idcard, String email)
  {
      super(name,surname,idcard,email);
      register();
  }
  
  boolean approved = false;

  public boolean retrieveapproval()
  {
      return approved;
  }

}
