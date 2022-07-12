/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.gasoline;

import com.arrebol.apc.controller.mobile.controller.gasoline.GasolineController;
import com.arrebol.apc.model.catalog.RouteCtlg;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.User;
import com.arrebol.apc.model.enums.GenericStatus;
import com.arrebol.apc.model.gasoline.Gasoline;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
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
@Path("gasoline")
public class GasolineWS implements Serializable {

    @PUT
    @Path("save-new-gasoline-payment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveNewGasolinePayment(@FormParam("idUser") String idUser,
            @FormParam("idOffice") String idOffice,
            @FormParam("idRoute") String idRoute,
            @FormParam("total") Double total,
            @FormParam("km") Double km,
            @FormParam("quantity") Double quantity,
            @FormParam("description") String description) {
        Response response;
        try {
            GasolineController controller = new GasolineController();

            Gasoline gasoline = new Gasoline(
                    UUID.randomUUID().toString(),
                    new User(idUser),
                    new Office(idOffice),
                    new RouteCtlg(idRoute),
                    quantity,
                    km,
                    total,
                    GenericStatus.ENABLED,
                    description,
                    idUser,
                    new Date());

            if (controller.saveNewGasolinePayment(gasoline)) {
                response = Response.ok().build();
            } else {
                response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            logger.error("saveNewGasolineEntry");
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    private static final long serialVersionUID = -5280895557294295916L;
    final Logger logger = LogManager.getLogger(getClass());
}
