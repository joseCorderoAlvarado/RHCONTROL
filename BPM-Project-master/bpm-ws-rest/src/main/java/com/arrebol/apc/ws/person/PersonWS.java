/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.person;

import com.arrebol.apc.controller.mobile.controller.person.PersonController;
import java.io.Serializable;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Path("person")
public class PersonWS implements Serializable {

    @PUT
    @Path("change-contact-number-by-loan-id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeContactNumber(
            @FormParam("loanId") String loanId,
            @FormParam("lastUpdatedBy") String lastUpdatedBy,
            @FormParam("contactNumber") String contactNumber,
            @FormParam("isCustomer") boolean isCustomer) {
        Response response;
        try {
            PersonController controller = new PersonController();

            if (controller.changeContactNumber(loanId, lastUpdatedBy, contactNumber, isCustomer)) {
                response = Response.ok().build();
            } else {
                response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            logger.error("changeContactNumber");
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    final Logger logger = LogManager.getLogger(getClass());
}
