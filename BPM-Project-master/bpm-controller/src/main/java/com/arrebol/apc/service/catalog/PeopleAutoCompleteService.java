/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.service.catalog;

import com.arrebol.apc.model.catalog.People;
import java.util.List;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 *
 */
public interface PeopleAutoCompleteService {

    List<People> findCustomersLike(String valueToSearch);

    List<People> findEndorsementsLike(String valueToSearch);
}
