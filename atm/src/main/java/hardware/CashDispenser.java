package hardware;

import java.util.LinkedHashMap;
import java.util.Map;

public class CashDispenser {
    private DenominationDispenser headD;

    public CashDispenser() {
        headD=new DenominationDispenser(500,10);
        DenominationDispenser d100=new DenominationDispenser(100,10);
        DenominationDispenser d50=new DenominationDispenser(50,10);
        DenominationDispenser d20=new DenominationDispenser(20,10);
        DenominationDispenser d10=new DenominationDispenser(10,10);

        headD.setNextDispenser(d100);
        d100.setNextDispenser(d50);
        d50.setNextDispenser(d20);
        d20.setNextDispenser(d10);
    }
    public boolean dispenseCash(int requestedAmount){
        if (requestedAmount%10!=0) {
            System.out.println("HardWare Error : Amount must be multiple of 10");
            return  false;
        }

        //step1
        Map<Integer,Integer> proposal=new LinkedHashMap<>();
        if(!headD.canDispense(requestedAmount,proposal)){
            System.out.println("Hardware Error: Exact Denominations unavailable for Rupee + "+requestedAmount);
            return false;
        }
        //step 2
        System.out.println("--- Dispensing Physical Cash ---");
        headD.executeDispense(proposal);
        System.out.println("---------------------------------");
        return  true;
    }

}
