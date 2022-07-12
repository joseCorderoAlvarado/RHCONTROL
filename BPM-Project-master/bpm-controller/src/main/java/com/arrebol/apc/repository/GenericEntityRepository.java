/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.repository;

import com.arrebol.apc.model.ModelParameter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class GenericEntityRepository extends AbstractRepository implements Serializable {

    private static final long serialVersionUID = -886033066217353341L;

    /**
     * Insert APC entity.
     *
     * @param apcEntity
     * @return
     */
    public boolean insertAPCEntity(Object apcEntity) {
        return super.save(apcEntity);
    }

    /**
     * Insert APC entities list.
     *
     * @param apcEntities
     * @return
     */
    public boolean insertManyAPCEntity(List apcEntities) {
        return super.saveMany(apcEntities);
    }

    /**
     * Delete APC entity.
     *
     * @param apcEntity
     * @return
     */
    public boolean deleteAPCEntityById(Object apcEntity) {
        return super.delete(apcEntity);
    }

    /**
     *
     * @param xmlUpdateOrDeleteQuery XML query mapped in Model hbm file.
     * @param parameters Paramerter cannot be empy, it has at least one paramert
     * in list.
     * @return Is parameters is empty return false, otherwise execute and return
     * true if was success the execution of query.
     */
    public boolean xmlUpdateOrDeleteAPCEntity(String xmlUpdateOrDeleteQuery, List<ModelParameter> parameters) {
        return super.xmlUpdateOrDelete(xmlUpdateOrDeleteQuery, parameters);
    }

    /**
     * Find an Object by its primary key.
     *
     * @param clazz
     * @param id
     * @return
     */
    public Object selectAPCEntityById(Class clazz, String id) {
        return (Object) findAPCEntity(clazz, id);
    }

    /**
     * Update APC entity.
     *
     * @param apcEntity
     * @return
     */
    public boolean updateAPCEntity(Object apcEntity) {
        return super.update(apcEntity);
    }

    /**
     *
     * @param date
     * @param query Must has parameter named ":date" and return just one field
     * @return
     */
    public boolean existRecordsUsingSQLQueryFindByCreatedOnField(Date date, String query) {
        boolean success = true;

        try {
            openConnection();

            List<String> records = getSession().createSQLQuery(query).setParameter("date", date).getResultList();

            if (records.isEmpty()) {
                success = false;
            }

            closeConnection();
        } catch (Exception e) {
            logger.error("existNextPaidStableGeneralBoxByCreatedOn", e);
            rollback();
        }
        return success;
    }
}
