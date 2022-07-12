/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.views;

import com.arrebol.apc.controller.mobile.repository.GenericRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.enums.LoanDetailsType;
import com.arrebol.apc.model.views.LoanApprovedDetailView;
import com.arrebol.apc.model.ws.parsed.LoanDetailJaxb;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Tuple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class LoanApprovedDetailViewRepository extends GenericRepository implements Serializable {

    /*
    public List<Exchange> findAllTransferByUserIdAndCurdate(String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("findAllTransferByUserIdAndCurdate");
        List<Exchange> results = new ArrayList<>();
        try {
            List<Tuple> tuples = xmlQueryTuple(xmlQuery, parameters);
            
            tuples.forEach((tuple) -> {
                results.add(
                        new Exchange(
                                tuple.get("id").toString(),
                                tuple.get("userTransmitter").toString().equals("SENDER"),
                                (BigDecimal) tuple.get("amountToTransfer"),
                                tuple.get("actionStatus").toString()
                        )
                );
            });
            
            return results;
        } catch (Exception e) {
            logger.error("findAllTransferByUserIdAndCurdate", e);
            throw e;
        }
    }
     */
    /**
     *
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public List<LoanDetailJaxb> findLoanDetailsByLoan(String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("findLoanDetailsByLoan");
        List<LoanDetailJaxb> results = new ArrayList<>();
        try {
            List<Tuple> tuples = xmlQueryTuple(xmlQuery, parameters);

            double total = 0d;

            for (Tuple tuple : tuples) {
                if (results.isEmpty()) {
                    total = ((BigDecimal) tuple.get("amountToPay")).doubleValue();
                }
                LoanDetailsType type = tuple.get("loanDetailsType", LoanDetailsType.class);

                switch (type) {
                    case PAYMENT:
                        total = total - ((BigDecimal) tuple.get("paymentAmount")).doubleValue();
                        break;
                    case FEE:
                        total = total + ((BigDecimal) tuple.get("paymentAmount")).doubleValue();
                        break;
                    case TRANSFER:
                        total = total - (tuple.get("paymentAmount", BigDecimal.class)).doubleValue();
                        break;
                }

                LoanDetailJaxb detail = new LoanDetailJaxb(
                        tuple.get("createdOn", String.class),
                        ((BigDecimal) tuple.get("paymentAmount")).doubleValue(),
                        total,
                        tuple.get("comments", String.class),
                        type.toString()
                );

                results.add(detail);
            }

            return results;
        } catch (Exception e) {
            logger.error("findLoanDetailsByLoan", e);
            throw e;
        }
    }

    /**
     *
     * @param idLoan
     * @return
     * @throws Exception
     */
    public LoanApprovedDetailView approvedDetailsByIdLoan(String idLoan) throws Exception {
        logger.debug("approvedDetailsByIdLoan");
        try {
            return (LoanApprovedDetailView) findAPCEntity(LoanApprovedDetailView.class, idLoan);
        } catch (Exception e) {
            logger.error("approvedDetailsByIdLoan", e);
            throw e;
        }
    }

    private static final long serialVersionUID = -7364653160752676339L;
    final Logger logger = LogManager.getLogger(LoanApprovedDetailViewRepository.class);

}
