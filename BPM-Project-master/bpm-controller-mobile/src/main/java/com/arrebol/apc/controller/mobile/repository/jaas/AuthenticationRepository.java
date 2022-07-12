/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.jaas;

import com.arrebol.apc.controller.mobile.repository.GenericRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.core.User;
import com.arrebol.apc.model.core.constance.MobileUserCfg;
import com.arrebol.apc.model.core.constance.UserMobilePreferenceCfg;
import com.arrebol.apc.model.mobile.access.MobileUser;
import com.arrebol.apc.model.mobile.preference.UserMobilePreference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class AuthenticationRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    public MobileUser findUser(String userName, String password) throws Exception {
        logger.debug("findUser");

        MobileUser mobileUser = null;
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(MobileUserCfg.FIELD_USER_NAME, userName));
            parameters.add(new ModelParameter(MobileUserCfg.FIELD_PASSWORD, password));

            mobileUser = (MobileUser) createNamedQueryUniqueResult(
                    MobileUser.class,
                    MobileUserCfg.QUERY_FIND_MOBILE_USER_FROM_LOGIN,
                    parameters
            );
        } catch (Exception e) {
            logger.error("findUser", e);
            throw e;
        }
        return mobileUser;
    }

    /**
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public List<UserMobilePreference> findAllMobilePreferenceByUser(String userId) throws Exception {
        logger.debug("findAllMobilePreferenceByUser");

        List<UserMobilePreference> userMobilePreferences = new ArrayList<>();
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(UserMobilePreferenceCfg.FIELD_USER, new User(userId)));

            userMobilePreferences = createNamedQueryResultList(
                    UserMobilePreference.class,
                    UserMobilePreferenceCfg.QUERY_FIND_ALL_MOBILE_PREFERENCES_BY_USER,
                    parameters
            );

        } catch (Exception e) {
            logger.error("findAllMobilePreferenceByUser", e);
            throw e;
        }
        return userMobilePreferences;
    }

    private static final long serialVersionUID = 4218734911509288001L;
    final Logger logger = LogManager.getLogger(AuthenticationRepository.class);

}
