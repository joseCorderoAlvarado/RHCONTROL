/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.core.constance;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public interface UserMobilePreferenceCfg extends GenericCfg {

    String QUERY_FIND_ALL_MOBILE_PREFERENCES_BY_USER = "findAllMobilePreferenceByUser";
    String QUERY_FIND_MOBILE_PREFERENCE_BY_USER_AND_PREFERENCE_NAME = "findMobilePreferenceByUserAndPreferenceName";
    String QUERY_UPDATE_PREFERENCE_VALUE = "updatePreferenceValueInUserMobilePreferenceById";

    String FIELD_USER = "user";
    String FIELD_PREFERENCE_NAME = "preferenceName";
    String FIELD_PREFERENCE_VALUE = "preferenceValue";
}
