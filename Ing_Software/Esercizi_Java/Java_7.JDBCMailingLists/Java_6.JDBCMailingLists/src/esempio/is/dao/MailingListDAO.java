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
public class MailingListDAO {
	/*
	    DROP TABLE MailingLists;
	    CREATE TABLE MailingLists (
	        Id LONG NOT NULL AUTO_INCREMENT PRIMARY KEY,
	        Topic VARCHAR(255),
	        SentMessages LONG NOT NULL DEFAULT 0);
	        
	    DROP TABLE Customers_MailingLists;
	    CREATE TABLE Customers_MailingLists (
	        CUSTOMER_ID LONG NOT NULL,
	        MAILING_LIST_ID LONG NOT NULL,
	        PRIMARY KEY(CUSTOMER_ID, MAILING_LIST_ID));
	*/
    
        
    
    private static MailingList deserializeCurrentRecord(java.sql.Connection connection, ResultSet rs) throws DAOException, SQLException {
        MailingList mailingList = new MailingList();
        mailingList.setID(rs.getLong("Id"));
        mailingList.setTopic(rs.getString("Topic"));
        mailingList.setSentMessages(rs.getInt("SentMessages"));
        
        java.util.List<Long> customerIds = readAssociatedCustomers(connection, mailingList.getID());
        mailingList.setCustomerIDs(customerIds);
        
        return mailingList;
    }
    
    private static void silentlyClose(Statement statement) {
    	try {
    		statement.close();
    	} catch (SQLException e) {
    		// TODO log here!
    	}
    }
    
    
    public static java.util.List<MailingList> readMailingLists(java.sql.Connection connection) throws DAOException {
    	java.util.List<MailingList> mailingListList = new java.util.ArrayList<>();
    	
    	Statement statement = null;
    	try {
    		statement = connection.createStatement();
    		final String QUERY_SQL = "SELECT * FROM MailingLists";
    		
    		ResultSet resultSet = statement.executeQuery(QUERY_SQL);
            while (resultSet.next() == true) {
                MailingList mailingList = deserializeCurrentRecord(connection, resultSet);
                mailingListList.add(mailingList);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException("Impossible to read the MailingLists!", e);
        } finally {
        	if (statement != null) {
        		silentlyClose(statement);
        	}
        }
        
        return mailingListList;
    }
    
    public static MailingList readMailingList(java.sql.Connection connection, long id) throws DAOException {
    	MailingList mailingList = null;
    	PreparedStatement preparedStatement = null;
    	try {
    		final String QUERY_SQL = "SELECT * FROM MailingLists WHERE ID=?" ;
    		preparedStatement = connection.prepareStatement(QUERY_SQL);
    		preparedStatement.setLong(1, id);
    		
    		ResultSet resultSet = preparedStatement.executeQuery(QUERY_SQL);
            while (resultSet.next() == true) {
                mailingList = deserializeCurrentRecord(connection, resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException("Impossible to read the MailingLists!", e);
        } finally {
        	if (preparedStatement != null) {
        		silentlyClose(preparedStatement);
        	}
        }
        
        return mailingList;
    }
    
    public static void createMailingList(java.sql.Connection connection, MailingList mailingList) throws DAOException {
    	if (mailingList.getID() != null) {
    		throw new DAOException("The object is already persisted!");
    	}
    	
    	PreparedStatement preparedStatement = null;
    	try {
    		final String QUERY_SQL = "INSERT INTO MailingLists (Topic, SentMessages) VALUES (?, ?)" ;
    		preparedStatement = connection.prepareStatement(QUERY_SQL);
    		preparedStatement.setString(1, mailingList.getTopic());
    		preparedStatement.setInt(2, mailingList.getSentMessages());
    		
    		preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next() == true) {
            	long ID = resultSet.getLong("SCOPE_IDENTITY()");
                if (resultSet.wasNull() == false) {
                    mailingList.setID(ID);
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException("Impossible to create a MailingList!", e);
        } finally {
        	if (preparedStatement != null) {
        		silentlyClose(preparedStatement);
        	}
        }
    }
    
    
    public static java.util.List<Long> readAssociatedCustomers(java.sql.Connection connection, Long mailingListId) throws DAOException {
    	java.util.List<Long> idList = new java.util.ArrayList<>();
    	
    	PreparedStatement preparedStatement = null;
    	try {
    		final String QUERY_SQL = "SELECT CUSTOMER_ID FROM Customers_MailingLists WHERE MAILING_LIST_ID=?" ;
    		preparedStatement = connection.prepareStatement(QUERY_SQL);
    		preparedStatement.setLong(1, mailingListId);
    		
    		ResultSet resultSet = preparedStatement.executeQuery(QUERY_SQL);
            while (resultSet.next() == true) {
            	long customerId = resultSet.getLong("CUSTOMER_ID");
                idList.add(customerId);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException("Impossible to read the MailingList!", e);
        } finally {
        	if (preparedStatement != null) {
        		silentlyClose(preparedStatement);
        	}
        }
        
        return idList;
    }
    
}
