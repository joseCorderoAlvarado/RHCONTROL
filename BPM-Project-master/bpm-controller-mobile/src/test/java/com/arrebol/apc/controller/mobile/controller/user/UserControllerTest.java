/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class UserControllerTest {

    /**
     * Avatar 2
     */
    public final static String MOBILE_USER_ID = "52cbc85a-8bc9-11ea-b45c-c7b846343364";

    private UserController controller;

    @Before
    public void setUp() {
        controller = new UserController();
    }

    @Test
    public void isUserEnebled() {
        try {
            assertTrue(controller.isUserEnebled(MOBILE_USER_ID));
        } catch (Exception e) {
            assertFalse(true);
        }
    }
}
