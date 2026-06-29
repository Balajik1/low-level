package state;

import core.ATM;
import strategy.AuthStrategy;

public class IdleState implements ATMState{
    private final ATM atm;
    public IdleState(ATM atm){
        this.atm=atm;
    }
    @Override
    public void initiateTransaction(AuthStrategy authStrategy) {
        atm.setAuthStrategy(authStrategy);
        System.out.println("Initiating "+authStrategy.getAuthMethodName()+" transaction...");
        atm.setCurrentState(authStrategy.getNextState(atm));
    }

    @Override
    public void provideCredentials(String credentials) {
        System.out.println("please initiate a transaction first");
    }

    @Override
    public void requestCash(int amount) {
        System.out.println("Please initiate transaction first");
    }

    @Override
    public void endSession() {
        System.out.println("No active session");
    }
}
