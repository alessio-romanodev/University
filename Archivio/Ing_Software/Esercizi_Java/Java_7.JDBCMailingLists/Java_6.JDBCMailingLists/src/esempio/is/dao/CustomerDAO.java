/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esempio.is.dao;

import esempio.is.entity.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nonplay
 */
public class CustomerDAO {
    /*
        DROP TABLE Customers;
        CREATE TABLE Customers (
            Id LONG NOT NULL AUTO_INCREMENT PRIMARY KEY,
            Name VARCHAR(255),
            Email VARCHAR(255));
            
        DROP TABLE Customers_MailingLists;
        CREATE TABLE Customers_MailingLists (
            CUSTOMER_ID LONG NOT NULL,
            MAILING_LIST_ID LONG NOT NULL,
            PRIMARY KEY(CUSTOMER_ID, MAILING_LIST_ID));
    */    
    
    private static Customer deserializeCurrentRecord(java.sql.Connection connection, ResultSet rs) throws SQLException, DAOException {
        Customer customer = new Customer();
        customer.setID(rs.getLong("Id"));
        customer.setName(rs.getString("Name"));
        customer.setEmail(rs.getString("Email"));
        
        java.util.List<Long> mailingListIds = readAssociatedMailingLists(connection, customer.getID());
        customer.setMailingListIDs(mailingListIds);
        
        return customer;
    }
    
    private static void silentlyClose(Statement statement) {
    	try {
    		statement.close();
    	} catch (SQLException e) {
    		// TODO log here!
    	}
    }
    
