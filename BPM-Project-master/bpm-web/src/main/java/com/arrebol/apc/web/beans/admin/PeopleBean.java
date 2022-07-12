/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.web.beans.admin;

import com.arrebol.apc.controller.admin.PeopleController;
import com.arrebol.apc.model.catalog.People;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.enums.ActiveStatus;
import com.arrebol.apc.model.enums.PeopleType;
import com.arrebol.apc.web.beans.GenericBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Oscar Armando Vargas Cardenas <oscar.vargas@arrebol.com.mx>
 */
@ManagedBean(name = "peopleManager")
@ViewScoped
public class PeopleBean extends GenericBean implements Serializable{

    public void addPeople() {
        try
        {
        logger.debug("addPeople");
        getSavePeople().setCreatedBy(getLoggedUser().getUser().getId());
        getSavePeople().setOffice(new Office(getLoggedUser().getOffice().getId()));
        getSavePeople().setActiveStatus(ActiveStatus.ENEBLED);
//        getSavePeople().setRouteCtlg(new RouteCtlg(routeId));
        getSavePeople().setThumbnail("");
        
        if(isCustomer == true && isEndorsement == false)
            getSavePeople().setPeopleType(PeopleType.CUSTOMER);
        if(isCustomer == true && isEndorsement == true)
            getSavePeople().setPeopleType(PeopleType.BOTH);
        if(isCustomer == false && isEndorsement == true)
            getSavePeople().setPeopleType(PeopleType.ENDORSEMENT);
        if(isCustomer == false && isEndorsement == false)
            getSavePeople().setPeopleType(PeopleType.BOTH);
        
        
        String messafeFormat = getBundlePropertyFile().getString("message.format.failure");
        FacesMessage.Severity severity = FacesMessage.SEVERITY_WARN;
        
        if(getPeopleCtrl().savePeople(getSavePeople())){
            setSavePeople(new People());
            isCustomer = false;
            isEndorsement = false;
            messafeFormat = getBundlePropertyFile().getString("message.format.sucess");
            severity = FacesMessage.SEVERITY_INFO;
        }
        
        Object[] param = {getBundlePropertyFile().getString("people"), getBundlePropertyFile().getString("created")};

        buildAndSendMessage(param, messafeFormat, severity, getBundlePropertyFile().getString("people"));
        
        }catch(Exception e)
        {
            logger.error("savePeople", e);
            Object[] param = {getBundlePropertyFile().getString("created")};

            buildAndSendMessage(
                    param,
                    getBundlePropertyFile().getString("message.format.fatal"),
                    FacesMessage.SEVERITY_FATAL,
                    getBundlePropertyFile().getString("people")
            );
        }
    }
    
    public PeopleController getPeopleCtrl() {
        return peopleCtrl;
    }

    public void setPeopleCtrl(PeopleController peopleCtrl) {
        this.peopleCtrl = peopleCtrl;
    }

    public boolean isIsCustomer() {
        return isCustomer;
    }

    public void setIsCustomer(boolean isCustomer) {
        this.isCustomer = isCustomer;
    }

    public boolean isIsEndorsement() {
        return isEndorsement;
    }

    public void setIsEndorsement(boolean isEndorsement) {
        this.isEndorsement = isEndorsement;
    }

    public People getSavePeople() {
        return savePeople;
    }

    public void setSavePeople(People savePeople) {
        this.savePeople = savePeople;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
   
    final Logger logger = LogManager.getLogger(PeopleBean.class);
    
    private PeopleController peopleCtrl;
    boolean isCustomer;
    boolean isEndorsement;
    public People savePeople;
    String routeId;
    
    @PostConstruct
    public void init() {
        loadBundlePropertyFile();
        peopleCtrl = new PeopleController();
        setSavePeople(new People());
        isCustomer = false;
        isEndorsement = false;
    }
}
