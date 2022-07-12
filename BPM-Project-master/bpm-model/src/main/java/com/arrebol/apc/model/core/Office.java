/*
 * Arrebol Consultancy copyright
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.core;

import com.arrebol.apc.model.catalog.People;
import com.arrebol.apc.model.enums.OfficeStatus;
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
@Table(name = "APC_OFFICE",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"office_name"})}
)
public class Office implements Serializable {

    private static final long serialVersionUID = -141661123746446879L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "office_name", length = 100, nullable = false)
    private String officeName;

    @Column(name = "address", length = 250)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "office_status", nullable = false)
    private OfficeStatus officeStatus;

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
            mappedBy = "office",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<UserByOffice> userByOffices;

    @OneToMany(
            mappedBy = "office",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<HumanResourceByOffice> humanResourceByOffices;

    @OneToMany(
            mappedBy = "office",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<People> peoples;

    public Office() {
    }

    /**
     *
     * @param id
     */
    public Office(String id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param officeName
     */
    public Office(String id, String officeName) {
        this.id = id;
        this.officeName = officeName;
    }

    public Office(String id, String officeName, String address) {
        this.id = id;
        this.officeName = officeName;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OfficeStatus getOfficeStatus() {
        return officeStatus;
    }

    public void setOfficeStatus(OfficeStatus officeStatus) {
        this.officeStatus = officeStatus;
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

    public List<UserByOffice> getUserByOffices() {
        return userByOffices;
    }

    public void setUserByOffices(List<UserByOffice> userByOffices) {
        this.userByOffices = userByOffices;
    }

    public List<HumanResourceByOffice> getHumanResourceByOffices() {
        return humanResourceByOffices;
    }

    public void setHumanResourceByOffices(List<HumanResourceByOffice> humanResourceByOffices) {
        this.humanResourceByOffices = humanResourceByOffices;
    }

    public List<People> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<People> peoples) {
        this.peoples = peoples;
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
        final Office other = (Office) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Office{" + "officeName=" + officeName + ", address=" + address + '}';
    }

}
