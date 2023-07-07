package Api;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author walid
 */
public class Bankapi {
      private static  org.omg.CORBA.Object ref;    
      public static corbaBank.Bank stub;
      public Bankapi() {
        try {
            Hashtable htEnv = new Hashtable();
            htEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
            htEnv.put(Context.PROVIDER_URL, "iiop://localhost:1050");
            Context context = new InitialContext(htEnv);
            this.ref = (org.omg.CORBA.Object) context.lookup("Bank");
            this.stub =corbaBank.BankHelper.narrow(ref);
            System.out.println("server connected to the Bank server,");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
