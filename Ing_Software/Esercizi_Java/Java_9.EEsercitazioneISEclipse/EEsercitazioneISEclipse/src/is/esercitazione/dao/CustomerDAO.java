/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.dao;

import static is.esercitazione.dao.ConversionUtils.readLong;
import is.esercitazione.entity.CompanyCustomer;
import is.esercitazione.entity.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import is.esercitazione.entity.PrivateCustomer;

/**
 *
 * @author nonplay
 */
public class CustomerDAO {
    /*
        DROP TABLE Customers;
        CREATE TABLE Customers (
            Id LONG NOT NULL AUTO_INCREMENT PRIMARY KEY,
            Address VARCHAR(255),
            EmailAddress VARCHAR(255),
            Company BOOLEAN,
            VatNumber VARCHAR(255),
            Name VARCHAR(255),
            Surname VARCHAR(255),
            DateOfBirth VARCHAR(64),
            TaxCode VARCHAR(255));
    */
    
        
    
    private static Customer deserializeCurrentRecord(ResultSet rs) throws SQLException {
        Customer customer;
        if (rs.getBoolean("Company")) {
            CompanyCustomer companyCustomer = new CompanyCustomer();
            companyCustomer.setVatNumber(rs.getString("VatNumber"));
            companyCustomer.setName(rs.getString("Name"));
            customer = companyCustomer;
        } else {
            PrivateCustomer privateCustomer = new PrivateCustomer();
            privateCustomer.setName(rs.getString("Name"));
            privateCustomer.setSurname(rs.getString("Surname"));
            privateCustomer.setDateOfBirth(ConversionUtils.deserializeDate(rs.getString("DateOfBirth")));
            privateCustomer.setTaxCode(rs.getString("TaxCode"));
            customer = privateCustomer;
        }
        customer.setId(rs.getLong("Id"));
        customer.setAddress(rs.getString("Address"));
        customer.setEmailAddress(rs.getString("EmailAddress"));
        return customer;
    }
    
    
    public static java.util.List<Customer> readCustomers(TransactionManager transactionManager) throws DAOException {
        return readCustomers(transactionManager, null);
    }
    
    public static java.util.List<Customer> readCustomers(TransactionManager transactionManager, String surname) throws DAOException {
        transactionManager.assertInTransaction();
        
        String query;
        if (surname == null) {
            query = "SELECT * FROM Customers";
        } else {
            query = "SELECT * FROM Customers WHERE (Name LIKE ? OR Surname LIKE ?)";
        }
        
        java.util.List<Customer> customerList = new java.util.ArrayList<>();
        try (PreparedStatement preparedStatement = transactionManager.getConnection().prepareStatement(query)) {
            if (surname != null) {
                preparedStatement.setString(1, surname);
                preparedStatement.setString(2, surname);
            }
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next() == true) {
                    Customer customer = transactionManager.getFromPersistanceContext(Customer.class, resultSet.getLong("Id"));
                    if (customer != null) {
                        customerList.add(customer);
                    } else {
                        customer = deserializeCurrentRecord(resultSet);
                        customerList.add(customer);
                        transactionManager.putInPersistanceContext(customer, customer.getId());
                    }                    
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to read the Customers", e);
        }
        
        return customerList;
    }
    
    public static Customer readCustomer(TransactionManager transactionManager, long id) throws DAOException {
        transactionManager.assertInTransaction();
        
        Customer foundObject = transactionManager.getFromPersistanceContext(Customer.class, id);
        
        if (foundObject == null) {
            try (PreparedStatement preparedStatement = transactionManager.getConnection().prepareStatement("SELECT * FROM Customers WHERE ID = ?")) {
                preparedStatement.setLong(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next() == true) {
                        foundObject = deserializeCurrentRecord(resultSet);
                        transactionManager.putInPersistanceContext(foundObject, id);
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to read the Customer id=" + id, e);
            }
        }
        
        return foundObject;
    }
    
    public static void createCustomer(TransactionManager transactionManager, Customer customer) throws DAOException {
        transactionManager.assertInTransaction();
        
        try (PreparedStatement ps = transactionManager.getConnection().prepareStatement(
                "INSERT INTO Customers (Company, VatNumber, Name, Surname, DateOfBirth, TaxCode, Address, EmailAddress) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            
            if (customer instanceof CompanyCustomer) {
                CompanyCustomer companyCustomer = (CompanyCustomer)customer;
                ps.setBoolean(1, true);
                ps.setString(2, companyCustomer.getVatNumber());
                ps.setString(3, companyCustomer.getName());
                ps.setString(4, null);
                ps.setString(5, null);
                ps.setString(6, null);
                
            } else if (customer instanceof PrivateCustomer) {
                PrivateCustomer privateCustomer = (PrivateCustomer)customer;
                ps.setBoolean(1, false);
                ps.setString(2, null);
                ps.setString(3, privateCustomer.getName());
                ps.setString(4, privateCustomer.getSurname());
                ps.setString(5, ConversionUtils.serializeDate(privateCustomer.getDateOfBirth()));
                ps.setString(6, privateCustomer.getTaxCode());
            }
            ps.setString(7, customer.getAddress());
            ps.setString(8, customer.getEmailAddress());
            
            ps.executeUpdate();
            try (ResultSet resultSet = ps.getGeneratedKeys()) {
                if (resultSet.next() == true) {
                    Long parcelId = readLong(resultSet, "SCOPE_IDENTITY()");
                    if (parcelId != null) {
                        customer.setId(parcelId);
                        transactionManager.putInPersistanceContext(customer, parcelId);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to persist a new Parcel!", e);
        }
    }
}
