/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.login;

import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.UserByOffice;
import com.arrebol.apc.repository.core.OfficeRepository;
import com.arrebol.apc.repository.core.UserByOfficeRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@RequestScoped
public class LoginServiceImpl implements LoginService {

    @Override
    public List<Office> getAllActiveOfficeController() {
        logger.debug("getAllActiveOfficeController");

        return officeRepository.getAllActiveOffice();

    }

    @Override
    public UserByOffice findUserLoggedController(UserByOffice userByOffice) {
        logger.debug("findUserLoggedController");
        return userByOfficeRepository.findUserLogged(userByOffice);
    }

    final Logger logger = LogManager.getLogger(getClass());

    @Inject
    private OfficeRepository officeRepository;
    @Inject
    private UserByOfficeRepository userByOfficeRepository;
}
