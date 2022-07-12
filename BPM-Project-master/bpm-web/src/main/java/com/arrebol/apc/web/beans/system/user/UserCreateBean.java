/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.web.beans.system.user;

import com.arrebol.apc.controller.system.user.UserCreateController;
import com.arrebol.apc.model.catalog.RouteCtlg;
import com.arrebol.apc.model.core.HumanResource;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.Permission;
import com.arrebol.apc.model.core.User;
import com.arrebol.apc.model.core.UserByOffice;
import com.arrebol.apc.model.enums.ActiveStatus;
import com.arrebol.apc.model.enums.ApplicationOwner;
import com.arrebol.apc.model.enums.HumanResourceStatus;
import com.arrebol.apc.model.enums.UserByOfficeStatus;
import com.arrebol.apc.model.enums.UserStatus;
import com.arrebol.apc.model.enums.UserType;
import com.arrebol.apc.security.APCSecure;
import com.arrebol.apc.web.beans.GenericBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
public class UserCreateBean extends GenericBean implements Serializable {

    /**
     * 1) Verifica que el usuario aun este disponible 2) Verifica que tipo de
     * usuario se va a crear y en todos los casos se debera de guardar usuario y
     * oficina más las carecteristicas propias de cada tipo de usuario: A) WEB
     * debe guardar permisos B) MOBILE debe guardar rutas C) BOTH debe guardar
     * rutas y permisos.
     */
    public void saveUser() {
        logger.debug("saveUser");

        try {
            FacesMessage.Severity severity = FacesMessage.SEVERITY_WARN;
            String messafeFormat = getBundlePropertyFile().getString("message.format.failure");
            String messageTitle = getBundlePropertyFile().getString("user");
            String messageAction = getBundlePropertyFile().getString("available");

            if (null != getUserType() && (getUserType().equals("MOBILE") || getUserType().equals("BOTH"))) {
                getUser().setCertifier(isCertifier() ? ActiveStatus.ENEBLED : ActiveStatus.DISABLED);
            } else {
                getUser().setCertifier(ActiveStatus.DISABLED);
            }

            getUser().setPassword(new APCSecure(APP, getPwdConfirm()).getPassword());
            getUser().setHumanResource(new HumanResource(getIdHRSelected()));
            getUser().setApplicationOwner(ApplicationOwner.APP_USER);
            getUser().setUserStatus(UserStatus.ENEBLED);
            getUser().setUserType(UserType.valueOf(getUserType()));
            getUser().setCreatedBy(getLoggedUser().getUser().getId());

            List<RouteCtlg> selectedRoutes = new ArrayList<>();
            List<Permission> selectedPermissions = new ArrayList<>();

            if (UserType.MOBILE.equals(getUser().getUserType())
                    || UserType.BOTH.equals(getUser().getUserType())) {
                selectedRoutes.addAll(getDualRouteCtlgLst().getTarget());
            }

            if (UserType.WEB.equals(getUser().getUserType())
                    || UserType.BOTH.equals(getUser().getUserType())) {
                selectedPermissions.addAll(getDualPermissionLst().getTarget());
            }

            if (isAvailableUserName()) {
                messageAction = getBundlePropertyFile().getString("created");

                setUserByOffice(
                        new UserByOffice(
                                new Office(getLoggedUser().getOffice().getId()),
                                UserByOfficeStatus.ENEBLED,
                                ApplicationOwner.APP_USER,
                                getLoggedUser().getUser().getId(),
                                new Date()
                        )
                );

                if (getController().saveUserController(getUser(), getUserByOffice(), selectedPermissions)) {
                    messafeFormat = getBundlePropertyFile().getString("message.format.sucess");
                    severity = FacesMessage.SEVERITY_INFO;
                    cleanForm();
                } else {
                    severity = FacesMessage.SEVERITY_WARN;
                }
            }

            Object[] param = {messageTitle, messageAction};

            buildAndSendMessage(param, messafeFormat, severity, messageTitle);
        } catch (Exception e) {
            logger.error("saveUser", e);
            Object[] param = {getBundlePropertyFile().getString("created")};

            buildAndSendMessage(
                    param,
                    getBundlePropertyFile().getString("message.format.fatal"),
                    FacesMessage.SEVERITY_FATAL,
                    getBundlePropertyFile().getString("user")
            );
        }
    }

