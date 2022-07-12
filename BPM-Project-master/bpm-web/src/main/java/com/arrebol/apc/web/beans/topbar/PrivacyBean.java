/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.web.beans.topbar;

import com.arrebol.apc.controller.topbar.PrivacyController;
import com.arrebol.apc.security.APCSecure;
import com.arrebol.apc.web.beans.GenericBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@ManagedBean
@ViewScoped
public class PrivacyBean extends GenericBean implements Serializable {

    /**
     *
     */
    public void updatePassword() {
        String messafeFormat = getBundlePropertyFile().getString("message.format.fatal");
        String messageTitle = getBundlePropertyFile().getString("user");
        String messageAction = getBundlePropertyFile().getString("modified");

        FacesMessage.Severity severity = FacesMessage.SEVERITY_FATAL;
        try {
            if (validatePwd() && getController().updatePasswordByUserId(new APCSecure(APP, getPwd()).getPassword(), getLoggedUser().getUser().getId())) {

                messageTitle = getBundlePropertyFile().getString("password");
                messafeFormat = getBundlePropertyFile().getString("message.format.sucess");
                severity = FacesMessage.SEVERITY_INFO;
            } else {
                messageTitle = getBundlePropertyFile().getString("password");
                messafeFormat = getBundlePropertyFile().getString("message.format.failure");
                severity = FacesMessage.SEVERITY_WARN;
            }

            Object[] param = {messageTitle, messageAction};

            buildAndSendMessage(param, messafeFormat, severity, messageTitle);
        } catch (Exception e) {
            logger.error("savePermission", e);
            Object[] param = {messageAction};

            buildAndSendMessage(param, messafeFormat, severity, messageTitle);
        }
    }

    @PostConstruct
    public void init() {
        try {
            loadBundlePropertyFile();
            setController(new PrivacyController());
        } catch (Exception e) {
            logger.error("init", e);
        }
    }

    /**
     *
     * @return
     */
    private boolean validatePwd() {
        boolean isValidPwd = false;
        try {
            if (getPwd().trim().length() > 4) {
                isValidPwd = true;
            }
        } catch (Exception e) {
            logger.error("validatePwd", e);
        }
        return isValidPwd;
    }

    private static final long serialVersionUID = 9192662395164473848L;

    final Logger logger = LogManager.getLogger(PrivacyBean.class);

    private PrivacyController controller;
    private String pwd;
    private String confirmPwd;

    public PrivacyController getController() {
        return controller;
    }

    public void setController(PrivacyController controller) {
        this.controller = controller;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

}
