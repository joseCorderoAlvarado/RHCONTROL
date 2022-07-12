/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.catalog;

import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.enums.ActiveStatus;
import com.arrebol.apc.model.enums.CustomerClassification;
import com.arrebol.apc.model.enums.PeopleType;
import com.arrebol.apc.model.ws.parsed.PersonJaxb;
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
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Entity
@Table(name = "APC_PEOPLE")
public class People implements Serializable {

    private static final long serialVersionUID = -3628564854853325265L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "first_name", length = 25, nullable = false)
    private String firstName;

    @Column(name = "second_name", length = 25)
    private String secondName;

    @Column(name = "last_name", length = 25, nullable = false)
    private String lastName;

    @Column(name = "middle_name", length = 25, nullable = false)
    private String middleName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "thumbnail", length = 250, nullable = false)
    private String thumbnail;

    @Column(name = "phone_home", length = 15, nullable = false)
    private String phoneHome;

    @Column(name = "address_home", length = 150, nullable = false)
    private String addressHome;

    @Column(name = "phone_business", length = 15)
    private String phoneBusiness;

    @Column(name = "address_business", length = 150)
    private String addressBusiness;

    @Column(name = "company_name", length = 150)
    private String companyName;

    @Enumerated(EnumType.STRING)
    @Column(name = "people_type", nullable = false)
    private PeopleType peopleType;

    @Enumerated(EnumType.STRING)
    @Column(name = "active_status", nullable = false)
    private ActiveStatus activeStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_office",
            referencedColumnName = "id",
            nullable = false
    )
    private Office office;

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
    
    @Enumerated(EnumType.STRING)
    @Column(name = "classification", nullable = false)
    private CustomerClassification classification;

    public People() {
    }

    /**
     *
     * @param id
     */
    public People(String id) {
        this.id = id;
    }

    /**
     * Complete constructor.
     *
     * @param id
     * @param firstName
     * @param secondName
     * @param lastName
     * @param middleName
     * @param birthdate
     * @param phoneHome
     * @param addressHome
     * @param phoneBusiness
     * @param addressBusiness
     * @param companyName
     * @param peopleType
     * @param activeStatus
     * @param office
     * @param createdBy
     * @param createdOn
     * @param lastUpdatedBy
     * @param lastUpdatedOn
     */
    public People(String id, String firstName, String secondName, String lastName, String middleName, Date birthdate, String phoneHome, String addressHome, String phoneBusiness, String addressBusiness, String companyName, PeopleType peopleType, ActiveStatus activeStatus, Office office, String createdBy, Date createdOn, String lastUpdatedBy, Date lastUpdatedOn) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthdate = birthdate;
        this.phoneHome = phoneHome;
        this.addressHome = addressHome;
        this.phoneBusiness = phoneBusiness;
        this.addressBusiness = addressBusiness;
        this.companyName = companyName;
        this.peopleType = peopleType;
        this.activeStatus = activeStatus;
        this.office = office;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedOn = lastUpdatedOn;
    }

    /**
     *
     * @param jaxb
     * @param isCustomer
     * @param officeId
     * @param userId
     * @param routeId
     */
    public People(PersonJaxb jaxb, boolean isCustomer, String officeId, String userId, String routeId) {
        this.firstName = jaxb.getFirstName();
        this.secondName = jaxb.getSecondName();
        this.lastName = jaxb.getLastName();
        this.middleName = jaxb.getMiddleName();
        this.phoneHome = jaxb.getPhoneHome();
        this.addressHome = jaxb.getAddressHome();

        if (isCustomer) {
            this.phoneBusiness = jaxb.getPhoneWork();
            this.addressBusiness = jaxb.getAddressWork();
            this.companyName = jaxb.getCompanyName();
            this.peopleType = PeopleType.CUSTOMER;
        } else {
            this.peopleType = PeopleType.ENDORSEMENT;
        }

        this.activeStatus = ActiveStatus.ENEBLED;
        this.office = new Office(officeId);
        this.thumbnail = jaxb.getThumbnail();
        this.createdBy = userId;
        this.createdOn = new Date();
        this.classification = CustomerClassification.WHITE;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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

    public String getPhoneBusiness() {
        return phoneBusiness;
    }

    public void setPhoneBusiness(String phoneBusiness) {
        this.phoneBusiness = phoneBusiness;
    }

    public String getAddressBusiness() {
        return addressBusiness;
    }

    public void setAddressBusiness(String addressBusiness) {
        this.addressBusiness = addressBusiness;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public PeopleType getPeopleType() {
        return peopleType;
    }

    public void setPeopleType(PeopleType peopleType) {
        this.peopleType = peopleType;
    }

    public ActiveStatus getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(ActiveStatus activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
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
    
    public CustomerClassification getClassification() {
        return classification;
    }

    public void setClassification(CustomerClassification classification) {
        this.classification = classification;
    }

    public String getFullName()
    {
        String name = "";
        if(secondName == null || secondName.equalsIgnoreCase("null") || secondName.isEmpty())
            return firstName.trim() + " " + lastName.trim() + " " + middleName.trim();
        else
            return firstName.trim() + " " + secondName.trim() + " " + lastName.trim() + " " + middleName.trim();
    }

}
