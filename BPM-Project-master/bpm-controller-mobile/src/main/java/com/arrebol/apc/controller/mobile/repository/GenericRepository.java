/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository;

import com.arrebol.apc.controller.mobile.util.HibernateUtil;
import com.arrebol.apc.model.ModelParameter;
import java.util.ArrayList;
import java.util.List;
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
public abstract class GenericRepository {

    private Session sessionOld;
    private Transaction transactionOld;

    /**
     * Save an APC entity.
     *
     * @param entity APC entity in DB.
     * @return
     * @throws java.lang.Exception
     */
    protected boolean save(Object entity) throws Exception {
        logger.debug("Save");
        boolean success = false;

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            session.save(entity);

            transaction.commit();
            logger.debug("Entity saved: " + entity);

            success = true;
        } catch (HibernateException e) {
            logger.error("Save Hibernate", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method save() ", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }
        /* finally {
            if (null != session) {
                session.close();
            }
        }*/

        return success;
    }

    /**
     *
     * @param entities
     * @return
     * @throws java.lang.Exception
     */
    protected boolean saveMany(List entities) throws Exception {
        logger.debug("saveMany");
        boolean success = false;

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            for (Object entity : entities) {
                session.save(entity);
            }

            transaction.commit();

            logger.debug("Entities saveMany: ");

            success = true;
        } catch (HibernateException e) {
            logger.error("saveMany Hibernate", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method saveMany() ", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }
        /* finally {
            if (null != session) {
                session.close();
            }
        }*/

        return success;
    }

    /**
     * Update an APC entity.
     *
     * @param entity APC entity in DB.
     * @return
     * @throws java.lang.Exception
     */
    protected boolean update(Object entity) throws Exception {
        logger.debug("update");
        boolean success = false;

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            session.update(entity);

            transaction.commit();

            logger.debug("Entity updated: " + entity);

            success = true;
        } catch (HibernateException e) {
            logger.error("update Hibernate", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("update save() ", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }
        /* finally {
            if (null != session) {
                session.close();
            }
        }*/

        return success;
    }

    /**
     *
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    protected boolean updateCreateNamedQuery(String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("updateCreateNamedQuery");
        boolean success = false;

        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            if (null != parameters && !parameters.isEmpty()) {
                Query query = session.createNamedQuery(xmlQuery);

                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });

                query.executeUpdate();
                logger.debug("Query update executed");
            }

            transaction.commit();

            logger.debug("Entity updateCreateNamedQuery");

            success = true;
        } catch (HibernateException e) {
            logger.error("updateCreateNamedQuery Hibernate", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method updateCreateNamedQuery() ", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }
        return success;
    }

    /**
     * Delete an APC entity.
     *
     * @param entity APC entity in DB.
     * @return
     * @throws java.lang.Exception
     */
    protected boolean delete(Object entity) throws Exception {
        logger.debug("delete");
        boolean success = false;

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            session.delete(entity);

            transaction.commit();

            logger.debug("Entity deleted: " + entity);

            success = true;
        } catch (HibernateException e) {
            logger.error("delete Hibernate", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method delete() ", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }
        /* finally {
            if (null != session) {
                session.close();
            }
        }*/

        return success;
    }

    /**
     * Find a APC entity.
     *
     * @param clazz APC entity class name to find in DB.
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    protected Object findAPCEntity(Class clazz, String id) throws Exception {
        logger.debug("findAPCEntity");

        Object apcEntity = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            apcEntity = session.get(clazz, id);

            transaction.commit();

            logger.debug("APC entity found: " + apcEntity);
        } catch (HibernateException e) {
            logger.error("findAPCEntity Hibernate", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method findAPCEntity() ", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }

        return apcEntity;
    }

    /**
     * Execute a query from XML mapping file.
     *
     * @param clazz
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws java.lang.Exception
     */
    protected Object createNamedQueryUniqueResult(Class clazz, String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("createNamedQueryUniqueResult");

        Object entity = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            if (null != parameters && !parameters.isEmpty()) {
                Query query = session.createNamedQuery(xmlQuery, clazz);

                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });

                entity = query.uniqueResult();
            }

            transaction.commit();
            logger.debug("APC entity from xml query: " + entity);
        } catch (HibernateException e) {
            logger.error("createNamedQueryUniqueResult Hibernate", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method createNamedQueryUniqueResult() ", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }
        /* finally {
            if (null != session) {
                session.close();
            }
        }*/

        return entity;
    }

    /**
     *
     * @param clazz
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws java.lang.Exception
     */
    protected List createNamedQueryResultList(Class clazz, String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("createNamedQueryResultList");

        List<Object> entities = new ArrayList<>();
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            if (null != parameters && !parameters.isEmpty()) {
                Query query = session.createNamedQuery(xmlQuery, clazz);

                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });

                entities = query.getResultList();
            }

            transaction.commit();
            logger.debug("Total of APC entities from xml query: " + entities.size());
        } catch (HibernateException e) {
            logger.error("createNamedQueryResultList Hibernate", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method createNamedQueryResultList() ", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }

        return entities;
    }

    /**
     *
     * @param clazz
     * @param xmlQuery
     * @param parameters
     * @param max
     * @return
     * @throws java.lang.Exception
     */
    protected List createNamedQueryResultList(Class clazz, String xmlQuery, List<ModelParameter> parameters, int max) throws Exception {
        logger.debug("createNamedQueryResultList");

        List<Object> entities = new ArrayList<>();
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            if (null != parameters && !parameters.isEmpty()) {
                Query query = session.createNamedQuery(xmlQuery, clazz);

                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });

                query.setMaxResults(max);

                entities = query.getResultList();
            }

            transaction.commit();
            logger.debug("Total of APC entities from xml query: " + entities.size());
        } catch (HibernateException e) {
            logger.error("createNamedQueryResultList Hibernate", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method createNamedQueryResultList() ", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }

        return entities;
    }

    /**
     *
     * @param xmlQuery
     * @param parameters
     * @return
     */
    protected List<Tuple> xmlQueryTuple(String xmlQuery, List<ModelParameter> parameters) {
        logger.debug("xmlQueryTuple");

        List<Tuple> entityList = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query query = session.createNamedQuery(xmlQuery, Tuple.class);

            if (null != parameters && !parameters.isEmpty()) {
                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });

                entityList = query.getResultList();
            }

            transaction.commit();
        } catch (HibernateException e) {
            logger.error("xmlQueryTuple Hibernate", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method xmlQueryTuple() ", e);

            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }

        return entityList;
    }

    protected Session getSession() {
        return sessionOld;
    }

    protected void openConnection() {
        try {
            sessionOld = HibernateUtil.getSessionFactory().getCurrentSession();
            transactionOld = getSession().beginTransaction();
        } catch (Exception e) {
            logger.error("openConnection", e);
            throw e;
        }
    }

    protected void closeConnection() {
        try {
            transactionOld.commit();
        } catch (Exception e) {
            logger.error("closeConnection", e);
            rollback();
        }
    }

    protected void rollback() {
        if (null != transactionOld) {
            transactionOld.rollback();
        }
    }

    protected void flushAndClear() {
        if (null != sessionOld) {
            getSession().flush();
            getSession().clear();
        }
    }

    final Logger logger = LogManager.getLogger(GenericRepository.class);
}
