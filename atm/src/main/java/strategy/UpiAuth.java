package strategy;

import core.ATM;
import entity.BankAccount;
import service.BankService;
import state.ATMState;

public class UpiAuth implements AuthStrategy{
    BankService bankService;
    BankAccount verifiedBankAccount;
    public UpiAuth(BankService service){
        this.bankService=service;
    }
    @Override
    public boolean authenticate(String upiId) {
        if(bankService.verifyUPIScan(upiId)){
            verifiedBankAccount=bankService.getBankAccountByUpiId(upiId);
        }
        return false;
    }

    @Override
    public BankAccount getBankAccount() {
        return verifiedBankAccount;
    }

    @Override
    public ATMState getNextState(ATM atm) {
        System.out.println("Please scan the QR code using your UPI app");
        return atm.getWaitingForUpiScanState();
    }

    @Override
    public String getAuthMethodName() {
        return "UPI";
    }
}
