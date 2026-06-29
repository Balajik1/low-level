package service;

import entity.BankAccount;

import java.util.HashMap;
import java.util.Map;

public class BankService {
    private final Map<String, BankAccount> accounts=new HashMap<>();

    //Mapping for different Authentication types
    private final Map<String,String> cardToAccountMap=new HashMap<>();
    private final Map<String,String> cardPins=new HashMap<>();

    private final Map<String,String> upiToAccountMap=new HashMap<>();

    public BankService(){
        //Mock Database Initialization
        accounts.put("ACC-1001",new BankAccount("ACC-1001",5000.00));

        //link Card to Account
        cardToAccountMap.put("CARD-123","ACC-1001");
        cardPins.put("CARD-123","1234");

        upiToAccountMap.put("user@upi","ACC-1001");
    }

    public boolean verifyCardPin(String cardNumber,String pin){
        String expectedPin=cardPins.get(cardNumber);
        return expectedPin!=null && expectedPin.equals(pin);
    }
    public boolean verifyUPIScan(String upiId){
        //In reality , this pings UPI gateway to verify dynamic QR scan
        //for our simulation , if the ID exists, its valid
        return upiToAccountMap.containsKey(upiId);
    }
    public BankAccount getBankAccount(String cardNumber){
        String accountNumber= cardToAccountMap.get(cardNumber);
        return accounts.get(accountNumber);
    }
    public BankAccount getBankAccountByUpiId(String pin){
        String accountNumber= cardToAccountMap.get(pin);
        return accounts.get(accountNumber);
    }
}
