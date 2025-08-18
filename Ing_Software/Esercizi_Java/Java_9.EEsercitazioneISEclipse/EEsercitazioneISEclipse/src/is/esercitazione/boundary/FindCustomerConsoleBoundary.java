/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.boundary;

import is.esercitazione.controller.AuthenticatorManager;
import is.esercitazione.controller.PersistanceException;
import is.esercitazione.controller.ShipmentAgencyEIS;
import is.esercitazione.entity.CompanyCustomer;
import is.esercitazione.entity.Customer;
import is.esercitazione.entity.PrivateCustomer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author nonplay
 */
public class FindCustomerConsoleBoundary {
    
    public FindCustomerConsoleBoundary(BufferedReader consoleReader, PrintWriter consoleWriter) {
        this.consoleReader = consoleReader;
        this.consoleWriter = consoleWriter;
    }

    private Customer getOption(List<Customer> customerList) throws IOException {
        if (customerList == null || customerList.isEmpty()) {
            consoleWriter.format("No  result!\n");
            return null;
        }
        
        consoleWriter.format("0: Back\n");
        for (int i = 0; i < customerList.size(); i++) {
            Customer c = customerList.get(i);
            if (c instanceof PrivateCustomer) {
                PrivateCustomer privateCustomer = (PrivateCustomer)c;
                consoleWriter.format("%d: %s\t%s\n", i+1, privateCustomer.getSurname(), privateCustomer.getName());
            } else if (c instanceof CompanyCustomer) {
                CompanyCustomer companyCustomer = (CompanyCustomer)c;
                consoleWriter.format("%d: %s\t%s\n", i+1, companyCustomer.getName(), companyCustomer.getVatNumber());
            }
        }
        consoleWriter.format("Select customer: ");
        String option =  consoleReader.readLine();
        
        try {
            int numericOption = Integer.parseInt(option);
            if (numericOption == 0) return null;
            else return customerList.get(numericOption-1);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            consoleWriter.format("Invalid value inserted! Retry.\n");
            return null;
        }
    }
    
    private ShipmentAgencyEIS getShipmentAgencyEIS() {
        return ShipmentAgencyEIS.getInstance();
    }
    
    public Customer findCustomer() throws IOException, AuthenticatorManager.OperationNotAllowed {
        try {
            Customer foundCustomer = null;
            do {
                consoleWriter.format("Insert customer (sur)name: ");
                String surname = consoleReader.readLine();

                if (surname.isEmpty()) {
                    return null;
                }

                List<Customer> customerList = getShipmentAgencyEIS().findCustomer(surname);
                foundCustomer = getOption(customerList);
            } while (foundCustomer == null);
            return foundCustomer;
        } catch (PersistanceException e) {
            throw new IOException(e);
        }
    }
    
    private final BufferedReader consoleReader;
    private final PrintWriter consoleWriter;
}
