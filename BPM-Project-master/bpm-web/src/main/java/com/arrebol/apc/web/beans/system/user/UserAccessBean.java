/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.web.beans.system.user;

import com.arrebol.apc.controller.system.user.UserAccessController;
import com.arrebol.apc.model.core.HumanResource;
import com.arrebol.apc.model.core.Permission;
import com.arrebol.apc.model.core.UserByOffice;
import com.arrebol.apc.model.enums.UserStatus;
import com.arrebol.apc.web.beans.GenericBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@ManagedBean
@ViewScoped
public class UserAccessBean extends GenericBean implements Serializable {

    public void dropBoxOnchange() {
        try {
            List<Permission> source = new ArrayList<>();
            List<Permission> target = new ArrayList<>();

            setHr(new HumanResource());
            setDualPermissionLst(new DualListModel<>(source, target));
            setUpdateForm(false);
        } catch (Exception e) {
            logger.error("saveHR", e);
            Object[] param = {getBundlePropertyFile().getString("searching")};

            buildAndSendMessage(
                    param,
                    getBundlePropertyFile().getString("message.format.fatal"),
                    FacesMessage.SEVERITY_FATAL,
                    getBundlePropertyFile().getString("user")
            );
        }
    }

    public void loadUserByOfficePermissionLst() {
        try {
            UserByOffice loadUserByOffice = new UserByOffice(getUserByOfficeId());

            List<Permission> source = getController().loadUserByOfficePermissionLst(loadUserByOffice, true);
            List<Permission> target = getController().loadUserByOfficePermissionLst(loadUserByOffice, false);

            int index = getUserByOfficeLst().indexOf(loadUserByOffice);

            setHr(getUserByOfficeLst().get(index).getUser().getHumanResource());
            setDualPermissionLst(new DualListModel<>(source, target));
            setUpdateForm(true);
        } catch (Exception e) {
            logger.error("saveHR", e);
            Object[] param = {getBundlePropertyFile().getString("searching")};

            buildAndSendMessage(
                    param,
                    getBundlePropertyFile().getString("message.format.fatal"),
                    FacesMessage.SEVERITY_FATAL,
                    getBundlePropertyFile().getString("user")
            );
        }
    }

    public void savePermission() {
        logger.debug("savePermission");

        String messafeFormat = getBundlePropertyFile().getString("message.format.fatal");
        String messageTitle = getBundlePropertyFile().getString("user");
        String messageAction = getBundlePropertyFile().getString("updated");

        FacesMessage.Severity severity = FacesMessage.SEVERITY_FATAL;
        try {
            if (getController().updatePermissionsController(dualPermissionLst.getTarget(), getUserByOfficeId(), getLoggedUser().getUser().getId())) {
                messafeFormat = getBundlePropertyFile().getString("message.format.sucess");
                severity = FacesMessage.SEVERITY_INFO;
            } else {
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

    private static final long serialVersionUID = 6738057461247413139L;
    final Logger logger = LogManager.getLogger(UserAccessBean.class);

    private UserAccessController controller;
    private String userByOfficeId;
    private List<UserByOffice> userByOfficeLst;
    private DualListModel<Permission> dualPermissionLst;
    private HumanResource hr;
    private boolean updateForm;

    @PostConstruct
    public void init() {
        try {
            loadBundlePropertyFile();

            setController(new UserAccessController());

            List<UserStatus> statuses = new ArrayList<>();

            statuses.add(UserStatus.ENEBLED);

            setUserByOfficeLst(
                    getController().findUsersInOfficeInStatuses(
                            statuses,
                            getLoggedUser().getOffice().getId(),
                            getLoggedUser().getUser().getId()
                    )
            );

            List<Permission> source = new ArrayList<>();
            List<Permission> target = new ArrayList<>();

            setDualPermissionLst(new DualListModel<>(source, target));
        } catch (Exception e) {
            logger.error("init", e);
        }
    }

    public UserAccessController getController() {
        return controller;
    }

    public void setController(UserAccessController controller) {
        this.controller = controller;
    }

    public String getUserByOfficeId() {
        return userByOfficeId;
    }

    public void setUserByOfficeId(String userByOfficeId) {
        this.userByOfficeId = userByOfficeId;
    }

    public List<UserByOffice> getUserByOfficeLst() {
        return userByOfficeLst;
    }

    public void setUserByOfficeLst(List<UserByOffice> userByOfficeLst) {
        this.userByOfficeLst = userByOfficeLst;
    }

    public DualListModel<Permission> getDualPermissionLst() {
        return dualPermissionLst;
    }

    public void setDualPermissionLst(DualListModel<Permission> dualPermissionLst) {
        this.dualPermissionLst = dualPermissionLst;
    }

    public HumanResource getHr() {
        return hr;
    }

    public void setHr(HumanResource hr) {
        this.hr = hr;
    }

    public boolean isUpdateForm() {
        return updateForm;
    }

    public void setUpdateForm(boolean updateForm) {
        this.updateForm = updateForm;
    }

}
