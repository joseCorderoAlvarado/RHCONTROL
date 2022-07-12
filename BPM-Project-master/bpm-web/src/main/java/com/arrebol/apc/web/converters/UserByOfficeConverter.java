/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.web.converters;

import com.arrebol.apc.model.core.UserByOffice;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@FacesConverter("userByOfficeConverter")
public class UserByOfficeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        try {

            if (!component.getChildren().isEmpty()) {

                for (final UIComponent chidren : component.getChildren()) {
                    if (chidren instanceof UISelectItems) {
                        return convertFromSelect(chidren, submittedValue);
                    }
                }
            }
            return null;
        } catch (Exception e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario/s no validos"));
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return String.valueOf(((UserByOffice) object).getId());
        } else {
            return null;
        }
    }

    /**
     *
     * @param component
     * @param submittedValue
     * @return
     */
    private Object convertFromSelect(UIComponent component, String submittedValue) {
        if (component instanceof UISelectItem) {
            final UISelectItem item = (UISelectItem) component;
            final UserByOffice value = (UserByOffice) item.getValue();
            if (submittedValue.equals(value.getId())) {
                return value;
            }
        }
        if (component instanceof UISelectItems) {
            UISelectItems items = (UISelectItems) component;
            List<UserByOffice> elements = (List<UserByOffice>) items.getValue();

            for (UserByOffice element : elements) {
                if (submittedValue.equals(element.getId())) {
                    return element;
                }
            }
        }
        return null;
    }

}
