/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.people;

import com.arrebol.apc.controller.mobile.repository.people.PeopleRepository;
import com.arrebol.apc.model.catalog.People;
import com.arrebol.apc.model.catalog.RouteCtlg;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.enums.ActiveStatus;
import com.arrebol.apc.model.enums.PeopleType;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class PeopleRepositoryTest {

    private PeopleRepository repository;
    public static final String OFFICE_ID = "e0f1a2fc-7d1f-11ea-af3e-28f659da398e";
    public static final String ROUTE_ID = "5e9a24e0-8e19-11ea-b65e-4e1376171215";
    public static final String TEXT_VALUE = "Testing";

    public PeopleRepositoryTest() {
    }

    @Before
    public void setUp() {
        repository = new PeopleRepository();
    }

    @Test
    public void createPeople() {
        try {
            People people = new People();

            people.setFirstName(TEXT_VALUE);
            people.setLastName(TEXT_VALUE);
            people.setMiddleName(TEXT_VALUE);
            people.setAddressHome(TEXT_VALUE);
            people.setPhoneHome(TEXT_VALUE);
            people.setThumbnail(TEXT_VALUE);
            people.setPeopleType(PeopleType.BOTH);
            people.setActiveStatus(ActiveStatus.DISABLED);
            people.setOffice(new Office(OFFICE_ID));
            people.setRouteCtlg(new RouteCtlg(ROUTE_ID));
            people.setCreatedBy(TEXT_VALUE);
            people.setCreatedOn(new Date());

            String id = null;//repository.createPeople(people);
            
            System.out.println(id);
            
            assertNotNull(id);
        } catch (Exception e) {
            assertFalse(true);
        }
    }

}
