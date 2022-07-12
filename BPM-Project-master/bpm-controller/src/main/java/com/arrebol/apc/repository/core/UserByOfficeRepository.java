/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.repository.core;

import com.arrebol.apc.controller.util.HibernateUtil;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.catalog.HumanResourceHasRoute;
import com.arrebol.apc.model.core.HumanResource;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.Permission;
import com.arrebol.apc.model.core.User;
import com.arrebol.apc.model.core.UserByOffice;
import com.arrebol.apc.model.core.UserByOfficeHasPermission;
import com.arrebol.apc.model.core.constance.HumanResourceHasRouteCfg;
import com.arrebol.apc.model.core.constance.UserByOfficeCfg;
import com.arrebol.apc.model.core.constance.UserByOfficeHasPermissionCfg;
import com.arrebol.apc.model.core.constance.UserCfg;
import com.arrebol.apc.model.enums.ActiveStatus;
import com.arrebol.apc.model.enums.UserStatus;
import com.arrebol.apc.model.enums.UserType;
import com.arrebol.apc.repository.AbstractRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Tuple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@RequestScoped
public class UserByOfficeRepository extends AbstractRepository implements Serializable {

    public List<UserByOffice> findUsersInOfficeInStatuses(List<UserStatus> statuses, Office office, String ownerId) {
        logger.debug("findUsersInOfficeInStatuses");

        List<UserByOffice> userByOfficeLst = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            List<Tuple> tupleLst = session
                    .createNamedQuery(
                            UserByOfficeCfg.QUERY_FIND_USERS_IN_OFFICE_IN_STATUSES, Tuple.class
                    )
                    .setParameter(UserByOfficeCfg.FIELD_OFFICE, office)
                    .setParameter(UserByOfficeCfg.PARAM_OWNER_ID, ownerId)
                    .setParameter(UserCfg.FIELD_USER_STATUS, statuses)
                    .getResultList();

            tupleLst.forEach(
                    (tuple) -> {
                        userByOfficeLst.add(
                                new UserByOffice(
                                        tuple.get("id", String.class),
                                        new User(
                                                tuple.get("usrId", String.class),
                                                new HumanResource(
                                                        tuple.get("hrId", String.class),
                                                        tuple.get("fullName", String.class)
                                                ),
                                                tuple.get("userName", String.class),
                                                tuple.get("certifier", ActiveStatus.class),
                                                tuple.get("userType", UserType.class)
                                        )
                                )
                        );
                    }
            );

            transaction.commit();
            logger.debug("Office has users " + userByOfficeLst.size());
        } catch (HibernateException e) {
            logger.error("findUsersInOfficeInStatuses Hibernate", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method findUsersInOfficeInStatuses() ", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (null != session) {
                session.close();
            }
        }
        return userByOfficeLst;
    }

    /**
     *
     * @param userByOffice
     * @return
     */
    public UserByOffice findUserLogged(UserByOffice userByOffice) {
        logger.debug("findUserLogged");

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Object idsLst = session.createNamedQuery(UserByOfficeCfg.QUERY_FIND_USER_LOGGED)
                    .setParameter(UserByOfficeCfg.PARAM_USER_NAME, userByOffice.getUser().getUserName())
                    .setParameter(UserByOfficeCfg.PARAM_OFFICE_ID, userByOffice.getOffice().getId())
                    .uniqueResult();

            if (null != idsLst && idsLst instanceof Object[] && ((Object[]) idsLst).length == 8) {
                Object[] obj = ((Object[]) idsLst);

                HumanResource hr = new HumanResource(
                        obj[2].toString(),
                        obj[3].toString(),
                        obj[4].toString(),
                        obj[5].toString()
                );

                userByOffice.setId(obj[0].toString());
                userByOffice.getUser().setId(obj[1].toString());
                userByOffice.getUser().setHumanResource(hr);
                userByOffice.setOffice(new Office(obj[6].toString(), obj[7].toString()));
            }

            transaction.commit();
            logger.debug("User logged: " + userByOffice);
        } catch (HibernateException e) {
            logger.error("findUserLogged Hibernate", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method findUserLogged() ", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (null != session) {
                session.close();
            }
        }

        return userByOffice;
    }

    /**
     *
     * @param xmlQuery
     * @param parameters
     * @return
     */
    public UserByOffice findIdOfUserByOffice(String xmlQuery, List<ModelParameter> parameters) {
        UserByOffice userByOffice = null;
        try {
            List<Tuple> tupleLst = xmlQueryTuple(xmlQuery, parameters);

            for (Tuple tuple : tupleLst) {
                userByOffice = new UserByOffice((String) tuple.get(UserByOfficeCfg.FIELD_ID));
            }
        } catch (Exception e) {
            logger.error("getAllActiveOffice hibernate", e);
        }
        return userByOffice;
    }

    public List findList(String xmlQuery, List<ModelParameter> parameters) {
        List results = new ArrayList();
        try {
            List<Tuple> tupleLst = xmlQueryTuple(xmlQuery, parameters);

            tupleLst.forEach((tuple) -> {
                results.add(
                        new Permission(
                                tuple.get("id", String.class),
                                tuple.get("permission", String.class),
                                tuple.get("description", String.class),
                                tuple.get("menuPath", String.class)
                        )
                );
            });
        } catch (Exception e) {
            logger.error("getAllActiveOffice hibernate", e);
        }
        return results;
    }

    // 1 Borrar rutas y/o permisos
    //      a- Si usuario previo es web - borrar permisos
    //      b- Si usuario previo es mobile - borrar rutas
    //      c- Si usuario previo es ambos - borrar permisos y rutas.
    // 2 Actualizar datos
    //      a- Si usuario es web - agregar permisos
    //      b- Si usuario es mobile - agragar rutas
    //      c- Si usuario es ambos - agregar permisos y rutas.
    // 3 Actualiza cretificador
    public boolean updateUser(UserType newUserType,
            UserByOffice userByOffice, ActiveStatus certifier,
            List<HumanResourceHasRoute> humanResourceHasRoutes,
            List<UserByOfficeHasPermission> userByOfficeHasPermissions) {
        logger.info("updateUser");
        boolean success = false;
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String query = UserCfg.QUERY_UPDATE_CERTIFIER_AND_USER_TYPE_BY_ID;
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(UserCfg.FIELD_USER_TYPE, newUserType));
            parameters.add(new ModelParameter(UserCfg.FIELD_CERTIFIER, certifier));
            parameters.add(new ModelParameter(UserCfg.FIELD_LAST_UPDATED_BY, userByOffice.getCreatedBy()));
            parameters.add(new ModelParameter(UserCfg.FIELD_LAST_UPDATED_ON, new Date()));
            parameters.add(new ModelParameter(UserCfg.FIELD_ID, userByOffice.getUser().getId()));

            Query updateOrDeleteQuery = session.createNamedQuery(query);

            for (ModelParameter parameter : parameters) {
                updateOrDeleteQuery.setParameter(parameter.getParameter(), parameter.getValue());
            }

            int total = updateOrDeleteQuery.executeUpdate();

            if (total > 0) {
                switch (userByOffice.getUser().getUserType()) {
                    case WEB:
                        query = UserByOfficeHasPermissionCfg.QUERY_DELETE_ALL_PERMISSION_BY_UBO;
                        updateOrDeleteQuery = session.createNamedQuery(query);

                        parameters.clear();
                        parameters.add(new ModelParameter(UserByOfficeHasPermissionCfg.FIELD_USER_BY_OFFICE, new UserByOffice(userByOffice.getId())));

                        for (ModelParameter parameter : parameters) {
                            updateOrDeleteQuery.setParameter(parameter.getParameter(), parameter.getValue());
                        }

                        updateOrDeleteQuery.executeUpdate();
                        break;
                    case MOBILE:
                        query = HumanResourceHasRouteCfg.QUERY_DELETE_ALL_HR_HAS_ROUTE_BY_HR;
                        updateOrDeleteQuery = session.createNamedQuery(query);

                        parameters.clear();
                        parameters.add(new ModelParameter(HumanResourceHasRouteCfg.FIELD_HUMAN_RESOURCE, new HumanResource(userByOffice.getUser().getHumanResource().getId())));

                        for (ModelParameter parameter : parameters) {
                            updateOrDeleteQuery.setParameter(parameter.getParameter(), parameter.getValue());
                        }

                        updateOrDeleteQuery.executeUpdate();
                        break;
                    case BOTH:
                        // WEB
                        query = UserByOfficeHasPermissionCfg.QUERY_DELETE_ALL_PERMISSION_BY_UBO;
                        updateOrDeleteQuery = session.createNamedQuery(query);

                        parameters.clear();
                        parameters.add(new ModelParameter(UserByOfficeHasPermissionCfg.FIELD_USER_BY_OFFICE, new UserByOffice(userByOffice.getId())));

                        for (ModelParameter parameter : parameters) {
                            updateOrDeleteQuery.setParameter(parameter.getParameter(), parameter.getValue());
                        }

                        updateOrDeleteQuery.executeUpdate();

                        // MOBILE
                        query = HumanResourceHasRouteCfg.QUERY_DELETE_ALL_HR_HAS_ROUTE_BY_HR;
                        updateOrDeleteQuery = session.createNamedQuery(query);

                        parameters.clear();
                        parameters.add(new ModelParameter(HumanResourceHasRouteCfg.FIELD_HUMAN_RESOURCE, new HumanResource(userByOffice.getUser().getHumanResource().getId())));

                        for (ModelParameter parameter : parameters) {
                            updateOrDeleteQuery.setParameter(parameter.getParameter(), parameter.getValue());
                        }

                        updateOrDeleteQuery.executeUpdate();
                        break;
                }

                switch (newUserType) {
                    case WEB:
                        userByOfficeHasPermissions.forEach(session::save);
                        break;
                    case MOBILE:
                        humanResourceHasRoutes.forEach(session::save);
                        break;
                    case BOTH:
                        userByOfficeHasPermissions.forEach(session::save);
                        humanResourceHasRoutes.forEach(session::save);
                        break;
                }

                success = true;
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (HibernateException e) {
            logger.error("updateUser Hibernate", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method updateUser() ", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (null != session) {
                session.close();
            }
        }
        return success;
    }

    /**
     *
     * @param updateQuery
     * @param parameters
     * @return
     */
    public boolean updateEntityByQuery(String updateQuery, List<ModelParameter> parameters) {
        logger.info("updateEntityByQuery");
        try {
            if (null == updateQuery || null == parameters || parameters.isEmpty()) {
                return false;
            }

            return xmlUpdateOrDelete(updateQuery, parameters);
        } catch (Exception e) {
            logger.error("updateEntityByQuery", e);
            throw e;
        }
    }

    private static final long serialVersionUID = -6925993802118470170L;
    final Logger logger = LogManager.getLogger(UserByOfficeRepository.class);
}
