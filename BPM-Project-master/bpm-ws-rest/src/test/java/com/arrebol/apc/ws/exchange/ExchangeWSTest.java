/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.exchange;

import com.arrebol.apc.model.ws.parsed.ExchangeJaxb;
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
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@RunWith(ConcurrentRunner.class)
public class ExchangeWSTest extends JerseyTest {

    private static final URI BASE_URI = URI.create("http://localhost:8080/apc-ws-rest/resource");

    public static final String USER_ID_AVATAR_ONE = "67b3081e-8bc9-11ea-b45c-c7b846343364";
    public static final String USER_ID_AVATAR_TWO = "52cbc85a-8bc9-11ea-b45c-c7b846343364";
    public static final String OFFICE_ID_GDL = "e0f1a2fc-7d1f-11ea-af3e-28f659da398e";
    public static final String OFFICE_ID_TPC = "caef3a64-7d1f-11ea-af3e-28f659da398e";
    public static final String TRANSFER_ID ="815c7092-94fe-40a0-bf8e-e4a047300c7c";

    @Override
    protected ResourceConfig configure() {
        enable(TestProperties.LOG_TRAFFIC);
        return new ResourceConfig(ExchangeWS.class);
    }

    //@Test
    public void findAllAvailableEndorsementsByOffice() {

        Response response = target().path("exchange/availableUsers")
                .queryParam("id", USER_ID_AVATAR_TWO)
                .queryParam("office", OFFICE_ID_TPC)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class);

        System.out.println("response: " + response);
        assertEquals(200, response.getStatus());
    }

    //@Test
    public void newExchange() {
        ExchangeJaxb jaxb = new ExchangeJaxb();

        jaxb.setOfficeId(OFFICE_ID_TPC);
        jaxb.setSenderId(USER_ID_AVATAR_ONE);
        jaxb.setReceiverId(USER_ID_AVATAR_TWO);
        jaxb.setAmount(new BigDecimal(1000.0));

        Response response = target().path("exchange/newExchange")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .put(Entity.entity(jaxb, MediaType.APPLICATION_JSON), Response.class);

        System.out.println("response: " + response);
        assertEquals(200, response.getStatus());
    }
    
    @Test
    public void updateExchange() {
       Form form = new Form();

        form.param("transfer", TRANSFER_ID);
        form.param("user", USER_ID_AVATAR_TWO);
        form.param("action", "true");
        

        Response response = target().path("exchange/updateExchange")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.form(form), Response.class);

        System.out.println("response: " + response);
        assertEquals(200, response.getStatus());
    }
}
