/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.exchange;

import com.arrebol.apc.model.views.ExchangeEnebledUsersView;
import com.arrebol.apc.model.ws.parsed.Exchange;
import java.util.List;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class ExchangeEnebledUsersControllerTest {

    public ExchangeEnebledUsersController controller;
    public static final String USER_ID_AVATAR_TWO = "52cbc85a-8bc9-11ea-b45c-c7b846343364";
    public static final String AVATAR= "22fb81e2-8bc9-11ea-b45c-c7b846343364";
    public static final String OFFICE_ID_GDL = "e0f1a2fc-7d1f-11ea-af3e-28f659da398e";
    public static final String OFFICE_ID_TPC = "caef3a64-7d1f-11ea-af3e-28f659da398e";

    public ExchangeEnebledUsersControllerTest() {
    }

    @Before
    public void setUp() {
        controller = new ExchangeEnebledUsersController();
    }

    //@Test
    public void findEnebledUsersToUserId() {
        try {
            List<ExchangeEnebledUsersView> results = controller.findEnebledUsersToUserId(USER_ID_AVATAR_TWO, OFFICE_ID_TPC);

            results.forEach(System.out::println);

            assertFalse(results.isEmpty());
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    public void exchangesByUsers() {
        try {
            List<Exchange> results = controller.exchangesByUsers(AVATAR);

            results.forEach(System.out::println);

            assertFalse(results.isEmpty());
        } catch (Exception e) {
            assertFalse(true);
        }
    }
}
