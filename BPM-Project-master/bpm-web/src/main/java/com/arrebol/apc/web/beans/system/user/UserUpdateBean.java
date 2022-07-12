/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.web.beans.system.user;

import com.arrebol.apc.controller.system.user.UserUpdateController;
import com.arrebol.apc.model.catalog.RouteCtlg;
import com.arrebol.apc.model.core.Permission;
import com.arrebol.apc.model.core.UserByOffice;
import com.arrebol.apc.model.enums.ActiveStatus;
import com.arrebol.apc.model.enums.PermissionType;
import com.arrebol.apc.model.enums.UserStatus;
import com.arrebol.apc.model.enums.UserType;
import com.arrebol.apc.security.APCSecure;
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
public class UserUpdateBean extends GenericBean implements Serializable {

    public void onClickUpdateUserBtn() {
        logger.info("onClickUpdateUserBtn");
        try {
            FacesMessage.Severity severity = FacesMessage.SEVERITY_WARN;

            String messafeFormat = getBundlePropertyFile().getString("message.format.failure");
            String messageTitle = getBundlePropertyFile().getString("user");
            String messageAction = getBundlePropertyFile().getString("updated");

            UserType userType = UserType.valueOf(getIdUserType());
            getUserByOfficeUpdateSelected().setCreatedBy(getLoggedUser().getUser().getId());

            List<RouteCtlg> routes = new ArrayList<>();
            List<Permission> permissions = new ArrayList<>();
            ActiveStatus certifierUser = ActiveStatus.DISABLED;

            switch (userType) {
                case WEB:
                    permissions.addAll(getGeneralPublicPermissionLst());
                    permissions.addAll(getDualPermissionLst().getTarget());
                    break;
                case MOBILE:
                    routes = getDualRouteCtlgLst().getTarget();
                    certifierUser = isCertifier() ? ActiveStatus.ENEBLED : ActiveStatus.DISABLED;
                    break;
                case BOTH:
                    permissions.addAll(getGeneralPublicPermissionLst());
                    permissions.addAll(getDualPermissionLst().getTarget());

                    routes = getDualRouteCtlgLst().getTarget();
                    certifierUser = isCertifier() ? ActiveStatus.ENEBLED : ActiveStatus.DISABLED;
                    break;
            }

            if (getController().updateUser(userType, getUserByOfficeUpdateSelected(), permissions, certifierUser)) {
                severity = FacesMessage.SEVERITY_INFO;
                messafeFormat = getBundlePropertyFile().getString("message.format.sucess");

                cleanFormFields();
                refreshUserEnebledLst();
            }

            Object[] param = {messageTitle, messageAction};

            buildAndSendMessage(param, messafeFormat, severity, messageTitle);
        } catch (Exception e) {
            logger.error("onClickUpdateUserBtn", e);
            Object[] param = {getBundlePropertyFile().getString("updated")};

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
    public void onClickOtherActionsBtn() {
        logger.info("onClickUpdateUserBtn");
        try {
            FacesMessage.Severity severity = FacesMessage.SEVERITY_WARN;

            String messafeFormat = getBundlePropertyFile().getString("message.format.failure");
            String messageTitle = getBundlePropertyFile().getString("user");
            String messageAction = getBundlePropertyFile().getString("updated");
            boolean goAhead = false;
            UserStatus userStatus = null;

            switch (getAction()) {
                case "N/A":
                    break;
                case "pwd":
                    setPwd(new APCSecure(APP, getPwdConfirm()).getPassword());
                    goAhead = true;
                    break;
                case "usr":
                    isUsernameAvailable();
                    goAhead = isAvailableUserName();
                    break;
                default:
                    userStatus = UserStatus.valueOf(getAction().toUpperCase());
                    goAhead = true;
                    break;
            }

            if (goAhead
                    && getController().updateUserOtherActions(
                            getAction(),
                            getUsrOtherActionSelected().getUser().getId(),
                            userStatus, getPwd(),
                            getUserName(),
                            getLoggedUser().getUser().getId()
                    )) {
                severity = FacesMessage.SEVERITY_INFO;
                messafeFormat = getBundlePropertyFile().getString("message.format.sucess");

                cleanOtherActionsFormFields();
                refreshUserEnebledLst();
            }

            Object[] param = {messageTitle, messageAction};

            buildAndSendMessage(param, messafeFormat, severity, messageTitle);
        } catch (Exception e) {
            logger.error("onClickOtherActionsBtn", e);
            Object[] param = {getBundlePropertyFile().getString("updated")};

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
    public void onUsrEnebledSlctChange() {
        logger.info("onUsrEnebledSlctChange");
        try {
            cleanFormFields();
            setIdUserType(getUserByOfficeUpdateSelected().getUser().getUserType().toString());
            fillUBOData();
        } catch (Exception e) {
            logger.error("onUsrEnebledSlctChange", e);
        }
    }

    /**
     *
     */
    public void onUserTypeSlctChange() {
        logger.info("onUserTypeSlctChange");
        try {
            fillUBOData();

            switch (getUserByOfficeUpdateSelected().getUser().getUserType()) {
                //Si USUARIO es WEB y selecciona tipo web no se debe hacer nada 
                //Si USUARIO es WEB y selecciona tipo mobile, se deberá cargar rutas
                //Si USUARIO es WEB y seleccion tipo both, se deberà cargar rutas
                case WEB:
                    if ("MOBILE".equals(getIdUserType())
                            || "BOTH".equals(getIdUserType())) {
                        setDualRouteCtlgLst(new DualListModel<>(getGeneralRouteCtlgLst(), new ArrayList<>()));
                    }
                    break;
                //Si USUARIO es MOBILE y selecciona tipo web, se debe cargar permisos privados
                //Si USUARIO es MOBILE y selecciona tipo mobile, hacer nada
                //Si USUARIO es MOBILE y seleccion tipo both, se debe cargar permisos privados
                case MOBILE:
                    if ("WEB".equals(getIdUserType())
                            || "BOTH".equals(getIdUserType())) {
                        setDualPermissionLst(new DualListModel<>(getGeneralPrivatePermissionLst(), new ArrayList<>()));
                    }
                    break;
            }
        } catch (Exception e) {
            logger.error("onUserTypeSlctChange", e);
        }
    }

    /**
     *
     */
    public void onSelectedOtherUserAction() {
        try {
            boolean goAhead = true;

            List<UserStatus> statuses = new ArrayList<>();

            switch (getAction()) {
                case "pwd":
                    statuses.add(UserStatus.ENEBLED);
                    statuses.add(UserStatus.DISABLED);
                    break;
                case "usr":
                    statuses.add(UserStatus.ENEBLED);
                    statuses.add(UserStatus.DISABLED);
                    break;
                case "enebled":
                    statuses.add(UserStatus.DISABLED);
                    break;
                case "disabled":
                    statuses.add(UserStatus.ENEBLED);
                    break;
                case "deleted":
                    statuses.add(UserStatus.ENEBLED);
                    statuses.add(UserStatus.DISABLED);
                    break;
                default:
                    goAhead = false;
                    break;
            }

            if (goAhead) {
                setPwd(null);
                setPwdConfirm(null);
                setUserName(null);
                setUsrOtherActionSelected(null);
                setUsrOtherActionLst(
                        getController().findUsersInOfficeInStatuses(
                                statuses,
                                getLoggedUser().getOffice().getId(),
                                getLoggedUser().getUser().getId()
                        )
                );
            } else {
                setUsrOtherActionLst(new ArrayList<>());
            }
        } catch (Exception e) {
            logger.error("enebledDisabledListener", e);
            Object[] param = {getBundlePropertyFile().getString("searching")};

            setUsrOtherActionLst(new ArrayList<>());
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
    public void isUsernameAvailable() {
        logger.debug("isUsernameAvailable");
        try {
            if (null != getUserName()
                    && getUserName().trim().length() >= 5
                    && getController().isUsernameAvailableController(getUserName(), getLoggedUser().getOffice().getId())) {
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
     *
     */
    private void refreshUserEnebledLst() {
        logger.info("refreshUserEnebledLst");
        try {
            List<UserStatus> statuses = new ArrayList<>();
            statuses.add(UserStatus.ENEBLED);

            setUserByOfficeUpdateSelected(null);
            setUserEnebledLst(
                    getController().findUsersInOfficeInStatuses(
                            statuses,
                            getLoggedUser().getOffice().getId(),
                            getLoggedUser().getUser().getId()
                    )
            );
        } catch (Exception e) {
            logger.error("refreshUserEnebledLst", e);
            setUserEnebledLst(new ArrayList<>());
        }
    }

    /**
     *
     */
    private void fillUBOData() {
        logger.debug("fillUBOData");
        try {
            switch (getUserByOfficeUpdateSelected().getUser().getUserType()) {
                // Bring all grants
                case WEB:
                    setDualPermissionLst(
                            new DualListModel<>(
                                    findAllPermissionsByUBO(false, false, getUserByOfficeUpdateSelected().getId()),
                                    findAllPermissionsByUBO(false, true, getUserByOfficeUpdateSelected().getId())
                            )
                    );
                    setCertifier(false);
                    break;
                //Bring all routes
                case MOBILE:
//                    setDualRouteCtlgLst(
//                            new DualListModel<>(
//                                    findAllRoutesByHRHR(true, false, getUserByOfficeUpdateSelected().getUser().getHumanResource().getId(), getLoggedUser().getOffice().getId()),
//                                    findAllRoutesByHRHR(true, true, getUserByOfficeUpdateSelected().getUser().getHumanResource().getId(), getLoggedUser().getOffice().getId())
//                            )
//                    );
                    setCertifier(ActiveStatus.ENEBLED.equals(getUserByOfficeUpdateSelected().getUser().getCertifier()));
                    break;
                // Bring all grants and routes
                case BOTH:
                    setDualPermissionLst(
                            new DualListModel<>(
                                    findAllPermissionsByUBO(false, false, getUserByOfficeUpdateSelected().getId()),
                                    findAllPermissionsByUBO(false, true, getUserByOfficeUpdateSelected().getId())
                            )
                    );
//                    setDualRouteCtlgLst(
//                            new DualListModel<>(
//                                    findAllRoutesByHRHR(true, false, getUserByOfficeUpdateSelected().getUser().getHumanResource().getId(), getLoggedUser().getOffice().getId()),
//                                    findAllRoutesByHRHR(true, true, getUserByOfficeUpdateSelected().getUser().getHumanResource().getId(), getLoggedUser().getOffice().getId())
//                            )
//                    );
                    setCertifier(ActiveStatus.ENEBLED.equals(getUserByOfficeUpdateSelected().getUser().getCertifier()));
                    break;
            }
        } catch (Exception e) {
            logger.error("fillUBOData", e);
        }
    }

    /**
     * Find all permissions that has assigned the User By Office selected.
     *
     * @param uboId User By Office Identification number.
     * @return
     */
    private List<Permission> findAllPermissionsByUBO(boolean isRuoute, boolean in, String uboId) {
        logger.info("findAllPermissionsByUBO");
        List<Permission> results = new ArrayList<>();

        try {
            results = getController().findList(isRuoute, in, uboId, null, null);
        } catch (Exception e) {
            logger.error("findAllPermissionsByUBO", e);
        }
        return results;
    }

    /**
     * Clean all forms fields values
     */
    private void cleanFormFields() {
        logger.info("clean");
        try {
            setIdUserType("N/A");
            setCertifier(false);
            setDualPermissionLst(new DualListModel<>(new ArrayList<>(), new ArrayList<>()));
            setDualRouteCtlgLst(new DualListModel<>(new ArrayList<>(), new ArrayList<>()));
        } catch (Exception e) {
            logger.error("clean", e);
        }
    }

    /**
     *
     */
    private void cleanOtherActionsFormFields() {
        logger.info("cleanOtherActionsFormFields");
        try {
            setAction("N/A");
            setUsrOtherActionSelected(null);
            setUserName(null);
            setPwd(null);
            setPwdConfirm(null);
        } catch (Exception e) {
            logger.error("cleanOtherActionsFormFields", e);
        }
    }

    private static final long serialVersionUID = -3695498920999228355L;
    final Logger logger = LogManager.getLogger(UserUpdateBean.class);

    @PostConstruct
    public void init() {
        try {
            loadBundlePropertyFile();

            List<UserStatus> statuses = new ArrayList<>();
            List<PermissionType> types = new ArrayList<>();

            types.add(PermissionType.PRIVATE);
            statuses.add(UserStatus.ENEBLED);

            setController(new UserUpdateController());
            setUserEnebledLst(getController().findUsersInOfficeInStatuses(statuses, getLoggedUser().getOffice().getId(), getLoggedUser().getUser().getId()));
            setGeneralRouteCtlgLst(getController().findGeneralList(true, null, getLoggedUser().getOffice().getId()));
            setGeneralPrivatePermissionLst(getController().findGeneralList(false, types, null));

            types.clear();
            types.add(PermissionType.PUBLIC);

            setGeneralPublicPermissionLst(getController().findGeneralList(false, types, null));

            cleanFormFields();
        } catch (Exception e) {
            logger.error("init", e);
        }
    }

    private UserUpdateController controller;
    private List<UserByOffice> userEnebledLst;
    private UserByOffice userByOfficeUpdateSelected;
    private DualListModel<Permission> dualPermissionLst;
    private DualListModel<RouteCtlg> dualRouteCtlgLst;
    private List<RouteCtlg> generalRouteCtlgLst;
    private boolean refresh;
    private String idUserType;
    private boolean certifier;
    private List<Permission> generalPrivatePermissionLst;
    private List<Permission> generalPublicPermissionLst;
    private String action;
    private List<UserByOffice> usrOtherActionLst;
    private UserByOffice usrOtherActionSelected;
    private boolean availableUserName;
    private String userName;
    private String pwd;
    private String pwdConfirm;

    public List<RouteCtlg> getGeneralRouteCtlgLst() {
        return generalRouteCtlgLst;
    }

    public void setGeneralRouteCtlgLst(List<RouteCtlg> generalRouteCtlgLst) {
        this.generalRouteCtlgLst = generalRouteCtlgLst;
    }

    public DualListModel<RouteCtlg> getDualRouteCtlgLst() {
        return dualRouteCtlgLst;
    }

    public void setDualRouteCtlgLst(DualListModel<RouteCtlg> dualRouteCtlgLst) {
        this.dualRouteCtlgLst = dualRouteCtlgLst;
    }

    public UserUpdateController getController() {
        return controller;
    }

    public void setController(UserUpdateController controller) {
        this.controller = controller;
    }

    public List<UserByOffice> getUserEnebledLst() {
        return userEnebledLst;
    }

    public void setUserEnebledLst(List<UserByOffice> userEnebledLst) {
        this.userEnebledLst = userEnebledLst;
    }

    public UserByOffice getUserByOfficeUpdateSelected() {
        return userByOfficeUpdateSelected;
    }

    public void setUserByOfficeUpdateSelected(UserByOffice userByOfficeUpdateSelected) {
        this.userByOfficeUpdateSelected = userByOfficeUpdateSelected;
    }

    public DualListModel<Permission> getDualPermissionLst() {
        return dualPermissionLst;
    }

    public void setDualPermissionLst(DualListModel<Permission> dualPermissionLst) {
        this.dualPermissionLst = dualPermissionLst;
    }

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }

    public String getIdUserType() {
        return idUserType;
    }

    public void setIdUserType(String idUserType) {
        this.idUserType = idUserType;
    }

    public boolean isCertifier() {
        return certifier;
    }

    public void setCertifier(boolean certifier) {
        this.certifier = certifier;
    }

    public List<Permission> getGeneralPrivatePermissionLst() {
        return generalPrivatePermissionLst;
    }

    public void setGeneralPrivatePermissionLst(List<Permission> generalPrivatePermissionLst) {
        this.generalPrivatePermissionLst = generalPrivatePermissionLst;
    }

    public List<Permission> getGeneralPublicPermissionLst() {
        return generalPublicPermissionLst;
    }

    public void setGeneralPublicPermissionLst(List<Permission> generalPublicPermissionLst) {
        this.generalPublicPermissionLst = generalPublicPermissionLst;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<UserByOffice> getUsrOtherActionLst() {
        return usrOtherActionLst;
    }

    public void setUsrOtherActionLst(List<UserByOffice> usrOtherActionLst) {
        this.usrOtherActionLst = usrOtherActionLst;
    }

    public UserByOffice getUsrOtherActionSelected() {
        return usrOtherActionSelected;
    }

    public void setUsrOtherActionSelected(UserByOffice usrOtherActionSelected) {
        this.usrOtherActionSelected = usrOtherActionSelected;
    }

    public boolean isAvailableUserName() {
        return availableUserName;
    }

    public void setAvailableUserName(boolean availableUserName) {
        this.availableUserName = availableUserName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwdConfirm() {
        return pwdConfirm;
    }

    public void setPwdConfirm(String pwdConfirm) {
        this.pwdConfirm = pwdConfirm;
    }

}
