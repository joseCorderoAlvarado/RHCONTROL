/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.loan;

import com.arrebol.apc.model.ws.parsed.PersonJaxb;
import com.arrebol.apc.model.ws.parsed.LoanRequestedJaxb;
import com.arrebol.apc.model.ws.parsed.NewAmountJaxb;
import java.math.BigDecimal;
import java.net.URI;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.util.runner.ConcurrentRunner;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@RunWith(ConcurrentRunner.class)
public class LoanWSTest extends JerseyTest {

    private static final URI BASE_URI = URI.create("http://localhost:8080/apc-ws-rest/resource");
    private static final String OFFICE_ID = "e0f1a2fc-7d1f-11ea-af3e-28f659da398e";
    private static final String LOAN_ID = "acccdfac-8e1b-11ea-b65e-4e1376171215";
    private static final String USER_ID = "52cbc85a-8bc9-11ea-b45c-c7b846343364";
    private static final String ROUTE_ID = "5e9a24e0-8e19-11ea-b65e-4e1376171215";
    private static final String LOAN_TYPE_ID = "dc255a16-8dff-11ea-8745-07889553dd5f";
    private static final String CUSTOMER_ID = "4adc2c28-8e1e-11ea-b65e-4e1376171215";
    private static final String ENDORSEMENT_ID = "ef10171e-8e1e-11ea-b65e-4e1376171215";
    private static final String CREATED_ON = "2020-06-02 12:13:14";
    private static final String DUMMY_STRING_DATA = "DUMMY DATA";

    public LoanWSTest() {
    }

    @Override
    protected ResourceConfig configure() {
        enable(TestProperties.LOG_TRAFFIC);
        return new ResourceConfig(LoanWS.class);
    }

    //@Test
    public void findAllLoanTypeByOffice() {

        Response response = target().path("loan/findAllLoans")
                .queryParam("id", OFFICE_ID)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class);

        System.out.println("response: " + response);
        assertEquals(200, response.getStatus());
    }

    //@Test
    public void findAllAvailableCustomersByOffice() {

        Response response = target().path("loan/findAllAvailableCustomers")
                .queryParam("id", OFFICE_ID)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class);

        System.out.println("response: " + response);
        assertEquals(200, response.getStatus());
    }

    //@Test
    public void findAllAvailableEndorsementsByOffice() {

        Response response = target().path("loan/findAllAvailableEndorsements")
                .queryParam("id", OFFICE_ID)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class);

        System.out.println("response: " + response);
        assertEquals(200, response.getStatus());
    }

    //@Test
    public void createLoan() {
        LoanRequestedJaxb loan = new LoanRequestedJaxb();

        loan.setLoanTypeId(LOAN_TYPE_ID);
        loan.setOfficeId(OFFICE_ID);
        loan.setUserId(USER_ID);
        loan.setRouteId(ROUTE_ID);
        loan.setCustomer(buildPerson(true, true));
        loan.setEndorsement(buildPerson(false, false));

        Response response = target().path("loan/create")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .put(Entity.entity(loan, MediaType.APPLICATION_JSON), Response.class);

        System.out.println("response: " + response);
        assertEquals(200, response.getStatus());
    }

    //@Test
    public void saveNewAmount() {
        try {
            NewAmountJaxb jaxb = newAmountJaxb();

            Response response = target().path("loan/saveNewAmount")
                    .request(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(jaxb, MediaType.APPLICATION_JSON), Response.class);

            System.out.println("response: " + response);
            assertEquals(200, response.getStatus());
        } catch (Exception e) {
            Assert.assertNull(null);
        }
    }

    //@Test
    public void renovation() {
        try {
            Form form = new Form();

            form.param("loan", LOAN_ID);
            form.param("credit", LOAN_TYPE_ID);
            form.param("user", USER_ID);
            form.param("date", CREATED_ON);

            Response response = target().path("loan/renovation")
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.form(form), Response.class);

            System.out.println("response: " + response);
            assertEquals(200, response.getStatus());
        } catch (Exception e) {
            Assert.assertNull(null);
        }
    }
    
    @Test
    public void certifierAction() {
        try {
            Form form = new Form();
            
            form.param("id", "148c6a61-2e80-45bb-a854-8515547d7871");
            form.param("user", "52cbc85a-8bc9-11ea-b45c-c7b846343364");
            form.param("comments", "USER AVATAR 2");
            form.param("action", "true");
            form.param("amount", "1230.0");

            Response response = target().path("loan/certifierAction")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.form(form), Response.class);

            System.out.println("response: " + response);
            assertEquals(200, response.getStatus());
        } catch (Exception e) {
            Assert.assertNull(null);
        }
    }

    /**
     *
     * @return
     */
    private NewAmountJaxb newAmountJaxb() {
        NewAmountJaxb jaxb = new NewAmountJaxb();

        jaxb.setLoanId(LOAN_ID);
        jaxb.setUserId(USER_ID);
        jaxb.setAmount(new BigDecimal(50));
        jaxb.setCustomer(true);
        jaxb.setFee(true);
        jaxb.setStrDate("2020-05-26 09:41:07");

        return jaxb;
    }

    /**
     *
     * @param isDummyPerson
     * @param isCustomer
     * @return
     */
    private PersonJaxb buildPerson(boolean isDummyPerson, boolean isCustomer) {
        PersonJaxb person = new PersonJaxb();

        if (isDummyPerson) {
            person.setFirstName(DUMMY_STRING_DATA);
            person.setLastName(DUMMY_STRING_DATA);
            person.setMiddleName(DUMMY_STRING_DATA);
            person.setAddressHome(DUMMY_STRING_DATA);
            person.setPhoneHome(DUMMY_STRING_DATA);
            person.setThumbnail("https://w7.pngwing.com/pngs/571/847/png-transparent-avatar-free-content-ugly-teacher-s-hat-cartoon-scalable-vector-graphics.png");
            person.setCreatePerson(true);
            if (isCustomer) {
                person.setAddressWork(DUMMY_STRING_DATA);
                person.setPhoneWork(DUMMY_STRING_DATA);
                person.setCompanyName(DUMMY_STRING_DATA);
            }
        } else {
            if (isCustomer) {
                person.setId(CUSTOMER_ID);
            } else {
                person.setId(ENDORSEMENT_ID);
            }
        }

        return person;
    }

}
