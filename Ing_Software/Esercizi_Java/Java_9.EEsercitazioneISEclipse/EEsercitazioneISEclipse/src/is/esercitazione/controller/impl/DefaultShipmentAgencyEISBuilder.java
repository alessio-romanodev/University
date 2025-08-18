/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.controller.impl;

import is.esercitazione.controller.AuthenticatorManager;
import is.esercitazione.controller.CustomerManager;
import is.esercitazione.controller.ShipmentAgencyEIS;
import is.esercitazione.controller.ShipmentManager;

/**
 *
 * @author nonplay
 */
public class DefaultShipmentAgencyEISBuilder extends ShipmentAgencyEIS.ShipmentAgencyEISBuilder {

    @Override
    public ShipmentAgencyEIS build() {
        AuthenticatorManager authenticationManager  = new AuthenticatorManagerImpl();
        CustomerManager customerManager = new CustomerManagerImpl();
        ShipmentManager shippingManager = new ShipmentManagerImpl();
        
        return super.build(authenticationManager, customerManager, shippingManager);
    }
    
}
