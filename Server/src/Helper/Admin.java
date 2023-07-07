/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

/**
 *
 * @author walid
 */
import AirLineCorba.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import AirLineCorba.Vol;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Admin {
    Connection con= ConnectionDB.Connectiondb();
    PreparedStatement pst=null;
    ResultSet rst= null;
    Statement st=null;
    String sql="";
    int lengthClient=0;
    int lengthVol=0;
    public Admin(){
          try {
            sql ="SELECT COUNT(*) FROM user where role='client'";
            pst = con.prepareStatement(sql);
            rst = pst.executeQuery();
            while(rst.next()) {
                lengthClient = rst.getInt("count(*)");
            }
            sql ="SELECT COUNT(*) FROM Vol";
            pst = con.prepareStatement(sql);
            rst = pst.executeQuery();
            while(rst.next()) {
                lengthVol = rst.getInt("count(*)");
            }
            System.out.println("length client is "+lengthClient);
            System.out.println("length vol is "+lengthVol);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                rst.close();
                                st.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public  Client[] getAllClients() {
        Client[] tool = new Client[lengthClient]; 
        try {
             int i=0;
             st=con.createStatement();
             sql = "select * from user where role='client'";
             rst=st.executeQuery(sql);
             while(rst.next() & i<lengthClient){
                int userId=rst.getInt("userId");
                String firstName= rst.getString("firstName");
                String lastName= rst.getString("lastName");
                String email = rst.getString("email");              
                String address = rst.getString("address");
                int phoneNumber= rst.getInt("phoneNumber");
                tool[i]= new Client (userId, firstName, lastName, email, address, phoneNumber,"****","client",i);
                i++;
             }
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex.getMessage());
         }finally{
            try {
                pst.close();
                rst.close();
                                st.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return tool;
    }
     public Client getclient(String email) {
       Client tool = null; 
        try {
            
            sql = "select * from User where email=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, email);
            rst = pst.executeQuery();
             while(rst.next()){
                int userId=rst.getInt("userId");
                String firstName= rst.getString("firstName");
                String lastName= rst.getString("lastName");
                String address = rst.getString("address");
                int phoneNumber= rst.getInt("phoneNumber");
                tool= new Client (userId, firstName, lastName, email, address, phoneNumber,"****","client",1);
             }
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex.getMessage());
         }finally{
            try {
                pst.close();
                rst.close();
                                st.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return tool;  
    }
    
    public Vol[] getAllVols() {
        Vol[] tool = new Vol[lengthVol]; 
        try {
             int i=0;
             st=con.createStatement();
             sql = "select * from Vol";
             rst=st.executeQuery(sql);
             while(rst.next() & i<lengthVol){
                int volId=rst.getInt("volId");
                String airport= rst.getString("airport");
                String destination= rst.getString("destination");
                String takeDate = rst.getString("takeDate");              
                double totalTicktite = rst.getDouble("totalTicktite");
                double price= rst.getInt("price");
                tool[i]= new Vol (volId,airport,destination,takeDate,totalTicktite,price);
                i++;
             }
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex.getMessage());
         }finally{
            try {
                pst.close();
                rst.close();
                                st.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return tool;
    }
   
    public boolean postVol(String airport, String destination,String takeDate ,double totalTicktite, double price){
        try {
            sql = "insert into Vol(airport,destination,takeDate,totalTicktite,price) values(?,?,?,?,?)";
            pst=con.prepareStatement(sql);  
            pst.setString(1, airport);
            pst.setString(2, destination);
            pst.setString(3, takeDate);
            pst.setDouble(4, totalTicktite);
            pst.setDouble(5, price);
            pst.execute();
            JOptionPane.showMessageDialog(null, "client ajouter");
          
            
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally{
            try {
                pst.close();
                rst.close();
                                st.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public Vol getvol(int volId) {
       Vol tool = null; 
        try {
            
            sql = "select * from Vol where volId=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, volId);
            rst = pst.executeQuery();
             while(rst.next()){
                String airport= rst.getString("airport");
                String destination= rst.getString("destination");
                String takeDate = rst.getString("takeDate");              
                double totalTicktite = rst.getDouble("totalTicktite");
                double price= rst.getInt("price");
                tool= new Vol (volId,airport,destination,takeDate,totalTicktite,price);
             }
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex.getMessage());
         }finally{
            try {
                pst.close();
                rst.close();
                                st.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return tool;  
    }
}
