/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.search;

import com.arrebol.apc.controller.mobile.controller.search.PersonSearchController;
import com.arrebol.apc.model.views.PersonSearchHistoricalDetailsView;
import com.arrebol.apc.model.views.PersonSearchView;
import java.io.Serializable;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
@Path("search")
public class SearchWS implements Serializable {

    private static final long serialVersionUID = 7794635470022372773L;

    @GET
    @Path("person/{fullNameToSearch}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPersonTypeByFullName(@PathParam("fullNameToSearch") String nameToSearch) {

        Response response;
        try {
            response = Response.ok(controller.fullNameEqualsToPersonSearch(nameToSearch)).build();
        } catch (Exception e) {
            logger.error("findExistingPerson", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @GET
    @Path("name/{personSearch}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllCoincidences(@PathParam("personSearch") String personSearch) {
        logger.debug("findAllCoincidences");

        Response response;
        try {
            GenericEntity<List<PersonSearchView>> entities
                    = new GenericEntity<List<PersonSearchView>>(controller.findAllCoincidences("%" + personSearch + "%")) {
            };

            response = Response.ok(entities).build();
        } catch (Exception e) {
            logger.error("findAllCoincidences", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @GET
    @Path("person")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPersonSearchDetailById(@QueryParam("id") String id) {
        logger.debug("findAllCoincidences");

        Response response;
        try {
            response = Response.ok(controller.findPersonSearchDetail(id)).build();
        } catch (Exception e) {
            logger.error("findAllCoincidences", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @GET
    @Path("historicalDetails")
    @Produces(MediaType.APPLICATION_JSON)
    public Response historicalDetails(@QueryParam("person") String personId) {
        logger.debug("historicalDetails");

        Response response;
        try {
            GenericEntity<List<PersonSearchHistoricalDetailsView>> entities
                    = new GenericEntity<List<PersonSearchHistoricalDetailsView>>(controller.findPersonHistoricalDetailsByPersonId(personId)) {
            };

            response = Response.ok(entities).build();
        } catch (Exception e) {
            logger.error("historicalDetails", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @GET
    @Path("payment-details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchPaymentDetails(@QueryParam("user") String user,
            @QueryParam("personSearch") String personSearch) {
        logger.debug("searchPaymentDetails");

        Response response;
        try {
            response = Response.ok(controller.searchPaymentDetails(user, personSearch)).build();
        } catch (Exception e) {
            logger.error("searchPaymentDetails", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    final Logger logger = LogManager.getLogger(SearchWS.class);
    private final PersonSearchController controller;

    public SearchWS() {
        this.controller = new PersonSearchController();
    }

}
