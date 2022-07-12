/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.route;

import com.arrebol.apc.controller.mobile.moxy.views.ContainerRoutes;
import com.arrebol.apc.controller.mobile.moxy.views.RouteJaxb;
import com.arrebol.apc.controller.mobile.repository.GenericRepository;
import com.arrebol.apc.controller.mobile.repository.people.PeopleRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.catalog.constance.RouteCfg;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Tuple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class RouteRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public ContainerRoutes findAllRoutesAvailables(String xmlQuery, List<ModelParameter> parameters) throws Exception {
        try {
            List<Tuple> tuples = xmlQueryTuple(xmlQuery, parameters);
            if (!tuples.isEmpty()) {
                List<RouteJaxb> results = new ArrayList<>();
                tuples.forEach(tuple -> {
                    results.add(new RouteJaxb(tuple.get(RouteCfg.FIELD_ID, String.class), tuple.get(RouteCfg.FIELD_NAME, String.class)));
                });
                return new ContainerRoutes(results);
            } else {
                throw new Exception("Error loading route, size equals to ZERO");
            }
        } catch (Exception e) {
            logger.error("findAllRoutesAvailables", e);
            throw e;
        }
    }

    final Logger logger = LogManager.getLogger(PeopleRepository.class);
}
