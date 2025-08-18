
package is.esercitazione.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

final public class TransactionManager {
	
    public static class TransactionStateException extends RuntimeException {
        public TransactionStateException(String message) {
            super(message);
        }
    }
    
    private final String DATABASE_PATH;
    private final String DATABASE_USERNAME; 
    private final String DATABASE_PASSWORD; 

    private Connection connection;
    private boolean inTransaction;
    
    final static class PrimaryKey {

        public PrimaryKey(Class clasz, Long id) {
            this.clasz = clasz;
            this.id = id;
        }

        public Class getClasz() {
            return clasz;
        }

        public Long getId() {
            return id;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 71 * hash + Objects.hashCode(this.clasz);
            hash = 71 * hash + Objects.hashCode(this.id);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final PrimaryKey other = (PrimaryKey) obj;
            if (!Objects.equals(this.clasz, other.clasz)) {
                return false;
            }
            if (!Objects.equals(this.id, other.id)) {
                return false;
            }
            return true;
        }
        
        final private Class clasz;
        final private Long id;
    }
    
    private final java.util.Map<PrimaryKey, Object> persistanceContext = new java.util.HashMap<>(); 

    protected TransactionManager(String databasePath, String databaseUsername, String databasePassword) {
        this.DATABASE_PATH = databasePath;
        this.DATABASE_USERNAME = databaseUsername;
        this.DATABASE_PASSWORD = databasePassword;
    }
    	
    public void beginTransaction() throws TransactionStateException {
        assertNotInTransaction();
        
        try {
        	connection = DriverManager.getConnection(DATABASE_PATH, DATABASE_USERNAME, DATABASE_PASSWORD);
            connection.setAutoCommit(false);
            
            inTransaction = true;
        } catch (SQLException e) {
            throw new RuntimeException("Impossible to create a new connection!", e);
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void assertInTransaction() throws TransactionStateException {
        if (isInTransaction() == false) {
            throw new TransactionStateException("Transaction not active!");
        }
    }
    
    public void assertNotInTransaction() throws TransactionStateException {
        if (isInTransaction() == true) {
            throw new TransactionStateException("Transaction is active!");
        }
    }
    
    public boolean isInTransaction() {
        return inTransaction;
    }
    
    private void clearPersistanceContext() {
        persistanceContext.clear();
    }
    
    public <T> T getFromPersistanceContext(Class<T> clasz, Long id) {
        PrimaryKey persistanceKey = new PrimaryKey(clasz, id);
        return (T)persistanceContext.get(persistanceKey);
    }
    
    public <T> void putInPersistanceContext(T object, Long id) {
        PrimaryKey persistanceKey = new PrimaryKey(object.getClass(), id);
        persistanceContext.put(persistanceKey, object);
    }

    public void commitTransaction() throws TransactionStateException, DAOException {
        try {
            assertInTransaction();

            connection.commit();
            connection.close();
            connection = null;
            inTransaction = false;
            clearPersistanceContext();
        } catch (SQLException e) {
            throw new DAOException("Impossible to commit the transaction!", e);
        }
    }
    
    public void rollbackTransaction() throws TransactionStateException {
        try {
            assertInTransaction();

            connection.rollback();
            connection.close();
            connection = null;
            inTransaction = false;
            clearPersistanceContext();
        } catch (SQLException e) {
            throw new RuntimeException("Unexpected exception during rollback!", e);
        }
    }
        
}
