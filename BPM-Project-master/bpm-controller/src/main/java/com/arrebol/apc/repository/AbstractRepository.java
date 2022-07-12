/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.repository;

import com.arrebol.apc.controller.util.HibernateUtil;
import com.arrebol.apc.model.ModelParameter;
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
public abstract class AbstractRepository {

    private Session sessionOld;
    private Transaction transactionOld;

    /**
     * Save an APC entity.
     *
     * @param entity APC entity in DB.
     * @return
     */
    protected boolean save(Object entity) {
        logger.debug("Save");
        boolean success = false;

        //Session session = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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
        }/* finally {
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
     */
    protected boolean saveMany(List entities) {
        logger.debug("saveMany");
        boolean success = false;

        //Session session = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            entities.forEach(entity -> {
                session.save(entity);
            });
            transaction.commit();

            logger.debug("Entities saved: ");

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
        }/* finally {
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
     */
    protected boolean update(Object entity) {
        logger.debug("update");
        boolean success = false;

        //Session session = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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
            logger.error("Method update() ", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }/* finally {
            if (null != session) {
                session.close();
            }
        }*/

        return success;
    }

    /**
     * Delete an APC entity.
     *
     * @param entity APC entity in DB.
     * @return
     */
    protected boolean delete(Object entity) {
        logger.debug("delete");
        boolean success = false;

        //Session session = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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
        }/* finally {
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
     */
    protected Object findAPCEntity(Class clazz, String id) {
        logger.debug("findAPCEntity");
        Object apcEntity = null;

        //Session session = null;
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
        }/* finally {
            if (null != session) {
                session.close();
            }
        }*/
        return apcEntity;
    }

    /**
     *
     * @param clazz
     * @param xmlQuery
     * @param parameters
     * @return
     */
    protected List xmlQueryLoadEntities(Class clazz, String xmlQuery, List<ModelParameter> parameters) {
        logger.debug("xmlQueryLoadEntities");

        List entities = null;
        //Session session = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query query = session.createNamedQuery(xmlQuery, clazz);

            if (null != parameters) {
                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });
            }

            entities = query.getResultList();

            transaction.commit();

            logger.debug("APC entities loaded from xml query: " + entities.size());
        } catch (HibernateException e) {
            logger.error("xmlQueryLoadEntities Hibernate", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method xmlQueryLoadEntities() ", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }/* finally {
            if (null != session) {
                session.close();
            }
        }*/
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
        //Session session = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query query = session.createNamedQuery(xmlQuery, Tuple.class);

            if (null != parameters) {
                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });
            }

            entityList = query.getResultList();
            transaction.commit();

            logger.debug("APC entity from xml query list size: " + entityList.size());
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
        }/* finally {
            if (null != session) {
                session.close();
            }
        }*/
        return entityList;
    }

    /**
     *
     * @param strQuery
     * @param parameters
     * @return
     */
    protected List<Tuple> queryTuple(String strQuery, List<ModelParameter> parameters) {
        logger.debug("queryTuple");

        List<Tuple> entityList = null;
        //Session session = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery(strQuery, Tuple.class);

            if (null != parameters) {
                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });
            }

            entityList = query.getResultList();
            transaction.commit();

            logger.debug("APC entity from string query list size: " + entityList.size());
        } catch (HibernateException e) {
            logger.error("queryTuple Hibernate", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method queryTuple() ", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }/* finally {
            if (null != session) {
                session.close();
            }
        }*/
        return entityList;
    }

    /**
     *
     * @param clazz
     * @param xmlQuery
     * @param parameters
     * @return
     */
    public List xmlQueryAPCEntities(Class clazz, String xmlQuery, List<ModelParameter> parameters) {
        logger.debug("xmlQueryAPCEntities");

        List<Tuple> entityList = null;
        //Session session = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query query = session.createNamedQuery(xmlQuery, clazz);

            if (null != parameters) {
                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });
            }

            entityList = query.getResultList();
            transaction.commit();

            logger.debug("APC entities from xml query list size: " + entityList.size());
        } catch (HibernateException e) {
            logger.error("xmlQueryAPCEntities Hibernate", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method xmlQueryAPCEntities() ", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }/* finally {
            if (null != session) {
                session.close();
            }
        }*/
        return entityList;
    }

    /**
     *
     * @param clazz
     * @param strQuery
     * @param parameters
     * @return
     */
    public List queryAPCEntities(Class clazz, String strQuery, List<ModelParameter> parameters) {
        logger.debug("queryAPCEntities");

        List<Tuple> entityList = null;
        //Session session = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery(strQuery, clazz);

            if (null != parameters) {
                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });
            }

            entityList = query.getResultList();
            transaction.commit();

            logger.debug("APC entities from string query list size: " + entityList.size());
        } catch (HibernateException e) {
            logger.error("queryAPCEntities Hibernate", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method queryAPCEntities() ", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }/* finally {
            if (null != session) {
                session.close();
            }
        }*/
        return entityList;
    }

    /**
     *
     * @param clazz
     * @param xmlQuery
     * @param parameters
     * @return
     */
    public Object xmlQueryAPCEntityUniqueResult(Class clazz, String xmlQuery, List<ModelParameter> parameters) {
        logger.debug("xmlQueryAPCEntityUniqueResult");

        Object entity = null;
        //Session session = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query query = session.createNamedQuery(xmlQuery, clazz);

            if (null != parameters) {
                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });
            }

            entity = query.uniqueResult();
            transaction.commit();

            logger.debug("APC entity from xml query: " + entity);
        } catch (HibernateException e) {
            logger.error("xmlQueryAPCEntityUniqueResult Hibernate", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method xmlQueryAPCEntityUniqueResult() ", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }/* finally {
            if (null != session) {
                session.close();
            }
        }*/
        return entity;
    }

    /**
     *
     * @param xmlQuery
     * @param parameters
     * @return
     */
    public Object xmlQueryAPCEntityUniqueResult(String xmlQuery, List<ModelParameter> parameters) {
        logger.debug("xmlQueryAPCEntityUniqueResult");

        Object entity = null;
        //Session session = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query query = session.createNamedQuery(xmlQuery);

            if (null != parameters) {
                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });
            }

            entity = query.uniqueResult();
            transaction.commit();

            logger.debug("APC entity from xml query: " + entity);
        } catch (HibernateException e) {
            logger.error("xmlQueryAPCEntityUniqueResult Hibernate", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method xmlQueryAPCEntityUniqueResult() ", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }/* finally {
            if (null != session) {
                session.close();
            }
        }*/
        return entity;
    }

    /**
     *
     * @param clazz
     * @param strQuery
     * @param parameters
     * @return
     */
    public Object queryAPCEntityUniqueResult(Class clazz, String strQuery, List<ModelParameter> parameters) {
        logger.debug("queryAPCEntity");

        Object entity = null;
        //Session session = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery(strQuery, clazz);

            if (null != parameters) {
                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });
            }

            entity = query.uniqueResult();
            transaction.commit();

            logger.debug("APC entity from string query " + entity);
        } catch (HibernateException e) {
            logger.error("queryAPCEntityUniqueResult Hibernate", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method queryAPCEntityUniqueResult() ", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }/* finally {
            if (null != session) {
                session.close();
            }
        }*/
        return entity;
    }

    /**
     *
     * @param xmlUpdateOrDeleteQuery XML query mapped in Model hbm file.
     * @param parameters Paramerter cannot be empy, it has at least one paramert
     * in list.
     * @return Is parameters is empty return false, otherwise execute and return
     * true if was success the execution of query.
     */
    protected boolean xmlUpdateOrDelete(String xmlUpdateOrDeleteQuery, List<ModelParameter> parameters) {
        boolean success = false;
        //Session session = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Query updateOrDeleteQuery = session.createNamedQuery(xmlUpdateOrDeleteQuery);

            if (null != parameters && !parameters.isEmpty()) {
                parameters.forEach((parameter) -> {
                    updateOrDeleteQuery.setParameter(parameter.getParameter(), parameter.getValue());
                });

                int total = updateOrDeleteQuery.executeUpdate();

                if (total > 0) {
                    transaction.commit();
                    success = true;
                } else {
                    transaction.rollback();
                }
            } else {
                transaction.rollback();
            }
        } catch (HibernateException e) {
            logger.error("xmlUpdateOrDelete Hibernate", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method xmlUpdateOrDelete() ", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }/* finally {
            if (null != session) {
                session.close();
            }
        }*/
        return success;
    }

    protected void openConnection() {
        try {
            sessionOld = HibernateUtil.getSessionFactory().getCurrentSession();
            transactionOld = sessionOld.beginTransaction();
        } catch (Exception e) {
            logger.error("openConnection", e);
            throw e;
        }
    }

    protected Session getSession() {
        return sessionOld;
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
            sessionOld.flush();
            sessionOld.clear();
        }
    }

    final Logger logger = LogManager.getLogger(AbstractRepository.class);
}
