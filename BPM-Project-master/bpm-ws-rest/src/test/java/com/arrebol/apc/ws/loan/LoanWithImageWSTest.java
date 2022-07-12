/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.loan;

import com.arrebol.apc.model.ws.parsed.LoanRequestedJaxb;
import com.arrebol.apc.model.ws.parsed.PersonJaxb;
import com.arrebol.apc.model.ws.parsed.ThumbnailJaxb;
import com.arrebol.apc.ws.cfg.ApplicationCfg;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.test.JerseyTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class LoanWithImageWSTest extends JerseyTest {

    @Override
    protected URI getBaseUri() {
        return UriBuilder.fromUri(super.getBaseUri()).path("apc-ws-rest").build();
    }

    @Override
    protected Application configure() {
        return new ApplicationCfg();
    }

    @Override
    protected void configureClient(ClientConfig config) {
        config.register(MultiPartFeature.class);
    }

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

    @Test
    public void createLoanWithImage() {
        LoanRequestedJaxb loan = new LoanRequestedJaxb();

        loan.setLoanTypeId(LOAN_TYPE_ID);
        loan.setOfficeId(OFFICE_ID);
        loan.setUserId(USER_ID);
        loan.setRouteId(ROUTE_ID);
        loan.setCustomer(buildPerson(true, true));
        loan.setEndorsement(buildPerson(true, false));

        ThumbnailJaxb thumbnail = new ThumbnailJaxb();
        String name = "/Users/Picasso/Desktop/login_bg.png";
        File initialFile = new File(name);

        try {
            thumbnail.setName(name);
            thumbnail.setFile(new FileInputStream(initialFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoanWSTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        final WebTarget target = target().path("loan/create-with-image2");

        final FormDataMultiPart mp = new FormDataMultiPart();
        
        mp.bodyPart(new FormDataBodyPart(FormDataContentDisposition.name("file").fileName("file").build(),
                thumbnail.getFile(),
                MediaType.APPLICATION_JSON_TYPE));
        
        

        Response response = target.request().put(Entity.entity(mp, MediaType.MULTIPART_FORM_DATA_TYPE), Response.class);

        System.out.println("response: " + response);
        assertEquals(200, response.getStatus());
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
