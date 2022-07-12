/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.moxy.login;

import com.arrebol.apc.controller.mobile.moxy.PersonMxy;
import java.util.List;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class UserMxy extends PersonMxy {

    private String userName;
    private String officeId;
    private String routeId;
    private String certifier;

    private List<UserPreferenceMxy> preferences;

    public UserMxy() {
    }

    /**
     *
     * @param id
     * @param userName
     * @param thumbnail
     * @param officeId
     * @param routeId
     * @param certifier
     */
    public UserMxy(String id, String userName, String thumbnail, String officeId, String routeId, String certifier) {
        this.id = id;
        this.userName = userName;
        this.thumbnail = thumbnail;
        this.officeId = officeId;
        this.routeId = routeId;
        this.certifier = certifier;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public void setPreferences(List<UserPreferenceMxy> preferences) {
        this.preferences = preferences;
    }

    public List<UserPreferenceMxy> getPreferences() {
        return preferences;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getCertifier() {
        return certifier;
    }

    public void setCertifier(String certifier) {
        this.certifier = certifier;
    }

    @Override
    public String toString() {
        return "UserMxy{" + "userName=" + userName + ", preferences=" + preferences + '}';
    }

}
