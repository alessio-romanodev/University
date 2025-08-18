/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.boundary;

import is.esercitazione.controller.AuthenticatorManager.InvalidCredentials;
import is.esercitazione.controller.ShipmentAgencyEIS;
import is.esercitazione.entity.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author nonplay
 */
public class ApplicationConsoleBoundary {
    
    public ApplicationConsoleBoundary() {
        final boolean AUTO_FLUSH_FLAG = true;
        
        this.consoleReader = new BufferedReader(new InputStreamReader(System.in));
        this.consoleWriter = new PrintWriter(System.out, AUTO_FLUSH_FLAG);
    }
    
    private void doLogin() throws IOException {
        consoleWriter.format("********** WELCOME **********\n");
        do {
            consoleWriter.format("Username: ");
            String username = consoleReader.readLine();
            consoleWriter.format("Password: ");
            String password = consoleReader.readLine();

            try {
                getShipmentAgencyEIS().login(username, password);
            } catch (InvalidCredentials e) {
                consoleWriter.format("Invalid Credentials! Retry.\n");
            }
        } while (getShipmentAgencyEIS().isUserAuthenticated() == false);
    }
    
    private void doLogout() {
        getShipmentAgencyEIS().logout();
        consoleWriter.format("********** GOODBYE **********\n");
    }
    
    private SalesEmployeeConsoleBoundary getSalesEmployeeConsoleBoundary() {
        return new SalesEmployeeConsoleBoundary(consoleReader, consoleWriter);
    }
    
    private void handleException(Exception e) {
        consoleWriter.format("Sorry! An exception occurred during the execution!\n");
        consoleWriter.format("The program must be terminated. Cause: %s\n",  e.getMessage());
        e.printStackTrace(consoleWriter);
    }
    
    private ShipmentAgencyEIS getShipmentAgencyEIS() {
        return ShipmentAgencyEIS.getInstance();
    }
    
    public TerminationState runApplication() {
        try {
            doLogin();
            try {
                User.UserRole authenticatedUserRole = getShipmentAgencyEIS().getAuthenticatedUser().getUserRole();
                if (authenticatedUserRole == User.UserRole.SALES_EMPLOYEE) {
                    getSalesEmployeeConsoleBoundary().showBoundary();
                } else {
                    consoleWriter.format("Sorry: application not yet developed for the user type %s!\n", authenticatedUserRole);
                }
            } finally {
                doLogout();
            }
            return TerminationState.CORRECT_TERMINATION;
        } catch (Exception e) {
            handleException(e);
            return TerminationState.ABNORMAL_TERMINATION;
        }
    }
    
    public static enum TerminationState {
        CORRECT_TERMINATION,
        ABNORMAL_TERMINATION
    };
    
    private final BufferedReader consoleReader;
    private final PrintWriter consoleWriter;
}
