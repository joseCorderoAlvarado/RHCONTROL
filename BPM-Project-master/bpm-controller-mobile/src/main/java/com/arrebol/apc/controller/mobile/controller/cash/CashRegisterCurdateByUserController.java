/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.cash;

import com.arrebol.apc.controller.mobile.repository.views.CashRegisterCurdateByUserViewRepository;
import com.arrebol.apc.controller.mobile.repository.views.TotalCashByCurdateViewRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.core.constance.CashRegisterCurdateByUserViewCfg;
import com.arrebol.apc.model.views.CashRegisterCurdateByUserView;
import com.arrebol.apc.model.views.TotalCashByCurdateView;
import com.arrebol.apc.model.ws.parsed.AmountJaxb;
import com.arrebol.apc.model.ws.parsed.CashRegisterJaxb;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class CashRegisterCurdateByUserController implements Serializable {

    /**
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public CashRegisterJaxb findAllCashRegisterCurdateByUserId(String userId) throws Exception {
        logger.debug("findAllCashRegisterCurdateByUserId");
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(
                    new ModelParameter(
                            CashRegisterCurdateByUserViewCfg.FIELD_USER_ID,
                            userId
                    )
            );

            Double totalCash = 0d;
            List<AmountJaxb> amounts = new ArrayList<>();

            List<CashRegisterCurdateByUserView> results = cashRegisterCurdateByUserViewRepository.findAllCashRegisterCurdateByUserId(
                    CashRegisterCurdateByUserView.class,
                    CashRegisterCurdateByUserViewCfg.QUERY_FIND_CASH_REGISTER,
                    parameters);

            for (CashRegisterCurdateByUserView result : results) {
                totalCash += result.getPayment().doubleValue();

                amounts.add(
                        new AmountJaxb(
                                result.getCustomerName(),
                                result.getPayment().doubleValue()
                        )
                );
            }

            return new CashRegisterJaxb(totalCash, amounts);
        } catch (Exception e) {
            logger.error("findAllCashRegisterCurdateByUserId", e);
            throw e;
        }
    }

    /**
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public TotalCashByCurdateView findDailyTotalsByUserId(String userId) throws Exception {
        logger.debug("findById");
        try {
            TotalCashByCurdateView result = totalCashByCurdateViewRepository.findById(userId);

            if (null == result) {
                throw new NullPointerException("userId: " + userId + " NOT found");
            } else {
                return result;
            }
        } catch (Exception e) {
            logger.error("findById", e);
            throw e;
        }
    }

    private static final long serialVersionUID = -3434981661467291172L;
    final Logger logger = LogManager.getLogger(CashRegisterCurdateByUserController.class);

    private final CashRegisterCurdateByUserViewRepository cashRegisterCurdateByUserViewRepository;
    private final TotalCashByCurdateViewRepository totalCashByCurdateViewRepository;

    public CashRegisterCurdateByUserController() {
        this.cashRegisterCurdateByUserViewRepository = new CashRegisterCurdateByUserViewRepository();
        this.totalCashByCurdateViewRepository = new TotalCashByCurdateViewRepository();
    }

}
