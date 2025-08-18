package esempio.is.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import esempio.is.dao.ConnectionFactory;
import esempio.is.dao.CustomerDAO;
import esempio.is.dao.DAOException;
import esempio.is.dao.MailingListDAO;
import esempio.is.entity.Customer;
import esempio.is.entity.MailingList;

public class MailingListService {
	
	private static MailingListService instance;
	
	private MailingListService() {}
	
	public synchronized static MailingListService getInstance() {
		if (instance == null) {
			instance = new MailingListService();
		}
		return instance;
	}
	
	public Customer createCustomer(String name, String email) throws MailingListServiceException {
		Connection connection = null;
		try {
			Customer customer = new Customer();
			customer.setName(name);
			customer.setEmail(email);
			
			connection = ConnectionFactory.createConnection();
			CustomerDAO.createCustomer(connection, customer);
			
			return customer;
		} catch (DAOException | SQLException e) {
			throw new MailingListServiceException("Cannot create a new customer!", e);
		} finally {
			if (connection != null) {
				silentlyClose(connection);
			}
		}
	}
	
	public MailingList createMailingList(String topic) throws MailingListServiceException {
		Connection connection = null;
		try {
			MailingList mailingList = new MailingList();
			mailingList.setTopic(topic);
			
			connection = ConnectionFactory.createConnection();
			MailingListDAO.createMailingList(connection, mailingList);
			
			return mailingList;
		} catch (DAOException | SQLException e) {
			throw new MailingListServiceException("Cannot create a new mailing list!", e);
		} finally {
			if (connection != null) {
				silentlyClose(connection);
			}
		}
	}
	
	public void registerCustomerToMailingList(Customer customer, MailingList mailingList) throws MailingListServiceException {
		Connection connection = null;
		try {
			if (customer.getMailingListIDs().contains(mailingList.getID()) == false) {
				
				customer.getMailingListIDs().add(mailingList.getID());
				mailingList.getCustomerIDs().add(customer.getID());
				
				connection = ConnectionFactory.createConnection();
				CustomerDAO.updateCustomer(connection, customer);
			}
		} catch (DAOException | SQLException e) {
			throw new MailingListServiceException("Cannot subscribe the customer to the mailing list!", e);
		} finally {
			if (connection != null) {
				silentlyClose(connection);
			}
		}
	}
	
	public void unregisterCustomerToMailingList(Customer customer, MailingList mailingList) throws MailingListServiceException {
		Connection connection = null;
		try {
			if (customer.getMailingListIDs().contains(mailingList.getID())) {
				
				customer.getMailingListIDs().remove(mailingList.getID());
				mailingList.getCustomerIDs().remove(customer.getID());
				
				connection = ConnectionFactory.createConnection();
				CustomerDAO.updateCustomer(connection, customer);
			}
		} catch (DAOException | SQLException e) {
			throw new MailingListServiceException("Cannot unsubscribe the customer to the mailing list!", e);
		} finally {
			if (connection != null) {
				silentlyClose(connection);
			}
		}
	}
	
	
	
	public static class MailingListServiceException extends Exception {
		public MailingListServiceException(String message, Throwable cause) {
			super(message, cause);
		}

		public MailingListServiceException(String message) {
			super(message);
		}
	}
	
	
	private static void silentlyClose(Connection connection) {
    	try {
    		connection.close();
    	} catch (SQLException e) {
    		// TODO log here!
    	}
    }
}
