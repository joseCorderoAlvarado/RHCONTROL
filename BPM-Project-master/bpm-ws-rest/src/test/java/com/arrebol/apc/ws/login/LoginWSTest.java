/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.login;

import java.net.URI;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.util.runner.ConcurrentRunner;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@RunWith(ConcurrentRunner.class)
public class LoginWSTest extends JerseyTest {

    private static final URI BASE_URI = URI.create("http://localhost:8080/apc-ws-rest/resource");
    private static final String USER_NAME = "avatar1";
    private static final String PASSWORD = "8478A4A2819E9C06AB738123C5D04B4FA1AA67548EBA64EAD40B635EA8AA8D5B";

    public LoginWSTest() {
    }

    @Override
    protected ResourceConfig configure() {
        enable(TestProperties.LOG_TRAFFIC);
        return new ResourceConfig(LoginWS.class);
    }

    @Test
    public void login() {
        Form form = new Form();

        form.param("userName", USER_NAME);
        form.param("token", PASSWORD);

        Response response = target().path("login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.form(form), Response.class);

        System.out.println("response: " + response);
        assertEquals(200, response.getStatus());
    }

}
