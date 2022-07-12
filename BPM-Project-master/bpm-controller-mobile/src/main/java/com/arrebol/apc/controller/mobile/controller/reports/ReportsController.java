/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.reports;

import com.arrebol.apc.controller.mobile.repository.reports.ReportsRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.reports.UserWeekReport;
import com.arrebol.apc.model.reports.constance.UserWeekReportCfg;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class ReportsController implements Serializable {

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public UserWeekReport findUserWeekReportDetailsByUser(String id) throws Exception {
        try {
            ReportsRepository reportsRepository = new ReportsRepository();
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(
                    new ModelParameter(UserWeekReportCfg.FIELD_ID, id)
            );

            return reportsRepository.findUserWeekReportDetailsByUser(UserWeekReport.class, UserWeekReportCfg.QUERY_FIND_USER_WEEK_REPORT_BY_USER, parameters);
        } catch (Exception e) {
            logger.error("findUserWeekReportDetailsByUser", e);
            throw e;
        }
    }

    private static final long serialVersionUID = -5280895557294295916L;
    final Logger logger = LogManager.getLogger(getClass());

    public ReportsController() {
    }

}
