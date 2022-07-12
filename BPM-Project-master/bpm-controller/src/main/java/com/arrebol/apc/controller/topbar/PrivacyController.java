/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.topbar;

import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.core.constance.UserCfg;
import com.arrebol.apc.repository.GenericEntityRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class PrivacyController implements Serializable {

    /**
     *
     * @param password
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean updatePasswordByUserId(String password, String userId) throws Exception {
        logger.debug("updatePasswordByUserId");
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(UserCfg.FIELD_PASSWORD, password));
            parameters.add(new ModelParameter(UserCfg.FIELD_ID, userId));

            return genericEntityRepository.xmlUpdateOrDeleteAPCEntity(UserCfg.QUERY_UPDATE_PASSWORD_BY_USER_ID, parameters);
        } catch (Exception e) {
            logger.error("updatePasswordByUserId", e);
            throw e;
        }
    }

    private static final long serialVersionUID = 1131011930227628970L;
    final Logger logger = LogManager.getLogger(PrivacyController.class);

    private final GenericEntityRepository genericEntityRepository;

    public PrivacyController() {
        this.genericEntityRepository = new GenericEntityRepository();
    }

}
