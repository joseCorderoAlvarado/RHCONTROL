/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.views;

import com.arrebol.apc.model.enums.PeopleType;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Entity
@Immutable
@Table(name = "APC_ADMINISTRATION_PERSON_SEARCH_VIEW")
public class AdministrationPersonSerchView implements Serializable {

    private static final long serialVersionUID = 3055231364366461069L;

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "phone_home", length = 15)
    private String phoneHome;

    @Column(name = "address_home", length = 150)
    private String addressHome;

    @Enumerated(EnumType.STRING)
    @Column(name = "people_type")
    private PeopleType peopleType;

    @Column(name = "id_route", length = 36)
    private String idRoute;

    @Column(name = "route_name", length = 25)
    private String routeName;

    @Column(name = "id_office", length = 36)
    private String idOffice;

    @Column(name = "office_name", length = 100)
    private String officeName;

    @Column(name = "person_search", length = 103)
    private String personSearch;

    public AdministrationPersonSerchView() {
    }

    /**
     *
     * @param id
     */
    public AdministrationPersonSerchView(String id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param phoneHome
     * @param addressHome
     * @param peopleType
     * @param idRoute
     * @param routeName
     * @param idOffice
     * @param officeName
     * @param personSearch
     */
    public AdministrationPersonSerchView(String id, String phoneHome, String addressHome, PeopleType peopleType, String idRoute, String routeName, String idOffice, String officeName, String personSearch) {
        this.id = id;
        this.phoneHome = phoneHome;
        this.addressHome = addressHome;
        this.peopleType = peopleType;
        this.idRoute = idRoute;
        this.routeName = routeName;
        this.idOffice = idOffice;
        this.officeName = officeName;
        this.personSearch = personSearch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public String getAddressHome() {
        return addressHome;
    }

    public void setAddressHome(String addressHome) {
        this.addressHome = addressHome;
    }

    public PeopleType getPeopleType() {
        return peopleType;
    }

    public void setPeopleType(PeopleType peopleType) {
        this.peopleType = peopleType;
    }

    public String getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(String idRoute) {
        this.idRoute = idRoute;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getIdOffice() {
        return idOffice;
    }

    public void setIdOffice(String idOffice) {
        this.idOffice = idOffice;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getPersonSearch() {
        return personSearch;
    }

    public void setPersonSearch(String personSearch) {
        this.personSearch = personSearch;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final AdministrationPersonSerchView other = (AdministrationPersonSerchView) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AdministrationPersonSerchView{" + "routeName=" + routeName + ", officeName=" + officeName + ", personSearch=" + personSearch + '}';
    }

}
