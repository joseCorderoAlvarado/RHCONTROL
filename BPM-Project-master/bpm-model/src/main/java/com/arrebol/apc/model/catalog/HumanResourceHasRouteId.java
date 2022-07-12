/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.catalog;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Embeddable
public class HumanResourceHasRouteId implements Serializable {

    private static final long serialVersionUID = -2805382610552215637L;

    @Column(name = "id_human_resource", length = 36, nullable = true)
    private String idHumanResource;

    @Column(name = "id_route", length = 36, nullable = true)
    private String idRoute;

    public HumanResourceHasRouteId() {
    }

    /**
     *
     * @param idHumanResource
     * @param idRoute
     */
    public HumanResourceHasRouteId(String idHumanResource, String idRoute) {
        this.idHumanResource = idHumanResource;
        this.idRoute = idRoute;
    }

    public String getIdHumanResource() {
        return idHumanResource;
    }

    public void setIdHumanResource(String idHumanResource) {
        this.idHumanResource = idHumanResource;
    }

    public String getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(String idRoute) {
        this.idRoute = idRoute;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.idHumanResource);
        hash = 17 * hash + Objects.hashCode(this.idRoute);
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
        final HumanResourceHasRouteId other = (HumanResourceHasRouteId) obj;
        if (!Objects.equals(this.idHumanResource, other.idHumanResource)) {
            return false;
        }
        if (!Objects.equals(this.idRoute, other.idRoute)) {
            return false;
        }
        return true;
    }

}
