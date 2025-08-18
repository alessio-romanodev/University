/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.controller.impl;

import is.esercitazione.controller.PersistanceException;
import is.esercitazione.entity.Parcel;
import java.util.List;
import is.esercitazione.controller.ShipmentManager;
import is.esercitazione.dao.TransactionManager;
import is.esercitazione.dao.TransactionManagerFactory;
import is.esercitazione.dao.DAOException;
import is.esercitazione.dao.ParcelDAO;
import is.esercitazione.dao.ShipmentDAO;
import is.esercitazione.entity.MethodOfPayment;
import is.esercitazione.entity.MethodOfShipment;
import is.esercitazione.entity.Shipment;
import java.util.UUID;

/**
 *
 * @author nonplay
 */
public class ShipmentManagerImpl implements ShipmentManager {
    
    @Override
    public double computePrice(String senderAddress, String receiverAddress, long parcelGrams, String parcelSizeClass, MethodOfShipment methodOfShipment) {
        return 100.00;
    }
    
    private Parcel createParcel(long parcelGrams, String parcelSizeClass) {
        Parcel parcel = new Parcel();
        parcel.setGrams(parcelGrams);
        parcel.setSizeClass(parcelSizeClass);
        return parcel;
    }
    
    private Shipment createShipment(String identification, double price, Long senderId, String senderAddress, Long receiverId, 
            String receiverAddress, MethodOfShipment methodOfShipment, MethodOfPayment methodOfPayment) {
        Shipment shipment = new Shipment();
        shipment.setDateOfShipmentRequest(new java.util.Date());
        shipment.setIdentification(identification);
        shipment.setMethodOfPayment(methodOfPayment);
        shipment.setMethodOfShipment(methodOfShipment);
//        shipment.setParcelId(parcelGrams);
        shipment.setPrice(price);
        shipment.setReceiverAddress(receiverAddress);
        shipment.setReceiverId(receiverId);
        shipment.setSenderAddress(senderAddress);
        shipment.setSenderId(senderId);
        shipment.setValidated(false);
        return shipment;
    }

    @Override
    public String createShipping(Long senderId, String senderAddress, Long receiverId, String receiverAddress,
            long parcelGrams, String parcelSizeClass, MethodOfShipment methodOfShipment, MethodOfPayment methodOfPayment) throws PersistanceException {
        
        double price = computePrice(senderAddress, receiverAddress, parcelGrams, parcelSizeClass, methodOfShipment);
        String shippingIdentification = UUID.randomUUID().toString();
        Shipment shipment = createShipment(shippingIdentification, price, senderId, senderAddress, receiverId, receiverAddress, methodOfShipment, methodOfPayment);
        Parcel parcel = createParcel(parcelGrams, parcelSizeClass);
        
        TransactionManager transactionManager = TransactionManagerFactory.createTransactionManager();
        transactionManager.beginTransaction();
        try {
            ParcelDAO.createParcel(transactionManager, parcel);
            shipment.setParcelId(parcel.getId());
            
            ShipmentDAO.createShipment(transactionManager, shipment);
            transactionManager.commitTransaction();
        } catch (DAOException e) {
            transactionManager.rollbackTransaction();
            throw new PersistanceException("Impossible to create a new shipping!", e);
        }
        return shippingIdentification;
    }

    @Override
    public Shipment findShipment(String shipmentUUID) throws PersistanceException {
        TransactionManager transactionManager = TransactionManagerFactory.createTransactionManager();
        Shipment shipment;
        
        transactionManager.beginTransaction();
        try {
            shipment = ShipmentDAO.readShipment(transactionManager, shipmentUUID);
            transactionManager.commitTransaction();
        } catch (DAOException e) {
            transactionManager.rollbackTransaction();
            throw new PersistanceException("Impossible to find the shipping UUID=" + shipmentUUID, e);
        }
        return shipment;
    }

    @Override
    public Shipment findShipment(long shipmentId) throws PersistanceException {
        TransactionManager transactionManager = TransactionManagerFactory.createTransactionManager();
        Shipment shipment;
        
        transactionManager.beginTransaction();
        try {
            shipment = ShipmentDAO.readShipment(transactionManager, shipmentId);
            transactionManager.commitTransaction();
        } catch (DAOException e) {
            transactionManager.rollbackTransaction();
            throw new PersistanceException("Impossible to find the shipping ID=" + shipmentId, e);
        }
        return shipment;
    }

    @Override
    public Parcel findParcel(long parcelId) throws PersistanceException {
        TransactionManager transactionManager = TransactionManagerFactory.createTransactionManager();
        Parcel parcel;
        
        transactionManager.beginTransaction();
        try {
            parcel = ParcelDAO.readParcel(transactionManager, parcelId);
            transactionManager.commitTransaction();
        } catch (DAOException e) {
            transactionManager.rollbackTransaction();
            throw new PersistanceException("Impossible to create a new shipping!", e);
        }
        return parcel;
    }

    @Override
    public List<Shipment> getActiveShipments() throws PersistanceException {
        TransactionManager transactionManager = TransactionManagerFactory.createTransactionManager();
        List<Shipment> shipmentList;
        
        transactionManager.beginTransaction();
        try {
            shipmentList = ShipmentDAO.readShipments(transactionManager);
            transactionManager.commitTransaction();
        } catch (DAOException e) {
            transactionManager.rollbackTransaction();
            throw new PersistanceException("Impossible to create a new shipping!", e);
        }
        return shipmentList;
    }

    
    
}
