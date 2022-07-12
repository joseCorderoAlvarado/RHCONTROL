/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.search;

import com.arrebol.apc.model.loan.Loan;
import com.arrebol.apc.model.views.LoanByUserView;
import com.arrebol.apc.model.views.PersonSearchDetailView;
import com.arrebol.apc.model.views.PersonSearchView;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class PersonSearchControllerTest {

    private PersonSearchController controller;
    public static final String PERSON_SEARCH_ID = "83d2cd30-8e1d-11ea-b65e-4e1376171215";
    public static final String USER_ID = "67b3081e-8bc9-11ea-b45c-c7b846343364";

    public PersonSearchControllerTest() {
    }

    @Before
    public void setUp() {
        controller = new PersonSearchController();
    }

    //@Test
    public void findAllLoanByUserOrderPreference() {
        try {
            List<PersonSearchView> results = controller.findAllCoincidences("%janitzio%");

            results.forEach(System.out::println);

            assertFalse(results.isEmpty());
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    //@Test
    public void findPersonSearchDetail() {
        try {
            PersonSearchDetailView personSearchDetailView = controller.findPersonSearchDetail(PERSON_SEARCH_ID);

            System.out.println(personSearchDetailView);
            assertNotNull(personSearchDetailView);
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    public void searchPaymentDetails() {
        try {
            LoanByUserView personSearchDetailView = controller.searchPaymentDetails(USER_ID, PERSON_SEARCH_ID);

            System.out.println(personSearchDetailView);
            assertNotNull(personSearchDetailView);
        } catch (Exception e) {
            assertFalse(true);
        }
    }

}
