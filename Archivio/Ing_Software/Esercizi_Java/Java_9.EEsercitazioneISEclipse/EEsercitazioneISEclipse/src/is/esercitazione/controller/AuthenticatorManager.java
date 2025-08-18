/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.controller;

import is.esercitazione.entity.User;

/**
 *
 * @author nonplay
 */
public interface AuthenticatorManager {
    
    
    public static class InvalidCredentials extends Exception {
    }
    
    public static class OperationNotAllowed extends Exception {
        public OperationNotAllowed(String operation, User user) {
            super("Not authorized operation");
            this.operation = operation;
            this.user = user;
        }

        public String getOperation() {
            return operation;
        }

        public User getUser() {
            return user;
        }
        
        final private String operation;
        final private User user;
    }
    
    public User login(String username, String password) throws InvalidCredentials;
    
    public User getAuthenticatedUser();
    
    public boolean isUserAuthenticated();
    
    public void assertOperationAllowed(String operation) throws OperationNotAllowed;
    
    public void logout() ;
}
