/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.repository.core;

import com.arrebol.apc.model.core.Permission;
import com.arrebol.apc.model.core.constance.PermissionCfg;
import com.arrebol.apc.model.enums.PermissionStatus;
import com.arrebol.apc.model.enums.PermissionType;
import com.arrebol.apc.repository.AbstractRepository;
import java.io.Serializable;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class PermissionRepository extends AbstractRepository implements Serializable {

    public List<Permission> getAllActivePermissionByType(PermissionType permissionType) {
        logger.debug("getAllActivePermissionByType");

        List<Permission> permissions = null;
        try {
            openConnection();

            CriteriaBuilder builder = getSession().getCriteriaBuilder();
            CriteriaQuery<Permission> query = builder.createQuery(Permission.class);
            Root<Permission> root = query.from(Permission.class);
            Path<String> id = root.get(PermissionCfg.FIELD_ID);
            Path<String> permission = root.get(PermissionCfg.FIELD_PERMISSION);
            Path<String> description = root.get(PermissionCfg.FIELD_DESCRIPTION);
            Path<String> menuPath = root.get(PermissionCfg.FIELD_MENU_PATH);

            query.select(builder.construct(Permission.class, id, permission, description, menuPath));
            query.where(
                    builder.and(
                            builder.equal(root.get(PermissionCfg.FIELD_PERMISSION_STATUS), PermissionStatus.ENEBLED),
                            builder.equal(root.get(PermissionCfg.FIELD_PERMISSION_TYPE), permissionType)
                    )
            );

            query.orderBy(
                    builder.asc(root.get(PermissionCfg.FIELD_LEFT_TO_RIGHT)),
                    builder.asc(root.get(PermissionCfg.FIELD_TOP_TO_BOTTOM))
            );

            permissions = getSession().createQuery(query).getResultList();

            closeConnection();
            logger.debug("Total of permissions: " + permissions.size());
        } catch (HibernateException e) {
            logger.error("getAllActivePermissionByType hibernate", e);
            rollback();
        } catch (Exception e) {
            logger.error("Method getAllActivePermissionByType() ", e);
            rollback();
        }

        return permissions;
    }

    /**
     *
     * @return
     */
    public List<Permission> findAllEnebledPermissions() {
        logger.debug("findAllEnebledPermissions");
        List<Permission> permissions = null;

        try {
            permissions = xmlQueryAPCEntities(Permission.class, PermissionCfg.QUERY_FIND_ENEBLED_PERMISSIONS, null);
        } catch (Exception e) {
            logger.error("findAllEnebledPermissions", e);
        }

        return permissions;
    }

    private static final long serialVersionUID = -3100646446560072563L;
    final Logger logger = LogManager.getLogger(PermissionRepository.class);
}
