/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.preferences;

import com.arrebol.apc.controller.mobile.controller.preference.SystemPreferenceController;
import com.arrebol.apc.controller.mobile.moxy.views.LoanByUserOrderPreferenceViewListJaxb;
import com.arrebol.apc.model.views.LoanByUserOrderPreferenceView;
import com.arrebol.apc.model.ws.parsed.ConfigurationJaxb;
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
@Path("systemPreference")
public class SystemPreferenceWS implements Serializable {

    private static final long serialVersionUID = -2316998237505965201L;

    @POST
    @Path("findAllLoanByUserOrderPreference")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllLoanByUserOrderPreference(@FormParam("userId") String userId) {
        logger.debug("findAllLoanByUserOrderPreference");
        Response response;
        try {
            GenericEntity<List<LoanByUserOrderPreferenceView>> list = new GenericEntity<List<LoanByUserOrderPreferenceView>>(
                    controller.findAllLoanByUserOrderPreference(userId)
            ) {
            };

            response = Response.ok(list).build();
        } catch (Exception e) {
            logger.error("findAllLoanByUserOrderPreference", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @PUT
    @Path("updateOrderInList")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrderInList(LoanByUserOrderPreferenceViewListJaxb updateOrderListPreference) {
        logger.debug("updateOrderInList");
        Response response;
        try {

            if (controller.updateOrderInList(updateOrderListPreference)) {
                response = Response.ok().build();
            } else {
                response = Response.notModified().build();
            }
        } catch (Exception e) {
            logger.error("updateOrderInList", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @PUT
    @Path("updateUserMobilePreference")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserMobilePreference(
            @FormParam("userId") String userId,
            @FormParam("preferenceName") String preferenceName,
            @FormParam("preferenceValue") String preferenceValue) {
        logger.debug("updateUserMobilePreference");
        Response response;

        try {
            if (controller.updateUserMobilePreference(userId, preferenceName, preferenceValue)) {
                response = Response.ok().build();
            } else {
                response = Response.notModified().build();
            }
        } catch (Exception e) {
            logger.error("updateUserMobilePreference", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }
   
    @GET
    @Path("active-button")
    @Produces(MediaType.APPLICATION_JSON)
    public Response activeButton(@QueryParam("user") String user, @QueryParam("office") String office) {
        logger.debug("activeButton");
        Response response;
        try {
            response = Response.ok(controller.findConfigurationButton(user, office)).build();
        } catch (Exception e) {
            logger.error("updateOrderInList", e);
            response = Response.ok(new ConfigurationJaxb(false)).build();
        }
        return response;
    }

    final Logger logger = LogManager.getLogger(SystemPreferenceWS.class);
    private final SystemPreferenceController controller;

    public SystemPreferenceWS() {
        this.controller = new SystemPreferenceController();
    }

}
