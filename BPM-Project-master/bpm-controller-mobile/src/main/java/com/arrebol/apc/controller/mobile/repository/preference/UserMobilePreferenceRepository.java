/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.preference;

import com.arrebol.apc.controller.mobile.repository.GenericRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.mobile.preference.UserMobilePreference;
import java.io.Serializable;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class UserMobilePreferenceRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param clazz
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public UserMobilePreference findUserMobilePreference(Class clazz, String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("findUserMobilePreference");

        try {
            return (UserMobilePreference) createNamedQueryUniqueResult(clazz, xmlQuery, parameters);
        } catch (Exception e) {
            logger.error("findUserMobilePreference", e);
            throw e;
        }
    }

    /**
     *
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public boolean updateQueryUserMobilePreference(String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("updateQueryUserMobilePreference");

        try {
            return updateCreateNamedQuery(xmlQuery, parameters);
        } catch (Exception e) {
            logger.error("updateQueryUserMobilePreference", e);
            throw e;
        }
    }

    /**
     *
     * @param userMobilePreference
     * @return
     * @throws Exception
     */
    public boolean insertUserMobilePreference(UserMobilePreference userMobilePreference) throws Exception {
        logger.debug("insertUserMobilePreference");

        try {
            return save(userMobilePreference);
        } catch (Exception e) {
            logger.error("insertUserMobilePreference", e);
            throw e;
        }
    }

    /**
     *
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public Long count(String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("count");
        try {
            return (Long) createNamedQueryUniqueResult(Long.class, xmlQuery, parameters);
        } catch (Exception e) {
            logger.error("count", e);
            throw e;
        }
    }

    private static final long serialVersionUID = 4667354927801455153L;
    final Logger logger = LogManager.getLogger(UserMobilePreferenceRepository.class);

}
