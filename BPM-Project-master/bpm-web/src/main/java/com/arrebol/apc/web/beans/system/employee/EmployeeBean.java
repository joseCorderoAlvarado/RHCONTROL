/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.web.beans.system.employee;

import com.arrebol.apc.controller.catalog.RoleController;
import com.arrebol.apc.controller.system.employee.EmployeeController;
import com.arrebol.apc.model.catalog.RoleCtlg;
import com.arrebol.apc.model.core.Bonus;
import com.arrebol.apc.model.core.HumanResource;
import com.arrebol.apc.model.core.HumanResourceByOffice;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.enums.ApplicationOwner;
import com.arrebol.apc.model.enums.HumanResourceStatus;
import com.arrebol.apc.web.beans.GenericBean;
import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@ManagedBean
@ViewScoped
public class EmployeeBean extends GenericBean implements Serializable {

    public void saveHR() {
        logger.debug("saveHR");
        try {
            getSaveHumanResource().setAvatar("images/avatar.png");
            getSaveHumanResource().setRoleCtlg(new RoleCtlg(role));
            getSaveHumanResource().setHumanResourceStatus(HumanResourceStatus.ENEBLED);
            getSaveHumanResource().setCreatedBy(getLoggedUser().getUser().getId());
            getSaveHumanResource().setBalance(BigDecimal.ZERO);

            if (getSaveHumanResource().getEmployeeSaving() == null) {
                getSaveHumanResource().setEmployeeSaving(BigDecimal.ZERO);
            }

            String messafeFormat = getBundlePropertyFile().getString("message.format.failure");
            FacesMessage.Severity severity = FacesMessage.SEVERITY_WARN;

            HumanResourceByOffice humanResourceByOffice = new HumanResourceByOffice(
                    new Office(getLoggedUser().getOffice().getId()),
                    getLoggedUser().getUser().getId(),
                    new Date(),
                    ApplicationOwner.APP_USER
            );

//            if (null != getBonusId() && getBonusId().length() == 36) {
//                getSaveHumanResource().setBonus(new Bonus(getBonusId()));
//            }
            if (getController().saveHRController(humanResourceByOffice, getSaveHumanResource())) {
                setSaveHumanResource(new HumanResource());

                messafeFormat = getBundlePropertyFile().getString("message.format.sucess");
                severity = FacesMessage.SEVERITY_INFO;

                refreshDropBox();
            }
            Object[] param = {getBundlePropertyFile().getString("employee"), getBundlePropertyFile().getString("created")};

            buildAndSendMessage(param, messafeFormat, severity, getBundlePropertyFile().getString("employee"));
        } catch (Exception e) {
            logger.error("saveHR", e);
            Object[] param = {getBundlePropertyFile().getString("created")};

            buildAndSendMessage(
                    param,
                    getBundlePropertyFile().getString("message.format.fatal"),
                    FacesMessage.SEVERITY_FATAL,
                    getBundlePropertyFile().getString("employee")
            );
        }
    }

    /**
     *
     * @param action 1 = TO DISABLED, 2 = TO ENEBLED, 3 = TO DELETED, 4 NOT
     * ALLOWED
     */
    public void actionDropBox(int action) {
        logger.debug("actionDropBox");
        try {
            String messageTitle = getBundlePropertyFile().getString("employee");
            String actionUserIdSelected = null;
            String messageAction = null;

            HumanResourceStatus status = null;

            switch (action) {
                case 1:
                    actionUserIdSelected = getEnebledId();
                    messageAction = getBundlePropertyFile().getString("disabled");
                    status = HumanResourceStatus.DISABLED;
                    break;
                case 2:
                    actionUserIdSelected = getDisabledId();
                    messageAction = getBundlePropertyFile().getString("enebled");
                    status = HumanResourceStatus.ENEBLED;
                    break;
                case 3:
                    actionUserIdSelected = getDeletedId();
                    messageAction = getBundlePropertyFile().getString("deleted");
                    status = HumanResourceStatus.DELETED;
                    break;
                default:
                    throw new Exception(action + " is NOT valid a option");
            }

            if (executeAction(status, actionUserIdSelected, messageTitle, messageAction)) {
                refreshDropBox();
            }
        } catch (Exception e) {
            logger.error("actionDropBox", e);
            Object[] param = {getBundlePropertyFile().getString("employee")};

            buildAndSendMessage(
                    param,
                    getBundlePropertyFile().getString("message.format.fatal"),
                    FacesMessage.SEVERITY_FATAL,
                    getBundlePropertyFile().getString("process")
            );
        }
    }

