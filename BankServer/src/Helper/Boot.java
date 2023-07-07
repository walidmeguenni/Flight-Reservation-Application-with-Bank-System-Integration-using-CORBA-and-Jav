/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author walid
 */
public class Boot {
    Connection con= ConnectionDB.Connectiondb();
    PreparedStatement pst=null;
    ResultSet rst= null;
    Statement st=null;
    String sql=""; 
     double balance=0;
    public boolean depotSearch(String email, int ccp,double price){

        sql = "select balance from Account where email = ? and ccp= ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setInt(2, ccp);
            rst = pst.executeQuery();
            while(rst.next()) {
                balance= rst.getDouble("balance") + price;
            }
            sql = "update Account set balance = ? where email = ? and ccp= ? ";
            pst=con.prepareStatement(sql);
            pst.setDouble(1, balance);
            pst.setString(2, email);
            pst.setInt(3, ccp);
            pst.executeUpdate();
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
    public boolean retraitSearch(String email, int ccp,double price){

        sql = "select balance from Account where email = ? and ccp= ?";
            try {
               pst = con.prepareStatement(sql);
               pst.setString(1, email);
               pst.setInt(2, ccp);
               rst = pst.executeQuery();
                while(rst.next()) {
                    balance = rst.getDouble("balance");  
                }
                    System.out.println(balance);
                    if(price > balance){
                        JOptionPane.showMessageDialog(null, "balance less");
                        return false;
                    }else{
                        balance= balance - price;
                        sql = "update Account set balance = ? where email = ? and ccp= ? ";
                        pst=con.prepareStatement(sql);
                        pst.setDouble(1, balance);
                        pst.setString(2, email);
                        pst.setInt(3, ccp);
                        pst.executeUpdate(); 
                        return true;
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
            return false;
    }
     public boolean transaction(String email, int ccp,double price ) {

            try {
                con.setAutoCommit(false);
                retraitSearch(email, ccp, price);
                depotSearch("meguennniwalid@gmail.com", 1111111, price);
                con.commit(); 
                return true;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
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
}
