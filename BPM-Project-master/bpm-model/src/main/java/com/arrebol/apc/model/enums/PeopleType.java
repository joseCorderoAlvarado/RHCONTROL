/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.enums;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public enum PeopleType {
    CUSTOMER("Cliente"), ENDORSEMENT("Aval"), BOTH("Cliente y aval");
    
    private final String value;

    private PeopleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
