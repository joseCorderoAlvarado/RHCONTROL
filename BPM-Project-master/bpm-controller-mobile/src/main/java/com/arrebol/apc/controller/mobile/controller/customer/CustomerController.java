/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.customer;

import com.arrebol.apc.controller.mobile.repository.views.LoanByUserViewRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.core.constance.LoanByUserViewCfg;
import com.arrebol.apc.model.enums.PreferenceValue;
import com.arrebol.apc.model.views.LoanByUserView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class CustomerController implements Serializable {

    /**
     *
     * @param orderList
     * @param userId
     * @return
     * @throws Exception
     */
    public List<LoanByUserView> findAllLoansByUserId(String orderList, String userId) throws Exception {
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            boolean isByOrderInList = PreferenceValue.ORDER_IN_LIST.toString().equals(orderList);

            parameters.add(new ModelParameter(LoanByUserViewCfg.FIELD_USER_ID, userId));

            return loanByUserViewRepository
                    .findAllLoansByUserId(
                            isByOrderInList
                                    ? LoanByUserViewCfg.QUERY_FIND_ALL_LOAN_BY_USER_ID_BY_ORDER_LIST
                                    : LoanByUserViewCfg.QUERY_FIND_ALL_LOAN_BY_USER_ID_BY_CUSTOMER_NAME,
                            parameters
                    );
        } catch (Exception e) {
            logger.error("findAllLoansByUserId", e);
            throw e;
        }
    }

    private static final long serialVersionUID = -6689182942800786108L;
    final Logger logger = LogManager.getLogger(CustomerController.class);

    private final LoanByUserViewRepository loanByUserViewRepository;

    public CustomerController() {
        this.loanByUserViewRepository = new LoanByUserViewRepository();
    }
}
