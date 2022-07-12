/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.moxy.views;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@XmlRootElement(name = "systemPreferences")
public class LoanByUserOrderPreferenceViewListJaxb {

    List<LoanByUserOrderPreferenceViewJaxb> loanByUserOrderPreferences;

    public LoanByUserOrderPreferenceViewListJaxb() {
    }

    public LoanByUserOrderPreferenceViewListJaxb(List<LoanByUserOrderPreferenceViewJaxb> loanByUserOrderPreferences) {
        this.loanByUserOrderPreferences = loanByUserOrderPreferences;
    }

    @XmlElement(name = "loanByUserOrderPreferences")
    public List<LoanByUserOrderPreferenceViewJaxb> getLoanByUserOrderPreferences() {
        return loanByUserOrderPreferences;
    }

    public void setLoanByUserOrderPreferences(List<LoanByUserOrderPreferenceViewJaxb> loanByUserOrderPreferences) {
        this.loanByUserOrderPreferences = loanByUserOrderPreferences;
    }

}
