/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.service.views;

import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.views.EnabledUserDetailsView;
import com.arrebol.apc.model.views.constance.EnabledUserDetailsViewCfg;
import com.arrebol.apc.repository.LazyDataModelDAORepository;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@RequestScoped
public class EnabledUserDetailsViewServiceImpl extends LazyDataModelDAORepository<EnabledUserDetailsView> implements EnabledUserDetailsViewService {

    @Override
    public List<EnabledUserDetailsView> findEnabledUsersByOffice(String officeId) {
        logger.debug("findEnabledUsersByOffice");
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(EnabledUserDetailsViewCfg.PARAM_OFFICE_ID, officeId));

            return xmlQueryAPCEntities(EnabledUserDetailsView.class, EnabledUserDetailsViewCfg.QUERY_FIND_ENABLED_USERS_BY_OFFICE, parameters);
        } catch (Exception e) {
            logger.error("findEnabledUsersByOffice", e);
            return new ArrayList<>();
        }
    }

    final Logger logger = LogManager.getLogger(getClass());
}
