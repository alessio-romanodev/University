/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.controller;

import is.esercitazione.entity.Customer;
import java.util.List;

/**
 *
 * @author nonplay
 */
public interface CustomerManager {
    
    public List<Customer> findCustomer(String surname) throws PersistanceException;
    public List<Customer> listCustomers() throws PersistanceException;
    public void createCustomer(Customer customer) throws PersistanceException;
    
    public static final String FIND_CUSTOMER_OPERATION = "customerManager.findCustomer";
    public static final String CREATE_CUSTOMER_OPERATION = "customerManager.createCustomer";
}
