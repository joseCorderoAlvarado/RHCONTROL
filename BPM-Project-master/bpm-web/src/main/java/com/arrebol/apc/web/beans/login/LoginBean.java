/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.web.beans.login;

import com.arrebol.apc.controller.login.LoginService;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.web.beans.GenericBean;
import com.arrebol.apc.model.core.User;
import com.arrebol.apc.model.core.UserByOffice;
import com.arrebol.apc.security.APCSecure;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Named
@SessionScoped
public class LoginBean extends GenericBean implements Serializable {

    /**
     * Authentication and authorization user.
     *
     * @throws IOException from {@link ExternalContext#redirect(String)}
     */
    public void login() throws IOException {
        ExternalContext externalContext = externalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            logger.info("User: " + getUsername());
            request.login(getUsername() + getOfficeId(), getPassword());

            User user = new User("", getUsername());
            Office office = new Office(getOfficeId(), getOfficeName());

            setLoggedUser(loginService.findUserLoggedController(new UserByOffice(user, office)));

            externalContext.redirect(forwardUrl);

        } catch (ServletException | IOException e) {
            logger.error("Login() - " + e, e);
            //logout();
            showMessage(FacesMessage.SEVERITY_ERROR, getBundlePropertyFile().getString("error.access.title"), getBundlePropertyFile().getString("error.access.details"));
            //showMessage(FacesMessage.SEVERITY_ERROR, "¡Acceso denegado!", "Verifica tus datos de ingreso.");
        } catch (Exception e) {
            logger.error("Login() - " + e, e);
            //logout();
            showMessage(FacesMessage.SEVERITY_FATAL, getBundlePropertyFile().getString("fatal.access.title"), getBundlePropertyFile().getString("fatal.access.details"));
            //showMessage(FacesMessage.SEVERITY_ERROR, "¡Acceso denegado!", "Por favor contacta a tu administrador.");
        }
    }

    /**
     * Remove current user from session.
     *
     * @throws IOException from {@link ExternalContext#redirect(String)}
     */
    public void logout() throws IOException {
        try {
            ExternalContext externalContext = externalContext();

            externalContext.invalidateSession();
            externalContext.redirect(externalContext.getRequestContextPath() + LOGIN);
        } catch (IOException e) {
            logger.error("logout() - " + e, e);
        }
    }

    /**
     * Check if currently user has the given role.
     *
     * @param role Role to be search.
     * @return true if user has given role otherwise false..
     */
    public boolean isUserInRole(String role) {
        FacesContext context = facesContext();
        ExternalContext externalContext = context.getExternalContext();

        return externalContext.isUserInRole(role);
    }

    /**
     *
     * @return
     */
    private String extractRequestedUrlBeforeLogin() {
        ExternalContext externalContext = externalContext();

        String requestedUrl = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);
        if (null == requestedUrl) {
            return externalContext.getRequestContextPath() + LOGIN;
        }

        String queryString = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

        return requestedUrl + (queryString == null ? "" : "?" + queryString);
    }

    /**
     * Class contants.
     */
    private static final long serialVersionUID = -6130715794722525144L;

    private static final String LOGIN = "/";

    final Logger logger = LogManager.getLogger(LoginBean.class);

    /**
     * Class variables.
     */
    @Inject
    private LoginService loginService;

    private String forwardUrl;
    private String password;
    private String username;
    private String officeName;
    private String officeId;
    //private LoginController controller;
    List<Office> offices;

    /**
     * Contructors and pre/post contructors.
     */
    @PostConstruct
    public void init() {
        try {
            loadBundlePropertyFile();
            this.forwardUrl = extractRequestedUrlBeforeLogin();
            setOffices(loginService.getAllActiveOfficeController());
        } catch (Exception e) {
            logger.error(e.getClass() + " --> " + e.getMessage(), e);
        }
    }

    @PreDestroy
    public void finish() {
        try {
            logout();
        } catch (Exception e) {
            logger.error(e.getClass() + " --> " + e.getMessage(), e);
        }
    }

    /**
     * Getters and Setters.
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new APCSecure(APP, password).getPassword();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOfficeName() {
        try {
            int index = offices.indexOf(new Office(officeId));
            officeName = offices.get(index).getOfficeName();
        } catch (Exception e) {
        }
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public List<Office> getOffices() {
        if (null == offices) {
            offices = new ArrayList<>();
        }
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

}
