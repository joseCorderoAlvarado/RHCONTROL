/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arrebol.apc.web.validators;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Picasso
 */
public abstract class GenericValidator {

    /**
     * Raise a message with SEVERITY_ERROR.s
     *
     * @param message
     * @throws ValidatorException
     */
    protected void errorSeverity(String message) throws ValidatorException {
        FacesMessage msg = new FacesMessage(message);

        msg.setSeverity(FacesMessage.SEVERITY_ERROR);

        throw new ValidatorException(msg);
    }

    protected ResourceBundle bundlePropertyFile;
    private final String PROPERTY_FILE = "com.arrebol.apc.i18n.app_background";

    public ResourceBundle getBundlePropertyFile() {
        return bundlePropertyFile;
    }

    public synchronized void loadBundlePropertyFile() {
        this.bundlePropertyFile = ResourceBundle.getBundle(PROPERTY_FILE);
    }
}
