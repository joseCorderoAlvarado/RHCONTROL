/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.web.beans.admin;

import com.arrebol.apc.controller.admin.AdministrationPersonSearchViewController;
import com.arrebol.apc.model.enums.PeopleType;
import com.arrebol.apc.model.views.AdministrationPersonSerchView;
import com.arrebol.apc.web.beans.Datatable;
import com.arrebol.apc.web.beans.GenericBean;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public abstract class GenericPersonSearchBean extends GenericBean implements Datatable {

    public void onSearchSinglePersonByNameItemSelect(SelectEvent event) {
        try {
            getPersonList().clear();
            getPersonList().add(getAdminSearchController().administrationPersonSerchViewById(((AdministrationPersonSerchView) event.getObject()).getId()));
        } catch (Exception e) {
            logger.error("onItemSelect", e);
        }
    }

    /**
     *
     * @param type
     */
    protected void fillPersonList(PeopleType type) {
//        try {
//            setPersonList(getAdminSearchController().findPersonByTypeAndRouteAndOffice(type, getLoggedUser().getOffice().getId()));
//        } catch (Exception e) {
//            setPersonList(new ArrayList<>());
//            logger.error("fillPersonList", e);
//        }
    }

    /**
     *
     * @param nameToSearch
     * @param type
     * @return
     */
    protected List<AdministrationPersonSerchView> executeAutoComplete(String nameToSearch, PeopleType type) {
        List<AdministrationPersonSerchView> results;
        try {
            results = getAdminSearchController().likePersonNameInPersonTypeAllRoutesByOffice("%" + nameToSearch + "%", type, getLoggedUser().getOffice().getId());
        } catch (Exception e) {
            results = new ArrayList<>();

            logger.error("executeAutoComplete", e);
        }
        return results;
    }

    /**
     *
     * @param isAll
     * @param peopleType
     */
    protected void executeQueryLike(boolean isAll, PeopleType peopleType) {
        try {
            cleanAndPrepareRoutesDetails(isAll);
            fillPersonListLikePersonName(peopleType);
        } catch (Exception e) {
            logger.error("executeQueryLike", e);
        }
    }

    /**
     *
     * @param type
     */
    private void fillPersonListLikePersonName(PeopleType type) {
//        try {
//            setPersonList(getAdminSearchController().likePersonNameInPersonTypeInRoutesAndOffice("%" + getSearchPersonName() + "%", type, getIdRoutes(), getLoggedUser().getOffice().getId()));
//        } catch (Exception e) {
//            setPersonList(new ArrayList<>());
//            logger.error("fillPersonListLikePersonName", e);
//        }
    }

    /**
     *
     * @param isAll
     */
    private void cleanAndPrepareRoutesDetails(boolean isAll) {
//        getIdRoutes().clear();
//
//        if (isAll) {
//            getRoutes().forEach((t) -> {
//                getIdRoutes().add(t.getId());
//            });
//        } else {
//            getIdRoutes().add(getRouteId());
//        }

    }

    final Logger logger = LogManager.getLogger(getClass());

    private AdministrationPersonSearchViewController adminSearchController;
    private List<AdministrationPersonSerchView> personList;
    private List<String> idRoutes;
    private String routeId;
    private AdministrationPersonSerchView selectedPerson;
    private String searchPersonName;
    private AdministrationPersonSerchView searchSinglePersonByName;

    public AdministrationPersonSearchViewController getAdminSearchController() {
        return adminSearchController;
    }

    public void setAdminSearchController(AdministrationPersonSearchViewController adminSearchController) {
        this.adminSearchController = adminSearchController;
    }

    public List<AdministrationPersonSerchView> getPersonList() {
        if (null == personList) {
            personList = new ArrayList<>();
        }
        return personList;
    }

    public void setPersonList(List<AdministrationPersonSerchView> personList) {
        this.personList = personList;
    }

    public List<String> getIdRoutes() {
        if (null == idRoutes) {
            idRoutes = new ArrayList<>();
        }
        return idRoutes;
    }

    public void setIdRoutes(List<String> idRoutes) {
        this.idRoutes = idRoutes;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public AdministrationPersonSerchView getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(AdministrationPersonSerchView selectedPerson) {
        this.selectedPerson = selectedPerson;
    }

    public String getSearchPersonName() {
        return searchPersonName;
    }

    public void setSearchPersonName(String searchPersonName) {
        this.searchPersonName = searchPersonName;
    }

    public AdministrationPersonSerchView getSearchSinglePersonByName() {
        return searchSinglePersonByName;
    }

    public void setSearchSinglePersonByName(AdministrationPersonSerchView searchSinglePersonByName) {
        this.searchSinglePersonByName = searchSinglePersonByName;
    }

}
