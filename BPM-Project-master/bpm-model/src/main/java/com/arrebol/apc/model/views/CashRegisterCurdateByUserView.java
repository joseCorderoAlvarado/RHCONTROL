/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.views;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "APC_CASH_REGISTER_CURDATE_BY_USER_VIEW")
public class CashRegisterCurdateByUserView implements Serializable {

    private static final long serialVersionUID = -3932800670151972950L;

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "payment")
    private BigDecimal payment;

    @Column(name = "customer_name", length = 51)
    private String customerName;

    @Column(name = "id_user", length = 36)
    private String userId;

    public CashRegisterCurdateByUserView() {
    }

    public CashRegisterCurdateByUserView(String id, BigDecimal payment, String customerName, String userId) {
        this.id = id;
        this.payment = payment;
        this.customerName = customerName;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CashRegisterCurdateByUserView{" + "customerName=" + customerName + '}';
    }

}
