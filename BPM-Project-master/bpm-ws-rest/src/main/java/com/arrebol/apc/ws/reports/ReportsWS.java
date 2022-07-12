/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.reports;

import com.arrebol.apc.controller.mobile.controller.reports.ReportsController;
import java.io.Serializable;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Path("reports")
public class ReportsWS implements Serializable {

    @GET
    @Path("find-user-week-report-details-by-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findUserWeekReportDetailsByUser(@QueryParam("id") String id) {
        logger.debug("findUserWeekReportDetailsByUser");

        Response response;
        try {
            ReportsController controller = new ReportsController();
            response = Response.ok(controller.findUserWeekReportDetailsByUser(id)).build();
        } catch (Exception e) {
            logger.error("findUserWeekReportDetailsByUser");
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    private static final long serialVersionUID = -5280895557294295916L;
    final Logger logger = LogManager.getLogger(getClass());
}
