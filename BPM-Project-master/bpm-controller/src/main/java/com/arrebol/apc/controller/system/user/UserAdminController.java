/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.system.user;

import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.UserByOffice;
import com.arrebol.apc.model.enums.UserStatus;
import com.arrebol.apc.repository.GenericEntityRepository;
import com.arrebol.apc.repository.core.UserByOfficeRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class UserAdminController implements Serializable {

    /**
     *
     * @param statuses
     * @param officeId
     * @param ownerId
     * @return
     */
    public List<UserByOffice> findUsersInOfficeInStatuses(List<UserStatus> statuses, String officeId, String ownerId) {
        logger.debug("findUsersInOfficeInStatuses");
        List retults = new ArrayList();
        try {
            retults = getUserByOfficeRepository()
                    .findUsersInOfficeInStatuses(statuses, new Office(officeId), ownerId);
        } catch (Exception e) {
            logger.error("findUsersInOfficeInStatuses", e);
        }

        return retults;
    }

    private static final long serialVersionUID = -2718549077979303345L;
    final Logger logger = LogManager.getLogger(UserAdminController.class);

    private final GenericEntityRepository genericEntityRepository;
    private final UserByOfficeRepository userByOfficeRepository;

    public UserAdminController() {
        this.genericEntityRepository = new GenericEntityRepository();
        this.userByOfficeRepository = new UserByOfficeRepository();
    }

    public GenericEntityRepository getGenericEntityRepository() {
        return genericEntityRepository;
    }

    public UserByOfficeRepository getUserByOfficeRepository() {
        return userByOfficeRepository;
    }

}
