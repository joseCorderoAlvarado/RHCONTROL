/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.reports;

import java.io.Serializable;
import java.util.Objects;
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
@Table(name = "APC_USER_WEEK_REPORT_VIEW")
public class UserWeekReport implements Serializable {

    private static final long serialVersionUID = -1257291916093653466L;

    @Id
    @Column(name = "id_user", length = 36)
    private String id;

    @Column(name = "payment_week")
    private Double paymentWeek;

    @Column(name = "total_new_customer_currweek")
    private Integer totalNewCustomerCurrweek;

    @Column(name = "debit_week")
    private Double debitWeek;

    public UserWeekReport() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPaymentWeek() {
        return paymentWeek;
    }

    public void setPaymentWeek(Double paymentWeek) {
        this.paymentWeek = paymentWeek;
    }

    public Integer getTotalNewCustomerCurrweek() {
        return totalNewCustomerCurrweek;
    }

    public void setTotalNewCustomerCurrweek(Integer totalNewCustomerCurrweek) {
        this.totalNewCustomerCurrweek = totalNewCustomerCurrweek;
    }

    public Double getDebitWeek() {
        return debitWeek;
    }

    public void setDebitWeek(Double debitWeek) {
        this.debitWeek = debitWeek;
    }

    @Override
    public String toString() {
        return "UserWeekReport{" + "id=" + id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final UserWeekReport other = (UserWeekReport) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
