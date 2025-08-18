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
import is.esercitazione.entity.Parcel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import is.esercitazione.entity.MethodOfPayment;
import is.esercitazione.entity.MethodOfShipment;
import is.esercitazione.entity.PrivateCustomer;
import is.esercitazione.entity.Shipment;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nonplay
 */
public class SalesEmployeeConsoleBoundary {

    public SalesEmployeeConsoleBoundary(BufferedReader consoleReader, PrintWriter consoleWriter) {
        this.consoleReader = consoleReader;
        this.consoleWriter = consoleWriter;
    }

    private SelectionOptions chooseOption() throws IOException {
        print("Hello sales employee! Please select the operation:\n");
        while (true) {
            print("0. Exit program\n");
            print("1. Create new shipment\n");
            print("2. List current shipments\n");
            print("3. Track shipment\n");
            print("4. List customers\n");
            print("5. Add customer\n");

            try {
                int optionValue = Integer.parseInt(consoleReader.readLine());
                switch (optionValue) {
                    case 0: return SelectionOptions.EXIT;
                    case 1: return SelectionOptions.NEW_SHIPMENT;
                    case 2: return SelectionOptions.LIST_SHIPPINGS;
                    case 3: return SelectionOptions.TRACK_SHIPPING;
                    case 4: return SelectionOptions.LIST_CUSTOMERS;
                    case 5: return SelectionOptions.ADD_CUSTOMER;
                    default: 
                        print("No action associated with the number! Retry.\n");
                }
            } catch (NumberFormatException e) {
                print("Invalid value inserted! Retry.\n");
            }
        }
    }
    
    public void showBoundary() throws IOException {
        SelectionOptions selectedOption = null;
        while (true) {
            selectedOption = chooseOption();
            
            try {
                if (selectedOption == SelectionOptions.NEW_SHIPMENT) {
                    createNewShipping();
                } else if (selectedOption == SelectionOptions.LIST_SHIPPINGS) {
                    listActiveShipments();
                } else if (selectedOption == SelectionOptions.TRACK_SHIPPING) {
                    trackShipping();
                } else if (selectedOption == SelectionOptions.LIST_CUSTOMERS) {
                    listCustomers();
                } else if (selectedOption == SelectionOptions.ADD_CUSTOMER) {
                    addCustomer();
                } else {
                    return;
                }
            } catch (AuthenticatorManager.OperationNotAllowed e) {
                consoleWriter.format("Error: %s\n", e.getMessage());
            }
        }
    }
    
    private static enum SelectionOptions {
        NEW_SHIPMENT,
        LIST_SHIPPINGS,
        TRACK_SHIPPING,
        LIST_CUSTOMERS,
        ADD_CUSTOMER,
        EXIT
    }
    
    private void listCustomers() {
        try {
            for (Customer customer : getShipmentAgencyEIS().listCustomers()) {
                printCustomer(customer);
            }
        } catch (Exception e) {
            handleException(e);
        }
    }
    
    private void addCustomer() throws IOException {
        String isPrivateCustomer = printAndReadLine("Is a private customer? (y/n): ");
        
        Customer customer;
        if ("y".equals(isPrivateCustomer.toLowerCase())) {
            PrivateCustomer privateCustomer = new PrivateCustomer();
            
            privateCustomer.setSurname(printAndReadLine("Surname: "));
            privateCustomer.setName(printAndReadLine("Name: "));
            
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date dateOfBirth = simpleDateFormat.parse(printAndReadLine("Date of Birth (dd/MM/yyyy): "));
                privateCustomer.setDateOfBirth(dateOfBirth);
            } catch (ParseException e) {
                throw new IOException("Invalid date format!", e);
            }
            
            privateCustomer.setTaxCode(printAndReadLine("Tax Code: "));
            
            customer = privateCustomer;
        } else {
            CompanyCustomer companyCustomer = new CompanyCustomer();
            companyCustomer.setName(printAndReadLine("Name: "));

            companyCustomer.setVatNumber(printAndReadLine("Vat Number: "));
            customer = companyCustomer;
        }
        
        customer.setAddress(printAndReadLine("Address: "));
        customer.setEmailAddress(printAndReadLine("Email: "));
        
