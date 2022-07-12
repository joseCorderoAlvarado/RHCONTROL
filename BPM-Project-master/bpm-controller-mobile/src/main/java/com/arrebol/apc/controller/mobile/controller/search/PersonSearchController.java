/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.search;

import com.arrebol.apc.controller.mobile.repository.views.PersonSearchHistoricalDetailsViewRepository;
import com.arrebol.apc.controller.mobile.repository.views.PersonSearchViewRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.catalog.People;
import com.arrebol.apc.model.core.User;
import com.arrebol.apc.model.core.constance.LoanByUserCfg;
import com.arrebol.apc.model.core.constance.LoanCfg;
import com.arrebol.apc.model.core.constance.PersonSearchHistoricalDetailsViewCfg;
import com.arrebol.apc.model.core.constance.PersonSearchViewCfg;
import com.arrebol.apc.model.core.constance.UserCfg;
import com.arrebol.apc.model.enums.PeopleType;
import com.arrebol.apc.model.views.LoanByUserView;
import com.arrebol.apc.model.views.PersonSearchDetailView;
import com.arrebol.apc.model.views.PersonSearchHistoricalDetailsView;
import com.arrebol.apc.model.views.PersonSearchView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class PersonSearchController implements Serializable {

    /**
     *
     * @param nameToSearch
     * @return
     * @throws Exception
     */
    public PersonSearchView fullNameEqualsToPersonSearch(String nameToSearch) throws Exception {
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(
                    new ModelParameter(
                            PersonSearchViewCfg.FIELD_PERSON_SEARCH,
                            nameToSearch
                    )
            );

            PersonSearchView result = (PersonSearchView) personSearchViewRepository.findResultXML(
                    PersonSearchView.class,
                    PersonSearchViewCfg.QUERY_FULL_NAME_EQUALS_TO_PERSON_SEARCH,
                    parameters
            );

            if (null == result) {
                result = new PersonSearchView("N/A", "N/A");
            }
            return result;
        } catch (Exception e) {
            logger.error("findAllCoincidences", e);
            throw e;
        }
    }

    /**
     *
     * @param search
     * @return If any return list of coincidences otherwise return empty list.
     * @throws Exception
     */
    public List<PersonSearchView> findAllCoincidences(String search) throws Exception {
        logger.debug("findAllCoincidences");
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(
                    new ModelParameter(
                            PersonSearchViewCfg.FIELD_PERSON_SEARCH,
                            search
                    )
            );

            return personSearchViewRepository.findResultList(
                    PersonSearchView.class,
                    PersonSearchViewCfg.QUERY_LIKE_BY_PERSON_SEARCH,
                    parameters
            );
        } catch (Exception e) {
            logger.error("findAllCoincidences", e);
            throw e;
        }
    }

    /**
     *
     * @param personSearchId
     * @return
     * @throws Exception
     */
    public PersonSearchDetailView findPersonSearchDetail(String personSearchId) throws Exception {
        logger.debug("findPersonSearchDetail");
        try {
            return personSearchViewRepository.findResult(PersonSearchDetailView.class, personSearchId);
        } catch (Exception e) {
            logger.error("findPersonSearchDetail", e);
            throw e;
        }
    }

    /**
     *
     * @param personSearchId
     * @return
     * @throws Exception
     */
    public List<PersonSearchHistoricalDetailsView> findPersonHistoricalDetailsByPersonId(String personSearchId) throws Exception {
        logger.debug("findPersonHistoricalDetailsByPersonId");
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(PersonSearchHistoricalDetailsViewCfg.FIELD_PERSON_SEARCH_ID, personSearchId));

            return personSearchHistoricalDetailsViewRepository.findAllByPersonId(
                    PersonSearchHistoricalDetailsView.class,
                    PersonSearchHistoricalDetailsViewCfg.QUERY_FIND_PERSON_SEARCH_HISTORICAL_DETAILS_BY_PERSON_ID,
                    parameters);
        } catch (Exception e) {
            logger.error("findPersonHistoricalDetailsByPersonId", e);
            throw e;
        }
    }

    //Necesito utilizar tuple
    /**
     *
     * @param idUser
     * @param personSearch
     * @return
     * @throws Exception
     */
    public LoanByUserView searchPaymentDetails(String idUser, String personSearch) throws Exception {
        logger.debug("searchPaymentDetails");
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(LoanCfg.FIELD_CUSTOMER, new People(personSearch)));
            parameters.add(new ModelParameter(LoanByUserCfg.FIELD_USER, new User(idUser)));
            parameters.add(new ModelParameter(UserCfg.FIELD_ID, idUser));

            return (LoanByUserView) personSearchViewRepository.findResultTupleXML(
                    LoanCfg.QUERY_SEARCH_PAYMENT_DETAILS,
                    parameters);

        } catch (Exception e) {
            logger.error("searchPaymentDetails", e);
            throw e;
        }
    }

    private static final long serialVersionUID = -3224360462532091921L;
    final Logger logger = LogManager.getLogger(PersonSearchController.class);

    final private PersonSearchViewRepository personSearchViewRepository;
    final private PersonSearchHistoricalDetailsViewRepository personSearchHistoricalDetailsViewRepository;

    public PersonSearchController() {
        this.personSearchViewRepository = new PersonSearchViewRepository();
        this.personSearchHistoricalDetailsViewRepository = new PersonSearchHistoricalDetailsViewRepository();
    }

}
