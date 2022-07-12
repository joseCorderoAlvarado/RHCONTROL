/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.customer;

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
public class CustomerWSTest extends JerseyTest {

    private static final URI BASE_URI = URI.create("http://localhost:8080/apc-ws-rest/resource");
    public static final String USER_ID = "67b3081e-8bc9-11ea-b45c-c7b846343364";

    public CustomerWSTest() {
    }

    @Override
    protected ResourceConfig configure() {
        enable(TestProperties.LOG_TRAFFIC);
        return new ResourceConfig(CustomerWS.class);
    }

    @Test
    public void findAllLoanByUserOrderPreference() {
        Form form = new Form();

        form.param("orderList", "ALPHABETICALLY");
        //form.param("orderList", "ORDER_IN_LIST");
        form.param("userId", USER_ID);

        Response response = target().path("/customer/list")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.form(form), Response.class);

        System.out.println("response: " + response);
        assertEquals(200, response.getStatus());
    }

}
