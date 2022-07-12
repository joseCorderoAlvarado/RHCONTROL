/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.system.user;

import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.catalog.HumanResourceHasRoute;
import com.arrebol.apc.model.core.HumanResource;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.Permission;
import com.arrebol.apc.model.core.UserByOffice;
import com.arrebol.apc.model.core.UserByOfficeHasPermission;
import com.arrebol.apc.model.core.UserByOfficeHasPermissionId;
import com.arrebol.apc.model.core.constance.HumanResourceHasRouteCfg;
import com.arrebol.apc.model.core.constance.OfficeCfg;
import com.arrebol.apc.model.core.constance.PermissionCfg;
import com.arrebol.apc.model.core.constance.UserByOfficeHasPermissionCfg;
import com.arrebol.apc.model.core.constance.UserCfg;
import com.arrebol.apc.model.enums.ActiveStatus;
import com.arrebol.apc.model.enums.PermissionStatus;
import com.arrebol.apc.model.enums.PermissionType;
import com.arrebol.apc.model.enums.UserStatus;
import com.arrebol.apc.model.enums.UserType;
import com.arrebol.apc.repository.core.UserByOfficeRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class UserUpdateController implements Serializable {

    /**
     *
     * @param statuses
     * @param officeId
     * @param ownerId
     * @return
     */
    public List<UserByOffice> findUsersInOfficeInStatuses(List<UserStatus> statuses, String officeId, String ownerId) {
        logger.debug("findUsersInOfficeInStatuses");
        List retults = new ArrayList();
        try {
            retults = userByOfficeRepository.findUsersInOfficeInStatuses(statuses, new Office(officeId), ownerId);
        } catch (Exception e) {
            logger.error("findUsersInOfficeInStatuses", e);
        }

        return retults;
    }

    /**
     *
     * @param isRoute true is route otherwise is permission.
     * @param in true means bring by UBO or HR, otherwise means find list that
     * does not have UBO or HR.
     * @param uboId User By Office Id.
     * @param hrId Human Resource Id.
     * @param officeId Office Id.
     * @return
     */
    public List findList(boolean isRoute, boolean in, String uboId, String hrId, String officeId) {
        logger.debug("findList");
        List results = new ArrayList();
//        try {
//            String query;
//            List<ModelParameter> parameters = new ArrayList<>();
//
//            if (isRoute) {
//                query = in ? RouteCfg.QUERY_FIND_ALL_ROUTES_BY_HRHR : RouteCfg.QUERY_FIND_ALL_NOT_ROUTES_BY_HRHR;
//
//                parameters.add(new ModelParameter(HumanResourceHasRouteCfg.FIELD_HUMAN_RESOURCE, new HumanResource(hrId)));
//                parameters.add(new ModelParameter(RouteCfg.FIELD_OFFICE, new Office(officeId)));
//            } else {
//                query = in ? PermissionCfg.QUERY_FIND_ALL_PERMISSIONS_BY_UBO : PermissionCfg.QUERY_FIND_ALL_NOT_PERMISSIONS_BY_UBO;
//
//                parameters.add(new ModelParameter(UserByOfficeHasPermissionCfg.FIELD_USER_BY_OFFICE, new UserByOffice(uboId)));
//            }
//            results = userByOfficeRepository.findList(query, parameters);
//        } catch (Exception e) {
//            logger.error("findList", e);
//        }
        return results;
    }

    /**
     *
     * @param isRoute
     * @param types
     * @param officeId
     * @return
     */
    public List findGeneralList(boolean isRoute, List<PermissionType> types, String officeId) {
        logger.debug("findGeneralList");
        List results = new ArrayList();
//        try {
//            String query;
//            List<ModelParameter> parameters = new ArrayList<>();
//
//            if (isRoute) {
//                query = RouteCfg.QUERY_FIND_ALL_AVAILABLES_ROUTES;
//
//                parameters.add(new ModelParameter(RouteCfg.FIELD_OFFICE, new Office(officeId)));
//                parameters.add(new ModelParameter(RouteCfg.FIELD_ACTIVE_STATUS, ActiveStatus.ENEBLED));
//            } else {
//                query = PermissionCfg.QUERY_FIND_ALL_PERMISSION_BY_TYPE_AND_STATUS;
//
//                parameters.add(new ModelParameter(PermissionCfg.FIELD_PERMISSION_TYPE, types));
//                parameters.add(new ModelParameter(PermissionCfg.FIELD_PERMISSION_STATUS, PermissionStatus.ENEBLED));
//            }
//            results = userByOfficeRepository.findList(query, parameters);
//        } catch (Exception e) {
//            logger.error("findGeneralList", e);
//        }
        return results;
    }

    public boolean updateUser(UserType newUserType, UserByOffice userByOffice, List<Permission> permissions, ActiveStatus certifier) {
        logger.info("updateUser");
        boolean success = false;
        try {
            List<HumanResourceHasRoute> humanResourceHasRoutes = new ArrayList<>();
            List<UserByOfficeHasPermission> userByOfficeHasPermissions = new ArrayList<>();

            switch (newUserType) {
                case WEB:
                    permissions.forEach((Permission value) -> {
                        UserByOfficeHasPermission row = new UserByOfficeHasPermission(
                                new UserByOfficeHasPermissionId(userByOffice.getId(), value.getId()), userByOffice.getCreatedBy()
                        );

                        userByOfficeHasPermissions.add(row);
                    });
                    break;
//                case MOBILE:
//                    routes.forEach((RouteCtlg value) -> {
//                        HumanResourceHasRoute row = new HumanResourceHasRoute(
//                                new HumanResourceHasRouteId(userByOffice.getUser().getHumanResource().getId(), value.getId()),
//                                userByOffice.getCreatedBy()
//                        );
//
//                        humanResourceHasRoutes.add(row);
//                    });
//                    break;
                case BOTH:
                    permissions.forEach((Permission value) -> {
                        UserByOfficeHasPermission row = new UserByOfficeHasPermission(
                                new UserByOfficeHasPermissionId(userByOffice.getId(), value.getId()), userByOffice.getCreatedBy()
                        );

                        userByOfficeHasPermissions.add(row);
                    });

//                    routes.forEach((RouteCtlg value) -> {
//                        HumanResourceHasRoute row = new HumanResourceHasRoute(
//                                new HumanResourceHasRouteId(userByOffice.getUser().getHumanResource().getId(), value.getId()),
//                                userByOffice.getCreatedBy()
//                        );
//
//                        humanResourceHasRoutes.add(row);
//                    });
                    break;
            }

            // 1 Borrar rutas y/o permisos
            //      a- Si usuario previo es web - borrar permisos
            //      b- Si usuario previo es mobile - borrar rutas
            //      c- Si usuario previo es ambos - borrar permisos y rutas.
            // 2 Actualizar datos
            //      a- Si usuario es web - agregar permisos
            //      b- Si usuario es mobile - agragar rutas
            //      c- Si usuario es ambos - agregar permisos y rutas.
            success = userByOfficeRepository.updateUser(
                    newUserType,
                    userByOffice,
                    certifier,
                    humanResourceHasRoutes,
                    userByOfficeHasPermissions
            );
        } catch (Exception e) {
            logger.error("updateUser", e);
        }
        return success;
    }

    /**
     *
     * @param searchingName
     * @param officeId
     * @return
     */
    public boolean isUsernameAvailableController(String searchingName, String officeId) {
        logger.debug("isUsernameAvailableController");

        boolean available = false;
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(UserCfg.FIELD_USER_NAME, searchingName));
            parameters.add(new ModelParameter(OfficeCfg.FIELD_ID, officeId));

            Long count = (Long) userByOfficeRepository
                    .xmlQueryAPCEntityUniqueResult(
                            UserCfg.QUERY_IS_USERNAME_AVAILABLE,
                            parameters
                    );

            if (count == 0) {
                available = true;
            }
        } catch (Exception e) {
            logger.error("isUsernameAvailableController", e);
        }

        return available;
    }

    /**
     *
     * @param action Actions are pwd, usr, enebled, disabled & deleted.
     * @param userId
     * @param status
     * @param pwd
     * @param userName
     * @param lastUpdatedBy
     * @return
     */
    public boolean updateUserOtherActions(
            String action,
            String userId,
            UserStatus status,
            String pwd,
            String userName,
            String lastUpdatedBy) {
        logger.info("updateUserOtherActions");
        boolean success = false;
        try {
            String updateQuery;
            List<ModelParameter> parameters = new ArrayList<>();
            switch (action) {
                case "pwd":
                    updateQuery = UserCfg.QUERY_UPDATE_PASSWORD_BY_USER_ID;

                    parameters.add(new ModelParameter(UserCfg.FIELD_PASSWORD, pwd));
                    break;
                case "usr":
                    updateQuery = UserCfg.QUERY_UPDATE_USER_NAME_BY_USER_ID;

                    parameters.add(new ModelParameter(UserCfg.FIELD_USER_NAME, userName));
                    parameters.add(new ModelParameter(UserCfg.FIELD_LAST_UPDATED_BY, lastUpdatedBy));
                    parameters.add(new ModelParameter(UserCfg.FIELD_LAST_UPDATED_ON, new Date()));
                    break;
                default:
                    updateQuery = UserCfg.QUERY_UPDATE_USER_STATUS_BY_USER_ID;

                    parameters.add(new ModelParameter(UserCfg.FIELD_USER_STATUS, status));
                    parameters.add(new ModelParameter(UserCfg.FIELD_LAST_UPDATED_BY, lastUpdatedBy));
                    parameters.add(new ModelParameter(UserCfg.FIELD_LAST_UPDATED_ON, new Date()));
                    break;
            }
            parameters.add(new ModelParameter(UserCfg.FIELD_ID, userId));

            success = userByOfficeRepository.updateEntityByQuery(updateQuery, parameters);
        } catch (Exception e) {
            logger.error("updateUserOtherActions", e);
        }
        return success;
    }

    private static final long serialVersionUID = -6448348501480568726L;
    final Logger logger = LogManager.getLogger(UserUpdateController.class);

    public UserUpdateController() {
        this.userByOfficeRepository = new UserByOfficeRepository();
    }

    private final UserByOfficeRepository userByOfficeRepository;

}
