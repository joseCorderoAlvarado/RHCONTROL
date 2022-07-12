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
public enum UserType {
    WEB("web") {
        @Override
        public String toString() {
            return "WEB";
        }
    }, MOBILE("mobile") {
        @Override
        public String toString() {
            return "MOBILE";
        }
    }, BOTH("both") {
        @Override
        public String toString() {
            return "BOTH";
        }
    }, ROOT("root") {
        @Override
        public String toString() {
            return "ROOT";
        }
    };

    private final String type;

    private UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
