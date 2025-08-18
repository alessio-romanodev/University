/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.controller;

import is.esercitazione.entity.MethodOfPayment;
import is.esercitazione.entity.MethodOfShipment;
import is.esercitazione.entity.Parcel;
import is.esercitazione.entity.Shipment;
import java.util.List;

/**
 *
 * @author nonplay
 */
public interface ShipmentManager {
    
    public double computePrice(String senderAddress, String receiverAddress, long parcelGrams, String parcelSizeClass, MethodOfShipment methodOfShipment);
    
    public String createShipping(Long senderId, String senderAddress, Long receiverId, String receiverAddress, long parcelGrams, String parcelSizeClass, MethodOfShipment methodOfShipment, MethodOfPayment methodOfPayment) throws PersistanceException;
    
    public Shipment findShipment(long shipmentId) throws PersistanceException;
    public Shipment findShipment(String shipmentUUID) throws PersistanceException;
    
    public Parcel findParcel(long parcelId) throws PersistanceException;
    
    public List<Shipment> getActiveShipments() throws PersistanceException;
}
