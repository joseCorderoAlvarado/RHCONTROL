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
 * @author Oscar Armando Vargas Cardenas <oscar.vargas@arrebol.com.mx>
 */
public enum ActionStatus {
    PENDING("Pendiente"), APPROVED("Aprobado"), REJECTED("Rechazado");
    
    private final String value;

    private ActionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
