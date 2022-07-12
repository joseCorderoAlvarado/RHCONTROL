/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.service;

import com.arrebol.apc.model.core.User;
import java.util.Date;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public interface GenericValidationService {

    /**
     *
     * @param user
     * @return
     */
    Date lastStableSmallBoxByDate(User user);

    /**
     *
     * @param date
     * @return
     */
    boolean existStableSmallBoxByCreatedOn(Date date);
}
