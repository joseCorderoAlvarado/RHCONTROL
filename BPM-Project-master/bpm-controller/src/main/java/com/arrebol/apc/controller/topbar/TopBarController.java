/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.topbar;

import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.repository.core.OfficeRepository;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.User;
import com.arrebol.apc.model.core.constance.OfficeCfg;
import com.arrebol.apc.model.core.constance.UserByOfficeCfg;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class TopBarController implements Serializable {

    public List<Office> findAllOfficesByUserController(String userId) {
        logger.debug("findAllOfficesByUserController");
        
        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(UserByOfficeCfg.FIELD_USER, new User(userId)));

        return getOfficeRepository().findAllOfficesByUser(OfficeCfg.QUERY_TUPLE_FIND_ALL_OFFICES_BY_USER, parameters);
    }

    private static final long serialVersionUID = -4239053737928432361L;
    final Logger logger = LogManager.getLogger(TopBarController.class);

    public final OfficeRepository officeRepository;

    public TopBarController() {
        this.officeRepository = new OfficeRepository();
    }

    public OfficeRepository getOfficeRepository() {
        return officeRepository;
    }

}
