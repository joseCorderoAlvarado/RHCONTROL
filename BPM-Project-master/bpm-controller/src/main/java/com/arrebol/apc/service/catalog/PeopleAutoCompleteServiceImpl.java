/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.service.catalog;

import com.arrebol.apc.model.catalog.People;
import com.arrebol.apc.repository.AutoCompleteDAORepository;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@RequestScoped
public class PeopleAutoCompleteServiceImpl extends AutoCompleteDAORepository<People> implements PeopleAutoCompleteService {

    @Override
    public List<People> findCustomersLike(String valueToSearch) {
        try {
            StringBuilder builder = new StringBuilder("FROM People WHERE ");

            builder.append("(firstName LIKE '%");
            builder.append(valueToSearch);
            builder.append("%' OR ");
            builder.append("secondName LIKE '%");
            builder.append(valueToSearch);
            builder.append("%' OR ");
            builder.append("lastName LIKE '%");
            builder.append(valueToSearch);
            builder.append("%' OR ");
            builder.append("middleName LIKE '%");
            builder.append(valueToSearch);
            builder.append("%') ");
            builder.append("AND peopleType IN('CUSTOMER','BOTH') ");
            builder.append("AND activeStatus = 'ENEBLED' ");
            builder.append("ORDER BY firstName");

            return searchLike(People.class, builder.toString(), null);
        } catch (Exception e) {
            logger.error("findCustomersLike", e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<People> findEndorsementsLike(String valueToSearch) {
        try {
            StringBuilder builder = new StringBuilder("FROM People WHERE ");

            builder.append("(firstName LIKE '%");
            builder.append(valueToSearch);
            builder.append("%' OR ");
            builder.append("secondName LIKE '%");
            builder.append(valueToSearch);
            builder.append("%' OR ");
            builder.append("lastName LIKE '%");
            builder.append(valueToSearch);
            builder.append("%' OR ");
            builder.append("middleName LIKE '%");
            builder.append(valueToSearch);
            builder.append("%') ");
            builder.append("AND peopleType IN('ENDORSEMENT','BOTH') ");
            builder.append("AND activeStatus = 'ENEBLED' ");
            builder.append("ORDER BY firstName");

            return searchLike(People.class, builder.toString(), null);
        } catch (Exception e) {
            logger.error("findEndorsementsLike", e);
            return new ArrayList<>();
        }
    }

    final Logger logger = LogManager.getLogger(getClass());

}
