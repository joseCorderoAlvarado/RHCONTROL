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
public enum ComissionType {
    INCLUDED("INCLUDED") {
        @Override
        public String toString() {
            return "INCLUDED";
        }
    }, EXCLUDED("EXCLUDED") {
        @Override
        public String toString() {
            return "EXCLUDED";
        }
    };

    private final String type;

    private ComissionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
