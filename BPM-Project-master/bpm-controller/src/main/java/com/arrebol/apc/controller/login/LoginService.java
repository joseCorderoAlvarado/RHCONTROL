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
import java.util.List;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public interface LoginService {

    /**
     *
     * @return
     */
    public List<Office> getAllActiveOfficeController();

    /**
     *
     * @param userByOffice
     * @return
     */
    public UserByOffice findUserLoggedController(UserByOffice userByOffice);
}
