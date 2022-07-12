/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.web.beans.system.user;

import com.arrebol.apc.controller.system.user.UserAdminController;
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

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@ManagedBean
@ViewScoped
public class UserAdminBean extends GenericBean implements Serializable {

    /**
     *
     * @param option 1 = enebled,2 = disabled, 3 = deleted, 4 = username, 5 =
     * password, 6 = avatar, 7 = routes
     */
    public void enebledDisabledListener(int option) {
        try {
            boolean goAHead = false;

            List<UserStatus> statuses = new ArrayList<>();

            switch (option) {
                case 1:
                    if (isEnebled()) {
                        goAHead = true;
                        statuses.add(UserStatus.ENEBLED);
                    } else {
                        setEnebledLst(null);
                    }
                    break;
                case 2:
                    if (isDisabled()) {
                        goAHead = true;
                        statuses.add(UserStatus.DISABLED);
                    } else {
                        setDisabledLst(null);
                    }
                    break;
                case 3:
                    if (isDeleted()) {
                        goAHead = true;
                        statuses.add(UserStatus.ENEBLED);
                        statuses.add(UserStatus.DISABLED);
                    } else {
                        setDeletedLst(null);
                    }
                    break;
                case 4:
                    if (isUsername()) {
                        goAHead = true;
                        statuses.add(UserStatus.ENEBLED);
                    } else {
                        setUserNameLst(null);
                    }
                    break;
                case 5:
                    if (isPwd()) {
                        goAHead = true;
                        statuses.add(UserStatus.ENEBLED);
                    } else {
                        setPwdLst(null);
                    }
                    break;
                case 6:
                    if (isAvatar()) {
                        goAHead = true;
                        statuses.add(UserStatus.ENEBLED);
                    } else {
                        setAvatarLst(null);
                    }
                case 7:
                    if (isRoute()) {
                        goAHead = true;
                        statuses.add(UserStatus.ENEBLED);
                    } else {
                        setRouteLst(null);
                    }
                    break;
            }

            if (goAHead) {
                loadDropBoxData(statuses, option);
            }
        } catch (Exception e) {
            logger.error("enebledDisabledListener", e);
            Object[] param = {getBundlePropertyFile().getString("searching")};

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
     * @param option 1 = enebled, 2 = disabled, 3 = deleted, 4 = username, 5 =
     * password, 6 = avatar:
     */
    public void dropBoxListener(int option) {
        switch (option) {
            case 1:
                setEnebledSelected(getEnebledLst().get(getEnebledLst().indexOf(new UserByOffice(getEnebledId()))));
                break;
            case 2:
                setDisabledSelected(getDisabledLst().get(getDisabledLst().indexOf(new UserByOffice(getDisabledId()))));
                break;
            case 3:
                setDeletedSelected(getDeletedLst().get(getDeletedLst().indexOf(new UserByOffice(getDeletedId()))));
                break;
            case 4:
                setUserNameSelected(getUserNameLst().get(getUserNameLst().indexOf(new UserByOffice(getUsernameId()))));
                break;
            case 5:
                setPwdSelected(getPwdLst().get(getPwdLst().indexOf(new UserByOffice(getPwdId()))));
                break;
            case 6:
                setAvatarSelect(getAvatarLst().get(getAvatarLst().indexOf(new UserByOffice(getAvatarId()))));
                break;
            case 7:
                setRouteSelect(getRouteLst().get(getRouteLst().indexOf(new UserByOffice(getRouteId()))));
                // getRouteId() = user selected id
                
                break;
        }
    }

    /**
     *
     */
    public void onUserTypeSlctChange() {
        if (null == getUserType() || getUserType().equals("WEB") || getUserType().equals("N/A")) {
            setCertifier(false);
//            setRouteCtlgLst(new ArrayList<>());
//            setDualRouteCtlgLst(new DualListModel<>(new ArrayList<>(), new ArrayList<>()));
        } else if (getUserType().equals("BOTH") || getUserType().equals("MOBILE")) {
            try {
//                setRouteCtlgLst(getController().findRoutesByOffice(getLoggedUser().getOffice().getId()));
//                setDualRouteCtlgLst(new DualListModel<>(getController().findRoutesWithNoCertifierUser(getLoggedUser().getOffice().getId()), new ArrayList<>()));
            } catch (Exception ex) {
                logger.error("onUserTypeSlctChange", ex);
            }
        }
    }

    /**
     *
     */
    public void onCertifiedChange() {
        try {
            if (isCertifier()) {
//                setDualRouteCtlgLst(
//                        new DualListModel<>(
//                                getController().findRoutesWithNoCertifierUser(getLoggedUser().getOffice().getId()),
//                                new ArrayList<>()
//                        )
//                );
//                setRouteCtlgLst(new ArrayList<>());
            } else {
//                setDualRouteCtlgLst(new DualListModel<>(new ArrayList<>(), new ArrayList<>()));
//                setRouteCtlgLst(getController().findRoutesByOffice(getLoggedUser().getOffice().getId()));
            }
        } catch (Exception e) {
            logger.error("onCertifiedChange", e);
        }
    }

    /**
     *
     * @param option 1 = enebled, 2 = disabled, 3 = deleted, 4 = username, 5 =
     * password, 6 = avatar:
     */
    private void loadDropBoxData(List<UserStatus> statuses, int option) {
        try {

            List<UserByOffice> retults = getController().findUsersInOfficeInStatuses(statuses, getLoggedUser().getOffice().getId(), getLoggedUser().getUser().getId());

            switch (option) {
                case 1:
                    setEnebledLst(retults);
                    break;
                case 2:
                    setDisabledLst(retults);
                    break;
                case 3:
                    setDeletedLst(retults);
                    break;
                case 4:
                    setUserNameLst(retults);
                    break;
                case 5:
                    setPwdLst(retults);
                    break;
                case 6:
                    setAvatarLst(retults);
                    break;
                case 7:
                    setRouteLst(retults);
                    break;
            }
        } catch (Exception e) {
            logger.error("loadDropBoxData", e);
            throw e;
        }
    }

    private static final long serialVersionUID = -2325684946201829629L;
    final Logger logger = LogManager.getLogger(UserAdminBean.class);

    private UserAdminController controller;

    private List<UserByOffice> enebledLst;
    private List<UserByOffice> disabledLst;
    private List<UserByOffice> deletedLst;
    private List<UserByOffice> userNameLst;
    private List<UserByOffice> pwdLst;
    private List<UserByOffice> avatarLst;
    private List<UserByOffice> routeLst;

    private UserByOffice enebledSelected;
    private UserByOffice disabledSelected;
    private UserByOffice deletedSelected;
    private UserByOffice userNameSelected;
    private UserByOffice pwdSelected;
    private UserByOffice avatarSelect;
    private UserByOffice routeSelect;

    private String enebledId;
    private String disabledId;
    private String deletedId;
    private String usernameId;
    private String pwdId;
    private String avatarId;
    private String routeId;

    private boolean enebled;
    private boolean disabled;
    private boolean deleted;
    private boolean username;
    private boolean pwd;
    private boolean avatar;
    private boolean route;

    private String userType;
    private boolean certifier;
    private boolean enebledGrants;
    private String routeSelectedId;

    @PostConstruct
    public void init() {
        try {
            loadBundlePropertyFile();

            setController(new UserAdminController());
        } catch (Exception e) {
            logger.error("init", e);
        }
    }

    public UserAdminController getController() {
        return controller;
    }

    public void setController(UserAdminController controller) {
        this.controller = controller;
    }

    public List<UserByOffice> getEnebledLst() {
        return enebledLst;
    }

    public void setEnebledLst(List<UserByOffice> enebledLst) {
        this.enebledLst = enebledLst;
    }

    public List<UserByOffice> getDisabledLst() {
        return disabledLst;
    }

    public void setDisabledLst(List<UserByOffice> disabledLst) {
        this.disabledLst = disabledLst;
    }

    public List<UserByOffice> getDeletedLst() {
        return deletedLst;
    }

    public void setDeletedLst(List<UserByOffice> deletedLst) {
        this.deletedLst = deletedLst;
    }

    public List<UserByOffice> getUserNameLst() {
        return userNameLst;
    }

    public void setUserNameLst(List<UserByOffice> userNameLst) {
        this.userNameLst = userNameLst;
    }

    public List<UserByOffice> getPwdLst() {
        return pwdLst;
    }

    public void setPwdLst(List<UserByOffice> pwdLst) {
        this.pwdLst = pwdLst;
    }

    public List<UserByOffice> getAvatarLst() {
        return avatarLst;
    }

    public void setAvatarLst(List<UserByOffice> avatarLst) {
        this.avatarLst = avatarLst;
    }

    public List<UserByOffice> getRouteLst() {
        return routeLst;
    }

    public void setRouteLst(List<UserByOffice> routeLst) {
        this.routeLst = routeLst;
    }

    public UserByOffice getEnebledSelected() {
        return enebledSelected;
    }

    public void setEnebledSelected(UserByOffice enebledSelected) {
        this.enebledSelected = enebledSelected;
    }

    public UserByOffice getDisabledSelected() {
        return disabledSelected;
    }

    public void setDisabledSelected(UserByOffice disabledSelected) {
        this.disabledSelected = disabledSelected;
    }

    public UserByOffice getDeletedSelected() {
        return deletedSelected;
    }

    public void setDeletedSelected(UserByOffice deletedSelected) {
        this.deletedSelected = deletedSelected;
    }

    public UserByOffice getUserNameSelected() {
        return userNameSelected;
    }

    public void setUserNameSelected(UserByOffice userNameSelected) {
        this.userNameSelected = userNameSelected;
    }

    public UserByOffice getPwdSelected() {
        return pwdSelected;
    }

    public void setPwdSelected(UserByOffice pwdSelected) {
        this.pwdSelected = pwdSelected;
    }

    public UserByOffice getAvatarSelect() {
        return avatarSelect;
    }

    public void setAvatarSelect(UserByOffice avatarSelect) {
        this.avatarSelect = avatarSelect;
    }

    public UserByOffice getRouteSelect() {
        return routeSelect;
    }

    public void setRouteSelect(UserByOffice routeSelect) {
        this.routeSelect = routeSelect;
    }

    public String getEnebledId() {
        return enebledId;
    }

    public void setEnebledId(String enebledId) {
        this.enebledId = enebledId;
    }

    public String getDisabledId() {
        return disabledId;
    }

    public void setDisabledId(String disabledId) {
        this.disabledId = disabledId;
    }

    public String getDeletedId() {
        return deletedId;
    }

    public void setDeletedId(String deletedId) {
        this.deletedId = deletedId;
    }

    public String getUsernameId() {
        return usernameId;
    }

    public void setUsernameId(String usernameId) {
        this.usernameId = usernameId;
    }

    public String getPwdId() {
        return pwdId;
    }

    public void setPwdId(String pwdId) {
        this.pwdId = pwdId;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public boolean isEnebled() {
        return enebled;
    }

    public void setEnebled(boolean enebled) {
        this.enebled = enebled;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isUsername() {
        return username;
    }

    public void setUsername(boolean username) {
        this.username = username;
    }

    public boolean isPwd() {
        return pwd;
    }

    public void setPwd(boolean pwd) {
        this.pwd = pwd;
    }

    public boolean isAvatar() {
        return avatar;
    }

    public void setAvatar(boolean avatar) {
        this.avatar = avatar;
    }

    public boolean isRoute() {
        return route;
    }

    public void setRoute(boolean route) {
        this.route = route;
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

    public boolean isEnebledGrants() {
        return enebledGrants;
    }

    public void setEnebledGrants(boolean enebledGrants) {
        this.enebledGrants = enebledGrants;
    }

    public String getRouteSelectedId() {
        return routeSelectedId;
    }

    public void setRouteSelectedId(String routeSelectedId) {
        this.routeSelectedId = routeSelectedId;
    }
}
