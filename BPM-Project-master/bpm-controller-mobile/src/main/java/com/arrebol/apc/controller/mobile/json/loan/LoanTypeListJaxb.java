/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.json.loan;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@XmlRootElement(name = "availableLoans")
public class LoanTypeListJaxb {

    private List<LoanTypeJaxb> loans;

    public LoanTypeListJaxb() {
    }

    /**
     *
     * @param loans
     */
    public LoanTypeListJaxb(List<LoanTypeJaxb> loans) {
        this.loans = loans;
    }

    @XmlElement(name = "loans")
    public List<LoanTypeJaxb> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanTypeJaxb> loans) {
        this.loans = loans;
    }

    @Override
    public String toString() {
        return "LoanTypeListJaxb{" + "loans=" + loans + '}';
    }

}
