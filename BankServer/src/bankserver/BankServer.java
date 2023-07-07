/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankserver;

/**
 *
 * @author walid
 */

import Services.BankSer;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import java.util.Hashtable;
import javax.naming.*;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class BankServer {
    static ORB orb;
    static POA rootPOA;
    static Context context;
    static  org.omg.CORBA.Object ref;
    
    public static void main(String[] args) {
        try {
            Hashtable htEnv = new Hashtable();
            
            htEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
            htEnv.put(Context.PROVIDER_URL, "iiop://localhost:1050");
            
            orb = ORB.init(args,null);
            rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();
            
            BankSer bank = new BankSer();

            context = new InitialContext(htEnv);
            ref =rootPOA.servant_to_reference(bank);
            
            System.out.println(ref);
            
            context.rebind("Bank",ref);                                      
            System.out.println("Server working");
            
            orb.run();
         } catch (InvalidName | NamingException | AdapterInactive    e) {
            throw new RuntimeException(e);
        } catch (ServantNotActive | WrongPolicy ex) {
            System.out.println(ex.getMessage());
        }
      
    }
}

