package state;

import core.ATM;
import strategy.AuthStrategy;

public class WaitingForUpiScanState implements ATMState{
    private final ATM atm;
    public WaitingForUpiScanState(ATM atm){
        this.atm=atm;
    }
    @Override
    public void initiateTransaction(AuthStrategy authStrategy) {
        System.out.println("Session Already Active.");
    }

    @Override
    public void provideCredentials(String upiId) {
        if(atm.getAuthStrategy().authenticate(upiId)){
            System.out.println("UPI ID Accepted");
            atm.setCurrentState(atm.getAuthenticatedState());
        }else{
            System.out.println("Invalid UPI ID");
            endSession();
        }
    }

    @Override
    public void requestCash(int amount) {

    }

    @Override
    public void endSession() {

    }
}
