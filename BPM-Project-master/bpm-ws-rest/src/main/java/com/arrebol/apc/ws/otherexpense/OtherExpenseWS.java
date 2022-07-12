/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.otherexpense;

import com.arrebol.apc.controller.mobile.controller.otherexpense.OtherExpenseController;
import com.arrebol.apc.controller.mobile.controller.preference.SystemPreferenceController;
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
@Path("otherExpense")
public class OtherExpenseWS implements Serializable {

    @PUT
    @Path("new")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newOtherExpense(
            @FormParam("office") String office,
            @FormParam("user") String user,
            @FormParam("expense") Double expense,
            @FormParam("description") String description) {
        Response response;
        try {

            if (systemPreferenceController.findConfigurationButton(user, office).isActiveButton()) {
                if (otherExpenseController.addOtherExpense(office, user, expense, description)) {
                    response = Response.ok().build();
                } else {
                    response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
                }
            } else {
                response = Response.status(Response.Status.CONFLICT).build();
            }

        } catch (Exception e) {
            logger.error("newOtherExpense", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

        return response;
    }

    private static final long serialVersionUID = 3210072051203175268L;

    final Logger logger = LogManager.getLogger(OtherExpenseWS.class);

    private final OtherExpenseController otherExpenseController;
    private final SystemPreferenceController systemPreferenceController;

    public OtherExpenseWS() {
        this.otherExpenseController = new OtherExpenseController();
        this.systemPreferenceController = new SystemPreferenceController();
    }

}
