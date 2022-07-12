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
import com.arrebol.apc.model.views.ExchangeEnebledUsersView;
import java.io.Serializable;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class ExchangeEnebledUsersViewRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public List<ExchangeEnebledUsersView> findEnebledUsersToUserId(String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("exchangeEnebledUsersViewRepository");
        try {
            return createNamedQueryResultList(ExchangeEnebledUsersView.class, xmlQuery, parameters);
        } catch (Exception e) {
            logger.error("findEnebledUsersToUserId", e);
            throw e;
        }
    }

    private static final long serialVersionUID = 3811795552537797634L;
    final Logger logger = LogManager.getLogger(ExchangeEnebledUsersViewRepository.class);
}
