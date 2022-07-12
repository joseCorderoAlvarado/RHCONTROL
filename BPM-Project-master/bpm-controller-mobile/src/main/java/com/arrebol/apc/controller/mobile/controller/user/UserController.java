/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.user;

import com.arrebol.apc.controller.mobile.repository.user.UserRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.core.constance.UserCfg;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class UserController implements Serializable {

    /**
     * 
     * @param userId
     * @return
     * @throws Exception 
     */
    public boolean isUserEnebled(String userId) throws Exception {
        logger.info("isUserEnebled");
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(UserCfg.FIELD_ID, userId));

            return userId.equals(userRepository.findUserStatusById(UserCfg.QUERY_VERIFY_USER_STATUS_BY_ID, parameters));
        } catch (Exception e) {
            logger.error("isUserEnebled", e);
            throw e;
        }
    }

    private static final long serialVersionUID = -2705701746315543593L;
    final Logger logger = LogManager.getLogger(getClass());

    private final UserRepository userRepository;

    public UserController() {
        this.userRepository = new UserRepository();
    }

}
