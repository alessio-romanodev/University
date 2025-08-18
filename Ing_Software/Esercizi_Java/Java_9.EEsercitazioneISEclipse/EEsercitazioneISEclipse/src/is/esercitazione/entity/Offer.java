/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.entity;

import java.util.Date;

/**
 *
 * @author nonplay
 */
public class Offer {
    private Long id;
    private java.util.Date startDate;
    private java.util.Date endDate;
    private double discount;
    private MethodOfShipment applicability;

    public Offer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public MethodOfShipment getApplicability() {
        return applicability;
    }

    public void setApplicability(MethodOfShipment applicability) {
        this.applicability = applicability;
    }
    
    
}
