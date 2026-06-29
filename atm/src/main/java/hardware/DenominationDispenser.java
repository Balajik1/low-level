package hardware;

import java.util.Map;

public class DenominationDispenser {
    private DenominationDispenser nextDispenser;
    private int denomination;
    private int count;
    public DenominationDispenser(int denomination, int count) {
        this.denomination = denomination;
        this.count = count;
    }
    public void setNextDispenser(DenominationDispenser nextDispenser) {
        this.nextDispenser = nextDispenser;
    }
    //Phase 1 to check is it possible / validate
    public boolean canDispense(int requestedAmount, Map<Integer,Integer> proposal) {
        int requiredDenomination=requestedAmount/denomination;
        int minRequired=Math.min(requiredDenomination,count);

        if(minRequired>0) {
            proposal.put(denomination, minRequired);
            requestedAmount-=denomination*minRequired;
        }
        if(requestedAmount==0) return true;
        if(nextDispenser!=null) return nextDispenser.canDispense(requestedAmount,proposal);
        return false;
    }
    //Phase 2 to dispense
    public void executeDispense(Map<Integer,Integer> proposal) {
       if(proposal.containsKey(denomination)){
            int notesToDrop=proposal.get(denomination);
            this.count-=notesToDrop;
            System.out.println(" -> Dropping "+notesToDrop+" X Rupees "+denomination);
       }

       if(nextDispenser!=null){
           nextDispenser.executeDispense(proposal);
       }
    }
}
