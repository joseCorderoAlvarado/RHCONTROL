package com.arrebol.apc.web.validators;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Carlos Janitzio Zavala Lopez
 */
@FacesValidator("selectOneMenuValidator")
public class SelectOneMenuValidator extends GenericValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        loadBundlePropertyFile();

        if (null == value
                || "".equals(value.toString())
                || "N/A".equals(value.toString())) {
            errorSeverity(getBundlePropertyFile().getString("validator.select.one.menu.invalid.option"));
        }
    }

}
