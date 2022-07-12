/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.loan;

import com.arrebol.apc.controller.mobile.controller.loan.LoanController;
import com.arrebol.apc.controller.mobile.controller.preference.SystemPreferenceController;
import com.arrebol.apc.model.enums.ComissionType;
import com.arrebol.apc.model.loan.LoanDetails;
import com.arrebol.apc.model.ws.parsed.LoanRequestedJaxb;
import com.arrebol.apc.model.views.AvailableCustomersView;
import com.arrebol.apc.model.views.AvailableEndorsementsView;
import com.arrebol.apc.model.views.LoanToDeliveryByCertifierView;
import com.arrebol.apc.model.ws.parsed.FeesToPayByLoanRequestJaxb;
import com.arrebol.apc.model.ws.parsed.LoanDetailJaxb;
import com.arrebol.apc.model.ws.parsed.NewAmountJaxb;
import com.arrebol.apc.model.ws.parsed.NewTransferAccountJaxb;
import com.arrebol.apc.model.ws.parsed.RenovationWithEndorsementJaxb;
import com.arrebol.apc.model.ws.parsed.ThumbnailJaxb;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
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
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Path("loan")
public class LoanWS implements Serializable {

    private static final long serialVersionUID = -5416113076264972459L;

    @GET
    @Path("findAllLoans")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllLoanTypeByOffice(@QueryParam("id") String id) {
        logger.debug("updateOrderInList");
        Response response;
        try {
            response = Response.ok(loanController.findAllLoanTypeByOffice(id)).build();
        } catch (Exception e) {
            logger.error("updateOrderInList", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @GET
    @Path("findAllAvailableCustomersByType")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllAvailableCustomersByType(@QueryParam("name") String name) {
        logger.debug("findAllAvailableCustomersByType");
        Response response;
        try {
            GenericEntity<List<AvailableCustomersView>> results
                    = new GenericEntity<List<AvailableCustomersView>>(
                            loanController.findAllAvailableCustomersByType("%" + name + "%")) {
            };

            response = Response.ok(results).build();
        } catch (Exception e) {
            logger.error("findAllAvailableCustomersByType", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @GET
    @Path("find-fees-to-pay-by-loan-id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findFeesToPayByLoanId(@QueryParam("idLoan") String idLoan) {
        logger.debug("findFeesToPayByLoanId");
        Response response;
        try {
            GenericEntity<List<LoanDetails>> results
                    = new GenericEntity<List<LoanDetails>>(
                            loanController.findFeesToPayByLoanId(idLoan)) {
            };

            response = Response.ok(results).build();
        } catch (Exception e) {
            logger.error("findFeesToPayByLoanId", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @PUT
    @Path("update-paid-fees-status-in-loan-detail-ids")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePaidFeesStatusInLoanDetailIds(FeesToPayByLoanRequestJaxb feesToPayByLoanRequestJaxb) {
        logger.debug("updatePaidFeesStatusInLoanDetailIds");

        Response response;
        try {
            if (loanController.updatePaidFeesStatusInLoanDetailIds(feesToPayByLoanRequestJaxb)) {
                response = Response.ok().build();
            } else {
                response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            if (e.getMessage().contains("User unavailable to this operation")) {
                response = Response.status(Response.Status.CONFLICT).build();
            } else {
                response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
            logger.error("updatePaidFeesStatusInLoanDetailIds", e);

        }
        return response;
    }

    @GET
    @Path("findAllAvailableEndorsementsByType")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllAvailableEndorsementsByType(@QueryParam("name") String name) {
        logger.debug("findAllAvailableEndorsementsByType");
        Response response;
        try {
            GenericEntity<List<AvailableEndorsementsView>> results
                    = new GenericEntity<List<AvailableEndorsementsView>>(
                            loanController.findAllAvailableEndorsementsByType("%" + name + "%")) {
            };

            response = Response.ok(results).build();
        } catch (Exception e) {
            logger.error("findAllAvailableEndorsementsByType", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @PUT
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLoan(LoanRequestedJaxb loan) {
        logger.debug("createLoan");

        Response response;
        try {
            if (loanController.createLoan(loan)) {
                response = Response.ok().build();
            } else {
                response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            if (e.getMessage().contains("User unavailable to this operation")) {
                response = Response.status(Response.Status.CONFLICT).build();
            } else {
                response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
            logger.error("createLoan", e);

        }
        return response;
    }

    @PUT
    @Path("create-with-image")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLoanWithImage(
            // LoanRequestedJaxb loan,
            @FormDataParam("thumbnail") ThumbnailJaxb thumbnail,
            @FormDataParam("thumbnail") FormDataContentDisposition thumbnailMetaData) {
        logger.debug("createLoan");

        Response response;
        try {
            if (true/*loanController.createLoan(loan)*/) {
                response = Response.ok().build();
            } else {
                response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            if (e.getMessage().contains("User unavailable to this operation")) {
                response = Response.status(Response.Status.CONFLICT).build();
            } else {
                response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
            logger.error("createLoan", e);

        }
        return response;
    }

    @PUT
    @Path("create-with-image2")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLoanWithImage2(
            // LoanRequestedJaxb loan,
            @FormDataParam("file") InputStream file,
            @FormDataParam("file") FormDataContentDisposition fileDisposition) {
        logger.debug("createLoan");

        Response response;
        try {
            if (true/*loanController.createLoan(loan)*/) {
                response = Response.ok().build();
            } else {
                response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            if (e.getMessage().contains("User unavailable to this operation")) {
                response = Response.status(Response.Status.CONFLICT).build();
            } else {
                response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
            logger.error("createLoan", e);

        }
        return response;
    }

    @POST
    @Path("saveNewAmount")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveNewAmount(NewAmountJaxb newAmountJaxb) {
        logger.debug("saveNewAmount");
        Response response;
        try {
            if (systemPreferenceController.findConfigurationButton(newAmountJaxb.getUserId(), newAmountJaxb.getOfficeId()).isActiveButton()) {

                if (loanController.saveNewAmount(newAmountJaxb)) {
                    response = Response.ok().build();
                } else {
                    response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
                }
            } else {
                response = Response.status(Response.Status.CONFLICT).build();
            }

        } catch (Exception e) {
            logger.error("saveNewAmount", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @POST
    @Path("saveNewTransferAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveNewTransferAccount(NewTransferAccountJaxb transfer) {
        logger.debug("saveNewTransferAccount");
        Response response;
        try {
            if (systemPreferenceController.findConfigurationButton(transfer.getUserId(), transfer.getOfficeId()).isActiveButton()) {

                if (loanController.saveNewTransferAmount(transfer)) {
                    response = Response.ok().build();
                } else {
                    response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
                }
            } else {
                response = Response.status(Response.Status.CONFLICT).build();
            }

        } catch (Exception e) {
            logger.error("saveNewTransferAccount", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @GET
    @Path("findNewCreditLimit")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findNewCreditLimit(@QueryParam("office") String office, @QueryParam("loan") String loan) {
        logger.debug("findNewCreditLimit");
        Response response;
        try {
            /**
             * This method is for get all loan types applying some business
             * rules, like number of Fees and if you are ok in 100% you can get
             * next loan.
             */
            //response = Response.ok(loanController.findNewCreditLimit(office, loan)).build();
            response = Response.ok(loanController.findAllLoansTypeByOffice(office)).build();
        } catch (Exception e) {
            logger.error("findNewCreditLimit", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @PUT
    @Path("renovation")
    @Produces(MediaType.APPLICATION_JSON)
    public Response renovation(
            @FormParam("loan") String loan,
            @FormParam("credit") String credit,
            @FormParam("user") String user,
            @FormParam("office") String office,
            @FormParam("amount") double amount,
            @FormParam("currentOwner") String currentOwner) {
        logger.debug("renovation");

        Response response;
        try {
            if (systemPreferenceController.findConfigurationButton(user, office).isActiveButton()) {
                if (validateRenovationData(loan, credit, user)) {
                    if (loanController.renovation(loan, credit, user, new BigDecimal(amount), null)) {
                        response = Response.ok().build();
                    } else {
                        response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
                    }
                } else {
                    response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
                }
            } else {
                response = Response.status(Response.Status.CONFLICT).build();
            }
        } catch (Exception e) {
            logger.error("renovation", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @PUT
    @Path("renovation-has-payment-today")
    @Produces(MediaType.APPLICATION_JSON)
    public Response renovationHasPaymentToday(
            @FormParam("loan") String loan,
            @FormParam("credit") String credit,
            @FormParam("user") String user,
            @FormParam("office") String office,
            @FormParam("amount") double amount,
            @FormParam("currentOwner") String currentOwner) {
        logger.debug("renovationHasPaymentToday");

        Response response;
        try {
            if (systemPreferenceController.findConfigurationButton(user, office).isActiveButton()) {
                if (validateRenovationData(loan, credit, user)) {
                    if (loanController.renovationHasPaymentToday(loan, credit, user, new BigDecimal(amount), currentOwner, null)) {
                        response = Response.ok().build();
                    } else {
                        response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
                    }
                } else {
                    response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
                }
            } else {
                response = Response.status(Response.Status.CONFLICT).build();
            }
        } catch (Exception e) {
            logger.error("renovationHasPaymentToday", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @GET
    @Path("deliveryList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deliveryList(@QueryParam("user") String userId) {
        logger.debug("deliveryList");
        Response response;
        try {
            GenericEntity<List<LoanToDeliveryByCertifierView>> results
                    = new GenericEntity< List< LoanToDeliveryByCertifierView>>(
                            loanController.findLoansByCertifier(userId)
                    ) {
            };

            response = Response.ok(results).build();
        } catch (Exception e) {
            logger.error("deliveryList", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @POST
    @Path("certifierAction")
    @Produces(MediaType.APPLICATION_JSON)
    public Response certifierAction(
            @FormParam(value = "id") String id,
            @FormParam(value = "user") String user,
            @FormParam(value = "office") String office,
            @FormParam(value = "comments") String comments,
            @FormParam(value = "action") boolean action,
            @FormParam(value = "amount") double amount,
            @FormParam(value = "discount") double discount) {
        logger.debug("certifierAction");
        Response response;
        try {
            if (systemPreferenceController.findConfigurationButton(user, office).isActiveButton()) {
                if (loanController.certifierAction(id, user, comments, action, new BigDecimal(amount), new BigDecimal(discount))) {
                    response = Response.ok().build();
                } else {
                    response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
                }
            } else {
                response = Response.status(Response.Status.CONFLICT).build();
            }
        } catch (Exception e) {
            logger.error("certifierAction", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @POST
    @Path("certifier-action-with-comission")
    @Produces(MediaType.APPLICATION_JSON)
    public Response certifierActionWithComission(
            @FormParam(value = "id") String id,
            @FormParam(value = "user") String user,
            @FormParam(value = "office") String office,
            @FormParam(value = "comments") String comments,
            @FormParam(value = "action") boolean action,
            @FormParam(value = "amount") double amount,
            @FormParam(value = "discount") double discount,
            @FormParam(value = "includeComission") boolean includeComission) {
        logger.debug("certifierAction");
        Response response;
        try {
            if (systemPreferenceController.findConfigurationButton(user, office).isActiveButton()) {
                if (loanController.certifierAction(id, user, comments, action, new BigDecimal(amount), new BigDecimal(discount), includeComission ? ComissionType.INCLUDED : ComissionType.EXCLUDED)) {
                    response = Response.ok().build();
                } else {
                    response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
                }
            } else {
                response = Response.status(Response.Status.CONFLICT).build();
            }
        } catch (Exception e) {
            logger.error("certifierAction", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @GET
    @Path("approved-details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approvedDetailsByIdLoan(@QueryParam("id") String loan) {
        logger.debug("approvedDetailsByIdLoan");
        Response response;
        try {

            GenericEntity<List<LoanDetailJaxb>> results
                    = new GenericEntity< List< LoanDetailJaxb>>(
                            loanController.approvedDetailsByIdLoan(loan)
                    ) {
            };

            response = Response.ok(results).build();
        } catch (Exception e) {
            logger.error("approvedDetailsByIdLoan", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @PUT
    @Path("renovation-with-endorsement")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response renovationWithEndorsement(RenovationWithEndorsementJaxb renovation) {
        logger.debug("renovationWithEndorsement");

        Response response;
        try {
            if (systemPreferenceController.findConfigurationButton(renovation.getUser(), renovation.getOffice()).isActiveButton()) {
                if (validateRenovationData(renovation.getLoan(), renovation.getCredit(), renovation.getUser())) {
                    if (renovation.isHasPaymentToday()) {
                        if (loanController.renovationHasPaymentToday(renovation.getLoan(), renovation.getCredit(), renovation.getUser(), new BigDecimal(renovation.getAmount()), renovation.getCurrentOwner(), renovation.getEndorsement())) {
                            response = Response.ok().build();
                        } else {
                            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
                        }
                    } else {
                        if (loanController.renovation(renovation.getLoan(), renovation.getCredit(), renovation.getUser(), new BigDecimal(renovation.getAmount()), renovation.getEndorsement())) {
                            response = Response.ok().build();
                        } else {
                            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
                        }
                    }
                } else {
                    response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
                }
            } else {
                response = Response.status(Response.Status.CONFLICT).build();
            }
        } catch (Exception e) {
            logger.error("renovationWithEndorsement", e);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    /**
     *
     * @param loan
     * @param credit
     * @param user
     * @param date yyyy-MM-dd HH:mm:ss
     * @return
     */
    private boolean validateRenovationData(String loan, String credit, String user) {
        if (null == loan || loan.length() != 36) {
            return false;
        }
        if (null == credit || credit.length() != 36) {
            return false;
        }
        return !(null == user || user.length() != 36);
    }

    final Logger logger = LogManager.getLogger(LoanWS.class);
    private final LoanController loanController;
    private final SystemPreferenceController systemPreferenceController;

    public LoanWS() {
        this.loanController = new LoanController();
        this.systemPreferenceController = new SystemPreferenceController();
    }

}
