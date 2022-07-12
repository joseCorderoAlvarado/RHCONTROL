/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.service.admin;

import com.arrebol.apc.model.catalog.People;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public interface PeopleService {

    /**
     *
     * @param peopleId
     * @return
     */
    People findPeopleById(String peopleId);

    /**
     *
     * @param people
     * @return
     */
    boolean savePeople(People people);
}
