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
@Table(name = "APC_PERSON_SEARCH_HISTORICAL_DETAILS_VIEW")
public class PersonSearchHistoricalDetailsView implements Serializable {

    private static final long serialVersionUID = 6263674846643349142L;

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "id_person_search", length = 36)
    private String personSearchId;

    @Column(name = "format_date", length = 10)
    private String createdOn;

    @Column(name = "person_type", length = 7)
    private String personType;

    @Column(name = "relationship", length = 51)
    private String relationShip;

    @Column(name = "loan")
    private Double loan;

    @Column(name = "payment_number", length = 8)
    private String paymentNumber;

    @Column(name = "amount_to_pay")
    private Double amountToPay;

    @Column(name = "total_fees", length = 2)
    private String totalFees;

    @Column(name = "loan_status", length = 25)
    private String status;

    public PersonSearchHistoricalDetailsView() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonSearchId() {
        return personSearchId;
    }

    public void setPersonSearchId(String personSearchId) {
        this.personSearchId = personSearchId;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public Double getLoan() {
        return loan;
    }

    public void setLoan(Double loan) {
        this.loan = loan;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public Double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(Double amountToPay) {
        this.amountToPay = amountToPay;
    }

    public String getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(String totalFees) {
        this.totalFees = totalFees;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PersonSearchHistoricalDetailsView{" + "createdOn=" + createdOn + ", personType=" + personType + ", status=" + status + '}';
    }

}
