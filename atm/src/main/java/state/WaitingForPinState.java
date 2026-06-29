package state;

import core.ATM;
import strategy.AuthStrategy;

public class WaitingForPinState implements  ATMState{
    private final ATM atm;
    public WaitingForPinState(ATM atm){
        this.atm=atm;
    }
    @Override
    public void initiateTransaction(AuthStrategy authStrategy) {
        System.out.println("Session Already Active.");
    }

    @Override
    public void provideCredentials(String pin) {
        if(atm.getAuthStrategy().authenticate(pin)){
            System.out.println("PIN Accepted");
            atm.setCurrentState(atm.getAuthenticatedState());
        }else{
            System.out.println("Invalid PIN");
            endSession();
        }
    }

    @Override
    public void requestCash(int amount) {
        System.out.println("First enter the pin");
    }

    @Override
    public void endSession() {
        System.out.println("Ejecting Card... Returning to Idle.");
        atm.setAuthStrategy(null);
        atm.setCurrentState(atm.getIdleState());
    }
}
