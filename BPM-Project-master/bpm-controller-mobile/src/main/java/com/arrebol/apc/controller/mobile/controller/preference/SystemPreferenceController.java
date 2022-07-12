/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.preference;

import com.arrebol.apc.controller.mobile.moxy.views.LoanByUserOrderPreferenceViewJaxb;
import com.arrebol.apc.controller.mobile.moxy.views.LoanByUserOrderPreferenceViewListJaxb;
import com.arrebol.apc.controller.mobile.repository.preference.UserMobilePreferenceRepository;
import com.arrebol.apc.controller.mobile.repository.views.LoanByUserOrderPreferenceViewRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.admin.constance.ClosingDayCfg;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.User;
import com.arrebol.apc.model.core.constance.LoanByUserCfg;
import com.arrebol.apc.model.core.constance.LoanByUserOrderPreferenceViewCfg;
import com.arrebol.apc.model.core.constance.UserMobilePreferenceCfg;
import com.arrebol.apc.model.enums.PreferenceName;
import com.arrebol.apc.model.enums.PreferenceValue;
import com.arrebol.apc.model.loan.LoanByUserId;
import com.arrebol.apc.model.mobile.preference.UserMobilePreference;
import com.arrebol.apc.model.views.LoanByUserOrderPreferenceView;
import com.arrebol.apc.model.ws.parsed.ConfigurationJaxb;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class SystemPreferenceController implements Serializable {

    /**
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public List<LoanByUserOrderPreferenceView> findAllLoanByUserOrderPreference(String userId) throws Exception {
        logger.debug("findAllLoanByUserOrderPreference");
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(LoanByUserOrderPreferenceViewCfg.FIELD_ORDER_USER_ID, userId));

            return loanByUserOrderPreferenceViewRepository
                    .findAllLoanByUserOrderPreference(
                            LoanByUserOrderPreferenceViewCfg.QUERY_FIND_ALL_LOAN_BY_USER_ORDER_PREFERENCE,
                            parameters
                    );
        } catch (Exception e) {
            logger.error("findAllLoanByUserOrderPreference", e);
            throw e;
        }
    }

    /**
     *
     * @param updateOrderListPreference
     * @return
     * @throws Exception
     */
    public boolean updateOrderInList(LoanByUserOrderPreferenceViewListJaxb updateOrderListPreference) throws Exception {
        logger.debug("updateOrderInList");

        boolean success = false;
        try {
            if (updateLoanByUser(updateOrderListPreference)) {
                success = updateUserMobilePreference(
                        new User(updateOrderListPreference.getLoanByUserOrderPreferences().get(0).getUserId()),
                        PreferenceName.ORDER_LIST.toString(),
                        PreferenceValue.ORDER_IN_LIST.toString()
                );
            }
            return success;
        } catch (Exception e) {
            logger.error("updateOrderInList", e);
            throw e;
        }
    }

    /**
     *
     * @param UserId
     * @param preferenceName
     * @param preferenceValue
     * @return
     * @throws Exception
     */
    public boolean updateUserMobilePreference(String UserId, String preferenceName, String preferenceValue) throws Exception {
        logger.debug("updateUserMobilePreference");

        boolean success = false;
        try {
            success = updateUserMobilePreference(
                    new User(UserId),
                    preferenceName,
                    preferenceValue
            );

        } catch (Exception e) {
            logger.error("updateUserMobilePreference", e);
            throw e;
        }
        return success;
    }

    /**
     *
     * @param updateOrderListPreference
     * @return
     * @throws Exception
     */
    private boolean updateLoanByUser(LoanByUserOrderPreferenceViewListJaxb updateOrderListPreference) throws Exception {
        logger.debug("updateLoanByUser");
        try {
            for (int i = 0; i < updateOrderListPreference.getLoanByUserOrderPreferences().size(); i++) {
                LoanByUserOrderPreferenceViewJaxb preference = updateOrderListPreference.getLoanByUserOrderPreferences().get(i);
                List<ModelParameter> parameters = new ArrayList<>();

                parameters.add(
                        new ModelParameter(
                                LoanByUserCfg.FIELD_ORDER_IN_LIST,
                                preference.getOrderInList()
                        )
                );

                parameters.add(
                        new ModelParameter(
                                LoanByUserCfg.FIELD_ID,
                                new LoanByUserId(
                                        preference.getId(),
                                        preference.getUserId()
                                )
                        )
                );

                loanByUserOrderPreferenceViewRepository.updateQuery(
                        LoanByUserCfg.QUERY_UPDATE_ORDER_IN_LIST,
                        parameters
                );
            }
            return true;
        } catch (Exception e) {
            logger.error("updateLoanByUser", e);
            throw e;
        }
    }

    /**
     *
     * @param user
     * @param preferenceName
     * @param preferenceValue
     * @return
     * @throws Exception
     */
    private boolean updateUserMobilePreference(User user, String preferenceName, String preferenceValue) throws Exception {
        logger.debug("updateUserMobilePreference");

        boolean success = false;
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(UserMobilePreferenceCfg.FIELD_USER, user));
            parameters.add(new ModelParameter(UserMobilePreferenceCfg.FIELD_PREFERENCE_NAME, preferenceName));

            UserMobilePreference userMobilePreference = userMobilePreferenceRepository.findUserMobilePreference(
                    UserMobilePreference.class,
                    UserMobilePreferenceCfg.QUERY_FIND_MOBILE_PREFERENCE_BY_USER_AND_PREFERENCE_NAME,
                    parameters);

            if (null != userMobilePreference) {
                parameters.clear();

                parameters.add(new ModelParameter(UserMobilePreferenceCfg.FIELD_PREFERENCE_VALUE, preferenceValue));
                parameters.add(new ModelParameter(UserMobilePreferenceCfg.FIELD_LAST_UPDATED_BY, user.getId()));
                parameters.add(new ModelParameter(UserMobilePreferenceCfg.FIELD_LAST_UPDATED_ON, new Date()));
                parameters.add(new ModelParameter(UserMobilePreferenceCfg.FIELD_ID, userMobilePreference.getId()));

                success = userMobilePreferenceRepository.updateQueryUserMobilePreference(
                        UserMobilePreferenceCfg.QUERY_UPDATE_PREFERENCE_VALUE,
                        parameters
                );
            } else {
                userMobilePreference = new UserMobilePreference(
                        user,
                        preferenceName,
                        preferenceValue,
                        user.getId());
                success = userMobilePreferenceRepository.insertUserMobilePreference(userMobilePreference);
            }
        } catch (Exception e) {
            logger.error("updateUserMobilePreference", e);
            throw e;
        }
        return success;
    }

    /**
     *
     * @param userId
     * @param officeId
     * @return
     * @throws Exception
     */
    public ConfigurationJaxb findConfigurationButton(String userId, String officeId) throws Exception {
        logger.debug("findConfigurationButton");
        ConfigurationJaxb configuration;
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(ClosingDayCfg.FIELD_USER, new User(userId)));
            parameters.add(new ModelParameter(ClosingDayCfg.FIELD_OFFICE, new Office(officeId)));

            Long result = userMobilePreferenceRepository.count(ClosingDayCfg.QUERY_COUNT_CLOSING_DATE_BY_USER_AND_OFFICE, parameters);
            configuration = new ConfigurationJaxb(result.compareTo(new Long(0)) == 0);
        } catch (Exception e) {
            logger.error("findConfigurationButton", e);
            configuration = new ConfigurationJaxb(false);
        }
        return configuration;
    }

    private static final long serialVersionUID = 6329489276545356862L;
    final Logger logger = LogManager.getLogger(SystemPreferenceController.class);

    private final LoanByUserOrderPreferenceViewRepository loanByUserOrderPreferenceViewRepository;
    private final UserMobilePreferenceRepository userMobilePreferenceRepository;

    public SystemPreferenceController() {
        this.loanByUserOrderPreferenceViewRepository = new LoanByUserOrderPreferenceViewRepository();
        this.userMobilePreferenceRepository = new UserMobilePreferenceRepository();
    }

}
