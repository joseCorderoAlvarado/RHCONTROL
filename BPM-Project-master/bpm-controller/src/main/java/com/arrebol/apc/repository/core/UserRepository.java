/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.repository.core;

import com.arrebol.apc.model.catalog.HumanResourceHasRoute;
import com.arrebol.apc.model.core.HumanResource;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.User;
import com.arrebol.apc.model.core.UserByOffice;
import com.arrebol.apc.model.core.UserByOfficeHasPermission;
import com.arrebol.apc.model.core.constance.UserByOfficeCfg;
import com.arrebol.apc.model.core.constance.UserCfg;
import com.arrebol.apc.repository.AbstractRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class UserRepository extends AbstractRepository implements Serializable {

    /**
     *
     * @param officeId
     * @return
     */
    public List<User> findAllUsersByOffice(String officeId) {
        logger.debug("findAllUsersByOffice");

        List<User> users = new ArrayList<>();
        try {
            openConnection();

            List<Tuple> tupleLst = getSession().createNamedQuery(UserCfg.QUERY_FIND_ALL_USERS_BY_OFFICE, Tuple.class)
                    .setParameter(UserByOfficeCfg.FIELD_OFFICE, new Office(officeId))
                    .getResultList();

            tupleLst.forEach((tuple) -> {
                HumanResource hr = new HumanResource((String) tuple.get(0), (String) tuple.get(0), (String) tuple.get(0));
                User usr = new User((String) tuple.get(0), (String) tuple.get(1), hr);

                users.add(usr);
            });

            closeConnection();
            logger.debug("Office has users " + users.size());
        } catch (HibernateException e) {
            logger.error("findAllUsersByOffice hibernate", e);
            rollback();
        } catch (Exception e) {
            logger.error("Method findAllUsersByOffice() ", e);
            rollback();
        }
        return users;
    }

    /**
     *
     * @param user
     * @param fromView 1 = From View User -> Create<br/>
     * @return
     */
    @Deprecated
    public boolean updateUserById(User user, int fromView) {
        logger.debug("updateByHumanResourceId");

        boolean success = false;
        try {
            openConnection();

            CriteriaBuilder builder = getSession().getCriteriaBuilder();
            CriteriaUpdate<HumanResource> update = builder.createCriteriaUpdate(HumanResource.class);
            Root<HumanResource> root = update.from(HumanResource.class);

            switch (fromView) {
                case 1:
                    update.set(root.get(UserCfg.FIELD_HUMAN_RESOURCE), user.getHumanResource());
                    //update.set(root.get(UserCfg.FIELD_USER_NAME), user.getUserName());
                    update.set(root.get(UserCfg.FIELD_PASSWORD), user.getPassword());
                    break;
            }

            update.set(root.get(UserCfg.FIELD_LAST_UPDATED_BY), user.getLastUpdatedBy());
            update.set(root.get(UserCfg.FIELD_LAST_UPDATED_ON), user.getLastUpdatedOn());

            update.where(builder.equal(root.get(UserCfg.FIELD_ID), user.getId()));

            int total = getSession().createQuery(update).executeUpdate();

            if (1 == total) {
                closeConnection();
                success = true;
                logger.debug("User updated");
            } else {
                logger.error("Update user", new Exception("From: " + fromView));
                rollback();
            }
        } catch (HibernateException e) {
            logger.error("updateByHumanResourceId hibernate", e);
            rollback();
        } catch (Exception e) {
            logger.error("Method updateByHumanResourceId() ", e);
            rollback();
        }
        return success;
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
     * @param humanResourceHasRoutes
     * @param userByOfficeHasPermissions
     * @return
     */
    public boolean createUser(User user, UserByOffice userByOffice,
            List<HumanResourceHasRoute> humanResourceHasRoutes,
            List<UserByOfficeHasPermission> userByOfficeHasPermissions) {
        boolean success = false;

        try {
            openConnection();

            // Start generic block for all user types
            getSession().save(user);

            userByOffice.setUser(new User(user.getId()));

            getSession().save(userByOffice);
            // End generic block for all user types

            switch (user.getUserType()) {
                case WEB:
                    userByOfficeHasPermissions.forEach((value) -> {
                        value.getId().setIdUserByOffice(userByOffice.getId());
                        getSession().save(value);
                    });
                    break;
                case MOBILE:
                    humanResourceHasRoutes.forEach(getSession()::save);
                    break;
                case BOTH:
                    userByOfficeHasPermissions.forEach((value) -> {
                        value.getId().setIdUserByOffice(userByOffice.getId());
                        getSession().save(value);
                    });

                    humanResourceHasRoutes.forEach(getSession()::save);
                    break;
            }

            closeConnection();
            success = true;
        } catch (HibernateException e) {
            logger.error("updateByHumanResourceId hibernate", e);
            rollback();
        } catch (Exception e) {
            logger.error("Method updateByHumanResourceId() ", e);
            rollback();
        }
        return success;
    }
    private static final long serialVersionUID = -3153392012298562511L;
    final Logger logger = LogManager.getLogger(UserRepository.class);
}
