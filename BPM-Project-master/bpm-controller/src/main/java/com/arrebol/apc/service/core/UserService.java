/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.service.core;

import com.arrebol.apc.model.core.User;
import java.util.List;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public interface UserService {

    /**
     *
     * @param officeId
     * @return
     */
    List<User> getAllUsersByOffice(String officeId);
}
