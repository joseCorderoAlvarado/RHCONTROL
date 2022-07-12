/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.loan;

import com.arrebol.apc.controller.mobile.repository.GenericRepository;
import com.arrebol.apc.controller.mobile.util.HibernateUtil;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.catalog.People;
import com.arrebol.apc.model.core.constance.LoanByUserCfg;
import com.arrebol.apc.model.core.constance.LoanCfg;
import com.arrebol.apc.model.enums.LoanRenovationStatus;
import com.arrebol.apc.model.enums.LoanStatus;
import com.arrebol.apc.model.loan.Delivery;
import com.arrebol.apc.model.loan.Loan;
import com.arrebol.apc.model.loan.LoanByRenovation;
import com.arrebol.apc.model.loan.LoanByRenovationId;
import com.arrebol.apc.model.loan.LoanByUser;
import com.arrebol.apc.model.loan.LoanDetails;
import com.arrebol.apc.model.ws.parsed.LoanRequestedJaxb;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class LoanRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param loan
     * @return
     * @throws Exception
     */
    public String saveLoan(Loan loan) throws Exception {
        logger.debug("saveLoan");
        try {
            if (save(loan)) {
                return loan.getId();
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("saveLoan", e);
            throw e;
        }
    }

    /**
     *
     * @param loanId
     * @return
     * @throws Exception
     */
    public Loan findLoanById(String loanId) throws Exception {
        logger.debug("findLoanById");
        try {
            return (Loan) findAPCEntity(Loan.class, loanId);
        } catch (Exception e) {
            logger.error("findLoanById", e);
            throw e;
        }
    }

    /**
     *
     * @param loanId
     * @param xmlQuery
     * @param parameters
     * @param status
     * @param delivery
     * @return
     * @throws Exception
     */
    public boolean updateLoan(String loanId, String xmlQuery, List<ModelParameter> parameters, LoanStatus status, Delivery delivery) throws Exception {
        logger.debug("updateLoan");
        boolean success = false;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            List<ModelParameter> params = new ArrayList<>();

            params.add(new ModelParameter(LoanByUserCfg.FIELD_LOAN, new Loan(loanId)));
            params.add(new ModelParameter(LoanByUserCfg.FIELD_LOAN_BY_USER_STATUS, status));

            Query query1 = session.createNamedQuery(LoanByUserCfg.QUERY_UPDATE_LOAN_BY_USER_BY_LOAND_ID);

            params.forEach((param) -> {
                query1.setParameter(param.getParameter(), param.getValue());
            });

            query1.executeUpdate();

            if (null != parameters && !parameters.isEmpty()) {
                Query query = session.createNamedQuery(xmlQuery);

                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });

                if (0 < query.executeUpdate()) {

                    if (null != delivery) {
                        session.save(delivery);
                    }

                    transaction.commit();

                    success = true;
                    logger.info("updateLoan suucess");
                } else {
                    if (null != transaction) {
                        transaction.rollback();
                    }
                }
            } else {
                if (null != transaction) {
                    transaction.rollback();
                }
            }
            return success;
        } catch (Exception e) {
            logger.error("updateLoan", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public boolean updateLoanWithComission(String loanId, String xmlQuery, List<ModelParameter> parameters, LoanStatus status, Delivery delivery) throws Exception {
        logger.debug("updateLoan");
        boolean success = false;

        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            List<ModelParameter> params = new ArrayList<>();

            params.add(new ModelParameter(LoanByUserCfg.FIELD_LOAN, new Loan(loanId)));
            params.add(new ModelParameter(LoanByUserCfg.FIELD_LOAN_BY_USER_STATUS, status));

            Query query1 = session.createNamedQuery(LoanByUserCfg.QUERY_UPDATE_LOAN_BY_USER_BY_LOAND_ID);

            params.forEach((param) -> {
                query1.setParameter(param.getParameter(), param.getValue());
            });

            query1.executeUpdate();

            if (null != parameters && !parameters.isEmpty()) {
                Query query = session.createNamedQuery(xmlQuery);

                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });

                if (0 < query.executeUpdate()) {

                    if (null != delivery) {
                        session.save(delivery);
                    }

                    transaction.commit();

                    success = true;
                    logger.info("updateLoan suucess");
                } else {
                    if (null != transaction) {
                        transaction.rollback();
                    }
                }
            } else {
                if (null != transaction) {
                    transaction.rollback();
                }
            }
            return success;
        } catch (Exception e) {
            logger.error("updateLoan", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        }
    }

    /**
     *
     * @param loan
     * @param loanByUser
     * @param jaxb
     * @return
     * @throws Exception
     */
    public boolean createLoan(Loan loan, LoanByUser loanByUser, LoanRequestedJaxb jaxb) throws Exception {
        logger.debug("createLoan");
        try {
            People customer = jaxb.getCustomer().isCreatePerson()
                    ? null
                    : new People(jaxb.getCustomer().getId());

            People endorsement = jaxb.getEndorsement().isCreatePerson()
                    ? null
                    : new People(jaxb.getEndorsement().getId());

            openConnection();

            if (null == customer) {
                customer = new People(
                        jaxb.getCustomer(),
                        true,
                        jaxb.getOfficeId(),
                        jaxb.getUserId(),
                        //jaxb.getRouteId()
                        jaxb.chooseRouteId()
                );

                getSession().save(customer);
            }

            if (null == endorsement) {
                endorsement = new People(
                        jaxb.getEndorsement(),
                        false,
                        jaxb.getOfficeId(),
                        jaxb.getUserId(),
                        jaxb.getRouteId()
                );

                getSession().save(endorsement);
            }

            loan.setCustomer(customer);
            loan.setEndorsement(endorsement);

            getSession().save(loan);

            loanByUser.getId().setIdLoan(loan.getId());

            getSession().save(loanByUser);

            flushAndClear();
            closeConnection();

            return true;
        } catch (HibernateException e) {
            logger.error("createLoan", e);
            rollback();
            throw e;
        } catch (Exception e) {
            logger.error("Method createLoan() ", e);
            rollback();
            throw e;
        }
    }

    /**
     *
     * @param oldLoanId
     * @param loan
     * @param loanByUser
     * @param xmlQuery
     * @param parameters
     * @param loanDetails
     * @param endorsement
     * @param isNewEndorsement
     * @return
     * @throws Exception
     */
    public boolean renovationLoan(String oldLoanId,
            Loan loan,
            LoanByUser loanByUser,
            String xmlQuery,
            List<ModelParameter> parameters,
            LoanDetails loanDetails,
            People endorsement,
            boolean isNewEndorsement) throws Exception {
        logger.debug("renovationLoan");

        boolean success = false;
        try {
            openConnection();
            int total = 0;

            // Crea Aval y lo asigna al nuevo prestamo
            if (isNewEndorsement) {
                getSession().save(endorsement);
                loan.setEndorsement(endorsement);
            }

            // falta actualizar el status de la tabla APC_LOAN_BY_USER
            // del loan (oldLoanId) a PENDING_RENOVATION
            List<ModelParameter> params = new ArrayList<>();

            params.add(new ModelParameter(LoanByUserCfg.FIELD_LOAN, new Loan(oldLoanId)));
            params.add(new ModelParameter(LoanByUserCfg.FIELD_LOAN_BY_USER_STATUS, LoanStatus.PENDING_RENOVATION));

            Query query1 = getSession().createNamedQuery(LoanByUserCfg.QUERY_UPDATE_LOAN_BY_USER_BY_LOAND_ID);

            params.forEach((param) -> {
                query1.setParameter(param.getParameter(), param.getValue());
            });

            query1.executeUpdate();

            if (null != parameters && !parameters.isEmpty()) {
                Query query = getSession().createNamedQuery(xmlQuery);

                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });

                total = query.executeUpdate();
                logger.info("Query update executed");
            }
            if (total > 0) {
                getSession().save(loan);

                loanByUser.getId().setIdLoan(loan.getId());

                getSession().save(loanByUser);

                LoanByRenovation renovation = new LoanByRenovation(
                        new LoanByRenovationId(
                                oldLoanId,
                                loan.getId()
                        )
                );

                renovation.setLoanRenovationStatus(LoanRenovationStatus.PENDING);
                renovation.setCreatedBy(loan.getCreatedBy());
                renovation.setCreatedOn(loan.getCreatedOn());

                getSession().save(renovation);

                getSession().save(loanDetails);

                flushAndClear();
                closeConnection();

                logger.info("Renovation was created");
                success = true;
            } else {
                logger.error("Renovation was not updated");
                rollback();
            }

            return success;
        } catch (HibernateException e) {
            logger.error("renovationLoan", e);
            rollback();
            throw e;
        } catch (Exception e) {
            logger.error("Method renovationLoan() ", e);
            rollback();
            throw e;
        }
    }

    /**
     *
     * @param oldLoanId
     * @param loan
     * @param loanByUser
     * @param endorsement
     * @param isNewEndorsement
     * @return
     * @throws Exception
     */
    public boolean renovationHasPaymentToday(String oldLoanId,
            Loan loan,
            LoanByUser loanByUser,
            People endorsement,
            boolean isNewEndorsement) throws Exception {
        logger.debug("renovationHasPaymentToday");

        boolean success = false;
        try {
            openConnection();
            int total;

            // Crea Aval y lo asigna al nuevo prestamo
            if (isNewEndorsement) {
                getSession().save(endorsement);
                loan.setEndorsement(endorsement);
            }

            /**
             * Actualiza el loan original a PENDING RENOVATION
             */
            List<ModelParameter> params = new ArrayList<>();

            params.add(new ModelParameter(LoanCfg.FIELD_LOAN_STATUS, LoanStatus.PENDING_RENOVATION));
            params.add(new ModelParameter(LoanCfg.FIELD_COMMENTS, "El cliente previamente abono su pago diario, solo me contactode nuevo para renovar su crédito"));
            params.add(new ModelParameter(LoanCfg.FIELD_LAST_UPDATED_BY, loanByUser.getCreatedBy()));
            params.add(new ModelParameter(LoanCfg.FIELD_LAST_UPDATED_ON, new Date()));
            params.add(new ModelParameter(LoanCfg.FIELD_ID, oldLoanId));

            Query query = getSession().createNamedQuery(LoanCfg.QUERY_UPDATE_LOAN_BY_ID_FROM_CERTIFIER_VIEW);

            params.forEach((param) -> {
                query.setParameter(param.getParameter(), param.getValue());
            });

            total = query.executeUpdate();

            if (total > 0) {
                /**
                 * Actualiza el loan by user a PENDING RENOVATION
                 */
                params.clear();

                params.add(new ModelParameter(LoanByUserCfg.FIELD_LOAN, new Loan(oldLoanId)));
                params.add(new ModelParameter(LoanByUserCfg.FIELD_LOAN_BY_USER_STATUS, LoanStatus.PENDING_RENOVATION));

                Query query2 = getSession().createNamedQuery(LoanByUserCfg.QUERY_UPDATE_LOAN_BY_USER_BY_LOAND_ID);

                params.forEach((param) -> {
                    query2.setParameter(param.getParameter(), param.getValue());
                });

                total = query.executeUpdate();

                if (total > 0) {
                    // Crea el nuevo prestamo
                    getSession().save(loan);

                    loanByUser.getId().setIdLoan(loan.getId());

                    // guarda elprestamo por usuario
                    getSession().save(loanByUser);

                    // crea historico para saber de donde proviene en nuevo prestamo
                    LoanByRenovation renovation = new LoanByRenovation(
                            new LoanByRenovationId(
                                    oldLoanId,
                                    loan.getId()
                            )
                    );

                    renovation.setLoanRenovationStatus(LoanRenovationStatus.PENDING);
                    renovation.setCreatedBy(loan.getCreatedBy());
                    renovation.setCreatedOn(loan.getCreatedOn());

                    getSession().save(renovation);

                    flushAndClear();
                    closeConnection();

                    logger.info("renovationHasPaymentToday was created");
                    success = true;
                } else {
                    logger.error("renovationHasPaymentToday was not updated");
                    rollback();
                }
            } else {
                logger.error("renovationHasPaymentToday was not updated");
                rollback();
            }
            return success;
        } catch (HibernateException e) {
            logger.error("renovationHasPaymentToday", e);
            rollback();
            throw e;
        } catch (Exception e) {
            logger.error("Method renovationHasPaymentToday() ", e);
            rollback();
            throw e;
        }
    }

    private static final long serialVersionUID = -9216757241090613435L;
    final Logger logger = LogManager.getLogger(LoanRepository.class);

}
