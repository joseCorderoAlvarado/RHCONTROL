/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.service.admin;

import com.arrebol.apc.model.catalog.People;
import com.arrebol.apc.repository.LazyDataModelDAORepository;
import javax.enterprise.context.RequestScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@RequestScoped
public class PeopleServiceImpl extends LazyDataModelDAORepository<People> implements PeopleService {

    @Override
    public People findPeopleById(String peopleId) {
        logger.debug("findPeopleById");

        return (People) findAPCEntity(People.class, peopleId);
    }

    public boolean savePeople(People people) {
        logger.debug("savePeopleController");
        return save(people);
    }

    final Logger logger = LogManager.getLogger(getClass());
}
