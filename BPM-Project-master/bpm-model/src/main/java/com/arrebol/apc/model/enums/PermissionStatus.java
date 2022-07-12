/*
 * Arrebol Consultancy copyright
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.enums;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public enum PermissionStatus {
    ENEBLED("enebled") {
        @Override
        public String toString() {
            return "ENEBLED";
        }
    }, DISABLED("disabled") {
        @Override
        public String toString() {
            return "DISABLED";
        }
    };

    private final String status;

    private PermissionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
