/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.web.beans.system.office;

import com.arrebol.apc.controller.system.office.OfficeController;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.enums.OfficeStatus;
import com.arrebol.apc.web.beans.Datatable;
import com.arrebol.apc.web.beans.GenericBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Oscar Armando Vargas Cardenas <oscar.vargas@arrebol.com.mx>
 */
@ManagedBean(name = "officeManager")
@ViewScoped
public class OfficeBean extends GenericBean implements Serializable, Datatable{

    public List<Office> fillDatatableOffice() {

        return officeCtrl.fillOfficeDatatable();
    }
    
    @Override
    public void editRow(RowEditEvent event) {
        Office office = (Office) event.getObject();
        if (office != null) {
            officeCtrl.updateByOfficeId(office);
            showMessage(FacesMessage.SEVERITY_INFO, "Registro Editado", "Se hizo el cambio correctamente.");
        }    }

    @Override
    public void onRowCancel(RowEditEvent event) {
        showMessage(FacesMessage.SEVERITY_INFO, "Edición Cancelada", ((Office) event.getObject()).getOfficeName());
    }

    @Override
    public void onRowReorder(ReorderEvent event) {
        showMessage(FacesMessage.SEVERITY_INFO, "Registro Movido", "De columna: " + (event.getFromIndex() + 1) + " a columna: " + (event.getToIndex() + 1));
    }

    @Override
    public void addRow() {
        Office officeObj = new Office();
        officeObj.setOfficeName(name);
        officeObj.setAddress(address);
        officeObj.setOfficeStatus(OfficeStatus.ENEBLED);
        officeObj.setCreatedBy(getLoggedUser().getUser().getId());

        officeCtrl.saveOffice(officeObj);
        office.add(officeObj);
        FacesMessage msg = new FacesMessage("Nueva oficina", "Se agregó correctamente");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    @Override
    public void deleteRow() {
        officeCtrl.updateOfficeByStatus(OfficeStatus.DISABLED, selectedOffice.getId(), getLoggedUser().getUser().getId());
        office.remove(selectedOffice);
        selectedOffice = null;
        showMessage(FacesMessage.SEVERITY_INFO, "Oficina eliminada", "Se eliminó correctamente.");
    }

    public OfficeController getOfficeCtrl() {
        return officeCtrl;
    }

    public void setOfficeCtrl(OfficeController officeCtrl) {
        this.officeCtrl = officeCtrl;
    }

    public List<Office> getOffice() {
        return office;
    }

    public void setOffice(List<Office> office) {
        this.office = office;
    }

    public Office getSelectedOffice() {
        return selectedOffice;
    }

    public void setSelectedOffice(Office selectedOffice) {
        this.selectedOffice = selectedOffice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    private OfficeController officeCtrl;
    
    private List<Office> office;
    
    private Office selectedOffice;

    private String id;
    private String name;
    private String address;
    
    @PostConstruct
    public void init() {
        officeCtrl = new OfficeController();
        office = fillDatatableOffice();
    }
    
}