    /**
     *
     */
    private void savePermission() {
        logger.debug("savePermission");

        String messafeFormat = getBundlePropertyFile().getString("message.format.fatal");
        String messageTitle = getBundlePropertyFile().getString("user");
        String messageAction = getBundlePropertyFile().getString("updated");

        FacesMessage.Severity severity = FacesMessage.SEVERITY_FATAL;
        try {
            if (!isUpdatePermissions()) {
                if (getController().saveManyController(dualPermissionLst.getTarget(), getUserByOffice().getId(), getLoggedUser().getUser().getId())) {
                    setUpdatePermissions(true);

                    messafeFormat = getBundlePropertyFile().getString("message.format.sucess");
                    severity = FacesMessage.SEVERITY_INFO;
                } else {
                    messafeFormat = getBundlePropertyFile().getString("message.format.failure");
                    severity = FacesMessage.SEVERITY_WARN;
                }
            } else {
                if (getController().updatePermissionsController(dualPermissionLst.getTarget(), getUserByOffice().getId(), getLoggedUser().getUser().getId())) {
                    setUpdatePermissions(true);

                    messafeFormat = getBundlePropertyFile().getString("message.format.sucess");
                    severity = FacesMessage.SEVERITY_INFO;
                } else {
                    messafeFormat = getBundlePropertyFile().getString("message.format.failure");
                    severity = FacesMessage.SEVERITY_WARN;
                }
            }
            Object[] param = {messageTitle, messageAction};

            buildAndSendMessage(param, messafeFormat, severity, messageTitle);
        } catch (Exception e) {
            logger.error("savePermission", e);
            Object[] param = {messageAction};

            buildAndSendMessage(param, messafeFormat, severity, messageTitle);
        }
    }

    /**
     *
     */
    public void isUsernameAvailable() {
        logger.debug("isUsernameAvailable");
        try {
            if (null != getUser().getUserName()
                    && getUser().getUserName().trim().length() >= 5
                    && getController().isUsernameAvailableController(getUser().getUserName(), getLoggedUser().getOffice().getId())) {
                setAvailableUserName(true);
            } else {
                setAvailableUserName(false);
            }
        } catch (Exception e) {
            logger.error("isUsernameAvailable", e);
            Object[] param = {getBundlePropertyFile().getString("searching")};

            buildAndSendMessage(
                    param,
                    getBundlePropertyFile().getString("message.format.fatal"),
                    FacesMessage.SEVERITY_FATAL,
                    getBundlePropertyFile().getString("user"));
        }
    }

    /**
     * SE TIENE QUE ACTUALIZAR A SOLO MOSTRAR TODAS LAS RUTAS
     */
    public void onUserTypeSlctChange() {
        if (null == getUserType() || getUserType().equals("WEB") || getUserType().equals("N/A")) {
            setCertifier(false);
//            setDualRouteCtlgLst(new DualListModel<>(new ArrayList<>(), new ArrayList<>()));
        } else if (getUserType().equals("BOTH") || getUserType().equals("MOBILE")) {
            try {
//                setDualRouteCtlgLst(new DualListModel<>(controller.findRoutesByOffice(getLoggedUser().getOffice().getId()), new ArrayList<>()));
            } catch (Exception ex) {
//                setDualRouteCtlgLst(new DualListModel<>(new ArrayList<>(), new ArrayList<>()));
                logger.error("onUserTypeSlctChange", ex);
            }
        }
    }

    private void cleanForm() {
        logger.info("cleanForm");
        try {
            setIdHRSelected(null);
            setUser(null);
            setCertifier(false);
            setUserType("N/A");
            setPwdConfirm("");
            List<Permission> source = getController().getAllActivePermissionController();
            List<Permission> target = new ArrayList<>();

            setDualPermissionLst(new DualListModel<>(source, target));

            loadHumanResourcesAvailables();
//            setDualRouteCtlgLst(new DualListModel<>(new ArrayList<>(), new ArrayList<>()));
        } catch (Exception e) {
            logger.error("cleanForm", e);
        }
    }

