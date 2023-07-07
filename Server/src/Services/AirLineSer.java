/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import AirLineCorba.Booking;
import AirLineCorba.Client;
import AirLineCorba.Connect;
import AirLineCorba.Vol;
import Api.Bankapi;
import Helper.Admin;
import Helper.User;

/**
 *
 * @author walid
 */
public class AirLineSer  extends AirLineCorba.AirLinePOA{
    
    User user = new User();
    Admin admin = new Admin();
    Booking booking = new Booking();
    //1 done
    @Override
    public Connect Login(String email, String password) {
        return user.login(email, password);
    }
    //2 done
    @Override
    public Connect Signup(String firstName, String lastName, String email, String password, String address, int phoneNumber) {
    return user.signup(firstName, lastName, email, password, address, phoneNumber);
    }
    //3 done
    @Override
    public boolean addVol(String airport, String destination,String takeDate,double totalTicktite, double price) {
        return admin.postVol(airport, destination, takeDate, totalTicktite, price);
    }
    //4
    @Override
    public boolean removeVol(int volId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //5
    @Override
    public boolean updateVol() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //100 done
    @Override
    public Vol getVol(int volId) {
        return admin.getvol(volId);
    }
    //7 done
    @Override
    public Vol[] getVols() {
        admin = new Admin();
        return admin.getAllVols();
    }
    //200 done
    @Override
    public Client getClient(String email) {
        return admin.getclient(email);
    }
    //9 donne
    @Override
    public Client[] getClients() {
        admin = new Admin();
        return admin.getAllClients();
    }
    //300 done
    @Override
    public boolean Transaction(String email, int ccp, double price) {
        Bankapi bank = new Bankapi();
        return bank.stub.Transaction(email, ccp, price);
    }
    //500 done
    @Override
    public Booking[] listBookings(int clientId) {
        user = new User();
       return user.listbooking(clientId);
        
    }
    //400 done
    @Override
    public boolean addBooking(int clientId, int volId, int ccp, String email) {
        return user.postBooking(clientId, volId, ccp, email);
    }

    @Override
    public Vol getVolByDestination(String destination) {
        user = new User();
        return user.getvolbydestination(destination);
    }
 
}
