/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.catalog;

import com.arrebol.apc.model.core.HumanResource;
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
@Table(name = "APC_HUMAN_RESOURCE_HAS_ROUTE")
public class HumanResourceHasRoute implements Serializable {

    private static final long serialVersionUID = -8746549036588217517L;

    @EmbeddedId
    private HumanResourceHasRouteId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_human_resource",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private HumanResource humanResource;

    @Column(name = "created_by", nullable = false, length = 36)
    private String createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_route",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private RouteCtlg routeCtlg;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    private Date createdOn;

    @Column(name = "last_updated_by", length = 36)
    private String lastUpdatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_on", length = 19)
    private Date lastUpdatedOn;

    public HumanResourceHasRoute() {
    }

    /**
     *
     * @param id
     */
    public HumanResourceHasRoute(HumanResourceHasRouteId id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param createdBy
     */
    public HumanResourceHasRoute(HumanResourceHasRouteId id, String createdBy) {
        this.id = id;
        this.createdBy = createdBy;
        createdOn = new Date();
    }

    public HumanResourceHasRouteId getId() {
        return id;
    }

    public void setId(HumanResourceHasRouteId id) {
        this.id = id;
    }

    public HumanResource getHumanResource() {
        return humanResource;
    }

    public void setHumanResource(HumanResource humanResource) {
        this.humanResource = humanResource;
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

        public RouteCtlg getRouteCtlg() {
        return routeCtlg;
    }

    public void setRouteCtlg(RouteCtlg routeCtlg) {
        this.routeCtlg = routeCtlg;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final HumanResourceHasRoute other = (HumanResourceHasRoute) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
