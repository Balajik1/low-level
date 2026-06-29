package core;

import hardware.CashDispenser;
import service.BankService;
import state.*;
import strategy.AuthStrategy;

public class ATM {
    private  final ATMState idleState;
    private final ATMState waitingForPinState;
    private final ATMState waitingForUpiScanState;
    private final ATMState authenticatedState;

    private ATMState currentState;
    private AuthStrategy authStrategy;

    private final BankService bankService;
    private final CashDispenser cashDispenser;

    public ATM(BankService bankService, CashDispenser cashDispenser){
        idleState=new IdleState(this);
        waitingForPinState=new WaitingForPinState(this);
        waitingForUpiScanState=new WaitingForUpiScanState(this);
        authenticatedState=new AuthenticatedState(this);
        currentState=idleState;
        this.bankService=bankService;
        this.cashDispenser=cashDispenser;
    }

    //pass through methods
    public void InitiateTransaction(AuthStrategy authStrategy){
        currentState.initiateTransaction(authStrategy);
    }
    public void provideCredentials(String credentials){
        currentState.provideCredentials(credentials);
    }
    public void requestCash(int amount){
        currentState.requestCash(amount);
    }

    //getters and setters
    public void setCurrentState(ATMState state){
        this.currentState=state;
    }
    public ATMState getIdleState(){
        return idleState;
    }
    public ATMState getWaitingForPinState(){
        return waitingForPinState;
    }
    public ATMState getWaitingForUpiScanState(){
        return waitingForUpiScanState;
    }
    public ATMState getAuthenticatedState(){
        return authenticatedState;
    }
    //while run time also u can change the auth strategy
    public void setAuthStrategy(AuthStrategy authStrategy){
        this.authStrategy=authStrategy;
    }
    public AuthStrategy getAuthStrategy(){
        return authStrategy;
    }
    public BankService getBankService(){
        return bankService;
    }

    public CashDispenser getCashDispenser() {
        return cashDispenser;
    }
}