    public void updateSlctBtnHRAction() {
        logger.debug("updateHR");
        try {
            getUpdateHumanResource().setLastUpdatedBy(getLoggedUser().getUser().getId());
            getUpdateHumanResource().setLastUpdatedOn(new Date());
            getUpdateHumanResource().setRoleCtlg(new RoleCtlg(roleUpdate));

            if (getUpdateHumanResource().getEmployeeSaving() == null) {
                getUpdateHumanResource().setEmployeeSaving(BigDecimal.ZERO);
            }

            String messafeFormat = getBundlePropertyFile().getString("message.format.failure");
            FacesMessage.Severity severity = FacesMessage.SEVERITY_WARN;

//            if (null != getBonusId() && getBonusId().length() == 36) {
//                getUpdateHumanResource().setBonus(new Bonus(getBonusId()));
//            }
            if (getController().updateByHumanResourceId(getUpdateHumanResource(), false)) {
                messafeFormat = getBundlePropertyFile().getString("message.format.sucess");
                severity = FacesMessage.SEVERITY_INFO;
            }

            Object[] param = {getBundlePropertyFile().getString("employee"), getBundlePropertyFile().getString("updated")};

            buildAndSendMessage(param, messafeFormat, severity, getBundlePropertyFile().getString("employee"));
        } catch (Exception e) {
            logger.error("updateSlctBtnHRActionListener", e);
            Object[] param = {getBundlePropertyFile().getString("updated")};

            buildAndSendMessage(
                    param,
                    getBundlePropertyFile().getString("message.format.fatal"),
                    FacesMessage.SEVERITY_FATAL,
                    getBundlePropertyFile().getString("employee")
            );
        }
    }

    public void loadUserToUpdate() {
        try {
            setUpdateHumanResource(getController().findHumanResourceById(getUpdateId()));
//            setRoleUpdate(getUpdateHumanResource().getBonus().getId());
            updateBonusId = getRoleUpdate();
        } catch (Exception e) {
            logger.error("updateSlctBtnHRActionListener", e);
            Object[] param = {getBundlePropertyFile().getString("searching")};

            buildAndSendMessage(
                    param,
                    getBundlePropertyFile().getString("message.format.fatal"),
                    FacesMessage.SEVERITY_FATAL,
                    getBundlePropertyFile().getString("employee")
            );
        }
    }

    /**
     *
     * @param option 1 = to disabled, 2 = to enebled, 4 = to updated
     */
    public void enebledDisabledDropBoxListener(int option) {
        logger.debug("enebledDisabledDropBoxListener");
        try {
            HumanResourceStatus status = HumanResourceStatus.ENEBLED;

            boolean goAHead = false;

            switch (option) {
                case 1:
                    if (isEnebledHR()) {
                        goAHead = isEnebledHR();
                    } else {
                        setEnebledHumanResourcesLst(null);
                    }
                    break;
                case 2:
                    if (isDisabledHR()) {
                        goAHead = isDisabledHR();
                    } else {
                        setDeletedHumanResourcesLst(null);
                    }
                    break;
                case 4:
                    if (isSelectedUpdateHR()) {
                        goAHead = isSelectedUpdateHR();
                    } else {
                        setUpdateHumanResourcesLst(null);
                        setUpdateHumanResource(new HumanResource());
                        setRoleUpdate("N/A");
                    }
                    break;
            }

            if (goAHead) {
                if (2 == option) {
                    status = HumanResourceStatus.DISABLED;
                }

                List<HumanResource> results = getController().findEmployeesByType(
                        new Office(getLoggedUser().getOffice().getId()),
                        status,
                        getLoggedUser().getUser().getHumanResource().getId()
                );

                switch (option) {
                    case 1:
                        setEnebledHumanResourcesLst(results);
                        break;
                    case 2:
                        setDisabledHumanResourcesLst(results);
                        break;
                    case 4:
                        setUpdateHumanResourcesLst(results);
                        setUpdateHumanResource(new HumanResource());
                        break;
                }
            }
        } catch (Exception e) {
            logger.error("enebledDisabledDropBoxListener");

            Object[] param = {getBundlePropertyFile().getString("searching")};

            buildAndSendMessage(
                    param,
                    getBundlePropertyFile().getString("message.format.fatal"),
                    FacesMessage.SEVERITY_FATAL,
                    getBundlePropertyFile().getString("employee")
            );
        }
    }

