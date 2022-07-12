/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.login;

import com.arrebol.apc.controller.mobile.moxy.login.UserMxy;
import com.arrebol.apc.controller.mobile.moxy.login.UserPreferenceMxy;
import com.arrebol.apc.controller.mobile.repository.jaas.AuthenticationRepository;
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
public class LoginWSController implements Serializable {

    public UserMxy login(String userName, String password) throws Exception {
        logger.debug("login");

        UserMxy userMxy;
        List<UserPreferenceMxy> preferenceLst;
        try {
            MobileUser mobileUser = getAuthenticationRepository().findUser(userName, password);

            if (null == mobileUser) {
                throw new Exception("Access denied");
            } else {
                userMxy = new UserMxy(
                        mobileUser.getId(),
                        mobileUser.getUserName(),
                        mobileUser.getAvatar(),
                        mobileUser.getOfficeId(),
                        mobileUser.getRouteId(),
                        mobileUser.getCertifier()
                );
            }

            List<UserMobilePreference> userMobilePreferences = getAuthenticationRepository().findAllMobilePreferenceByUser(userMxy.getId());

            if (!userMobilePreferences.isEmpty()) {
                preferenceLst = new ArrayList<>();

                userMobilePreferences.forEach((preference) -> {
                    preferenceLst.add(new UserPreferenceMxy(preference.getPreferenceName(), preference.getPreferenceValue()));
                });

                userMxy.setPreferences(preferenceLst);
            }
        } catch (Exception e) {
            logger.error("login", e);
            throw e;
        }
        return userMxy;
    }

    public LoginWSController() {
        this.authenticationRepository = new AuthenticationRepository();
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    private static final long serialVersionUID = 2795964728722199660L;
    final Logger logger = LogManager.getLogger(LoginWSController.class);

    private final AuthenticationRepository authenticationRepository;

}
