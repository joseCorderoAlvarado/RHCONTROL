/*
 * Arrebol Consultancy copyright
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.core;

import com.arrebol.apc.model.enums.ActiveStatus;
import com.arrebol.apc.model.enums.ApplicationOwner;
import com.arrebol.apc.model.enums.UserStatus;
import com.arrebol.apc.model.enums.UserType;
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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Entity
@Table(name = "APC_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 5293919946950359645L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_human_resource",
            referencedColumnName = "id",
            nullable = false
    )
    private HumanResource humanResource;

    @Column(name = "user_name", length = 100, nullable = false)
    private String userName;

    @Column(name = "pwd", length = 100, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false)
    private UserStatus userStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_owner", nullable = false)
    private ApplicationOwner applicationOwner;

    @Enumerated(EnumType.STRING)
    @Column(name = "certifier", nullable = false)
    private ActiveStatus certifier;

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
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserByOffice> userByOffices;

    public User() {
    }

    public User(String id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param userName
     */
    public User(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    /**
     *
     * @param id
     * @param userName
     * @param humanResource
     */
    public User(String id, String userName, HumanResource humanResource) {
        this.id = id;
        this.userName = userName;
        this.humanResource = humanResource;
    }

    /**
     *
     * @param id
     * @param humanResource
     * @param userName
     * @param certifier
     * @param userType
     */
    public User(String id, HumanResource humanResource, String userName, ActiveStatus certifier, UserType userType) {
        this.id = id;
        this.humanResource = humanResource;
        this.userName = userName;
        this.certifier = certifier;
        this.userType = userType;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public ApplicationOwner getApplicationOwner() {
        return applicationOwner;
    }

    public void setApplicationOwner(ApplicationOwner applicationOwner) {
        this.applicationOwner = applicationOwner;
    }

    public ActiveStatus getCertifier() {
        return certifier;
    }

    public void setCertifier(ActiveStatus certifier) {
        this.certifier = certifier;
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

    @Override
    public String toString() {
        return "User{" + "userName=" + userName + '}';
    }
}