    private void loadHumanResourcesAvailables() {
        try {
            setHumanResourcesAvailables(
                    getController().findAllHRsWithoutUser(
                            new Office(getLoggedUser().getOffice().getId()),
                            HumanResourceStatus.ENEBLED
                    )
            );
        } catch (Exception e) {
            logger.error("loadHumanResourcesAvailables", e);
        }
    }

    private static final long serialVersionUID = 1501878035779416819L;
    final Logger logger = LogManager.getLogger(UserCreateBean.class);

    private UserCreateController controller;
    private User user;
    private List<String> roles;
    private String pwdConfirm;
    private boolean availableUserName;
    private String role;
    private DualListModel<Permission> dualPermissionLst;
    private boolean updatePermissions;
    private List<HumanResource> humanResourcesAvailables;
    private String idHRSelected;
    private UserByOffice userByOffice;
    private String userType;
    private boolean certifier;
    private DualListModel<RouteCtlg> dualRouteCtlgLst;

    @PostConstruct
    public void init() {
        try {
            loadBundlePropertyFile();

            setController(new UserCreateController());

            List<Permission> source = getController().getAllActivePermissionController();
            List<Permission> target = new ArrayList<>();

            setDualPermissionLst(new DualListModel<>(source, target));

            loadHumanResourcesAvailables();
            setDualRouteCtlgLst(new DualListModel<>(new ArrayList<>(), new ArrayList<>()));
        } catch (Exception e) {
            logger.error("init", e);
        }
    }

    @PreDestroy
    public void finish() {
        setUser(null);
        setHumanResourcesAvailables(null);
        setDualPermissionLst(null);
        setController(null);
    }

    public UserCreateController getController() {
        return controller;
    }

    public void setController(UserCreateController controller) {
        this.controller = controller;
    }

    public User getUser() {
        if (null == user) {
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getRoles() {
        if (null == roles) {
            /*
            roles = Stream.of(
                    HumanResourceType.values())
                    .map(Enum::name)
                    .collect(Collectors.toList()
                    );
             */
        }
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getPwdConfirm() {
        return pwdConfirm;
    }

    public void setPwdConfirm(String pwdConfirm) {
        this.pwdConfirm = pwdConfirm;
    }

    public boolean isAvailableUserName() {
        return availableUserName;
    }

    public void setAvailableUserName(boolean availableUserName) {
        this.availableUserName = availableUserName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public DualListModel<Permission> getDualPermissionLst() {
        return dualPermissionLst;
    }

    public void setDualPermissionLst(DualListModel<Permission> dualPermissionLst) {
        this.dualPermissionLst = dualPermissionLst;
    }

    public boolean isUpdatePermissions() {
        return updatePermissions;
    }

    public void setUpdatePermissions(boolean updatePermissions) {
        this.updatePermissions = updatePermissions;
    }

    public List<HumanResource> getHumanResourcesAvailables() {
        return humanResourcesAvailables;
    }

    public void setHumanResourcesAvailables(List<HumanResource> humanResourcesAvailables) {
        this.humanResourcesAvailables = humanResourcesAvailables;
    }

    public String getIdHRSelected() {
        return idHRSelected;
    }

    public void setIdHRSelected(String idHRSelected) {
        this.idHRSelected = idHRSelected;
    }

    public UserByOffice getUserByOffice() {
        return userByOffice;
    }

    public void setUserByOffice(UserByOffice userByOffice) {
        this.userByOffice = userByOffice;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean isCertifier() {
        return certifier;
    }

    public void setCertifier(boolean certifier) {
        this.certifier = certifier;
    }

    public DualListModel<RouteCtlg> getDualRouteCtlgLst() {
        return dualRouteCtlgLst;
    }

    public void setDualRouteCtlgLst(DualListModel<RouteCtlg> dualRouteCtlgLst) {
        this.dualRouteCtlgLst = dualRouteCtlgLst;
    }
}