        try {
            getShipmentAgencyEIS().createCustomer(customer);
        } catch (Exception e) {
            consoleWriter.format("Cannot create the new shipment: %s\n", e.getMessage());
        }
    }
    
    private FindCustomerConsoleBoundary getFindCustomerBoundary() {
        return new FindCustomerConsoleBoundary(consoleReader, consoleWriter);
    }
    
    private ShipmentAgencyEIS getShipmentAgencyEIS() {
        return ShipmentAgencyEIS.getInstance();
    }
    
    private void print(String s, Object... params) {
        consoleWriter.format(s, params);
    }
    
    private String printAndReadLine(String s, Object... params) throws IOException {
        print(s, params);
        return consoleReader.readLine();
    }
    
    private void createNewShipping() throws IOException, AuthenticatorManager.OperationNotAllowed {
        
        String sizeClass = printAndReadLine("Insert the parcel size class: ");
        Long parcelGrams = Long.parseLong(printAndReadLine("Insert the weight in grams: "));
        
        print("Choose the sender customer.\n");
        Customer sender = getFindCustomerBoundary().findCustomer();
        if (sender == null) return; // User aborted
        
        String isReceiverACustomer = printAndReadLine("Is the receiver a registered customer? (y/n): ");
        Long receiverId = null;
        String receiverAddress;
        if ("y".equals(isReceiverACustomer.toLowerCase())) {
            print("Choose the receiver customer.\n");
            Customer receiver = getFindCustomerBoundary().findCustomer();

            if (receiver == null) return; // User aborted
            
            receiverId = receiver.getId();
            receiverAddress = receiver.getAddress();
        } else {
            receiverAddress = printAndReadLine("Receiver address: ");
        }
        
        MethodOfShipment methodOfShipment = MethodOfShipment.STANDARD;
        MethodOfPayment methodOfPayment = MethodOfPayment.DEBIT_CARD;
        double price = getShipmentAgencyEIS().computePrice(sender.getAddress(), receiverAddress, parcelGrams, sizeClass, methodOfShipment);
        
        print("Price for the shipment: %f\n", price);
        
        try {
            String shippingUUID = 
                    getShipmentAgencyEIS().createShipping(
                            sender.getId(), sender.getAddress(), 
                            receiverId, receiverAddress, 
                            parcelGrams, sizeClass, methodOfShipment, methodOfPayment);
            print("New shipment inserted with UUID=%s\n", shippingUUID);
        } catch (Exception e) {
            print("Cannot create the new shipment: %s\n", e.getMessage());
        }
    }
    
    private void trackShipping() throws IOException, AuthenticatorManager.OperationNotAllowed {
        Long shippingId = Long.parseLong(printAndReadLine("Insert the shipment id: "));
        
        try {
            Shipment shipment = getShipmentAgencyEIS().findShipment(shippingId);
            if (shipment == null) {
                print("No shipment found!\n");
            } else {
                printShipment(shipment);
            }
        } catch (PersistanceException e) {
            handleException(e);
        }
    }
    
    private void listActiveShipments() throws IOException, AuthenticatorManager.OperationNotAllowed {
        try {
            for (Shipment activeShipment : getShipmentAgencyEIS().getActiveShipments()) {
                printShipment(activeShipment);
            }
        } catch (PersistanceException e) {
            handleException(e);
        }
    }
    
    private void printShipment(Shipment shipment) {
        try {
            Parcel associatedParcel = getShipmentAgencyEIS().getParcel(shipment.getParcelId());
            print("Shipment uuid=%s (id=%s)\n", shipment.getIdentification(), shipment.getId());
            print("\tSender Address=%s (id=%s)\n", shipment.getSenderAddress(), shipment.getSenderId());
            print("\tReceiver Address=%s (id=%s)\n", shipment.getReceiverAddress(), shipment.getReceiverId());
            print("\tParcel size=%s\n", associatedParcel.getSizeClass());
            print("\tParcel grams=%s\n", associatedParcel.getGrams().toString());
        } catch (PersistanceException e) {
            handleException(e);
        }
    }
    
    private void printCustomer(Customer customer) {
        if (customer instanceof PrivateCustomer) {
            PrivateCustomer privateCustomer = (PrivateCustomer) customer;
            print("Private Customer (id=%s)\n", privateCustomer.getId());
            print("\tSurname=%s\n", privateCustomer.getSurname());
            print("\tName=%s\n", privateCustomer.getName());
            print("\tDate of birth=%s\n", DateFormat.getDateInstance().format(privateCustomer.getDateOfBirth()));
            print("\tTax Code=%s\n", privateCustomer.getTaxCode());

        } else if (customer instanceof CompanyCustomer) {
            CompanyCustomer companyCustomer = (CompanyCustomer) customer;
            print("Company Customer (id=%s)\n", companyCustomer.getId());
            print("\tName=%s\n", companyCustomer.getName());
            print("\tVAT Number=%s\n", companyCustomer.getVatNumber());
        } else {
            print("Customer id=%s not recognized!\n", customer.getId());
        }
        print("\tAddress=%s\n", customer.getAddress());
        print("\tEmail Address=%s\n", customer.getEmailAddress());
    }
    
    private void handleException(Exception e) {
        print("Error: %s!\nImpossible to complete the operation, please retry.\n", e.getLocalizedMessage());
        print("Detailed stacktrace for the developers:\n");
        e.printStackTrace(consoleWriter);
        consoleWriter.flush();
    }
    
    private final BufferedReader consoleReader;
    private final PrintWriter consoleWriter;
}
