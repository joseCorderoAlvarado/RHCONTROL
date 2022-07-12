/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.route;

import com.arrebol.apc.controller.mobile.controller.route.RouteController;
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
@Path("route")
public class RouteWS implements Serializable {

    @GET
    @Path("find-all-routes-availables-by-office-id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllRoutesAvailablesByOfficeId(@QueryParam("id") String id) {
        logger.debug("findAllRoutesAvailablesByOfficeId");
        Response response;
        try {
            RouteController controller = new RouteController();
            response = Response.ok(controller.findAllRoutesAvailables(id)).build();
        } catch (Exception e) {
            logger.error("findAllRoutesAvailablesByOfficeId", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }
    final Logger logger = LogManager.getLogger(getClass());
}
