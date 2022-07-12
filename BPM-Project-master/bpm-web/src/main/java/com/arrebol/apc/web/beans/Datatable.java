/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.web.beans;

import java.util.List;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Oscar Armando Vargas Cardenas <oscar.vargas@arrebol.com.mx>
 */
public interface Datatable {
    
    public void editRow(RowEditEvent event);

    public void onRowCancel(RowEditEvent event);

    public void onRowReorder(ReorderEvent event);

    public void addRow();

    public void deleteRow();
        
}
