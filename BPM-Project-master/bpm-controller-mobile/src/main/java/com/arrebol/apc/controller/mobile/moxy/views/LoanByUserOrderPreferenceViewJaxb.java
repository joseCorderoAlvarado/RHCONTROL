/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.moxy.views;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@XmlRootElement(name = "loanByUserOrderPreference")
public class LoanByUserOrderPreferenceViewJaxb {

    private String id;

    private String userId;

    private String customerName;

    private String customerAddressHome;

    private String customerAddressBusiness;

    private Integer orderInList;

    public LoanByUserOrderPreferenceViewJaxb() {
    }

    /**
     *
     * @param id
     * @param userId
     * @param orderInList
     */
    public LoanByUserOrderPreferenceViewJaxb(String id, String userId, Integer orderInList) {
        this.id = id;
        this.userId = userId;
        this.orderInList = orderInList;
    }

    /**
     *
     * @param customerName
     * @param orderInList
     */
    public LoanByUserOrderPreferenceViewJaxb(String customerName, Integer orderInList) {
        this.customerName = customerName;
        this.orderInList = orderInList;
    }

    @XmlAttribute(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute(name = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @XmlAttribute(name = "customerName")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @XmlAttribute(name = "customerAddressHome")
    public String getCustomerAddressHome() {
        return customerAddressHome;
    }

    public void setCustomerAddressHome(String customerAddressHome) {
        this.customerAddressHome = customerAddressHome;
    }

    @XmlAttribute(name = "customerAddressBusiness")
    public String getCustomerAddressBusiness() {
        return customerAddressBusiness;
    }

    public void setCustomerAddressBusiness(String customerAddressBusiness) {
        this.customerAddressBusiness = customerAddressBusiness;
    }

    @XmlAttribute(name = "orderInList")
    public Integer getOrderInList() {
        return orderInList;
    }

    public void setOrderInList(Integer orderInList) {
        this.orderInList = orderInList;
    }

    @Override
    public String toString() {
        return "LoanByUserOrderPreferenceViewJaxb{" + "customerName=" + customerName + ", orderInList=" + orderInList + '}';
    }

}
