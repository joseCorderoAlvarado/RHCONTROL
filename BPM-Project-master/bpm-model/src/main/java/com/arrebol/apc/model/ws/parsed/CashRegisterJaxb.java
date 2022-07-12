/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.ws.parsed;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@XmlRootElement(name = "cashRegister")
public class CashRegisterJaxb {

    private Double totalCash;
    private List<AmountJaxb> amounts;

    public CashRegisterJaxb() {
    }

    public CashRegisterJaxb(Double totalCash, List<AmountJaxb> amounts) {
        this.totalCash = totalCash;
        this.amounts = amounts;
    }

    @XmlElement(name = "totalCash")
    public Double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(Double totalCash) {
        this.totalCash = totalCash;
    }

    @XmlElement(name = "amounts")
    public List<AmountJaxb> getAmounts() {
        return amounts;
    }

    public void setAmounts(List<AmountJaxb> amounts) {
        this.amounts = amounts;
    }

    @Override
    public String toString() {
        return "CashRegisterJaxb{" + "amounts=" + amounts + '}';
    }

}
