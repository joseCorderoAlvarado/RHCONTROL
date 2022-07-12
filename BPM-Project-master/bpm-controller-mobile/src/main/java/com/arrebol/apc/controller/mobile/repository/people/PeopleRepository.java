/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.people;

import com.arrebol.apc.controller.mobile.repository.GenericRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.catalog.People;
import com.arrebol.apc.model.loan.Loan;
import java.io.Serializable;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class PeopleRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param people
     * @return
     * @throws Exception
     */
    public String createPeople(People people) throws Exception {
        logger.debug("createPeople");
        try {
            save(people);

            return people.getId();

        } catch (Exception e) {
            logger.error("createPeople", e);
            throw e;
        }
    }

    /**
     *
     * @param people
     * @return
     * @throws Exception
     */
    public boolean removePeople(People people) throws Exception {
        logger.debug("removePeople");
        try {
            people = (People) findAPCEntity(People.class, people.getId());

            return delete(people);
        } catch (Exception e) {
            logger.error("removePeople", e);
            throw e;
        }
    }

    /**
     *
     * @param loanId
     * @param isCustomer
     * @return
     * @throws Exception
     */
    public String findPeopleIdByIdLoad(String loanId, boolean isCustomer) throws Exception {
        try {
            Loan loan = (Loan) findAPCEntity(Loan.class, loanId);

            return isCustomer ? loan.getCustomer().getId() : loan.getEndorsement().getId();
        } catch (Exception e) {
            logger.error("findPeopleByIdLoad", e);
            throw e;
        }
    }

    /**
     *
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public boolean changeContactNumber(String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("changeContactNumber");
        try {
            return updateCreateNamedQuery(xmlQuery, parameters);
        } catch (Exception e) {
            logger.error("changeContactNumber", e);
            throw e;
        }
    }

    private static final long serialVersionUID = 6168828614367099322L;
    final Logger logger = LogManager.getLogger(PeopleRepository.class);
}
