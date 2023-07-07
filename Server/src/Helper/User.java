/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

/**
 *
 * @author walid
 */
import AirLineCorba.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import AirLineCorba.Connect;
import AirLineCorba.Vol;
import Api.Bankapi;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class User {
    Connection con= ConnectionDB.Connectiondb();
    PreparedStatement pst=null;
    ResultSet rst= null;
    Statement st=null;
    String sql="";
    int lengthBooking=0,lengthVol=0;
    
    public User(){

        try{
            sql ="SELECT COUNT(*) FROM Booking ";
            pst = con.prepareStatement(sql);
            rst = pst.executeQuery();
            while(rst.next()) {
                lengthBooking = rst.getInt("count(*)");
            } 
            sql ="SELECT COUNT(*) FROM Vol";
            pst = con.prepareStatement(sql);
            rst = pst.executeQuery();
            while(rst.next()) {
                lengthVol = rst.getInt("count(*)");
            }
             System.out.println("length client is "+lengthVol);
            System.out.println("length client is "+lengthBooking);
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

    public Connect login(String email, String password){
         sql = "select * from user where email = ?";

        try {
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                rst = pst.executeQuery();
                while(rst.next()) {
                   String pass= rst.getString("password");
                   String userId=String.valueOf(rst.getInt("userId"));
                   String role = rst.getString("role");
                   if(pass == null ? String.valueOf(password) == null : pass.equals(String.valueOf(password))) {
                        return new Connect(true,userId,role);
                    }
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
            JOptionPane.showMessageDialog(null, "eamil or  password invalid");
        return new Connect(false,"","");
    }
    public Connect signup(String firstName, String lastName, String email, String password, String address, int phoneNumber) {
       sql = "insert into user(firstName,lastName,email,address,phoneNumber,role,password) values(?,?,?,?,?,?,?)";

       try {
                pst=con.prepareStatement(sql);               
                pst.setString(1, firstName);
                pst.setString(2, lastName);
                pst.setString(3, email);
                pst.setString(4, address);
                pst.setInt(5, phoneNumber);
                pst.setString(6, "client");
                pst.setString(7, password);
                pst.execute();
                JOptionPane.showMessageDialog(null, "successfully signup");
                return new Connect(true,"","");
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
       return new Connect(false,"","");
  }  
      
    public Booking[] listbooking(int userId ){
        Booking[] tool = new Booking[lengthBooking]; 
        try { 

            
            int i=0;
            sql = "select U.userId,U.firstName,U.lastName,V.* ,B.* from user U,Vol V INNER JOIN Booking B on B.userId = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, userId);
            rst = pst.executeQuery();
            while(rst.next() & i<lengthBooking){
                int bookingId=rst.getInt("bookingId");
                String firstName = rst.getString("firstName");
                String lastName = rst.getString("lastName");
                int volId=rst.getInt("volId");
                String airport= rst.getString("airport");
                String destination= rst.getString("destination");
                String takeDate = rst.getString("takeDate");              
                double totalTicktite = rst.getDouble("totalTicktite");
                double price= rst.getInt("price");
                String bookingDate = rst.getString("bookingDate");
                int cpp = rst.getInt("ccp");
                tool[i]= new Booking(bookingId,volId,userId,firstName,lastName,airport,destination,takeDate,totalTicktite,bookingDate,price,cpp);
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
    
    public boolean postBooking(int userId, int volId , int ccp, String email){
        try {
            double price = 0;
            con.setAutoCommit(false);
            sql = "select * from Vol where volId=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, volId);
            rst = pst.executeQuery();
            while(rst.next()){
                price= rst.getInt("price");
            }
            Bankapi bank = new Bankapi();
            boolean state=bank.stub.Transaction(email, ccp, price);
            if (state == false){
               return false;
            }
            sql = "insert into Booking(userId, volId, ccp,bookingDate) values(?,?,?,?)";
            pst=con.prepareStatement(sql);               
            pst.setInt(1, userId);
            pst.setInt(2, volId);
            pst.setInt(3, ccp);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            pst.setString(4, dtf.format(now));
            pst.execute();
            con.commit();
            JOptionPane.showMessageDialog(null, "successfully post");
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
    
    public Vol getvolbydestination(String destination){
        Vol tool = null ; 
        try {

            int i=0;
            st=con.createStatement();
            sql = "select * from Vol where destination = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, destination);
            rst = pst.executeQuery();
             while(rst.next() & i<1){
                int volId=rst.getInt("volId");
                String airport= rst.getString("airport");
                String takeDate = rst.getString("takeDate");              
                double totalTicktite = rst.getDouble("totalTicktite");
                double price= rst.getInt("price");
                tool = new Vol (volId,airport,destination,takeDate,totalTicktite,price);
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
}
