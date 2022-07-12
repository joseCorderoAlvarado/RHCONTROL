/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.login;

import com.arrebol.apc.controller.mobile.controller.login.LoginWSController;
import java.io.Serializable;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
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
@Path("login")
public class LoginWS implements Serializable {

    private static final long serialVersionUID = 4130449130428311393L;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("userName") String userName,
            @FormParam("token") String token) {
        logger.info("access");

        Response response;
        try {
            response = Response.status(Response.Status.OK).entity(getController().login(userName, token)).build();
        } catch (Exception e) {
            logger.error("Access denied", e);
            response = Response.status(Response.Status.FORBIDDEN).build();
        }
        return response;
    }

    final Logger logger = LogManager.getLogger(LoginWS.class);

    private final LoginWSController controller;

    public LoginWS() {
        controller = new LoginWSController();
    }

    private LoginWSController getController() {
        return controller;
    }

}
