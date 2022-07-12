/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.route;

import com.arrebol.apc.controller.mobile.moxy.views.ContainerRoutes;
import com.arrebol.apc.controller.mobile.repository.route.RouteRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.catalog.constance.RouteCfg;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.enums.ActiveStatus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class RouteController implements Serializable {

    public ContainerRoutes findAllRoutesAvailables(String officeId) throws Exception {
        try {
            RouteRepository repository = new RouteRepository();
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(RouteCfg.FIELD_OFFICE, new Office(officeId)));
            parameters.add(new ModelParameter(RouteCfg.FIELD_ACTIVE_STATUS, ActiveStatus.ENEBLED));

            return repository.findAllRoutesAvailables(RouteCfg.QUERY_FIND_ALL_AVAILABLES_ROUTES, parameters);
        } catch (Exception e) {
            logger.error("findAllRoutesAvailables", e);
            throw e;
        }
    }

    final Logger logger = LogManager.getLogger(getClass());
}
