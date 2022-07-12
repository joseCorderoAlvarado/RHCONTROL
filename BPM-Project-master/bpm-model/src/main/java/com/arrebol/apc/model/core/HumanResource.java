/*
 * Arrebol Consultancy copyright
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.core;

import com.arrebol.apc.model.catalog.HumanResourceHasRoute;
import com.arrebol.apc.model.catalog.RoleCtlg;
import com.arrebol.apc.model.enums.HumanResourceStatus;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Entity
@Table(name = "APC_HUMAN_RESOURCE")
public class HumanResource implements Serializable {

    private static final long serialVersionUID = -7296859753095844932L;

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

    @Column(name = "avatar", length = 150, nullable = false)
    private String avatar;

    @Column(name = "curp", length = 20)
    private String curp;

    @Column(name = "rfc", length = 13)
    private String rfc;

    @Column(name = "ife", length = 20)
    private String ife;

    @Temporal(TemporalType.DATE)
    @Column(name = "admission_date")
    private Date admissionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "human_resource_status", nullable = false)
    private HumanResourceStatus humanResourceStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_role",
            referencedColumnName = "id",
            nullable = false
    )
    private RoleCtlg roleCtlg;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_bonus",
            referencedColumnName = "id"
    )
    private Bonus bonus;

    @Column(name = "payment")
    private BigDecimal payment;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "imss")
    private BigDecimal imss;

    @Column(name = "employee_saving")
    private BigDecimal employeeSaving;

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

    @OneToOne(
            mappedBy = "humanResource",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private User user;

    @OneToMany(
            mappedBy = "humanResource",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<HumanResourceByOffice> humanResourceByOffices;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "humanResource",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<HumanResourceHasRoute> humanResourceHasRoutes;

    @Transient
    private String fullName;

    public HumanResource() {
    }

    public HumanResource(String id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param fullName
     */
    public HumanResource(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param avatar
     */
    public HumanResource(String id, String firstName, String lastName, String avatar) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param avatar
     */
    public HumanResource(String firstName, String lastName, String avatar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getIfe() {
        return ife;
    }

    public void setIfe(String ife) {
        this.ife = ife;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public HumanResourceStatus getHumanResourceStatus() {
        return humanResourceStatus;
    }

    public void setHumanResourceStatus(HumanResourceStatus humanResourceStatus) {
        this.humanResourceStatus = humanResourceStatus;
    }

    public RoleCtlg getRoleCtlg() {
        return roleCtlg;
    }

    public void setRoleCtlg(RoleCtlg roleCtlg) {
        this.roleCtlg = roleCtlg;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getEmployeeSaving() {
        return employeeSaving;
    }

    public void setEmployeeSaving(BigDecimal employeeSaving) {
        this.employeeSaving = employeeSaving;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getImss() {
        return imss;
    }

    public void setImss(BigDecimal imss) {
        this.imss = imss;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<HumanResourceByOffice> getHumanResourceByOffices() {
        return humanResourceByOffices;
    }

    public void setHumanResourceByOffices(List<HumanResourceByOffice> humanResourceByOffices) {
        this.humanResourceByOffices = humanResourceByOffices;
    }

    public List<HumanResourceHasRoute> getHumanResourceHasRoutes() {
        return humanResourceHasRoutes;
    }

    public void setHumanResourceHasRoutes(List<HumanResourceHasRoute> humanResourceHasRoutes) {
        this.humanResourceHasRoutes = humanResourceHasRoutes;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "HumanResource{" + "firstName=" + firstName + ", secondName=" + secondName + ", lastName=" + lastName + ", middleName=" + middleName + '}';
    }

}
