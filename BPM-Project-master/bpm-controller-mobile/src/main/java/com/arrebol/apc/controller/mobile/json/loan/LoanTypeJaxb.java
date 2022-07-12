/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.json.loan;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@XmlRootElement(name = "loan")
public class LoanTypeJaxb {

    private String id;
    private BigDecimal payment;

    public LoanTypeJaxb() {
    }

    public LoanTypeJaxb(String id, BigDecimal payment) {
        this.id = id;
        this.payment = payment;
    }

    @XmlElement(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "payment")
    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "LoanTypeJaxb{" + "payment=" + payment + '}';
    }

}
