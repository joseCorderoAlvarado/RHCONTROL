/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.core;

import com.arrebol.apc.model.enums.ActiveStatus;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "APC_BONUS")
public class Bonus implements Serializable {

    private static final long serialVersionUID = 3584728331941248013L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "loan_bonus", nullable = false)
    private BigDecimal loanBonus;

    @Column(name = "mora_bonus", nullable = false)
    private BigDecimal moraBonus;

    @Column(name = "new_customer_bonus", nullable = false)
    private BigDecimal newCustomerBonus;

    @Enumerated(EnumType.STRING)
    @Column(name = "administrative", nullable = false)
    private ActiveStatus administrative;

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

    public Bonus() {
    }

    /**
     *
     * @param id
     */
    public Bonus(String id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param name
     */
    public Bonus(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLoanBonus() {
        return loanBonus;
    }

    public void setLoanBonus(BigDecimal loanBonus) {
        this.loanBonus = loanBonus;
    }

    public BigDecimal getMoraBonus() {
        return moraBonus;
    }

    public void setMoraBonus(BigDecimal moraBonus) {
        this.moraBonus = moraBonus;
    }

    public BigDecimal getNewCustomerBonus() {
        return newCustomerBonus;
    }

    public void setNewCustomerBonus(BigDecimal newCustomerBonus) {
        this.newCustomerBonus = newCustomerBonus;
    }

    public ActiveStatus getAdministrative() {
        return administrative;
    }

    public void setAdministrative(ActiveStatus administrative) {
        this.administrative = administrative;
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

    @Override
    public String toString() {
        return "Bonus{" + "name=" + name + ", createdOn=" + createdOn + '}';
    }

}
