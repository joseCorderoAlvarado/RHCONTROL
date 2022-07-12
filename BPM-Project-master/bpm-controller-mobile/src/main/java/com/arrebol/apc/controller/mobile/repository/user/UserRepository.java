/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.user;

import com.arrebol.apc.controller.mobile.repository.GenericRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.core.constance.UserCfg;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Tuple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class UserRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public String findUserStatusById(String xmlQuery, List<ModelParameter> parameters) throws Exception {
        try {
            for (Tuple tuple : xmlQueryTuple(xmlQuery, parameters)) {
                return tuple.get(UserCfg.FIELD_ID, String.class);
            }

            return null;
        } catch (Exception e) {
            logger.error("findUserStatusById", e);
            throw e;
        }
    }

    private static final long serialVersionUID = -7339139534291674077L;
    final Logger logger = LogManager.getLogger(getClass());

}
