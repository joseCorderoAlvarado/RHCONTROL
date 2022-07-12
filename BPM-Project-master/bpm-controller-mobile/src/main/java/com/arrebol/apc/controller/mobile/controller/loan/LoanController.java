/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.loan;

import com.arrebol.apc.model.ws.parsed.LoanRequestedJaxb;
import com.arrebol.apc.controller.mobile.json.loan.LoanTypeJaxb;
import com.arrebol.apc.controller.mobile.json.loan.LoanTypeListJaxb;
import com.arrebol.apc.controller.mobile.repository.loan.AddAmountRepository;
import com.arrebol.apc.controller.mobile.repository.loan.LoanByRenovationRepository;
import com.arrebol.apc.controller.mobile.repository.loan.LoanDetailsRepository;
import com.arrebol.apc.controller.mobile.repository.loan.LoanFeeNotificationRepository;
import com.arrebol.apc.controller.mobile.repository.loan.LoanRepository;
import com.arrebol.apc.controller.mobile.repository.loan.LoanToDeliveryByCertifierRepository;
import com.arrebol.apc.controller.mobile.repository.loan.LoanTypeRepository;
import com.arrebol.apc.controller.mobile.repository.views.LoanApprovedDetailViewRepository;
import com.arrebol.apc.controller.mobile.repository.views.SearchPersonAvailableRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.catalog.People;
import com.arrebol.apc.model.catalog.RouteCtlg;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.User;
import com.arrebol.apc.model.core.constance.AvailableCustomersViewCfg;
import com.arrebol.apc.model.core.constance.AvailableEndorsementsViewCfg;
import com.arrebol.apc.model.core.constance.LoanCfg;
import com.arrebol.apc.model.core.constance.LoanDetailsCfg;
import com.arrebol.apc.model.core.constance.LoanFeeNotificationCfg;
import com.arrebol.apc.model.core.constance.LoanToDeliveryByCertifierViewCfg;
import com.arrebol.apc.model.core.constance.LoanTypeCfg;
import com.arrebol.apc.model.enums.ActiveStatus;
import com.arrebol.apc.model.enums.ComissionType;
import com.arrebol.apc.model.enums.LoanDetailsType;
import com.arrebol.apc.model.enums.LoanStatus;
import com.arrebol.apc.model.enums.OwnerLoan;
import com.arrebol.apc.model.enums.PeopleType;
import com.arrebol.apc.model.loan.Delivery;
import com.arrebol.apc.model.loan.Loan;
import com.arrebol.apc.model.loan.LoanByRenovation;
import com.arrebol.apc.model.loan.LoanByUser;
import com.arrebol.apc.model.loan.LoanByUserId;
import com.arrebol.apc.model.loan.LoanDetails;
import com.arrebol.apc.model.loan.LoanFeeNotification;
import com.arrebol.apc.model.loan.LoanType;
import com.arrebol.apc.model.views.AvailableCustomersView;
import com.arrebol.apc.model.views.AvailableEndorsementsView;
import com.arrebol.apc.model.views.LoanToDeliveryByCertifierView;
import com.arrebol.apc.model.ws.parsed.FeesToPayByLoanRequestJaxb;
import com.arrebol.apc.model.ws.parsed.LoanDetailJaxb;
import com.arrebol.apc.model.ws.parsed.NewAmountJaxb;
import com.arrebol.apc.model.ws.parsed.NewTransferAccountJaxb;
import com.arrebol.apc.model.ws.parsed.PersonJaxb;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class LoanController implements Serializable {

    /**
     *
     * @param idLoan
     * @return
     * @throws Exception
     */
    public List<LoanDetails> findFeesToPayByLoanId(String idLoan) throws Exception {
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(LoanDetailsCfg.FIELD_ID_LOAN, new Loan(idLoan)));
            return loanDetailsRepository.findFeesToPayByLoanId(LoanDetailsCfg.QUERY_FIND_ALL_FEES_BY_LOAN_ID, parameters);
        } catch (Exception e) {
            logger.error("findFeesToPayByLoanId", e);
            throw e;
        }
    }

    /**
     *
     * @param feesToPayByLoanRequestJaxb
     * @return
     * @throws Exception
     */
    public boolean updatePaidFeesStatusInLoanDetailIds(FeesToPayByLoanRequestJaxb feesToPayByLoanRequestJaxb) throws Exception {
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            List<String> ids = new ArrayList<>();

            feesToPayByLoanRequestJaxb.getFeeToPayList().forEach(fee -> {
                ids.add(fee.getId());
            });

            int cantidad = feesToPayByLoanRequestJaxb.getFeeToPayList().size();
            String texto;
            String precio;

            Locale canada = new Locale("en", "CA");
            NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(canada);
            BigDecimal feePayment;

            if (1 == cantidad) {
                precio = dollarFormat.format(50);
                feePayment = new BigDecimal("50");

                texto = " de la multa del " + feesToPayByLoanRequestJaxb.getFeeToPayList().get(0).getStrDateToPay();
            } else {
                feePayment = new BigDecimal(50 * cantidad);
                precio = dollarFormat.format(50 * cantidad);
                String fechas = "";

                for (int i = 0; i < feesToPayByLoanRequestJaxb.getFeeToPayList().size(); i++) {
                    fechas = fechas + feesToPayByLoanRequestJaxb.getFeeToPayList().get(i).getStrDateToPay();
                    if (feesToPayByLoanRequestJaxb.getFeeToPayList().size() > 1) {
                        fechas = fechas + ",";
                    }
                }

                if (feesToPayByLoanRequestJaxb.getFeeToPayList().size() > 1) {
                    fechas = fechas.substring(0, fechas.length() - 1);
                }

                texto = " de las multas " + fechas;
            }

            String comments = "Se pago " + precio + texto;

            LoanDetails details = new LoanDetails(
                    UUID.randomUUID().toString(),
                    new Loan(feesToPayByLoanRequestJaxb.getIdLoan()),
                    new User(feesToPayByLoanRequestJaxb.getIdUser()),
                    PeopleType.CUSTOMER,
                    0,
                    feePayment,
                    LoanDetailsType.PAYMENT,
                    comments,
                    feesToPayByLoanRequestJaxb.getIdUser(),
                    new Date());

            parameters.add(new ModelParameter(LoanDetailsCfg.FIELD_ID, ids));

            return loanDetailsRepository.updatePaidFeesStatusInLoanDetailIds(LoanDetailsCfg.QUERY_UPDATE_PAID_FEES_STATUS_IN_LOAN_DETAILS_IDS, parameters, details);
        } catch (Exception e) {
            logger.error("updatePaidFeesStatusInLoanDetailIds", e);
            throw e;
        }
    }

    /**
     *
     * @param officeId
     * @return
     * @throws Exception
     */
    public LoanTypeListJaxb findAllLoanTypeByOffice(String officeId) throws Exception {
        logger.debug("findAllLoanTypeByOffice");
        LoanTypeListJaxb loanTypeListJaxb = new LoanTypeListJaxb();
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(LoanTypeCfg.FIELD_OFFICE, new Office(officeId)));

            List<LoanType> results = loanTypeRepository.findAllLoanTypeByOffice(LoanTypeCfg.QUERY_FIND_ALL_LOAN_TYPE_BY_OFFICE, parameters);
            List<LoanTypeJaxb> loanTypeJaxbs = new ArrayList<>();

            results.forEach((result) -> {
                loanTypeJaxbs.add(new LoanTypeJaxb(result.getId(), result.getPayment()));
            });

            loanTypeListJaxb.setLoans(loanTypeJaxbs);

            return loanTypeListJaxb;
        } catch (Exception e) {
            logger.error("findAllLoanTypeByOffice", e);
            throw e;
        }
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public List<AvailableCustomersView> findAllAvailableCustomersByType(String name) throws Exception {
        logger.debug("findAllAvailableCustomersByType");
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(AvailableCustomersViewCfg.FIELD_AVAILABLE_PERSON, name));

            return searchPersonAvailableRepository.findAvailablePersonLike(
                    AvailableCustomersView.class,
                    AvailableCustomersViewCfg.QUERY_FIND_AVAILABLE_CUSTOMERS,
                    parameters);
        } catch (Exception e) {
            logger.error("findAllAvailableCustomersByType", e);
            throw e;
        }
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public List<AvailableEndorsementsView> findAllAvailableEndorsementsByType(String name) throws Exception {
        logger.debug("findAllAvailableEndorsementsByType");
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(AvailableEndorsementsViewCfg.FIELD_AVAILABLE_PERSON, name));

            return searchPersonAvailableRepository.findAvailablePersonLike(
                    AvailableEndorsementsView.class,
                    AvailableEndorsementsViewCfg.QUERY_FIND_AVAILABLE_ENDORSEMENTS,
                    parameters);
        } catch (Exception e) {
            logger.error("findAllAvailableEndorsementsByType", e);
            throw e;
        }
    }

    /**
     *
     * @param jaxb
     * @return
     * @throws Exception
     */
    synchronized public boolean createLoan(LoanRequestedJaxb jaxb) throws Exception {
        logger.debug("createLoan");

        try {

            if (!jaxb.getCustomer().isCreatePerson()
                    && !verifyPersonAvailability(
                            AvailableCustomersView.class,
                            jaxb.getCustomer().getId()
                    )) {
                throw new Exception(user_unavailable);
            }

            if (!jaxb.getEndorsement().isCreatePerson()
                    && !verifyPersonAvailability(
                            AvailableEndorsementsView.class,
                            jaxb.getEndorsement().getId()
                    )) {
                throw new Exception(user_unavailable);
            }

//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();//dateFormat.parse(jaxb.getStrDate());

            LoanByUser loanByUser = new LoanByUser(
                    new LoanByUserId(null, jaxb.getUserId()),
                    LoanStatus.PENDING,
                    OwnerLoan.CURRENT_OWNER,
                    jaxb.getUserId());

            LoanType loanType = loanTypeRepository.findLoanType(jaxb.getLoanTypeId());
            RouteCtlg routeCtlg = new RouteCtlg(jaxb.getRouteId());

            Loan loan = new Loan(
                    new LoanType(loanType.getId()),
                    null,
                    null,
                    routeCtlg,
                    LoanStatus.PENDING,
                    BigDecimal.ZERO,
                    loanType.getPaymentTotal(),
                    0,
                    jaxb.getUserId(),
                    date,
                    jaxb.getCustomer().isCreatePerson() ? ActiveStatus.ENEBLED : ActiveStatus.DISABLED
            );
            return loanRepository.createLoan(
                    loan,
                    loanByUser,
                    jaxb);
        } catch (Exception e) {
            logger.error("createLoan", e);
            throw e;
        }
    }

    /**
     *
     * @param newAmountJaxb
     * @return
     * @throws Exception
     */
    public boolean saveNewAmount(NewAmountJaxb newAmountJaxb) throws Exception {
        logger.debug("saveNewAmount");

        LoanFeeNotification notification = null;
        try {
            //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();//dateFormat.parse(newAmountJaxb.getStrDate());
            List<ModelParameter> parameters = new ArrayList<>();

            if (newAmountJaxb.isFee()) {
                parameters.add(
                        new ModelParameter(
                                LoanFeeNotificationCfg.FIELD_LOAN,
                                new Loan(newAmountJaxb.getLoanId())
                        )
                );

                Long total = loanFeeNotificationRepository.countNotificationByLoanId(
                        Long.class,
                        LoanFeeNotificationCfg.QUERY_COUNT_NOTIFICATION_BY_LOAN_ID,
                        parameters);

                notification = new LoanFeeNotification(
                        new Loan(newAmountJaxb.getLoanId()),
                        new User(newAmountJaxb.getUserId()),
                        (total.intValue() + 1),
                        newAmountJaxb.getUserId(),
                        date
                );
            }

            if (newAmountJaxb.isFee() && null == notification) {
                throw new Exception("Could not create the notification for the loan " + newAmountJaxb.getLoanId());
            }

            Loan loan = loanRepository.findLoanById(newAmountJaxb.getLoanId());
            int referenceNumber = loan.getLastReferenceNumber() + 1;

            LoanDetails loanDetails = new LoanDetails(
                    new Loan(newAmountJaxb.getLoanId()),
                    new User(newAmountJaxb.getUserId()),
                    newAmountJaxb.isCustomer() ? PeopleType.CUSTOMER : PeopleType.ENDORSEMENT,
                    newAmountJaxb.getAmount(),
                    referenceNumber,
                    newAmountJaxb.isFee() ? LoanDetailsType.FEE : LoanDetailsType.PAYMENT,
                    newAmountJaxb.getUserId(),
                    date,
                    newAmountJaxb.getComments()
            );

            BigDecimal newAmountPaid, newAmountToPay;

            if (newAmountJaxb.isFee()) {
                newAmountPaid = loan.getAmountPaid();
                newAmountToPay = loan.getAmountToPay().add(newAmountJaxb.getAmount());
            } else {
                newAmountPaid = loan.getAmountPaid().add(newAmountJaxb.getAmount());
                newAmountToPay = loan.getAmountToPay();
            }

            parameters.clear();

            String query = LoanCfg.QUERY_UPDATE_LOAN_BY_ID;

            if (!newAmountJaxb.isFee() && 0 == loan.getAmountToPay().compareTo(newAmountPaid)) {
                parameters.add(new ModelParameter(LoanCfg.FIELD_LOAN_STATUS, LoanStatus.FINISH));

                query = LoanCfg.QUERY_UPDATE_AND_FINISH_LOAN_BY_ID;
            }

            parameters.add(new ModelParameter(LoanCfg.FIELD_AMOUNT_PAID, newAmountPaid));
            parameters.add(new ModelParameter(LoanCfg.FIELD_AMOUNT_TO_PAY, newAmountToPay));
            parameters.add(new ModelParameter(LoanCfg.FIELD_LAST_REFERENCE_NUMBER, referenceNumber));
            parameters.add(new ModelParameter(LoanCfg.FIELD_LAST_UPDATED_BY, newAmountJaxb.getUserId()));
            parameters.add(new ModelParameter(LoanCfg.FIELD_LAST_UPDATED_ON, date));
            parameters.add(new ModelParameter(LoanCfg.FIELD_ID, newAmountJaxb.getLoanId()));

            return addAmountRepository.saveNewAmount(notification, loanDetails, query, parameters);
        } catch (Exception e) {
            logger.error("saveNewAmount", e);
            throw e;
        }
    }

    /**
     *
     * @param transfer
     * @return
     * @throws Exception
     */
    public boolean saveNewTransferAmount(NewTransferAccountJaxb transfer) throws Exception {
        logger.debug("saveNewTransferAmount");

        LoanFeeNotification notification = null;
        try {
            Date date = new Date();
            List<ModelParameter> parameters = new ArrayList<>();

            Loan loan = loanRepository.findLoanById(transfer.getLoanId());
            int referenceNumber = loan.getLastReferenceNumber() + 1;

            LoanDetails loanDetails = new LoanDetails(
                    new Loan(transfer.getLoanId()),
                    new User(transfer.getUserId()),
                    PeopleType.CUSTOMER,
                    transfer.getAmount(),
                    referenceNumber,
                    LoanDetailsType.TRANSFER,
                    transfer.getUserId(),
                    date,
                    transfer.getComments()
            );

            BigDecimal newAmountPaid = loan.getAmountPaid().add(transfer.getAmount());

            parameters.clear();

            String query = LoanCfg.QUERY_UPDATE_LOAN_BY_ID;

            if (0 == loan.getAmountToPay().compareTo(newAmountPaid)) {
                parameters.add(new ModelParameter(LoanCfg.FIELD_LOAN_STATUS, LoanStatus.FINISH));

                query = LoanCfg.QUERY_UPDATE_AND_FINISH_LOAN_BY_ID;
            }

            parameters.add(new ModelParameter(LoanCfg.FIELD_AMOUNT_PAID, newAmountPaid));
            parameters.add(new ModelParameter(LoanCfg.FIELD_AMOUNT_TO_PAY, loan.getAmountToPay()));
            parameters.add(new ModelParameter(LoanCfg.FIELD_LAST_REFERENCE_NUMBER, referenceNumber));
            parameters.add(new ModelParameter(LoanCfg.FIELD_LAST_UPDATED_BY, transfer.getUserId()));
            parameters.add(new ModelParameter(LoanCfg.FIELD_LAST_UPDATED_ON, date));
            parameters.add(new ModelParameter(LoanCfg.FIELD_ID, transfer.getLoanId()));

            return addAmountRepository.saveNewAmount(notification, loanDetails, query, parameters);
        } catch (Exception e) {
            logger.error("saveNewTransferAmount", e);
            throw e;
        }
    }

    /**
     * This method is for get all loan types applying some business rules, like
     * number of Fees and if you are ok in 100% you can get next loan.
     *
     * @param officeId
     * @param loanId
     * @return
     * @throws Exception
     */
    public LoanTypeListJaxb findNewCreditLimit(String officeId, String loanId) throws Exception {
        logger.debug("findNewCreditLimit");
        LoanTypeListJaxb loanTypeListJaxb = new LoanTypeListJaxb();
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(
                    new ModelParameter(
                            LoanTypeCfg.FIELD_OFFICE,
                            new Office(officeId)
                    )
            );

            parameters.add(
                    new ModelParameter(
                            LoanTypeCfg.PARAM_LOAN,
                            new Loan(loanId)
                    )
            );

            parameters.add(
                    new ModelParameter(
                            LoanTypeCfg.PARAM_LOAN_ID,
                            loanId
                    )
            );

            List<LoanType> results = loanTypeRepository.findIdAndPaymentLoans(LoanTypeCfg.QUERY_FIND_NEW_CREDIT_LINE_BY_LOAN_ID, parameters);
            List<LoanTypeJaxb> loanTypeJaxbs = new ArrayList<>();

            results.forEach((result) -> {
                loanTypeJaxbs.add(new LoanTypeJaxb(result.getId(), result.getPayment()));
            });

            loanTypeListJaxb.setLoans(loanTypeJaxbs);

            return loanTypeListJaxb;
        } catch (Exception e) {
            logger.error("findNewCreditLimit", e);
            throw e;
        }
    }

    /**
     *
     * @param officeId
     * @return
     * @throws Exception
     */
    public LoanTypeListJaxb findAllLoansTypeByOffice(String officeId) throws Exception {
        logger.debug("findNewCreditLimit");
        LoanTypeListJaxb loanTypeListJaxb = new LoanTypeListJaxb();
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(
                    new ModelParameter(
                            LoanTypeCfg.FIELD_OFFICE,
                            new Office(officeId)
                    )
            );

            List<LoanType> results = loanTypeRepository.findIdAndPaymentLoans(
                    LoanTypeCfg.QUERY_FIND_ALL_LOAN_TYPE_BY_OFFICE,
                    parameters);

            List<LoanTypeJaxb> loanTypeJaxbs = new ArrayList<>();

            results.forEach((result) -> {
                loanTypeJaxbs.add(new LoanTypeJaxb(result.getId(), result.getPayment()));
            });

            loanTypeListJaxb.setLoans(loanTypeJaxbs);

            return loanTypeListJaxb;
        } catch (Exception e) {
            logger.error("findNewCreditLimit", e);
            throw e;
        }
    }

    /**
     *
     * @param loan
     * @param credit
     * @param user
     * @param paymentAmount
     * @param endorsement
     * @return
     * @throws Exception
     */
    public boolean renovation(String loan,
            String credit,
            String user,
            BigDecimal paymentAmount,
            PersonJaxb endorsement) throws Exception {
        logger.debug("renovation");
        try {
            Date date = new Date();

            Loan currentLoan = loanRepository.findLoanById(loan);
            LoanType newCredit = loanTypeRepository.findLoanType(credit);
            int lastReferenceNumber = currentLoan.getLastReferenceNumber() + 1;

            LoanDetails loanDetails = new LoanDetails(
                    new Loan(loan),
                    new User(user),
                    PeopleType.CUSTOMER,
                    paymentAmount,
                    lastReferenceNumber,
                    LoanDetailsType.PAYMENT,
                    user,
                    date,
                    "Pago y solicitud de renovacion"
            );

            LoanByUser loanByUser = new LoanByUser(
                    new LoanByUserId(null, user),
                    LoanStatus.PENDING,
                    OwnerLoan.CURRENT_OWNER,
                    user);

            Loan renovation = new Loan(
                    new LoanType(newCredit.getId()),
                    new People(currentLoan.getCustomer().getId()),
                    null,
                    new RouteCtlg(currentLoan.getRouteCtlg().getId()),
                    LoanStatus.PENDING,
                    BigDecimal.ZERO,
                    newCredit.getPaymentTotal(),
                    0,
                    user,
                    date,
                    ActiveStatus.DISABLED
            );

            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(
                    new ModelParameter(
                            LoanCfg.FIELD_AMOUNT_PAID,
                            currentLoan.getAmountPaid().add(paymentAmount)
                    )
            );

            parameters.add(
                    new ModelParameter(
                            LoanCfg.FIELD_LAST_REFERENCE_NUMBER,
                            lastReferenceNumber
                    )
            );

            parameters.add(
                    new ModelParameter(
                            LoanCfg.FIELD_LOAN_STATUS,
                            LoanStatus.PENDING_RENOVATION
                    )
            );

            parameters.add(
                    new ModelParameter(
                            LoanCfg.FIELD_LAST_UPDATED_BY,
                            user
                    )
            );

            parameters.add(
                    new ModelParameter(
                            LoanCfg.FIELD_LAST_UPDATED_ON,
                            date
                    )
            );

            parameters.add(
                    new ModelParameter(
                            LoanCfg.FIELD_ID,
                            loan
                    )
            );

            People people = null;

            if (null != endorsement) {
                if (endorsement.isCreatePerson()) {
                    people = new People(
                            endorsement, false,
                            currentLoan.getRouteCtlg().getOffice().getId(),
                            user, currentLoan.getRouteCtlg().getId()
                    );
                } else {
                    renovation.setEndorsement(new People(endorsement.getId()));
                }
            } else {
                renovation.setEndorsement(new People(currentLoan.getEndorsement().getId()));
            }

            return loanRepository.renovationLoan(
                    loan,
                    renovation,
                    loanByUser,
                    LoanCfg.QUERY_UPDATE_LOAN_FROM_RENOVATION,
                    parameters,
                    loanDetails, people,
                    null == endorsement ? false : endorsement.isCreatePerson());
        } catch (Exception e) {
            logger.error("renovation", e);
            throw e;
        }
    }

    /**
     *
     * @param loan
     * @param credit
     * @param user
     * @param paymentAmount
     * @param currentOwner
     * @param endorsement
     * @return
     * @throws Exception
     */
    public boolean renovationHasPaymentToday(String loan,
            String credit,
            String user,
            BigDecimal paymentAmount,
            String currentOwner,
            PersonJaxb endorsement) throws Exception {
        logger.debug("renovationHasPaymentToday");
        try {
            Date date = new Date();

            Loan currentLoan = loanRepository.findLoanById(loan);
            LoanType newCredit = loanTypeRepository.findLoanType(credit);

            LoanByUser loanByUser = new LoanByUser(
                    new LoanByUserId(null, currentOwner),
                    LoanStatus.PENDING,
                    OwnerLoan.CURRENT_OWNER,
                    user);

            Loan renovation = new Loan(
                    new LoanType(newCredit.getId()),
                    new People(currentLoan.getCustomer().getId()),
                    null,
                    new RouteCtlg(currentLoan.getRouteCtlg().getId()),
                    LoanStatus.PENDING,
                    BigDecimal.ZERO,
                    newCredit.getPaymentTotal(),
                    0,
                    user,
                    date,
                    ActiveStatus.DISABLED
            );

            People people = null;

            if (null != endorsement) {
                if (endorsement.isCreatePerson()) {
                    people = new People(
                            endorsement, false,
                            currentLoan.getRouteCtlg().getOffice().getId(),
                            user, currentLoan.getRouteCtlg().getId()
                    );
                } else {
                    renovation.setEndorsement(new People(endorsement.getId()));
                }
            } else {
                renovation.setEndorsement(new People(currentLoan.getEndorsement().getId()));
            }

            return loanRepository.renovationHasPaymentToday(
                    loan,
                    renovation,
                    loanByUser,
                    people,
                    null == endorsement ? false : endorsement.isCreatePerson());
        } catch (Exception e) {
            logger.error("renovationHasPaymentToday", e);
            throw e;
        }
    }

    /**
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public List<LoanToDeliveryByCertifierView> findLoansByCertifier(String userId) throws Exception {
        logger.debug("findLoansByCertifier");
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(LoanToDeliveryByCertifierViewCfg.FIELD_USER_ID, userId));

            return toDeliveryByCertifierRepository.findLoansByCertifier(
                    LoanToDeliveryByCertifierView.class,
                    LoanToDeliveryByCertifierViewCfg.QUERY_FIND_LOANS_BY_CERTIFIER,
                    parameters);
        } catch (Exception e) {
            logger.error("findLoansByCertifier", e);
            throw e;
        }
    }

    /**
     *
     * @param id
     * @param user
     * @param comments
     * @param action true Approved otherwise Rejected.
     * @param amount
     * @param discount
     * @return
     * @throws Exception
     */
    public boolean certifierAction(String id, String user, String comments, boolean action, BigDecimal amount, BigDecimal discount) throws Exception {
        logger.debug("certifierAction");
        boolean success;
        try {
            // revisar si es nuevo (1) o renovado (2).
            // 1 = se cambia el estatus al valor de action
            // 2, si action es true, hay que hacer update de 3 tablas
            //  A)loan renovado estutus = APPROVED
            //  B) Finish
            //      1)old loan estatus = FINISH, 
            //      2)old loandbyuser estatus = FINISH
            //  C)loans by renovation estatus = APPROVED
            // 2, si action es false, hay que hacer update de 3 tablas
            //  A)loan renovado estutus = REJECT, and loandbyuser estatus = REJECT
            //  B)old loan estatus = APPROVED
            //      1)old loan estatus = APPROVED
            //      2)old loandbyuser estatus = APPROVED
            //  C)loans by renovation estatus = REJECT
            LoanByRenovation loanByRenovation = loanByRenovationRepository.findLoanRenovationByNewLoanId(id);

            if (null == loanByRenovation) {
                success = updateLoanByIdFromCertifiedView(
                        id,
                        user,
                        action,
                        comments,
                        amount,
                        ComissionType.INCLUDED);
            } else {
                LoanDetails loanDetails = null;
                BigDecimal totalAmountPaid = null;
                Integer newLastReferenceNumber = null;

                // Means discount is bigger than 0
                if (1 == discount.compareTo(new BigDecimal(0))) {
                    Loan loan = loanRepository.findLoanById(loanByRenovation.getLoanOld().getId());

                    totalAmountPaid = loan.getAmountPaid().add(discount);
                    newLastReferenceNumber = loan.getLastReferenceNumber() + 1;

                    loanDetails = new LoanDetails(
                            new Loan(loanByRenovation.getLoanOld().getId()),
                            new User(user),
                            PeopleType.CUSTOMER,
                            discount,
                            newLastReferenceNumber,
                            LoanDetailsType.RENOVATION_PAYMENT,
                            user,
                            new Date(),
                            "Retención de " + discount + " el la entrega del crédito renovado");
                }

                success = loanByRenovationRepository.updateLoanRenovationFromCerfierView(
                        loanByRenovation,
                        user,
                        comments,
                        action,
                        amount,
                        discount,
                        loanDetails,
                        totalAmountPaid,
                        newLastReferenceNumber,
                        ComissionType.INCLUDED);
            }
            return success;
        } catch (Exception e) {
            logger.error("certifierAction", e);
            throw e;
        }
    }

    /**
     *
     * @param id
     * @param user
     * @param comments
     * @param action
     * @param amount
     * @param discount
     * @param comissionType
     * @return
     * @throws Exception
     */
    public boolean certifierAction(String id, String user, String comments, boolean action, BigDecimal amount, BigDecimal discount, ComissionType comissionType) throws Exception {
        logger.debug("certifierAction");
        boolean success;
        try {
            // revisar si es nuevo (1) o renovado (2).
            // 1 = se cambia el estatus al valor de action
            // 2, si action es true, hay que hacer update de 3 tablas
            //  A)loan renovado estutus = APPROVED
            //  B) Finish
            //      1)old loan estatus = FINISH, 
            //      2)old loandbyuser estatus = FINISH
            //  C)loans by renovation estatus = APPROVED
            // 2, si action es false, hay que hacer update de 3 tablas
            //  A)loan renovado estutus = REJECT, and loandbyuser estatus = REJECT
            //  B)old loan estatus = APPROVED
            //      1)old loan estatus = APPROVED
            //      2)old loandbyuser estatus = APPROVED
            //  C)loans by renovation estatus = REJECT
            LoanByRenovation loanByRenovation = loanByRenovationRepository.findLoanRenovationByNewLoanId(id);

            if (null == loanByRenovation) {
                success = updateLoanByIdFromCertifiedView(
                        id,
                        user,
                        action,
                        comments,
                        amount,
                        comissionType);
            } else {
                LoanDetails loanDetails = null;
                BigDecimal totalAmountPaid = null;
                Integer newLastReferenceNumber = null;

                // Means discount is bigger than 0
                if (1 == discount.compareTo(new BigDecimal(0))) {
                    Loan loan = loanRepository.findLoanById(loanByRenovation.getLoanOld().getId());

                    totalAmountPaid = loan.getAmountPaid().add(discount);
                    newLastReferenceNumber = loan.getLastReferenceNumber() + 1;

                    loanDetails = new LoanDetails(
                            new Loan(loanByRenovation.getLoanOld().getId()),
                            new User(user),
                            PeopleType.CUSTOMER,
                            discount,
                            newLastReferenceNumber,
                            LoanDetailsType.RENOVATION_PAYMENT,
                            user,
                            new Date(),
                            "Retención de " + discount + " el la entrega del crédito renovado");
                }

                success = loanByRenovationRepository.updateLoanRenovationFromCerfierView(
                        loanByRenovation,
                        user,
                        comments,
                        action,
                        amount,
                        discount,
                        loanDetails,
                        totalAmountPaid,
                        newLastReferenceNumber,
                        comissionType);
            }
            return success;
        } catch (Exception e) {
            logger.error("certifierAction", e);
            throw e;
        }
    }

    /**
     *
     * @param idLoan
     * @return
     * @throws Exception
     */
    public List<LoanDetailJaxb> approvedDetailsByIdLoan(String idLoan) throws Exception {
        logger.debug("approvedDetailsByIdLoan");
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(LoanDetailsCfg.FIELD_ID_LOAN, new Loan(idLoan)));

            return loanApprovedDetailViewRepository.findLoanDetailsByLoan(
                    LoanDetailsCfg.QUERY_FIND_LOAN_DETAILS_BY_LOAN,
                    parameters
            );
        } catch (Exception e) {
            logger.error("approvedDetailsByIdLoan", e);
            throw e;
        }
    }

    /**
     *
     * @param loanId
     * @param userId
     * @param action
     * @param comments
     * @param amount
     * @param comissionType
     * @return
     * @throws Exception
     */
    private boolean updateLoanByIdFromCertifiedView(String loanId, String userId, boolean action, String comments, BigDecimal amount, ComissionType comissionType) throws Exception {
        logger.debug("updateLoanByIdFromCertifiedView");
        try {
            List<ModelParameter> parameters = new ArrayList<>();
            Date date = new Date();

            parameters.add(
                    new ModelParameter(LoanCfg.FIELD_LOAN_STATUS, action ? LoanStatus.APPROVED : LoanStatus.REJECTED)
            );

            parameters.add(
                    new ModelParameter(LoanCfg.FIELD_COMMENTS, comments)
            );

            parameters.add(
                    new ModelParameter(LoanCfg.FIELD_CREATED_ON, date)
            );

            parameters.add(
                    new ModelParameter(LoanCfg.FIELD_LAST_UPDATED_BY, userId)
            );

            parameters.add(
                    new ModelParameter(LoanCfg.FIELD_LAST_UPDATED_ON, date)
            );

            parameters.add(
                    new ModelParameter(LoanCfg.FIELD_ID, loanId)
            );

            Delivery delivery = null;

            if (action) {
                delivery = new Delivery(
                        new User(userId),
                        new Loan(loanId),
                        amount,
                        userId,
                        date,
                        comissionType);
            }

            return loanRepository.updateLoan(
                    loanId,
                    LoanCfg.QUERY_UPDATE_LOAN_WITH_CREATED_ON_BY_ID_FROM_CERTIFIER_VIEW,
                    parameters,
                    action ? LoanStatus.APPROVED : LoanStatus.REJECTED,
                    delivery);
        } catch (Exception e) {
            logger.error("updateLoanByIdFromCertifiedView", e);
            throw e;
        }
    }

    /**
     * Vefify that person is available to create a new Loan.
     *
     * @param clazz AvailableCustomersView or AvailableEndorsementsView.
     * @param idPerson Identification NUmber
     * @return true if is available otherwise false.
     * @throws Exception
     */
    private boolean verifyPersonAvailability(Class clazz, String idPerson) throws Exception {
        logger.debug("verifyPersonAvailability");
        try {
            if (null == idPerson || "".equals(idPerson.trim())) {
                throw new NullPointerException(clazz + " id is null");
            }

            return null != searchPersonAvailableRepository.findAvailablePersonByPersonId(clazz, idPerson);
        } catch (Exception e) {
            logger.error("verifyPersonAvailability", e);
            throw e;
        }
    }

    private static final long serialVersionUID = -3608679734068691688L;
    final Logger logger = LogManager.getLogger(LoanController.class
    );

    private final LoanTypeRepository loanTypeRepository;
    private final LoanRepository loanRepository;
    private final LoanFeeNotificationRepository loanFeeNotificationRepository;
    private final AddAmountRepository addAmountRepository;
    private final LoanToDeliveryByCertifierRepository toDeliveryByCertifierRepository;
    private final LoanByRenovationRepository loanByRenovationRepository;
    private final SearchPersonAvailableRepository searchPersonAvailableRepository;
    private final LoanApprovedDetailViewRepository loanApprovedDetailViewRepository;
    private final LoanDetailsRepository loanDetailsRepository;

    private final String user_unavailable = "User unavailable to this operation";

    public LoanController() {
        this.loanTypeRepository = new LoanTypeRepository();
        this.loanRepository = new LoanRepository();
        this.loanFeeNotificationRepository = new LoanFeeNotificationRepository();
        this.addAmountRepository = new AddAmountRepository();
        this.toDeliveryByCertifierRepository = new LoanToDeliveryByCertifierRepository();
        this.loanByRenovationRepository = new LoanByRenovationRepository();
        this.searchPersonAvailableRepository = new SearchPersonAvailableRepository();
        this.loanApprovedDetailViewRepository = new LoanApprovedDetailViewRepository();
        this.loanDetailsRepository = new LoanDetailsRepository();
    }
}
