/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.controller;

import is.esercitazione.entity.Customer;
import is.esercitazione.entity.MethodOfPayment;
import is.esercitazione.entity.MethodOfShipment;
import is.esercitazione.entity.PrivateCustomer;
import is.esercitazione.entity.Shipment;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author nonplay
 */
public class ShipmentAgencyEISTest {
    
    public ShipmentAgencyEISTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws AuthenticatorManager.InvalidCredentials {
        instance = ShipmentAgencyEIS.getInstance();
        instance.login("test", "test");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }
    
    private static ShipmentAgencyEIS instance;
    
    private PrivateCustomer createPrivateCustomer() {
        PrivateCustomer privateCustomer = new PrivateCustomer();
        privateCustomer.setAddress("My Address");
        privateCustomer.setDateOfBirth(new Date());
        privateCustomer.setEmailAddress("email@unina.it");
        privateCustomer.setName("Name");
        privateCustomer.setSurname("MySurname");
        privateCustomer.setTaxCode("TaxCode");
        return privateCustomer;
    }
    
    private Customer selectCustomerById(List<Customer> customerList, Long id) throws NotSingleException {
    	if (customerList == null) return null;
    	
    	Customer customerToReturn = null;
    	for (Customer customer : customerList) {
    		if (id.equals(customer.getId())) {
    			if (customerToReturn == null) {
    				customerToReturn = customer;
    			} else {
    				throw new NotSingleException("More than one customer with same id! Id=" + id);
    			}
    		}
    	}
    	return customerToReturn;
    }
    
    @Test
    public void testInsertionNewCustomer() throws Exception {
        PrivateCustomer privateCustomer = createPrivateCustomer();
        
        assertNull(privateCustomer.getId()); // Pre
        
        instance.createCustomer(privateCustomer);
        
        Long generatedId = privateCustomer.getId();
        assertNotNull(generatedId); // Post
        
        List<Customer> customerList = instance.findCustomer(privateCustomer.getSurname());
        
        PrivateCustomer actualCustomer = (PrivateCustomer) selectCustomerById(customerList, generatedId);
        
        assertNotNull(actualCustomer); // Post
        assertEquals(privateCustomer.getAddress(), actualCustomer.getAddress());
        assertEquals(privateCustomer.getDateOfBirth(), actualCustomer.getDateOfBirth());
        assertEquals(privateCustomer.getEmailAddress(), actualCustomer.getEmailAddress());
        assertEquals(privateCustomer.getName(), actualCustomer.getName());
        assertEquals(privateCustomer.getSurname(), actualCustomer.getSurname());
        assertEquals(privateCustomer.getTaxCode(), actualCustomer.getTaxCode());
    }
    
    @Test
    public void testInsertionNewShipment() throws Exception {
        
        PrivateCustomer sender = createPrivateCustomer();
        PrivateCustomer receiver = createPrivateCustomer();
        
        instance.createCustomer(sender);
        instance.createCustomer(receiver);
        
        final MethodOfShipment ACTUAL_METHOD_OF_SHIPMENT = MethodOfShipment.STANDARD;
        final MethodOfPayment ACTUAL_METHOD_OF_PAYMENT = MethodOfPayment.DEBIT_CARD;
        
        final String PARCEL_SIZE_CLASS = "STANDARD";
        final long ACTUAL_WEIGHT = 1230;
        final double ACTUAL_PRICE = instance.computePrice(sender.getAddress(), receiver.getAddress(), ACTUAL_WEIGHT, PARCEL_SIZE_CLASS, ACTUAL_METHOD_OF_SHIPMENT);
        
        String shipment_UUID = instance.createShipping(sender.getId(), sender.getAddress(),
                                            receiver.getId(), receiver.getAddress(), 
                                            ACTUAL_WEIGHT, PARCEL_SIZE_CLASS, ACTUAL_METHOD_OF_SHIPMENT, ACTUAL_METHOD_OF_PAYMENT);
        
        
        assertTrue(shipment_UUID != null && !shipment_UUID.isEmpty());
        
        Shipment actualShipment = instance.findShipment(shipment_UUID);
        assertTrue(actualShipment.getAppliedOffer().isEmpty());
        assertTrue(Math.abs(new Date().getTime() - actualShipment.getDateOfShipmentRequest().getTime()) < 60000);
        assertEquals(shipment_UUID, actualShipment.getIdentification());
        assertNull(actualShipment.getInvoiceId());
        assertEquals(ACTUAL_METHOD_OF_PAYMENT, actualShipment.getMethodOfPayment());
        assertEquals(ACTUAL_METHOD_OF_SHIPMENT, actualShipment.getMethodOfShipment());
        assertNotNull(actualShipment.getParcelId());
        assertEquals(ACTUAL_PRICE, actualShipment.getPrice(), 1e-3);
        assertEquals(receiver.getId(), actualShipment.getReceiverId());
        assertEquals(receiver.getAddress(), actualShipment.getReceiverAddress());
        assertEquals(sender.getId(), actualShipment.getSenderId());
        assertEquals(sender.getAddress(), actualShipment.getSenderAddress());
        
    }
    
}
