/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.web.beans;

import com.arrebol.apc.controller.util.DateWrapper;
import com.arrebol.apc.model.core.UserByOffice;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public abstract class GenericBean {

    /**
     * It Shows messages in xhtml.
     *
     * @param success True shows info, False shows warn, null shows fatal.
     */
    public void testActionButtom(Boolean success) {
        try {
            if (success) {
                showMessage(FacesMessage.SEVERITY_INFO,
                        getBundlePropertyFile().getString("generic.title"),
                        getBundlePropertyFile().getString("generic.true"));
            } else {
                showMessage(FacesMessage.SEVERITY_WARN,
                        getBundlePropertyFile().getString("generic.title"),
                        getBundlePropertyFile().getString("generic.false"));
            }
        } catch (Exception e) {
            showMessage(FacesMessage.SEVERITY_FATAL,
                    getBundlePropertyFile().getString("generic.title"),
                    getBundlePropertyFile().getString("generic.exception"));
        }

    }

    /**
     *
     * @param field
     * @param value
     */
    public void initStartAndEndDates(int field, int value) {
        try {
            Calendar starDateCalendar = Calendar.getInstance();

            starDateCalendar.setTime(DateWrapper.getTodayMXTime());
            starDateCalendar.set(field, value);

            setStarDate(starDateCalendar.getTime());
            setEndDate(DateWrapper.getTodayMXTime());
        } catch (Exception e) {

        }
    }

    public void initOneWeekBeforeToCurrdate() {
        try {
            Calendar start = Calendar.getInstance();

            start.set(Calendar.WEEK_OF_YEAR, start.get(Calendar.WEEK_OF_YEAR) - 1);
            start.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

            setStarDate(start.getTime());
            setEndDate(DateWrapper.getTodayMXTime());
        } catch (Exception e) {

        }
    }
    
    public void initThisWeekToCurrdate() {
        try {
            Calendar start = Calendar.getInstance();

            //start.set(Calendar.WEEK_OF_YEAR, start.get(Calendar.WEEK_OF_YEAR) - 1);
            start.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            setStarDate(start.getTime());
            
            setEndDate(DateWrapper.getTodayMXTime());
        } catch (Exception e) {

        }
    }

    /**
     *
     * @return
     */
    protected synchronized FacesContext facesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     *
     * @return
     */
    protected synchronized ExternalContext externalContext() {
        return facesContext().getExternalContext();
    }

    /**
     *
     * @return
     */
    public synchronized UserByOffice getLoggedUser() {
        return (UserByOffice) externalContext().getSessionMap().get(APC_SESSION);
    }

    /**
     *
     * @param userByOffice
     */
    protected synchronized void setLoggedUser(UserByOffice userByOffice) {
        externalContext().getSessionMap().put(APC_SESSION, userByOffice);
    }

    /**
     *
     * @param severity
     * @param title
     * @param details
     */
    protected void showMessage(FacesMessage.Severity severity, String title, String details) {
        facesContext().addMessage(null, new FacesMessage(severity, title, details));
    }

    /**
     *
     * @param number Must be numeric (BigDecimal, Double, Float or Integer)
     * @return
     */
    public String currencyFormatNumber(Object number) {
        String currency;
        try {
            NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
            currency = defaultFormat.format(number);
        } catch (Exception e) {
            currency = "$0.00";
        }
        return currency;
    }
    
    /**
     * Construye y envia el mensaje de notification del resultado de la accion
     * ejecutada.
     *
     * @param params argumentos {0},{1}, etc.
     * @param msg mesage que se sustituira lo {0},{1}, etc.
     * @param severity Solo se permiten INFO, WARN or FATAL.
     * @param title Titulo que aparece en message
     */
    protected void buildAndSendMessage(Object[] params, String msg,
            FacesMessage.Severity severity, String title) {
        MessageFormat mf = new MessageFormat(msg);

        facesContext().addMessage(null, new FacesMessage(severity, title, mf.format(params)));
    }

    protected static final String APP = "ApoyoAProyectosComerciales";

    protected static final String APC_SESSION = "APCAuthorization";
    private ResourceBundle bundlePropertyFile;
    private final String PROPERTY_FILE = "com.arrebol.apc.i18n.app_background";
    private final int pwdMaxlength = 15;
    private Date starDate;
    private Date endDate;

    public ResourceBundle getBundlePropertyFile() {
        return bundlePropertyFile;
    }

    public void loadBundlePropertyFile() {
        this.bundlePropertyFile = ResourceBundle.getBundle(PROPERTY_FILE);
    }

    public int getPwdMaxlength() {
        return pwdMaxlength;
    }

    public Date getStarDate() {
        return starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
