/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.controller.impl;

import is.esercitazione.controller.*;
import is.esercitazione.dao.TransactionManager;
import is.esercitazione.dao.TransactionManagerFactory;
import is.esercitazione.dao.CustomerDAO;
import is.esercitazione.dao.DAOException;
import is.esercitazione.entity.Customer;
import java.util.List;

/**
 *
 * @author nonplay
 */
public class CustomerManagerImpl implements CustomerManager {
    
    @Override
    public List<Customer> findCustomer(String surname) throws PersistanceException {
        TransactionManager transactionManager = TransactionManagerFactory.createTransactionManager();
        List<Customer> customerList;
        
        transactionManager.beginTransaction();
        try {
            customerList = CustomerDAO.readCustomers(transactionManager, surname);
            transactionManager.commitTransaction();
        } catch (DAOException e) {
            transactionManager.rollbackTransaction();
            throw new PersistanceException("Impossible to list the customers!", e);
        }
        return customerList;
    }

    @Override
    public List<Customer> listCustomers() throws PersistanceException {
        TransactionManager transactionManager = TransactionManagerFactory.createTransactionManager();
        List<Customer> customerList;
        
        transactionManager.beginTransaction();
        try {
            customerList = CustomerDAO.readCustomers(transactionManager);
            transactionManager.commitTransaction();
        } catch (DAOException e) {
            transactionManager.rollbackTransaction();
            throw new PersistanceException("Impossible to list the customers!", e);
        }
        return customerList;
    }
    
    @Override
    public void createCustomer(Customer customer) throws PersistanceException {
        TransactionManager transactionManager = TransactionManagerFactory.createTransactionManager();
        
        transactionManager.beginTransaction();
        try {
            CustomerDAO.createCustomer(transactionManager, customer);
            transactionManager.commitTransaction();
        } catch (DAOException e) {
            transactionManager.rollbackTransaction();
            throw new PersistanceException("Impossible to list the customers!", e);
        }
    }
    
}
