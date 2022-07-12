/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.web.beans.system.bitacora;

import com.arrebol.apc.controller.BitacoraController;

import com.arrebol.apc.model.system.logs.Bitacora;
import com.arrebol.apc.web.beans.Datatable;
import com.arrebol.apc.web.beans.GenericBean;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Oscar Armando Vargas Cardenas <oscar.vargas@arrebol.com.mx>
 */
@ManagedBean(name = "bitacoraManager")
@ViewScoped
public class BitacoraBean extends GenericBean implements Serializable, Datatable {

    public void searchHistoricalAction() {
        try {
            if (getStarDate().after(getEndDate())) {
                showMessage(FacesMessage.SEVERITY_ERROR, getBundlePropertyFile().getString("generic.start.date"), getBundlePropertyFile().getString("generic.end.date.error"));
            } else {
                setBitacora(fillDatatableBitacoraByDate());
            }
        } catch (Exception e) {
        }
    }

    public void searchAllAction() {
        try {
            if (getStarDate().after(getEndDate())) {
                showMessage(FacesMessage.SEVERITY_ERROR, getBundlePropertyFile().getString("generic.start.date"), getBundlePropertyFile().getString("generic.end.date.error"));
            } else {
                setBitacora(fillDatatableBitacora());
            }
        } catch (Exception e) {
        }
    }
    
    public List<Bitacora> fillDatatableBitacora() {

        return bitacoraCtrl.fillBitacoraDatatable(getLoggedUser().getOffice().getId());
    }
    
    public List<Bitacora> fillDatatableBitacoraByDate() {

        return bitacoraCtrl.fillBitacoraDatatableByDate(getLoggedUser().getOffice().getId(), getStarDate(), getEndDate());
    }

    @Override
    public void editRow(RowEditEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onRowCancel(RowEditEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onRowReorder(ReorderEvent event) {
        showMessage(FacesMessage.SEVERITY_INFO, "Registro Movido", "De columna: " + (event.getFromIndex() + 1) + " a columna: " + (event.getToIndex() + 1));
    }

    @Override
    public void addRow() {
    }

    @Override
    public void deleteRow() {

    }

    public BitacoraController getBitacoraCtrl() {
        return bitacoraCtrl;
    }

    public void setBitacoraCtrl(BitacoraController bitacoraCtrl) {
        this.bitacoraCtrl = bitacoraCtrl;
    }

    public List<Bitacora> getBitacora() {
        return bitacora;
    }

    public void setBitacora(List<Bitacora> bitacora) {
        this.bitacora = bitacora;
    }

    public Bitacora getSelectedBitacora() {
        return selectedBitacora;
    }

    public void setSelectedBitacora(Bitacora selectedBitacora) {
        this.selectedBitacora = selectedBitacora;
    }

    private BitacoraController bitacoraCtrl;

    private List<Bitacora> bitacora;

    private Bitacora selectedBitacora;

    @PostConstruct
    public void init() {
        loadBundlePropertyFile();
        initStartAndEndDates(Calendar.DAY_OF_WEEK, 2);
        bitacoraCtrl = new BitacoraController();
        bitacora = fillDatatableBitacora();
    }

}
