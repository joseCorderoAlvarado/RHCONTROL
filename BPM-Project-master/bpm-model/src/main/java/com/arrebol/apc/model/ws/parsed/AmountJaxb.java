/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.ws.parsed;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@XmlRootElement(name = "amount")
public class AmountJaxb {

    private String customerName;
    private Double payment;

    public AmountJaxb() {
    }

    public AmountJaxb(String customerName, Double payment) {
        this.customerName = customerName;
        this.payment = payment;
    }

    @XmlElement(name = "customerName")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @XmlElement(name = "payment")
    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "AmountJaxb{" + "customerName=" + customerName + '}';
    }

}
