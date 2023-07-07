package Services;



import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
public class Clients {
    private static  org.omg.CORBA.Object ref;    
    public static AirLineCorba.AirLine stub;

    
    public Clients() {
        try {
           Hashtable htEnv = new Hashtable();
           htEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
           htEnv.put(Context.PROVIDER_URL, "iiop://localhost:1050");
           Context context = new InitialContext(htEnv);
           this.ref = (org.omg.CORBA.Object) context.lookup("AirLine");
           this.stub =AirLineCorba.AirLineHelper.narrow(ref);
            System.out.println("Client connected to the server,");
         
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

}
