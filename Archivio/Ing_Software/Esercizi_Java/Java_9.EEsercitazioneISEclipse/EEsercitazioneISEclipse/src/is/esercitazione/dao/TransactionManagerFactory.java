/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.dao;

/**
 *
 * @author nonplay
 */
public class TransactionManagerFactory {
    
    private static String databasePath = "jdbc:h2:shipmentApplication";
    private static String databaseUsername = "sa";
    private static String databasePassword = "";
    private static boolean initialized = false;
    
    public static void initializeTransactionManager(String databasePath, String databaseUsername, String databasePassword) {
        if (initialized) {
            throw new IllegalStateException("TransactionManager already initialized!");
        }
        TransactionManagerFactory.databasePath = databasePath;
        TransactionManagerFactory.databaseUsername = databaseUsername;
        TransactionManagerFactory.databasePassword = databasePassword;
        TransactionManagerFactory.initialized = true;
    }
    
    public static TransactionManager createTransactionManager() {
        return new TransactionManager(databasePath, databaseUsername, databasePassword);
    }
}
