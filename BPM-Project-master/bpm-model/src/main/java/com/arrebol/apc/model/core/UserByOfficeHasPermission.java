/*
 * Arrebol Consultancy copyright
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.core;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Entity
@Table(name = "APC_USER_BY_OFFICE_HAS_PERMISSION")
public class UserByOfficeHasPermission implements Serializable {

    private static final long serialVersionUID = -4218581013559259950L;

    @EmbeddedId
    private UserByOfficeHasPermissionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_user_by_office",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private UserByOffice userByOffice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_permission",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private Permission permission;

    @Column(name = "created_by", nullable = false, length = 36)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    private Date createdOn;

    @Column(name = "last_updated_by", length = 36)
    private String lastUpdatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_on", length = 19)
    private Date lastUpdatedOn;

    public UserByOfficeHasPermission() {
    }

    /**
     *
     * @param id
     * @param createdBy
     */
    public UserByOfficeHasPermission(UserByOfficeHasPermissionId id, String createdBy) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdOn = new Date();
    }

    /**
     *
     * @param userByOffice
     * @param createdBy
     */
    public UserByOfficeHasPermission(UserByOffice userByOffice, String createdBy) {
        this.userByOffice = userByOffice;
        this.createdBy = createdBy;
    }

    public UserByOfficeHasPermissionId getId() {
        return id;
    }

    public void setId(UserByOfficeHasPermissionId id) {
        this.id = id;
    }

    public UserByOffice getUserByOffice() {
        return userByOffice;
    }

    public void setUserByOffice(UserByOffice userByOffice) {
        this.userByOffice = userByOffice;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final UserByOfficeHasPermission other = (UserByOfficeHasPermission) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserByOfficeHasPermission{" + "userByOffice=" + userByOffice + ", permission=" + permission + '}';
    }

}
