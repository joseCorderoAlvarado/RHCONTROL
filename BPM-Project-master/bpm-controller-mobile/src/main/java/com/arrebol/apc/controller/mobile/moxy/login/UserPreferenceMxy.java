/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.moxy.login;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class UserPreferenceMxy {

    private String preferenceName;
    private String preferenceValue;

    public UserPreferenceMxy() {
    }

    /**
     *
     * @param preferenceName
     * @param preferenceValue
     */
    public UserPreferenceMxy(String preferenceName, String preferenceValue) {
        this.preferenceName = preferenceName;
        this.preferenceValue = preferenceValue;
    }

    public String getPreferenceName() {
        return preferenceName;
    }

    public void setPreferenceName(String preferenceName) {
        this.preferenceName = preferenceName;
    }

    public String getPreferenceValue() {
        return preferenceValue;
    }

    public void setPreferenceValue(String preferenceValue) {
        this.preferenceValue = preferenceValue;
    }

    @Override
    public String toString() {
        return "UserPreferenceMxy{" + "preferenceName=" + preferenceName + '}';
    }

}
