/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.exchange;

import com.arrebol.apc.controller.mobile.repository.admin.TransferRepository;
import com.arrebol.apc.controller.mobile.repository.views.ExchangeEnebledUsersViewRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.admin.Transfer;
import com.arrebol.apc.model.admin.constance.TransferCfg;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.User;
import com.arrebol.apc.model.core.constance.ExchangeEnebledUsersViewCfg;
import com.arrebol.apc.model.enums.ActionStatus;
import com.arrebol.apc.model.enums.ActiveStatus;
import com.arrebol.apc.model.views.ExchangeEnebledUsersView;
import com.arrebol.apc.model.ws.parsed.Exchange;
import com.arrebol.apc.model.ws.parsed.ExchangeJaxb;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class ExchangeEnebledUsersController implements Serializable {

    /**
     *
     * @param userId
     * @param officeId
     * @return
     * @throws Exception
     */
    public List<ExchangeEnebledUsersView> findEnebledUsersToUserId(String userId, String officeId) throws Exception {
        logger.debug("exchangeEnebledUsersViewRepository");
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(
                    new ModelParameter(
                            ExchangeEnebledUsersViewCfg.PARAM_OFFICE_ID,
                            officeId
                    )
            );

            parameters.add(
                    new ModelParameter(
                            ExchangeEnebledUsersViewCfg.FIELD_VIEW_ID,
                            userId
                    )
            );

            return exchangeEnebledUsersViewRepository.findEnebledUsersToUserId(
                    ExchangeEnebledUsersViewCfg.QUERY_FIND_ENEBLED_USERS_TO_USER_ID,
                    parameters
            );
        } catch (Exception e) {
            logger.error("findEnebledUsersToUserId", e);
            throw e;
        }
    }

    /**
     *
     * @param jaxb
     * @return
     * @throws Exception
     */
    public boolean newExchange(ExchangeJaxb jaxb) throws Exception {
        logger.debug("newExchange");
        try {
            Transfer transfer = new Transfer(
                    new User(jaxb.getSenderId()),
                    new User(jaxb.getReceiverId()),
                    ActiveStatus.ENEBLED,
                    ActionStatus.PENDING,
                    jaxb.getAmount(),
                    new Office(jaxb.getOfficeId()),
                    jaxb.getSenderId());

            return transferRepository.addTransfer(transfer);
        } catch (Exception e) {
            logger.error("newExchange", e);
            throw e;
        }
    }

    /**
     *
     * @param transerId
     * @param userId
     * @param isApproved
     * @return
     * @throws Exception
     */
    public boolean updateExchange(String transerId, String userId, boolean isApproved) throws Exception {
        logger.debug("updateExchange");
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(TransferCfg.FIELD_ACTION_STATUS, isApproved ? ActionStatus.APPROVED : ActionStatus.REJECTED));
            parameters.add(new ModelParameter(TransferCfg.FIELD_LAST_UPDATED_BY, userId));
            parameters.add(new ModelParameter(TransferCfg.FIELD_LAST_UPDATED_ON, new Date()));
            parameters.add(new ModelParameter(TransferCfg.FIELD_ID, transerId));

            return transferRepository.updateTransfer(TransferCfg.QUERY_UPDATE_TRANSFER_BY_ACTION, parameters);
        } catch (Exception e) {
            logger.error("updateExchange", e);
            throw e;
        }
    }

    /**
     * 
     * @param userId
     * @return
     * @throws Exception 
     */
    public List<Exchange> exchangesByUsers(String userId) throws Exception {
        logger.debug("exchangesByUsers");
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(
                    new ModelParameter(
                            TransferCfg.FIELD_USER_TRANSMITTER,
                            new User(userId)
                    )
            );
            
            parameters.add(
                    new ModelParameter(
                            TransferCfg.FIELD_USER_RECEIVER,
                            new User(userId)
                    )
            );

            return transferRepository.findAllTransferByUserIdAndCurdate(TransferCfg.QUERY_FIND_ALL_TRANSFER_BY_USER_ID_AND_CURDATE, parameters);
        } catch (Exception e) {
            logger.error("exchangesByUsers", e);
            throw e;
        }
    }

    private static final long serialVersionUID = 2625775904919860613L;
    final Logger logger = LogManager.getLogger(ExchangeEnebledUsersController.class);

    private final ExchangeEnebledUsersViewRepository exchangeEnebledUsersViewRepository;
    private final TransferRepository transferRepository;

    public ExchangeEnebledUsersController() {
        this.exchangeEnebledUsersViewRepository = new ExchangeEnebledUsersViewRepository();
        this.transferRepository = new TransferRepository();
    }

}
