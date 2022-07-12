/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.web.beans.catalog;

import com.arrebol.apc.controller.catalog.RoleController;
import com.arrebol.apc.model.catalog.RoleCtlg;
import com.arrebol.apc.model.enums.ActiveStatus;
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
@ManagedBean(name = "roleManager")
@ViewScoped
public class RoleBean extends GenericBean implements Serializable, Datatable {
    
    public List<RoleCtlg> fillDatatableRole() {

        return roleCtrl.fillRolesDatatable();
    }
    
    @Override
    public void editRow(RowEditEvent event) {
        RoleCtlg role = (RoleCtlg) event.getObject();
        if (role != null) {
            roleCtrl.updateByRoleId(role);
            showMessage(FacesMessage.SEVERITY_INFO, "Registro Editado", "Se hizo el cambio correctamente.");
        }
    }
    
    @Override
    public void addRow() {
        RoleCtlg roleObj = new RoleCtlg();
        roleObj.setRole(name);
        roleObj.setActiveStatus(ActiveStatus.ENEBLED);
        roleObj.setCreatedBy(getLoggedUser().getUser().getId());

        roleCtrl.saveRoles(roleObj);
        role.add(roleObj);
        FacesMessage msg = new FacesMessage("Nuevo puesto", "Se agregó correctamente");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    @Override
    public void deleteRow() {
        roleCtrl.updateRoleByStatus(ActiveStatus.DISABLED, selectedRole.getId(), getLoggedUser().getUser().getId());
        role.remove(selectedRole);
        selectedRole = null;
        showMessage(FacesMessage.SEVERITY_INFO, "Puesto Eliminado", "Se eliminó correctamente.");
    }

    @Override
    public void onRowCancel(RowEditEvent event) {
        showMessage(FacesMessage.SEVERITY_INFO, "Edición Cancelada", ((RoleCtlg) event.getObject()).getRole());
    }

    @Override
    public void onRowReorder(ReorderEvent event) {
        showMessage(FacesMessage.SEVERITY_INFO, "Registro Movido", "De columna: " + (event.getFromIndex() + 1) + " a columna: " + (event.getToIndex() + 1));
    }
    
    public RoleController getRoleCtrl() {
        return roleCtrl;
    }

    public void setRoleCtrl(RoleController roleCtrl) {
        this.roleCtrl = roleCtrl;
    }

    public List<RoleCtlg> getRole() {
        return role;
    }

    public void setRole(List<RoleCtlg> role) {
        this.role = role;
    }

    public RoleCtlg getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(RoleCtlg selectedRole) {
        this.selectedRole = selectedRole;
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
    
    private RoleController roleCtrl;
    
    private List<RoleCtlg> role;
    
    private RoleCtlg selectedRole;

    private String id;
    private String name;
    
    @PostConstruct
    public void init() {
        roleCtrl = new RoleController();
        role = fillDatatableRole();
    }
}
