package state;

import core.ATM;
import entity.BankAccount;
import strategy.AuthStrategy;

public class AuthenticatedState implements  ATMState{
    private  final ATM atm;
    public AuthenticatedState(ATM atm){
        this.atm=atm;
    }
    @Override
    public void initiateTransaction(AuthStrategy authStrategy) {
        System.out.println("Already Authenticated ");
    }

    @Override
    public void provideCredentials(String credentials) {
        System.out.println("Already authenticated");
    }

    @Override
    public void requestCash(int amount) {
        BankAccount account=atm.getAuthStrategy().getBankAccount();
        if (amount>account.getBalance()){
            //Attempt digital deduction (Thread safe)
            if (account.withdraw(amount)){
                 //Attempt physical dispense (Hardware safe)
                if (atm.getCashDispenser().dispenseCash(amount)){
                    System.out.println("Transaction Complete.Remaining Balance: "+account.getBalance());
                }else{
                    //RollBack digital Transaction if hardware fails
                    System.out.println("Dispense failed. Rolling back digital transaction for amount: "+amount);

                }
            }else {
                System.out.println("Digital transaction failed");
            }

        }else{
            System.out.println("Insufficient funds in your account");
        }
        endSession();
    }

    @Override
    public void endSession() {
        System.out.println("Ending session securely. Returning to Idle.");
        atm.setAuthStrategy(null);
        atm.setCurrentState(atm.getIdleState());
    }
}
