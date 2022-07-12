/*
 * Arrebol Consultancy copyright
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.core;

import com.arrebol.apc.model.enums.ApplicationOwner;
import com.arrebol.apc.model.enums.UserByOfficeStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Entity
@Table(name = "APC_USER_BY_OFFICE",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"id_user", "id_office"}, name = "apc_user_by_office_uk")}
)
public class UserByOffice implements Serializable {

    private static final long serialVersionUID = -8206398520981509425L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_user",
            referencedColumnName = "id",
            nullable = false
    )
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_office",
            referencedColumnName = "id",
            nullable = false
    )
    private Office office;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_by_office_status", nullable = true)
    private UserByOfficeStatus userByOfficeStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_owner", nullable = false)
    private ApplicationOwner applicationOwner;

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

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "userByOffice",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<UserByOfficeHasPermission> userByOfficeHasPermissions;

    public UserByOffice() {
    }

    /**
     *
     * @param id
     */
    public UserByOffice(String id) {
        this.id = id;
    }

    /**
     *
     * @param user
     * @param office
     */
    public UserByOffice(User user, Office office) {
        this.user = user;
        this.office = office;
    }

    /**
     *
     * @param office
     * @param userByOfficeStatus
     * @param createdBy
     * @param createdOn
     */
    public UserByOffice(Office office, UserByOfficeStatus userByOfficeStatus, String createdBy, Date createdOn) {
        this.office = office;
        this.userByOfficeStatus = userByOfficeStatus;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
    }

    /**
     *
     * @param office
     * @param userByOfficeStatus
     * @param applicationOwner
     * @param createdBy
     * @param createdOn
     */
    public UserByOffice(Office office, UserByOfficeStatus userByOfficeStatus, ApplicationOwner applicationOwner, String createdBy, Date createdOn) {
        this.office = office;
        this.userByOfficeStatus = userByOfficeStatus;
        this.applicationOwner = applicationOwner;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
    }

    /**
     *
     * @param id
     * @param user
     */
    public UserByOffice(String id, User user) {
        this.id = id;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public UserByOfficeStatus getUserByOfficeStatus() {
        return userByOfficeStatus;
    }

    public void setUserByOfficeStatus(UserByOfficeStatus userByOfficeStatus) {
        this.userByOfficeStatus = userByOfficeStatus;
    }

    public ApplicationOwner getApplicationOwner() {
        return applicationOwner;
    }

    public void setApplicationOwner(ApplicationOwner applicationOwner) {
        this.applicationOwner = applicationOwner;
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

    public List<UserByOfficeHasPermission> getUserByOfficeHasPermissions() {
        return userByOfficeHasPermissions;
    }

    public void setUserByOfficeHasPermissions(List<UserByOfficeHasPermission> userByOfficeHasPermissions) {
        this.userByOfficeHasPermissions = userByOfficeHasPermissions;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final UserByOffice other = (UserByOffice) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserByOffice{" + "user=" + user + ", office=" + office + '}';
    }

}
