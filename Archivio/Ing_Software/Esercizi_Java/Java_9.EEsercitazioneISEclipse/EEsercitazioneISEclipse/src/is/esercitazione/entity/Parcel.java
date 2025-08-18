/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.entity;

import java.util.List;

/**
 *
 * @author nonplay
 */
public class Parcel {

    private Long id;
    private Long grams;
    private String sizeClass;
    private java.util.List<Long> routeIdList = new java.util.ArrayList();
    private Long awaitingDeliveryStorehouse;

    public Parcel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGrams() {
        return grams;
    }

    public void setGrams(Long grams) {
        this.grams = grams;
    }

    public String getSizeClass() {
        return sizeClass;
    }

    public void setSizeClass(String sizeClass) {
        this.sizeClass = sizeClass;
    }

    public List<Long> getRouteIdList() {
        return routeIdList;
    }

    public void setRouteIdList(List<Long> routeIdList) {
        this.routeIdList = routeIdList;
    }

    public Long getAwaitingDeliveryStorehouse() {
        return awaitingDeliveryStorehouse;
    }

    public void setAwaitingDeliveryStorehouse(Long awaitingDeliveryStorehouse) {
        this.awaitingDeliveryStorehouse = awaitingDeliveryStorehouse;
    }

    
    
    
}
