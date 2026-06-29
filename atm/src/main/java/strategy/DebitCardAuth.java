package strategy;

import core.ATM;
import entity.BankAccount;
import entity.Card;
import service.BankService;
import state.ATMState;

public class DebitCardAuth implements  AuthStrategy{
    private final BankService bankService;
    private final Card card;
    private BankAccount verifiedBankAccount;
    public DebitCardAuth(BankService bankService, Card card){
        this.bankService=bankService;
        this.card=card;
    }

    @Override
    public boolean authenticate(String pin) {
        if(bankService.verifyCardPin(card.cardNumber,pin)){
            verifiedBankAccount=bankService.getBankAccount(card.cardNumber);
        }
        return false;
    }

    @Override
    public BankAccount getBankAccount() {
        return null;
    }

    @Override
    public ATMState getNextState(ATM atm) {
        System.out.println("Card Inserted. Please enter your PIN");
        return atm.getWaitingForPinState();
    }

    @Override
    public String getAuthMethodName() {
        return "Debit Card";
    }
}
