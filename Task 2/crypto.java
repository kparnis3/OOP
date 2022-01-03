import java.util.LinkedHashMap;
public class crypto
{
   private LinkedHashMap<String, Double> hmap = new LinkedHashMap<String, Double>();   

   protected String ChosenCryptoName;
   protected double ChosenCryptoValue; 

   public String getCryptoName()
   {
      return ChosenCryptoName;
   }

   public double getCryptoValue()
   {
      return ChosenCryptoValue;
   }

   public void setCryptoName(String newCryptoName)
   {
      this.ChosenCryptoName = newCryptoName;
   }

   public void setCryptoValue(double newCryptoValue)
   {
      this.ChosenCryptoValue = newCryptoValue;
   }

   public crypto() //base crypto available
   {
      hmap.put("Bitcoin", 300.0);
      hmap.put("Ethereum", 400.0);
      hmap.put("Dogecoin", 200.0);
      hmap.put("Cardano", 100.0);
   }

   public LinkedHashMap<String, Double> getCrypto()
   {
      return this.hmap;
   }

   public void addNewCrypto(String Cr, double val)
   {
      hmap.put(Cr, val);
   }

   public void removeCrypto(String Cr)
   {
      hmap.remove(Cr);
   }

 
}