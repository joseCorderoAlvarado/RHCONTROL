/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.login;

import com.arrebol.apc.repository.core.OfficeRepository;
import com.arrebol.apc.repository.core.UserByOfficeRepository;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.UserByOffice;
import java.io.Serializable;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class LoginController implements Serializable {

    public List<Office> getAllActiveOfficeController() {
        logger.debug("getAllActiveOfficeController");

        return getOfficeRepository().getAllActiveOffice();

    }

    public UserByOffice findUserLoggedController(UserByOffice userByOffice) {
        logger.debug("findUserLoggedController");
        return getUserByOfficeRepository().findUserLogged(userByOffice);
    }

    private static final long serialVersionUID = 6949757021314889125L;
    final Logger logger = LogManager.getLogger(LoginController.class);

    private final OfficeRepository officeRepository;
    private final UserByOfficeRepository userByOfficeRepository;

    public LoginController() {
        this.officeRepository = new OfficeRepository();
        this.userByOfficeRepository = new UserByOfficeRepository();
    }

    public OfficeRepository getOfficeRepository() {
        return officeRepository;
    }

    public UserByOfficeRepository getUserByOfficeRepository() {
        return userByOfficeRepository;
    }

}
