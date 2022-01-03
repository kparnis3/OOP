public class Order
{

    String crypto, type, userBuy, userSell;
    float amount;

Order(String crypto, String type, String userBuy, String userSell, float amount)
{
    this.crypto = crypto;
    this.type = type;
    this.userBuy = userBuy;
    this.userSell = userSell;
    this.amount = amount;
}


}