    public static java.util.List<Customer> readCustomers(java.sql.Connection connection) throws DAOException {
    	java.util.List<Customer> customerList = new java.util.ArrayList<>();
    	
    	Statement statement = null;
    	try {
    		statement = connection.createStatement();
    		final String QUERY_SQL = "SELECT * FROM Customers";
    		
    		ResultSet resultSet = statement.executeQuery(QUERY_SQL);
            while (resultSet.next() == true) {
                Customer customer = deserializeCurrentRecord(connection, resultSet);
                customerList.add(customer);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException("Impossible to read the Customers!", e);
        } finally {
        	if (statement != null) {
        		silentlyClose(statement);
        	}
        }
        
        return customerList;
    }
    
    public static Customer readCustomer(java.sql.Connection connection, long id) throws DAOException {
    	Customer customer = null;
    	PreparedStatement preparedStatement = null;
    	try {
    		final String QUERY_SQL = "SELECT * FROM Customers WHERE ID=?" ;
    		preparedStatement = connection.prepareStatement(QUERY_SQL);
    		preparedStatement.setLong(1, id);
    		
    		ResultSet resultSet = preparedStatement.executeQuery(QUERY_SQL);
            while (resultSet.next() == true) {
                customer = deserializeCurrentRecord(connection, resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException("Impossible to read the Customers!", e);
        } finally {
        	if (preparedStatement != null) {
        		silentlyClose(preparedStatement);
        	}
        }
        
        return customer;
    }
    
    public static java.util.List<Long> readAssociatedMailingLists(java.sql.Connection connection, Long customerId) throws DAOException {
    	java.util.List<Long> idList = new java.util.ArrayList<>();
    	
    	PreparedStatement preparedStatement = null;
    	try {
    		final String QUERY_SQL = "SELECT MAILING_LIST_ID FROM Customers_MailingLists WHERE CUSTOMER_ID=?" ;
    		preparedStatement = connection.prepareStatement(QUERY_SQL);
    		preparedStatement.setLong(1, customerId);
    		
    		ResultSet resultSet = preparedStatement.executeQuery(QUERY_SQL);
            while (resultSet.next() == true) {
            	long mailingListId = resultSet.getLong("MAILING_LIST_ID");
                idList.add(mailingListId);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException("Impossible to read the Customers!", e);
        } finally {
        	if (preparedStatement != null) {
        		silentlyClose(preparedStatement);
        	}
        }
        
        return idList;
    }
    
    public static void createCustomer(java.sql.Connection connection, Customer customer) throws DAOException {
    	if (customer.getID() != null) {
    		throw new DAOException("The object is already persisted!");
    	}
    	
    	PreparedStatement preparedStatement = null;
    	try {
    		final String QUERY_SQL = "INSERT INTO Customers (Name, Email) VALUES (?, ?)" ;
    		preparedStatement = connection.prepareStatement(QUERY_SQL);
    		preparedStatement.setString(1, customer.getName());
    		preparedStatement.setString(2, customer.getEmail());
    		
    		preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next() == true) {
            	long ID = resultSet.getLong("SCOPE_IDENTITY()");
                if (resultSet.wasNull() == false) {
                    customer.setID(ID);
                }
            }
            resultSet.close();
            
    		insertMailingLists(connection, customer);
        } catch (SQLException e) {
            throw new DAOException("Impossible to create a Customer!", e);
        } finally {
        	if (preparedStatement != null) {
        		silentlyClose(preparedStatement);
        	}
        }
    }
    
    private static void insertMailingLists (java.sql.Connection connection, Customer customer) throws SQLException {
		final String QUERY_SQL = "INSERT INTO Customers_MailingLists (CUSTOMER_ID, MAILING_LIST_ID) VALUES (?, ?)" ;
		
    	PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SQL);
    	
    	for (Long mailingListID : customer.getMailingListIDs()) {
    		preparedStatement.setLong(1, customer.getID());
			preparedStatement.setLong(2, mailingListID);
			preparedStatement.executeUpdate();
    	}
    	
    	preparedStatement.close();
    }
    
    public static void deleteCustomer(java.sql.Connection connection, Customer customer) throws DAOException {
    	if (customer.getID() == null) {
    		throw new DAOException("The object is not persisted!");
    	}
    	
    	PreparedStatement preparedStatement = null;
    	try {
    		final String QUERY_SQL = "DELETE FROM Customers WHERE ID=?" ;
    		preparedStatement = connection.prepareStatement(QUERY_SQL);
    		preparedStatement.setLong(1, customer.getID());
    		preparedStatement.executeUpdate();
            deleteMailingLists(connection, customer.getID());
            
            customer.setID(null);
            
        } catch (SQLException e) {
            throw new DAOException("Impossible to update a Customer!", e);
        } finally {
        	if (preparedStatement != null) {
        		silentlyClose(preparedStatement);
        	}
        }
    }
    
    private static void deleteMailingLists (java.sql.Connection connection, long customerId) throws SQLException {
		final String QUERY_SQL = "DELETE FROM Customers_MailingLists WHERE CUSTOMER_ID = ?" ;
		
    	PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SQL);
    	preparedStatement.setLong(1, customerId);
		preparedStatement.executeUpdate();
    	preparedStatement.close();
    }
    
    public static void updateCustomer(java.sql.Connection connection, Customer customer) throws DAOException {
    	if (customer.getID() == null) {
    		throw new DAOException("The object is not persisted!");
    	}
    	
    	PreparedStatement preparedStatement = null;
    	try {
    		final String QUERY_SQL = "UPDATE Customers SET Name=?, Email=? WHERE ID=?" ;
    		preparedStatement = connection.prepareStatement(QUERY_SQL);
    		preparedStatement.setString(1, customer.getName());
    		preparedStatement.setString(2, customer.getEmail());
    		preparedStatement.setLong(3, customer.getID());
    		
    		preparedStatement.executeUpdate();
            updateMailingLists(connection, customer);
            
        } catch (SQLException e) {
            throw new DAOException("Impossible to update a Customer!", e);
        } finally {
        	if (preparedStatement != null) {
        		silentlyClose(preparedStatement);
        	}
        }
    }
    
    private static void updateMailingLists (java.sql.Connection connection, Customer customer) throws SQLException {
    	deleteMailingLists(connection, customer.getID());
    	insertMailingLists(connection, customer);
    }

    
}