    public void deletedHRListener() {
        try {
            if (isDeletedHR()) {
                List<HumanResourceStatus> statusLst = new ArrayList<>();

                statusLst.add(HumanResourceStatus.ENEBLED);
                statusLst.add(HumanResourceStatus.DISABLED);

                setDeletedHumanResourcesLst(
                        getController().findEmployeesInType(
                                new Office(getLoggedUser().getOffice().getId()),
                                statusLst,
                                getLoggedUser().getUser().getHumanResource().getId()
                        )
                );
            } else {
                setDeletedHumanResourcesLst(null);
            }
        } catch (Exception e) {
            logger.error("deletedHRListener", e);
            Object[] param = {getBundlePropertyFile().getString("searching")};

            buildAndSendMessage(
                    param,
                    getBundlePropertyFile().getString("message.format.fatal"),
                    FacesMessage.SEVERITY_FATAL,
                    getBundlePropertyFile().getString("employee")
            );
        }
    }

    /**
     *
     * @param status
     * @param userIdSelected
     * @param msgTitle
     * @param msgAction
     */
    private boolean executeAction(HumanResourceStatus status, String userIdSelected, String msgTitle, String msgAction) {
        logger.debug("executeAction");

        boolean success = false;

        try {
            String messafeFormat = getBundlePropertyFile().getString("message.format.failure");
            FacesMessage.Severity severity = FacesMessage.SEVERITY_WARN;

            success = getController().updateHRByStatus(status, userIdSelected, getLoggedUser().getUser().getId());

            if (success) {
                logger.debug("executeAction");

                messafeFormat = getBundlePropertyFile().getString("message.format.sucess");
                severity = FacesMessage.SEVERITY_INFO;
            }

            Object[] param = {msgTitle, msgAction};

            buildAndSendMessage(param, messafeFormat, severity, msgTitle);
        } catch (Exception e) {
            logger.error("executeAction", e);
            Object[] param = {msgTitle};

            buildAndSendMessage(
                    param,
                    getBundlePropertyFile().getString("message.format.fatal"),
                    FacesMessage.SEVERITY_FATAL,
                    msgTitle
            );
        }
        return success;
    }

    private void refreshDropBox() {
        try {
            if (isEnebledHR()) {
                enebledDisabledDropBoxListener(1);
            }
            if (isDisabledHR()) {
                enebledDisabledDropBoxListener(2);
            }

            if (isDeletedHR()) {
                deletedHRListener();
            }

            if (isSelectedUpdateHR()) {
                enebledDisabledDropBoxListener(4);
            }
        } catch (Exception e) {
            logger.error("executeAction", e);
        }
    }

    private static final long serialVersionUID = 2969985354193657703L;
    final Logger logger = LogManager.getLogger(EmployeeBean.class);

    public EmployeeController controller;
    public RoleController roleCtrl;

    public HumanResource saveHumanResource;
    public HumanResource updateHumanResource;

    public String role;
    public List<RoleCtlg> roles;

    public boolean selectedUpdateHR;
    public String updateId;

    private String roleUpdate;
    public List<HumanResource> updateHumanResourcesLst;
    public List<RoleCtlg> typeLst;

    public boolean enebledHR;
    public String enebledId;
    public List<HumanResource> enebledHumanResourcesLst;

    public boolean disabledHR;
    public String disabledId;
    public List<HumanResource> disabledHumanResourcesLst;

    public List<Bonus> bonuses;
    public boolean deletedHR;
    public String deletedId;
    public List<HumanResource> deletedHumanResourcesLst;

    public String bonusId;
    public String updateBonusId;

    @PostConstruct()
    public void init() {
        try {
            loadBundlePropertyFile();
            setController(new EmployeeController());
            setRoleCtrl(new RoleController());
            setSaveHumanResource(new HumanResource());
            setUpdateHumanResource(new HumanResource());
            roles = getRoleCtrl().fillRolesDatatable();
            setBonuses(new ArrayList<>());
        } catch (Exception e) {
            logger.error("init", e);
        }
    }

