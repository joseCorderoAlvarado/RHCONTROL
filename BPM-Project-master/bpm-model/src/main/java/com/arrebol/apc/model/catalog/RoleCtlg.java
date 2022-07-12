/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.catalog;

import com.arrebol.apc.model.core.HumanResource;
import com.arrebol.apc.model.enums.ActiveStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Table(name = "APC_ROLE",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"role_name"})
        }
)
public class RoleCtlg implements Serializable {

    private static final long serialVersionUID = -7005571878079681478L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "role_name", length = 100, nullable = false)
    private String role;

    @Enumerated(EnumType.STRING)
    @Column(name = "active_status", nullable = false)
    private ActiveStatus activeStatus;

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
            mappedBy = "roleCtlg",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<HumanResource> humanResources;

    public RoleCtlg() {
    }
    
    public RoleCtlg(String id, String role) {
        this.id = id;
        this.role = role;
    }

    public RoleCtlg(String id) {
        this.id = id;
    }
    /**
     * Complete constructor.
     *
     * @param id
     * @param role
     * @param activeStatus
     * @param createdBy
     * @param createdOn
     * @param lastUpdatedBy
     * @param lastUpdatedOn
     */
    public RoleCtlg(String id, String role, ActiveStatus activeStatus, String createdBy, Date createdOn, String lastUpdatedBy, Date lastUpdatedOn) {
        this.id = id;
        this.role = role;
        this.activeStatus = activeStatus;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ActiveStatus getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(ActiveStatus activeStatus) {
        this.activeStatus = activeStatus;
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

    public List<HumanResource> getHumanResources() {
        return humanResources;
    }

    public void setHumanResources(List<HumanResource> humanResources) {
        this.humanResources = humanResources;
    }

    @Override
    public String toString() {
        return "RoleCtlg{" + "role=" + role + '}';
    }

}
