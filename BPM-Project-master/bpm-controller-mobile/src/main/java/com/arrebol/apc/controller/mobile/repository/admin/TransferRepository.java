/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.admin;

import com.arrebol.apc.controller.mobile.repository.GenericRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.admin.Transfer;
import com.arrebol.apc.model.ws.parsed.Exchange;
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
public class TransferRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param transfer
     * @return
     * @throws Exception
     */
    public boolean addTransfer(Transfer transfer) throws Exception {
        logger.debug("addTransfer");
        try {
            return save(transfer);
        } catch (Exception e) {
            logger.error("addTransfer", e);
            throw e;
        }
    }

    /**
     *
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public boolean updateTransfer(String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("updateTransfer");
        try {
            return updateCreateNamedQuery(xmlQuery, parameters);
        } catch (Exception e) {
            logger.error("updateTransfer", e);
            throw e;
        }
    }
    
    /**
     * 
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception 
     */
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

    private static final long serialVersionUID = 1787207383193052932L;
    final Logger logger = LogManager.getLogger(TransferRepository.class);
}
