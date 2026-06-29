package strategy;

import core.ATM;
import entity.BankAccount;
import state.ATMState;

public interface AuthStrategy {
    boolean authenticate(String credentials);
    BankAccount getBankAccount();
    ATMState getNextState(ATM atm);
    String getAuthMethodName();
}
