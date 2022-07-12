/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.loan;

import com.arrebol.apc.controller.mobile.json.loan.LoanTypeListJaxb;
import com.arrebol.apc.model.views.AvailableCustomersView;
import com.arrebol.apc.model.views.AvailableEndorsementsView;
import com.arrebol.apc.model.views.LoanToDeliveryByCertifierView;
import com.arrebol.apc.model.ws.parsed.LoanDetailJaxb;
import com.arrebol.apc.model.ws.parsed.LoanRequestedJaxb;
import com.arrebol.apc.model.ws.parsed.PersonJaxb;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class LoanControllerTest {

    /**
     * $3,000.00
     */
    public final static String LOAN_TYPE_ID = "8d91bc36-8e00-11ea-8745-07889553dd5f";
    /**
     * GDL
     */
    public static final String OFFICE_ID = "e0f1a2fc-7d1f-11ea-af3e-28f659da398e";
    /**
     * TEPIC
     */
    public static final String TEPIC_OFFICE_ID = "caef3a64-7d1f-11ea-af3e-28f659da398e";
    /**
     * LOANS
     */
    public static final String LOAND_ID = "acccdfac-8e1b-11ea-b65e-4e1376171215";
    /**
     * Avatar 2
     */
    public final static String MOBILE_USER_ID = "52cbc85a-8bc9-11ea-b45c-c7b846343364";
    /**
     *
     */
    public final static String ROUTE_ID = "55baf3ae-8e19-11ea-b65e-4e1376171215";
    /**
     *
     */
    public final static String DUMMY_DATA = "DUMMY";
    private LoanController controller;

    @Before
    public void setUp() {
        controller = new LoanController();
    }

    //@Test
    public void findAllLoanTypeByOffice() {
        try {
            LoanTypeListJaxb result = controller.findAllLoanTypeByOffice(OFFICE_ID);

            System.out.println(result);

            assertFalse(result.getLoans().isEmpty());
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    //@Test
    public void findAvailableCustomersByOffice() {
        try {
            List<AvailableCustomersView> results = controller.findAllAvailableCustomersByType("%SeGuNDo%");

            results.forEach(System.out::println);

            assertFalse(results.isEmpty());
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    //@Test
    public void findAvailableEndorsementByOffice() {
        try {
            List<AvailableEndorsementsView> results = controller.findAllAvailableEndorsementsByType("%AvAl%");

            results.forEach(System.out::println);

            assertFalse(results.isEmpty());
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    //@Test
    public void findNewCreditLimit() {
        try {
            LoanTypeListJaxb result = controller.findNewCreditLimit(OFFICE_ID, LOAND_ID);

            System.out.println(result);

            assertFalse(result.getLoans().isEmpty());
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    //@Test
    public void createLoan() {
        try {
            LoanRequestedJaxb jaxb = new LoanRequestedJaxb();

            PersonJaxb customer = buildPerson(false, true);
            PersonJaxb endorsement = buildPerson(false, false);

            jaxb.setLoanTypeId(LOAN_TYPE_ID);
            jaxb.setOfficeId(OFFICE_ID);
            jaxb.setUserId(MOBILE_USER_ID);
            jaxb.setRouteId(ROUTE_ID);
            jaxb.setCustomer(customer);
            jaxb.setEndorsement(endorsement);
            jaxb.setStrDate("2020-28-05 16:04:62");

            //assertTrue(controller.createLoan(jaxb));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    //@Test
    public void findLoansByCertifier() {
        try {
            List<LoanToDeliveryByCertifierView> results = controller.findLoansByCertifier(MOBILE_USER_ID);

            results.forEach(System.out::println);

            assertFalse(results.isEmpty());
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    public void approvedDetailsByIdLoan() {
        try {
            //LoanJaxb result = controller.approvedDetailsByIdLoan(LOAND_ID);
            List<LoanDetailJaxb> result = controller.approvedDetailsByIdLoan(LOAND_ID);
            System.out.println(result);

            assertNotNull(result);
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    private PersonJaxb buildPerson(boolean isNewPerson, boolean isCustomer) {
        PersonJaxb person = new PersonJaxb();

        if (!isNewPerson) {
            if (isCustomer) {
                person.setId("e61db008-8e1e-11ea-b65e-4e1376171215");
            } else {
                person.setId("2b502d50-8e1e-11ea-b65e-4e1376171215");
            }
        } else {
            person.setFirstName(DUMMY_DATA);
            person.setSecondName(DUMMY_DATA);
            person.setLastName(DUMMY_DATA);
            person.setMiddleName(DUMMY_DATA);
            person.setAddressHome(DUMMY_DATA);
            person.setPhoneHome(DUMMY_DATA);
            person.setCreatePerson(true);
            person.setThumbnail("https://f0.pngfuel.com/png/980/304/man-profile-illustration-computer-icons-user-profile-avatar-png-clip-art.png");

            if (isCustomer) {
                person.setAddressWork(DUMMY_DATA);
                person.setPhoneWork(DUMMY_DATA);
                person.setCompanyName(DUMMY_DATA);
            }
        }

        return person;
    }
}
