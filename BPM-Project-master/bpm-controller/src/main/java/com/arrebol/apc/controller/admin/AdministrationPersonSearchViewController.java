/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.admin;

import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.enums.PeopleType;
import com.arrebol.apc.model.views.AdministrationPersonSerchView;
import com.arrebol.apc.model.views.constance.AdministrationPersonSerchViewCfg;
import com.arrebol.apc.repository.GenericEntityRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class AdministrationPersonSearchViewController implements Serializable {
    /**
     *
     * @param personSearch
     * @param type
     * @param idOffice
     * @return
     */
    public List<AdministrationPersonSerchView> likePersonNameInPersonTypeAllRoutesByOffice(String personSearch, PeopleType type, String idOffice) {
        List<AdministrationPersonSerchView> persons;
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(new ModelParameter(AdministrationPersonSerchViewCfg.FIELD_PERSON_SEARCH, personSearch));
            parameters.add(new ModelParameter(AdministrationPersonSerchViewCfg.FIELD_PEOPLE_TYPE, type));
            parameters.add(new ModelParameter(AdministrationPersonSerchViewCfg.FIELD_ID_OFFICE, idOffice));

            persons = genericEntityRepository.xmlQueryAPCEntities(AdministrationPersonSerchView.class, AdministrationPersonSerchViewCfg.QUERY_LIKE_PERSON_NAME_IN_PERSON_TYPE_ALL_ROUTES_BY_OFFICE, parameters);
        } catch (Exception e) {
            persons = new ArrayList<>();

            logger.error("likePersonNameInPersonTypeAllRoutesByOffice", e);
        }
        return persons;
    }

    /**
     *
     * @param id
     * @return
     */
    public AdministrationPersonSerchView administrationPersonSerchViewById(String id) {
        AdministrationPersonSerchView person;
        try {
            person = (AdministrationPersonSerchView) genericEntityRepository.selectAPCEntityById(AdministrationPersonSerchView.class, id);
        } catch (Exception e) {
            person = new AdministrationPersonSerchView();

            logger.error("administrationPersonSerchViewById", e);
        }
        return person;
    }

    public AdministrationPersonSearchViewController() {
        this.genericEntityRepository = new GenericEntityRepository();
    }

    final Logger logger = LogManager.getLogger(getClass());
    private final GenericEntityRepository genericEntityRepository;
}
