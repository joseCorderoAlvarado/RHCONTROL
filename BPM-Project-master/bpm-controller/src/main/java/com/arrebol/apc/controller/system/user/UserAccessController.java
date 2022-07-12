/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.system.user;

import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.Permission;
import com.arrebol.apc.model.core.UserByOffice;
import com.arrebol.apc.model.core.UserByOfficeHasPermission;
import com.arrebol.apc.model.core.UserByOfficeHasPermissionId;
import com.arrebol.apc.model.core.constance.PermissionCfg;
import com.arrebol.apc.model.core.constance.UserByOfficeHasPermissionCfg;
import com.arrebol.apc.model.enums.UserStatus;
import com.arrebol.apc.repository.GenericEntityRepository;
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
public class UserAccessController implements Serializable {

    public List findUsersInOfficeInStatuses(List<UserStatus> statuses, String officeId, String ownerId) {
        logger.debug("findUsersInOfficeInStatuses");
        List retults = new ArrayList();
        try {
            retults = getUserByOfficeRepository()
                    .findUsersInOfficeInStatuses(statuses, new Office(officeId), ownerId);
        } catch (Exception e) {
            logger.error("findUsersInOfficeInStatuses", e);
        }

        return retults;
    }

    /**
     *
     * @param userByOffice
     * @param missing
     * @return
     */
    public List loadUserByOfficePermissionLst(UserByOffice userByOffice, boolean missing) {
        logger.debug("loadUserByOfficePermissionLst");
        String query = PermissionCfg.QUERY_FIND_PERMISSION_THAT_USER_HAS_ASSIGNED;

        if (missing) {
            query = PermissionCfg.QUERY_FIND_MISSING_PERMISSION_UBO;
        }
        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(UserByOfficeHasPermissionCfg.FIELD_USER_BY_OFFICE, userByOffice));

        return getGenericEntityRepository().xmlQueryAPCEntities(Permission.class, query, parameters);
    }

    /**
     *
     * @param permissions
     * @param createdBy
     * @param userByOfficeId Used to look all old permissions that need to be
     * delete before to insert new permission list.
     * @return
     */
    public boolean updatePermissionsController(List<Permission> permissions, String userByOfficeId, String createdBy) {
        logger.debug("updatePermissionsController");

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

    private static final long serialVersionUID = -4994341295468752327L;
    final Logger logger = LogManager.getLogger(UserAccessController.class);

    private final GenericEntityRepository genericEntityRepository;
    private final UserRepository userRepository;
    private final UserByOfficeRepository userByOfficeRepository;
    private final PermissionRepository permissionRepository;

    public UserAccessController() {
        this.genericEntityRepository = new GenericEntityRepository();
        this.userRepository = new UserRepository();
        this.userByOfficeRepository = new UserByOfficeRepository();
        this.permissionRepository = new PermissionRepository();
    }

    public GenericEntityRepository getGenericEntityRepository() {
        return genericEntityRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public UserByOfficeRepository getUserByOfficeRepository() {
        return userByOfficeRepository;
    }

    public PermissionRepository getPermissionRepository() {
        return permissionRepository;
    }

}
