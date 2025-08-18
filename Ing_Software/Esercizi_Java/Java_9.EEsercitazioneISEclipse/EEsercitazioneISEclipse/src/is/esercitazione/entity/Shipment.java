/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.entity;

import java.util.Date;
import java.util.List;

/**
 *
 * @author nonplay
 */
public class Shipment {
    /* 
        Insert shipment
        - price
        - senderAddress, receiverAddress
        - senderId, senderId
        - parcelWeight
        - parcelSize
        - methodOfShipment
        - methodOfPayment
    */
    private Long id;
    private String identification;
    private double price;
    private String senderAddress;
    private String receiverAddress;
    private java.util.Date dateOfShipmentRequest;
    private boolean validated;
    private Long receiverId;
    private Long senderId;
    private Long invoiceId;
    private Long parcelId;
    private MethodOfShipment methodOfShipment;
    private MethodOfPayment methodOfPayment;
    private java.util.List<Long> appliedOffer = new java.util.ArrayList();

    public Shipment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Date getDateOfShipmentRequest() {
        return dateOfShipmentRequest;
    }

    public void setDateOfShipmentRequest(Date dateOfShipmentRequest) {
        this.dateOfShipmentRequest = dateOfShipmentRequest;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
    }

    public MethodOfShipment getMethodOfShipment() {
        return methodOfShipment;
    }

    public void setMethodOfShipment(MethodOfShipment methodOfShipment) {
        this.methodOfShipment = methodOfShipment;
    }

    public List<Long> getAppliedOffer() {
        return appliedOffer;
    }

    public void setAppliedOffer(List<Long> appliedOffer) {
        this.appliedOffer = appliedOffer;
    }

    public MethodOfPayment getMethodOfPayment() {
        return methodOfPayment;
    }

    public void setMethodOfPayment(MethodOfPayment methodOfPayment) {
        this.methodOfPayment = methodOfPayment;
    }

    
}
