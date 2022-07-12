/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.web.converters;

import com.arrebol.apc.model.views.AdministrationPersonSerchView;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@FacesConverter("perseonSearchConverter")
public class PerseonSearchConverter implements Converter<AdministrationPersonSerchView> {

    @Override
    public AdministrationPersonSerchView getAsObject(FacesContext context, UIComponent component, String id) {
        try {
            return new AdministrationPersonSerchView(id);
        } catch (Exception e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en búsqueda", "No se pudo obtener los datos de la persona búscada."));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, AdministrationPersonSerchView value) {
        if (value != null) {
            return value.getId();
        } else {
            return null;
        }
    }
}
