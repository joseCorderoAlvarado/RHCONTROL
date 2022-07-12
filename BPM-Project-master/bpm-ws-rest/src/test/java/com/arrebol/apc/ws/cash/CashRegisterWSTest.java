/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.cash;

import java.net.URI;
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
public class CashRegisterWSTest extends JerseyTest {

    private static final URI BASE_URI = URI.create("http://localhost:8080/apc-ws-rest/resource");
    public static final String USER_ID = "67b3081e-8bc9-11ea-b45c-c7b846343364";

    @Override
    protected ResourceConfig configure() {
        enable(TestProperties.LOG_TRAFFIC);
        return new ResourceConfig(CashRegisterWS.class);
    }

    @Test
    public void findAllLoanByUserOrderPreference() {
        Response response = target().path("cashRegister/findAllBy")
                .queryParam("id", USER_ID)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class);

        System.out.println("response: " + response);
        assertEquals(200, response.getStatus());
    }
}
