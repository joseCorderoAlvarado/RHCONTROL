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
 * @author David Rodríguez Huaracha
 */
public enum CustomerClassification {
    WHITE("WHITE"), RED("RED"), YELLOW("YELLOW");
    
    private final String value;

    private CustomerClassification(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
