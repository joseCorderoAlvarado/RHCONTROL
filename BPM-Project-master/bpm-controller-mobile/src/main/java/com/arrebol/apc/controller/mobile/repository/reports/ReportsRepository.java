/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.reports;

import com.arrebol.apc.controller.mobile.repository.GenericRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.reports.UserWeekReport;
import java.io.Serializable;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class ReportsRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param clazz
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public UserWeekReport findUserWeekReportDetailsByUser(Class clazz, String xmlQuery, List<ModelParameter> parameters) throws Exception {
        try {
            return (UserWeekReport) createNamedQueryUniqueResult(
                    clazz,
                    xmlQuery,
                    parameters
            );
        } catch (Exception e) {
            logger.error("findUserWeekReportDetailsByUser", e);
            throw e;
        }
    }

    private static final long serialVersionUID = 5669198954766725576L;
    final Logger logger = LogManager.getLogger(getClass());
}
