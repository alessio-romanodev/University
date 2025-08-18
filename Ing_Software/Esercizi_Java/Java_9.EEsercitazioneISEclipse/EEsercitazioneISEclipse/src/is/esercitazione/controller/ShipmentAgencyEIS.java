/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.controller;

import is.esercitazione.controller.impl.DefaultShipmentAgencyEISBuilder;
import is.esercitazione.entity.Customer;
import is.esercitazione.entity.MethodOfPayment;
import is.esercitazione.entity.MethodOfShipment;
import is.esercitazione.entity.Parcel;
import is.esercitazione.entity.Shipment;
import is.esercitazione.entity.User;
import java.util.List;

/**
 *
 * @author nonplay
 */
final public class ShipmentAgencyEIS {

    private static ShipmentAgencyEIS instance;
    
    public synchronized static ShipmentAgencyEIS getInstance() {
        if (instance == null) {
            instance = new DefaultShipmentAgencyEISBuilder().build();
        }
        return instance;
    }
    
    
    public abstract static class ShipmentAgencyEISBuilder {
        
        protected ShipmentAgencyEIS build(AuthenticatorManager authenticationManager, CustomerManager customerManager, ShipmentManager shippingManager) {
            return new ShipmentAgencyEIS(authenticationManager, customerManager, shippingManager);
        }
        
        public abstract ShipmentAgencyEIS build();
    }
  
    protected ShipmentAgencyEIS(AuthenticatorManager authenticationManager, CustomerManager customerManager, ShipmentManager shippingManager) {
        this.authenticationManager = authenticationManager;
        this.customerManager = customerManager;
        this.shippingManager = shippingManager;
    }
    
    
    public double computePrice(String senderAddress, String receiverAddress, long parcelGrams, String parcelSizeClass, MethodOfShipment methodOfShipment) {
        return shippingManager.computePrice(senderAddress, receiverAddress, parcelGrams, parcelSizeClass, methodOfShipment);
    }


    public String createShipping(Long senderId, String senderAddress, Long receiverId, String receiverAddress,
            long parcelGrams, String parcelSizeClass, MethodOfShipment methodOfShipment, MethodOfPayment methodOfPayment) throws PersistanceException{
        return shippingManager.createShipping(senderId, senderAddress, receiverId, receiverAddress, parcelGrams, parcelSizeClass, methodOfShipment, methodOfPayment);
    }

    public Shipment findShipment(long shipment) throws PersistanceException{
        return shippingManager.findShipment(shipment);
    }
    
    public Shipment findShipment(String shipmentUUID) throws PersistanceException{
        return shippingManager.findShipment(shipmentUUID);
    }

    public List<Shipment> getActiveShipments() throws PersistanceException {
        return shippingManager.getActiveShipments();
    }
    
    public Parcel getParcel(long parcelId) throws PersistanceException {
        return shippingManager.findParcel(parcelId);
    }

    public User login(String username, String password) throws AuthenticatorManager.InvalidCredentials {
        return authenticationManager.login(username, password);
    }

    public User getAuthenticatedUser() {
        return authenticationManager.getAuthenticatedUser();
    }

    public boolean isUserAuthenticated() {
        return authenticationManager.isUserAuthenticated();
    }

    public void assertOperationAllowed(String operation) throws AuthenticatorManager.OperationNotAllowed {
        authenticationManager.assertOperationAllowed(operation);
    }

    public void logout() {
        authenticationManager.logout();
    }

    public List<Customer> findCustomer(String surname) throws AuthenticatorManager.OperationNotAllowed, PersistanceException {
        authenticationManager.assertOperationAllowed(CustomerManager.FIND_CUSTOMER_OPERATION);
        return customerManager.findCustomer(surname);
    }
    
    
    public List<Customer> listCustomers() throws AuthenticatorManager.OperationNotAllowed, PersistanceException {
        authenticationManager.assertOperationAllowed(CustomerManager.FIND_CUSTOMER_OPERATION);
        return customerManager.listCustomers();
    }

    public void createCustomer(Customer customer) throws AuthenticatorManager.OperationNotAllowed, PersistanceException {
        authenticationManager.assertOperationAllowed(CustomerManager.CREATE_CUSTOMER_OPERATION);
        customerManager.createCustomer(customer);
    }
    
    
    final private AuthenticatorManager authenticationManager;
    final private CustomerManager customerManager;
    final private ShipmentManager shippingManager;
}
