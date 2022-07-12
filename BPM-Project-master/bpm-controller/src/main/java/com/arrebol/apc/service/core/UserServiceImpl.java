/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.service.core;

import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.User;
import com.arrebol.apc.model.core.constance.UserCfg;
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
public class UserServiceImpl extends LazyDataModelDAORepository<User> implements UserService {

    @Override
    public List<User> getAllUsersByOffice(String officeId) {
        logger.debug("getAllUsersByOffice");
        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(UserCfg.FIELD_OFFICE, new Office(officeId)));

        return xmlQueryAPCEntities(User.class, UserCfg.QUERY_FIND_ALL_USERS_COMPLETE, parameters);
    }

    final Logger logger = LogManager.getLogger(getClass());
}
