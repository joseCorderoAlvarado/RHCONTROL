/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.cash;

import com.arrebol.apc.controller.mobile.controller.cash.CashRegisterCurdateByUserController;
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
@Path("cashRegister")
public class CashRegisterWS implements Serializable {

    @GET
    @Path("findAllBy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllCashRegisterCurdateByUserId(@QueryParam("id") String userId) {
        logger.debug("findAllCashRegisterCurdateByUserId");

        Response response;
        try {
            if (null == userId) {
                throw new NullPointerException("Id is null");
            }

            response = Response.ok(
                    cashRegisterCurdateByUserController.findAllCashRegisterCurdateByUserId(userId)
            ).build();
        } catch (Exception e) {
            logger.error("findAllCashRegisterCurdateByUserId", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

        return response;
    }
    
    @GET
    @Path("findDailyTotals")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findDailyTotalsByUserId(@QueryParam("id") String userId) {
        logger.debug("findDailyTotalsByUserId");

        Response response;
        try {
            if (null == userId) {
                throw new NullPointerException("Id is null");
            }

            response = Response.ok(
                    cashRegisterCurdateByUserController.findDailyTotalsByUserId(userId)
            ).build();
        } catch (Exception e) {
            logger.error("findDailyTotalsByUserId", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

        return response;
    }

    private static final long serialVersionUID = 5664587426544509670L;
    final Logger logger = LogManager.getLogger(CashRegisterWS.class);
    private final CashRegisterCurdateByUserController cashRegisterCurdateByUserController;

    public CashRegisterWS() {
        this.cashRegisterCurdateByUserController = new CashRegisterCurdateByUserController();
    }

}
