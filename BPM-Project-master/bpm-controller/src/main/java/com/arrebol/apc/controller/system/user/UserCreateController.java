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
import com.arrebol.apc.model.core.User;
import com.arrebol.apc.model.core.UserByOffice;
import com.arrebol.apc.model.core.UserByOfficeHasPermission;
import com.arrebol.apc.model.core.UserByOfficeHasPermissionId;
import com.arrebol.apc.model.core.constance.HumanResourceCfg;
import com.arrebol.apc.model.core.constance.OfficeCfg;
import com.arrebol.apc.model.core.constance.PermissionCfg;
import com.arrebol.apc.model.core.constance.UserByOfficeCfg;
import com.arrebol.apc.model.core.constance.UserByOfficeHasPermissionCfg;
import com.arrebol.apc.model.core.constance.UserCfg;
import com.arrebol.apc.model.enums.HumanResourceStatus;
import com.arrebol.apc.model.enums.PermissionStatus;
import com.arrebol.apc.model.enums.PermissionType;
import com.arrebol.apc.repository.GenericEntityRepository;
import com.arrebol.apc.repository.core.HumanResourceRepository;
import com.arrebol.apc.repository.core.PermissionRepository;
import com.arrebol.apc.repository.core.UserByOfficeRepository;
import com.arrebol.apc.repository.core.UserRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class UserCreateController implements Serializable {

    /**
     *
     * @return
     */
    public List<Permission> getAllActivePermissionController() {
        logger.debug("getAllActivePermissionController");
        return getPermissionRepository().getAllActivePermissionByType(PermissionType.PRIVATE);
    }

    /**
     *
     * @param searchingName
     * @param officeId
     * @return True if userName is available otherwise false.
     */
    public boolean isUsernameAvailableController(String searchingName, String officeId) {
        logger.debug("isUsernameAvailableController");

        boolean available = false;
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(UserCfg.FIELD_USER_NAME, searchingName));
            parameters.add(new ModelParameter(OfficeCfg.FIELD_ID, officeId));

            Long count = (Long) getUserRepository()
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
     * 1) Verifica que el usuario aun este disponible 2) Verifica que tipo de
     * usuario se va a crear y en todos los casos se debera de guardar usuario y
     * oficina más las carecteristicas propias de cada tipo de usuario: A) WEB
     * debe guardar permisos B) MOBILE debe guardar rutas C) BOTH debe guardar
     * rutas y permisos.
     *
     * @param user
     * @param userByOffice
     * @param routes
     * @param permissions
     * @return
     */
    public boolean saveUserController(User user, UserByOffice userByOffice,
            List<Permission> permissions) {
        logger.debug("saveUserController");
        boolean success = false;
        try {
            List<HumanResourceHasRoute> humanResourceHasRoutes = new ArrayList<>();
            List<UserByOfficeHasPermission> userByOfficeHasPermissions = new ArrayList<>();
            List<ModelParameter> parameters = new ArrayList<>();
            List<PermissionType> types = new ArrayList<>();
            List<Permission> genericPermissions;

            switch (user.getUserType()) {
                case WEB:
                    types.add(PermissionType.PUBLIC);

                    parameters.add(new ModelParameter(PermissionCfg.FIELD_PERMISSION_TYPE, types));
                    parameters.add(new ModelParameter(PermissionCfg.FIELD_PERMISSION_STATUS, PermissionStatus.ENEBLED));

                    genericPermissions = getGenericEntityRepository().xmlQueryAPCEntities(Permission.class, PermissionCfg.QUERY_FIND_PERMISSION_BY_TYPE_AND_STATUS, parameters);

                    genericPermissions.forEach((Permission value) -> {
                        UserByOfficeHasPermission row = new UserByOfficeHasPermission(
                                new UserByOfficeHasPermissionId(null, value.getId()), user.getCreatedBy()
                        );

                        userByOfficeHasPermissions.add(row);
                    });

                    permissions.forEach((Permission value) -> {
                        UserByOfficeHasPermission row = new UserByOfficeHasPermission(
                                new UserByOfficeHasPermissionId(null, value.getId()), user.getCreatedBy()
                        );

                        userByOfficeHasPermissions.add(row);
                    });
                    break;
//                case MOBILE:
//                    routes.forEach((RouteCtlg value) -> {
//                        HumanResourceHasRoute row = new HumanResourceHasRoute(
//                                new HumanResourceHasRouteId(user.getHumanResource().getId(), value.getId()),
//                                user.getCreatedBy()
//                        );
//
//                        humanResourceHasRoutes.add(row);
//                    });
//                    break;
                case BOTH:
                    types.add(PermissionType.PUBLIC);

                    parameters.add(new ModelParameter(PermissionCfg.FIELD_PERMISSION_TYPE, types));
                    parameters.add(new ModelParameter(PermissionCfg.FIELD_PERMISSION_STATUS, PermissionStatus.ENEBLED));

                    genericPermissions = getGenericEntityRepository().xmlQueryAPCEntities(Permission.class, PermissionCfg.QUERY_FIND_PERMISSION_BY_TYPE_AND_STATUS, parameters);

                    genericPermissions.forEach((Permission value) -> {
                        UserByOfficeHasPermission row = new UserByOfficeHasPermission(
                                new UserByOfficeHasPermissionId(null, value.getId()), user.getCreatedBy()
                        );

                        userByOfficeHasPermissions.add(row);
                    });

                    permissions.forEach((Permission value) -> {
                        UserByOfficeHasPermission row = new UserByOfficeHasPermission(
                                new UserByOfficeHasPermissionId(null, value.getId()), user.getCreatedBy()
                        );

                        userByOfficeHasPermissions.add(row);
                    });

//                    routes.forEach((RouteCtlg value) -> {
//                        HumanResourceHasRoute row = new HumanResourceHasRoute(
//                                new HumanResourceHasRouteId(user.getHumanResource().getId(), value.getId()),
//                                user.getCreatedBy()
//                        );
//
//                        humanResourceHasRoutes.add(row);
//                    });
                    break;
            }

            success = userRepository.createUser(user,
                    userByOffice,
                    humanResourceHasRoutes,
                    userByOfficeHasPermissions);
        } catch (Exception e) {
            logger.error(e);
        }

        return success;
    }

    /**
     * Save a list of APC entities
     *
     *
     * @param permissions
     * @param userByOfficeId
     * @param createdBy
     * @return
     */
    public boolean saveManyController(List<Permission> permissions, String userByOfficeId, String createdBy) {

        return getGenericEntityRepository().insertManyAPCEntity(buildUniquePermissionList(permissions, userByOfficeId, createdBy));
    }

    /**
     *
     * @param permissions New permission to be saved.
     * @param createdBy
     * @param userByOfficeId Used to look all old permissions that need to be
     * delete before to insert new permission list.
     * @return
     */
    public boolean updatePermissionsController(List<Permission> permissions, String userByOfficeId, String createdBy) {

        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(UserByOfficeHasPermissionCfg.FIELD_USER_BY_OFFICE, new UserByOffice(userByOfficeId)));

        boolean success = getGenericEntityRepository().xmlUpdateOrDeleteAPCEntity(UserByOfficeHasPermissionCfg.XML_QUERY_DELETE_PERMISSION_BY_USER_ID_AND_OFFICE_ID, parameters);

        if (success) {
            success = getGenericEntityRepository().insertManyAPCEntity(buildUniquePermissionList(permissions, userByOfficeId, createdBy));
        }

        return success;
    }

    /**
     *
     * @param user
     * @param fromView 1 = From User-> Create<br/>
     * @return
     */
    @Deprecated
    public boolean updateCreateUserController(User user, int fromView) {
        logger.debug("updateCreateUser");

        return getUserRepository().updateUserById(user, fromView);
    }

    /**
     *
     * @param office
     * @param humanResourceStatus
     * @return
     */
    public List<HumanResource> findAllHRsWithoutUser(Office office, HumanResourceStatus humanResourceStatus) {
        logger.debug("findAllHRsWithoutUser");

        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(HumanResourceCfg.FIELD_OFFICE, office));
        parameters.add(new ModelParameter(HumanResourceCfg.FIELD_HR_STATUS, humanResourceStatus));

        return getGenericEntityRepository().xmlQueryAPCEntities(HumanResource.class, HumanResourceCfg.QUERY_FIND_ALL_HRS_WITHOUT_USER, parameters);
    }

    /**
     *
     * @param userId
     * @param officeId
     * @return If user was found return an instace of UserByOffice object, but
     * return null if user was not found.
     */
    public UserByOffice findIdOfUserByOffice(String userId, String officeId) {
        logger.debug("findAllHRsWithoutUser");
        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(UserByOfficeCfg.FIELD_USER, new User(userId)));
        parameters.add(new ModelParameter(UserByOfficeCfg.FIELD_OFFICE, new Office(officeId)));

        return getUserByOfficeRepository().findIdOfUserByOffice(UserByOfficeCfg.QUERY_FIND_ID_OF_USER_BY_OFFICE, parameters);
    }

    /**
     *
     * @return
     */
    public List<Permission> findAllEnebledPermissions() {
        logger.debug("findAllEnebledPermissions");
        return getPermissionRepository().findAllEnebledPermissions();
    }

    /**
     *
     * @param entities
     * @return
     */
    private List buildUniquePermissionList(List<Permission> permissions, String userByOfficeId, String createdBy) {
        logger.info("buildUniquePermissionList");

        List<UserByOfficeHasPermission> allNewPermissions = new ArrayList<>();

        try {
            Set<Permission> uniquePermission = new HashSet<>();

            List<Permission> appMenuLst = findAllEnebledPermissions();

            permissions.forEach((permission) -> {
                if (!uniquePermission.contains(permission)) {
                    uniquePermission.add(permission);
                }

                Optional<Permission> optional = appMenuLst.stream()
                        .filter((appMenu) -> appMenu.getPermission().equals(permission.getParentName()))
                        .findAny();

                if (optional.isPresent()) {
                    if (!uniquePermission.contains(optional.get())) {
                        uniquePermission.add(optional.get());
                    }
                }
            });

            uniquePermission.forEach((unique) -> {
                allNewPermissions.add(
                        new UserByOfficeHasPermission(
                                new UserByOfficeHasPermissionId(
                                        userByOfficeId,
                                        unique.getId()
                                ),
                                createdBy
                        )
                );
            });
        } catch (Exception e) {
            logger.error("buildUniquePermissionList", e);
            throw e;
        }

        return allNewPermissions;
    }

    private static final long serialVersionUID = 3496689435395697940L;
    final Logger logger = LogManager.getLogger(UserCreateController.class);

    private final GenericEntityRepository genericEntityRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final HumanResourceRepository humanResourceRepository;
    private final UserByOfficeRepository userByOfficeRepository;

    public UserCreateController() {
        this.genericEntityRepository = new GenericEntityRepository();
        this.permissionRepository = new PermissionRepository();
        this.userRepository = new UserRepository();
        this.humanResourceRepository = new HumanResourceRepository();
        this.userByOfficeRepository = new UserByOfficeRepository();
    }

    private GenericEntityRepository getGenericEntityRepository() {
        return genericEntityRepository;
    }

    private PermissionRepository getPermissionRepository() {
        return permissionRepository;
    }

    private UserRepository getUserRepository() {
        return userRepository;
    }

    private HumanResourceRepository getHumanResourceRepository() {
        return humanResourceRepository;
    }

    public UserByOfficeRepository getUserByOfficeRepository() {
        return userByOfficeRepository;
    }

}
