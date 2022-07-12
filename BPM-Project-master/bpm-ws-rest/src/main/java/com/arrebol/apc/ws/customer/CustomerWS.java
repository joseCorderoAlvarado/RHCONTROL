/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.customer;

import com.arrebol.apc.controller.mobile.controller.customer.CustomerController;
import com.arrebol.apc.model.views.LoanByUserView;
import java.io.Serializable;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Path("customer")
public class CustomerWS implements Serializable {

    private static final long serialVersionUID = -5280895557294295716L;

    @POST
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllLoansByUserId(@FormParam("orderList") String orderList, @FormParam("userId") String userId) {
        logger.debug("findAllLoansByUserId");

        Response response;
        try {
            GenericEntity<List<LoanByUserView>> list = new GenericEntity<List<LoanByUserView>>(controller.findAllLoansByUserId(orderList, userId)) {
            };

            response = Response.ok(list).build();
        } catch (Exception e) {
            logger.error("findAllLoansByUserId", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    final Logger logger = LogManager.getLogger(CustomerWS.class);
    private final CustomerController controller;

    public CustomerWS() {
        this.controller = new CustomerController();
    }

}
