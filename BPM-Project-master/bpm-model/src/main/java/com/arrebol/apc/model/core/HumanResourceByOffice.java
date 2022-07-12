/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.core;

import com.arrebol.apc.model.enums.ApplicationOwner;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "APC_HUMAN_RESOURCE_BY_OFFICE",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"id_human_resource", "id_office"}, name = "apc_human_resource_by_office_uk")
        })
public class HumanResourceByOffice implements Serializable {

    private static final long serialVersionUID = -2892820400055813543L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_human_resource",
            referencedColumnName = "id",
            nullable = false
    )
    private HumanResource humanResource;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_office",
            referencedColumnName = "id",
            nullable = false
    )
    private Office office;

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

    public HumanResourceByOffice() {
    }

    /**
     *
     * @param id
     * @param humanResource
     * @param office
     */
    public HumanResourceByOffice(String id, HumanResource humanResource, Office office) {
        this.id = id;
        this.humanResource = humanResource;
        this.office = office;
    }

    /**
     *
     * @param office
     * @param createdBy
     * @param createdOn
     * @param applicationOwner
     */
    public HumanResourceByOffice(Office office, String createdBy, Date createdOn, ApplicationOwner applicationOwner) {
        this.office = office;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.applicationOwner = applicationOwner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HumanResource getHumanResource() {
        return humanResource;
    }

    public void setHumanResource(HumanResource humanResource) {
        this.humanResource = humanResource;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
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

    @Override
    public String toString() {
        return "HumanResourceByOffice{" + "humanResource=" + humanResource + ", office=" + office + '}';
    }

}
