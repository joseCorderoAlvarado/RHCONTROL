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
public enum PermissionType {
    PUBLIC("public") {
        @Override
        public String toString() {
            return "PUBLIC";
        }
    }, PRIVATE("private") {
        @Override
        public String toString() {
            return "PRIVATE";
        }
    }, EXCLUSIVE("exclusive") {
        @Override
        public String toString() {
            return "EXCLUSIVE";
        }
    };

    private final String type;

    private PermissionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
