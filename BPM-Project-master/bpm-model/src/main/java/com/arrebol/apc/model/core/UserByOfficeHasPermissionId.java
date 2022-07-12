/*
 * Arrebol Consultancy copyright
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.core;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Embeddable
public class UserByOfficeHasPermissionId implements Serializable {

    private static final long serialVersionUID = -7541179490083208294L;

    @Column(name = "id_user_by_office", length = 36, nullable = true)
    private String idUserByOffice;

    @Column(name = "id_permission", length = 36, nullable = true)
    private String idPermission;

    public UserByOfficeHasPermissionId() {
    }

    public UserByOfficeHasPermissionId(String idUserByOffice, String idPermission) {
        this.idUserByOffice = idUserByOffice;
        this.idPermission = idPermission;
    }

    public String getIdUserByOffice() {
        return idUserByOffice;
    }

    public void setIdUserByOffice(String idUserByOffice) {
        this.idUserByOffice = idUserByOffice;
    }

    public String getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(String idPermission) {
        this.idPermission = idPermission;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.idUserByOffice);
        hash = 29 * hash + Objects.hashCode(this.idPermission);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserByOfficeHasPermissionId other = (UserByOfficeHasPermissionId) obj;
        if (!Objects.equals(this.idUserByOffice, other.idUserByOffice)) {
            return false;
        }
        if (!Objects.equals(this.idPermission, other.idPermission)) {
            return false;
        }
        return true;
    }

}
