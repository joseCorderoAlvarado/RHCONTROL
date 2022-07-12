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
import com.arrebol.apc.model.views.LoanByUserView;
import com.arrebol.apc.model.views.PersonSearchDetailView;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Tuple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class PersonSearchViewRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param clazz
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public List findResultList(Class clazz, String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("likePersonSearchViewByPersonSearch");

        try {
            return createNamedQueryResultList(clazz, xmlQuery, parameters, 10);
        } catch (Exception e) {
            logger.error("likePersonSearchViewByPersonSearch", e);
            throw e;
        }
    }

    /**
     *
     * @param clazz
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public Object findResultXML(Class clazz, String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("findResultXML");
        try {
            return createNamedQueryUniqueResult(clazz, xmlQuery, parameters);
        } catch (Exception e) {
            logger.error("findResultXML", e);
            throw e;
        }
    }

    public Object findResultTupleXML(String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("findResultXML");
        try {
            List<Tuple> tuples = xmlQueryTuple(xmlQuery, parameters);

            LoanByUserView result = new LoanByUserView();

            for (Tuple tuple : tuples) {

                result = new LoanByUserView(
                        tuple.get("customerName", String.class),
                        tuple.get("customerAddressHome", String.class),
                        tuple.get("customerAddressBusiness", String.class),
                        tuple.get("companyName", String.class),
                        tuple.get("customerThumbnail", String.class),
                        tuple.get("endorsementName", String.class),
                        tuple.get("endorsementAddressHome", String.class),
                        tuple.get("endorsementThumbnail", String.class),
                        tuple.get("endorsementPhoneHome", String.class),
                        tuple.get("paymentDaily", BigDecimal.class),
                        tuple.get("fee", BigDecimal.class),
                        tuple.get("notificationNumber", Long.class).intValue(),
                        tuple.get("renovation", String.class),
                        tuple.get("maxAmountToPay", BigDecimal.class),
                        1 == tuple.get("hasPaymentToday", Integer.class),
                        tuple.get("loanId", String.class),
                        tuple.get("currentOwner", String.class)
                );
            }

            return result;
        } catch (Exception e) {
            logger.error("findResultXML", e);
            throw e;
        }
    }

    /**
     *
     * @param clazz
     * @param id
     * @return
     * @throws Exception
     */
    public PersonSearchDetailView findResult(Class clazz, String id) throws Exception {
        logger.debug("findResult");
        try {
            return (PersonSearchDetailView) findAPCEntity(clazz, id);
        } catch (Exception e) {
            logger.error("findResult", e);
            throw e;
        }
    }

    private static final long serialVersionUID = -5127034542019248849L;
    final Logger logger = LogManager.getLogger(PersonSearchViewRepository.class);

}
