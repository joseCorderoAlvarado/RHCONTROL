/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.views;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Entity
@Immutable
@Table(name = "APC_USER_BY_ROUTE_VIEW")
public class UserByRouteView implements Serializable {

    private static final long serialVersionUID = -4547463041660221310L;

    @Id
    @Column(name = "id", length = 72)
    private String id;

    @Column(name = "id_user", length = 36)
    private String idUser;

    @Column(name = "id_route", length = 36)
    private String idRoute;

    @Column(name = "employee_name", length = 103)
    private String employeeName;

    public UserByRouteView() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(String idRoute) {
        this.idRoute = idRoute;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public String toString() {
        return "UserByRouteView{" + "employeeName=" + employeeName + '}';
    }

}
