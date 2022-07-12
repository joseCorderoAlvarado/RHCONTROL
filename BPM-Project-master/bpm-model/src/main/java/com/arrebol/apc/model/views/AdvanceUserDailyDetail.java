/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.views;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author Oscar Armando Vargas Cardenas <oscar.vargas@arrebol.com.mx>
 */
@Entity
@Immutable
@Table(name = "APC_ADVANCE_USER_DAILY_DETAIL_VIEW")
public class AdvanceUserDailyDetail implements Serializable{

    private static final long serialVersionUID = -4849944622024557288L;
    
    @Id
    @Column(name = "id", length = 36)
    private String id;
    
    @Column(name = "id_user")
    private String idUser;
    
    @Column(name = "customer_name")
    private String customerName;
    
    @Column(name = "payment_amount")
    private String paymentAmount;
    
    @Column(name = "created_on")
    private Date createdOn;
    
    @Column(name = "address_business")
    private String address;
    
    @Column(name = "type_ayment")
    private String typePayment;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }
   
    
}