    @PreDestroy
    public void finish() {
        try {
            setSaveHumanResource(new HumanResource());
            setUpdateHumanResource(new HumanResource());
            setController(null);
        } catch (Exception e) {
            logger.error("finish", e);
        }
    }

    public List<Bonus> getBonuses() {
        return bonuses;
    }

    public void setBonuses(List<Bonus> bonuses) {
        this.bonuses = bonuses;
    }

    public RoleController getRoleCtrl() {
        return roleCtrl;
    }

    public void setRoleCtrl(RoleController roleCtrl) {
        this.roleCtrl = roleCtrl;
    }

    public EmployeeController getController() {
        return controller;
    }

    public void setController(EmployeeController controller) {
        this.controller = controller;
    }

    public HumanResource getSaveHumanResource() {
        return saveHumanResource;
    }

    public void setSaveHumanResource(HumanResource saveHumanResource) {
        this.saveHumanResource = saveHumanResource;
    }

    public HumanResource getUpdateHumanResource() {
        return updateHumanResource;
    }

    public void setUpdateHumanResource(HumanResource updateHumanResource) {
        this.updateHumanResource = updateHumanResource;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<RoleCtlg> getRoles() {
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

    public void setRoles(List<RoleCtlg> roles) {
        this.roles = roles;
    }

    public boolean isSelectedUpdateHR() {
        return selectedUpdateHR;
    }

    public void setSelectedUpdateHR(boolean selectedUpdateHR) {
        this.selectedUpdateHR = selectedUpdateHR;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getRoleUpdate() {
        return roleUpdate;
    }

    public void setRoleUpdate(String roleUpdate) {
        this.roleUpdate = roleUpdate;
    }

    public List<HumanResource> getUpdateHumanResourcesLst() {
        return updateHumanResourcesLst;
    }

    public void setUpdateHumanResourcesLst(List<HumanResource> updateHumanResourcesLst) {
        this.updateHumanResourcesLst = updateHumanResourcesLst;
    }

    public List<RoleCtlg> getTypeLst() {
        return typeLst;
    }

    public void setTypeLst(List<RoleCtlg> typeLst) {
        this.typeLst = typeLst;
    }

    public boolean isEnebledHR() {
        return enebledHR;
    }

    public void setEnebledHR(boolean enebledHR) {
        this.enebledHR = enebledHR;
    }

    public String getEnebledId() {
        return enebledId;
    }

    public void setEnebledId(String enebledId) {
        this.enebledId = enebledId;
    }

    public List<HumanResource> getEnebledHumanResourcesLst() {
        return enebledHumanResourcesLst;
    }

    public void setEnebledHumanResourcesLst(List<HumanResource> enebledHumanResourcesLst) {
        this.enebledHumanResourcesLst = enebledHumanResourcesLst;
    }

    public boolean isDisabledHR() {
        return disabledHR;
    }

    public void setDisabledHR(boolean disabledHR) {
        this.disabledHR = disabledHR;
    }

    public String getDisabledId() {
        return disabledId;
    }

    public void setDisabledId(String disabledId) {
        this.disabledId = disabledId;
    }

    public List<HumanResource> getDisabledHumanResourcesLst() {
        return disabledHumanResourcesLst;
    }

    public void setDisabledHumanResourcesLst(List<HumanResource> disabledHumanResourcesLst) {
        this.disabledHumanResourcesLst = disabledHumanResourcesLst;
    }

    public boolean isDeletedHR() {
        return deletedHR;
    }

    public void setDeletedHR(boolean deletedHR) {
        this.deletedHR = deletedHR;
    }

    public String getDeletedId() {
        return deletedId;
    }

    public void setDeletedId(String deletedId) {
        this.deletedId = deletedId;
    }

    public List<HumanResource> getDeletedHumanResourcesLst() {
        return deletedHumanResourcesLst;
    }

    public void setDeletedHumanResourcesLst(List<HumanResource> deletedHumanResourcesLst) {
        this.deletedHumanResourcesLst = deletedHumanResourcesLst;
    }

    public String getBonusId() {
        return bonusId;
    }

    public void setBonusId(String bonusId) {
        this.bonusId = bonusId;
    }

    public String getUpdateBonusId() {
        return updateBonusId;
    }

    public void setUpdateBonusId(String updateBonusId) {
        this.updateBonusId = updateBonusId;
    }

}
