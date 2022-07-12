/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.preferences;

import com.arrebol.apc.controller.mobile.moxy.views.LoanByUserOrderPreferenceViewJaxb;
import com.arrebol.apc.controller.mobile.moxy.views.LoanByUserOrderPreferenceViewListJaxb;
import com.arrebol.apc.model.enums.PreferenceName;
import com.arrebol.apc.model.enums.PreferenceValue;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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
public class SystemPreferenceWSTest extends JerseyTest {

    private static final URI BASE_URI = URI.create("http://localhost:8080/apc-ws-rest/resource");
    public static final String USER_ID = "52cbc85a-8bc9-11ea-b45c-c7b846343364";
    public static final String USER_ID_2 = "67b3081e-8bc9-11ea-b45c-c7b846343364";
    public static final String USER_ID_3 = "3870767c-8bc9-11ea-b45c-c7b846343364";

    public SystemPreferenceWSTest() {
    }

    @Override
    protected ResourceConfig configure() {
        enable(TestProperties.LOG_TRAFFIC);
        return new ResourceConfig(SystemPreferenceWS.class);
    }

    //Test
    public void findAllLoanByUserOrderPreference() {
        Form form = new Form();

        form.param("userId", USER_ID);

        Response response = target().path("/systemPreference/findAllLoanByUserOrderPreference")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.form(form), Response.class);

        System.out.println("response: " + response);
        assertEquals(200, response.getStatus());
    }

    //@Test
    public void updateOrderInList() {
        List<LoanByUserOrderPreferenceViewJaxb> orderPreference = new ArrayList<>();

        orderPreference.add(
                new LoanByUserOrderPreferenceViewJaxb(
                        "5c925364-8e1b-11ea-b65e-4e1376171215",
                        "3870767c-8bc9-11ea-b45c-c7b846343364",
                        1
                )
        );

        LoanByUserOrderPreferenceViewListJaxb updateOrderListPreference = new LoanByUserOrderPreferenceViewListJaxb(orderPreference);

        Response response = target().path("/systemPreference/updateOrderInList")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .put(Entity.entity(updateOrderListPreference, MediaType.APPLICATION_JSON), Response.class);

        System.out.println("response: " + response);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void updateUserMobilePreference() {
        Form form = new Form();

        form.param("userId", USER_ID_3);
        form.param("preferenceName", PreferenceName.ORDER_LIST.name());
        form.param("preferenceValue", PreferenceValue.ALPHABETICALLY.name());

        Response response = target().path("/systemPreference/updateUserMobilePreference")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.form(form), Response.class);

        System.out.println("response: " + response);
        assertEquals(200, response.getStatus());
    }

}
