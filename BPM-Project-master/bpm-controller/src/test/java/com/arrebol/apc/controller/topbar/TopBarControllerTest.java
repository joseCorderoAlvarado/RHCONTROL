/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.topbar;

import com.arrebol.apc.model.core.Office;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class TopBarControllerTest {

    private TopBarController controller;
    private final String USER_ID = "5751074e-7d1b-11ea-af3e-28f659da398e";
    private final String OFFICE_ID = "e0f1a2fc-7d1f-11ea-af3e-28f659da398e";

    public TopBarControllerTest() {
    }

    @Before
    public void init() {
        controller = new TopBarController();
    }

    @Test
    public void findAllOfficesByUser() {
        try {
            List<Office> offices = controller.findAllOfficesByUserController(USER_ID);

            offices.forEach(System.out::println);

            assertFalse(offices.isEmpty());
        } catch (Exception e) {
            assertFalse(true);
        }
    }
}
