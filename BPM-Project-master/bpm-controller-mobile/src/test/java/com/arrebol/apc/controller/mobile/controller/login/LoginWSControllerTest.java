/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.login;

import com.arrebol.apc.controller.mobile.moxy.login.UserMxy;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class LoginWSControllerTest {

    private LoginWSController controller;
    private final static String USER_NAME = "avatar1";
    private final static String PASSWORD = "8478A4A2819E9C06AB738123C5D04B4FA1AA67548EBA64EAD40B635EA8AA8D5B";

    public LoginWSControllerTest() {
    }

    @Before
    public void setUp() {
        controller = new LoginWSController();
    }

    @Test
    public void login() {
        try {
            UserMxy userMxy = controller.login(USER_NAME, PASSWORD);
            
            System.out.println(userMxy);
            
            assertFalse(userMxy.getPreferences().isEmpty());
        } catch (Exception e) {
            assertFalse(true);
        }
    }

}
