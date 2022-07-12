/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.exchange;

import com.arrebol.apc.controller.mobile.controller.exchange.ExchangeEnebledUsersController;
import com.arrebol.apc.controller.mobile.controller.preference.SystemPreferenceController;
import com.arrebol.apc.model.views.ExchangeEnebledUsersView;
import com.arrebol.apc.model.ws.parsed.Exchange;
import com.arrebol.apc.model.ws.parsed.ExchangeJaxb;
import java.io.Serializable;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Path("exchange")
public class ExchangeWS implements Serializable {

    @GET
    @Path("availableUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response availableUsers(@QueryParam("id") String userId, @QueryParam("office") String officeId) {
        logger.debug("availableUsers");
        Response response;
        try {
            GenericEntity<List<ExchangeEnebledUsersView>> results
                    = new GenericEntity<List<ExchangeEnebledUsersView>>(
                            exchangeEnebledUsersController.findEnebledUsersToUserId(userId, officeId)
                    ) {
            };

            response = Response.ok(results).build();
        } catch (Exception e) {
            logger.error("availableUsers", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @PUT
    @Path("newExchange")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newExchange(ExchangeJaxb exchange) {
        logger.debug("newExchange");
        Response response;
        try {
            if (systemPreferenceController.findConfigurationButton(exchange.getSenderId(), exchange.getOfficeId()).isActiveButton()) {
                if (exchangeEnebledUsersController.newExchange(exchange)) {
                    response = Response.ok().build();
                } else {
                    response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
                }
            } else {
                response = Response.status(Response.Status.CONFLICT).build();
            }

        } catch (Exception e) {
            logger.error("newExchange", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @POST
    @Path("updateExchange")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateExchange(
            @FormParam(value = "transfer") String transfer,
            @FormParam(value = "user") String user,
            @FormParam(value = "office") String office,
            @FormParam(value = "action") boolean isApproved) {
        logger.debug("updateExchange");
        Response response;
        try {
            if (systemPreferenceController.findConfigurationButton(user, office).isActiveButton()) {
                if (exchangeEnebledUsersController.updateExchange(transfer, user, isApproved)) {
                    response = Response.ok().build();
                } else {
                    response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
                }
            } else {
                response = Response.status(Response.Status.CONFLICT).build();
            }
        } catch (Exception e) {
            logger.error("updateExchange", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @GET
    @Path("allBy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response exchangesByUsers(@QueryParam("id") String userId) {
        logger.debug("availableUsers");
        Response response;
        try {
            GenericEntity<List<Exchange>> results
                    = new GenericEntity<List<Exchange>>(
                            exchangeEnebledUsersController.exchangesByUsers(userId)
                    ) {
            };

            response = Response.ok(results).build();
        } catch (Exception e) {
            logger.error("availableUsers", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    private static final long serialVersionUID = -4827528662562714897L;
    final Logger logger = LogManager.getLogger(ExchangeWS.class);

    private final ExchangeEnebledUsersController exchangeEnebledUsersController;
    private final SystemPreferenceController systemPreferenceController;

    public ExchangeWS() {
        this.exchangeEnebledUsersController = new ExchangeEnebledUsersController();
        this.systemPreferenceController = new SystemPreferenceController();
    }

}
