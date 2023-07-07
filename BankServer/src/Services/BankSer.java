package Services;

import Helper.Boot;

public class BankSer extends corbaBank.BankPOA{

    @Override
    public boolean Transaction(String email, int ccp,double balance) {
        Boot boot = new Boot();
        return boot.transaction(email,ccp,balance);
    }
    
}