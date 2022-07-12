/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.views;

import com.arrebol.apc.controller.mobile.repository.GenericRepository;
import com.arrebol.apc.model.ModelParameter;
import java.io.Serializable;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Views: AvailableCustomersView or AvailableEndorsementsView
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class SearchPersonAvailableRepository extends GenericRepository implements Serializable {

    /**
     * 
     * @param clazz
     * @param id
     * @return
     * @throws Exception 
     */
    public Object findAvailablePersonByPersonId(Class clazz, String id) throws Exception {
        logger.debug("findAvailablePersonByPersonId");
        try {
            return findAPCEntity(clazz, id);
        } catch (Exception e) {
            logger.error("findAvailablePersonByPersonId", e);
            throw e;
        }
    }

    /**
     * Find customers or endorsemenent using hibernate like clause.
     *
     * @param clazz AvailableCustomersView and AvailableEndorsementsView
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public List findAvailablePersonLike(Class clazz, String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("findAvailablePersonLike");
        try {
            return createNamedQueryResultList(clazz, xmlQuery, parameters,10);
        } catch (Exception e) {
            logger.error("findAvailablePersonLike", e);
            throw e;
        }
    }

    private static final long serialVersionUID = 1734430028791204289L;
    final Logger logger = LogManager.getLogger(SearchPersonAvailableRepository.class);

}
