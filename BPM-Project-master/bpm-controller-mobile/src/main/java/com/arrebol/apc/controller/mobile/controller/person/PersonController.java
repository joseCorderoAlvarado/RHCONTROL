/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.person;

import com.arrebol.apc.controller.mobile.repository.people.PeopleRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.catalog.constance.PeopleCfg;
import com.arrebol.apc.model.catalog.constance.RouteCfg;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.enums.ActiveStatus;
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
public class PersonController implements Serializable {

    /**
     *
     * @param loanId
     * @param lastUpdatedBy
     * @param phoneNumber
     * @param isCustomer
     * @return
     */
    public boolean changeContactNumber(String loanId, String lastUpdatedBy, String phoneNumber, boolean isCustomer) {
        boolean success = false;
        try {
            PeopleRepository repository = new PeopleRepository();
            String personId = repository.findPeopleIdByIdLoad(loanId, isCustomer);
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(PeopleCfg.FIELD_PHONE_HOME, phoneNumber));
            parameters.add(new ModelParameter(PeopleCfg.FIELD_LAST_UPDATED_BY, lastUpdatedBy));
            parameters.add(new ModelParameter(PeopleCfg.FIELD_LAST_UPDATED_ON, new Date()));
            parameters.add(new ModelParameter(PeopleCfg.FIELD_ID, personId));

            return repository.changeContactNumber(PeopleCfg.QUERY_UPDATE_PHONE_HOME_BY_PEOPLE_ID, parameters);
        } catch (Exception e) {
            logger.error("changeContactNumber", e);
        }
        return success;
    }

    final Logger logger = LogManager.getLogger(getClass());
}
