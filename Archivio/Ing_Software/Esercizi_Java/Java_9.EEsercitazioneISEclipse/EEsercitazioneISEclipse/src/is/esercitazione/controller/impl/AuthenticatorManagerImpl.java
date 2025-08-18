/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.controller.impl;

import is.esercitazione.controller.AuthenticatorManager;
import is.esercitazione.entity.User;

/**
 *
 * @author nonplay
 */
public class AuthenticatorManagerImpl implements AuthenticatorManager {
    
    private User authenticatedUser;
    
    @Override
    public User login(String username, String password) throws InvalidCredentials {
        if (isUserAuthenticated()) {
            logout();
        }
        authenticatedUser = new User();
        authenticatedUser.setUserRole(User.UserRole.SALES_EMPLOYEE);
        return authenticatedUser;
    }
    
    @Override
    public User getAuthenticatedUser() {
        return authenticatedUser;
    }
    
    @Override
    public boolean isUserAuthenticated() {
        return authenticatedUser != null;
    }
    
    @Override
    public void assertOperationAllowed(String operation) throws OperationNotAllowed {
        if (isUserAuthenticated() == false) {
            throwOperationNotAllowed(operation);
        }
        if (authenticatedUser.getUserRole() != User.UserRole.SALES_EMPLOYEE) {
            throwOperationNotAllowed(operation);
        }
    }
    
    private void throwOperationNotAllowed(String operation) throws OperationNotAllowed {
        throw new OperationNotAllowed(operation, authenticatedUser);
    }
    
    @Override
    public void logout() {
        authenticatedUser = null;
    }
}
