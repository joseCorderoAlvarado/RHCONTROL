/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.login;

import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.User;
import com.arrebol.apc.model.core.UserByOffice;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class LoginControllerTest {

    private LoginController controller;
    private UserByOffice ubo;
    private String USER_DIRECTOR = "direccion";
    private String CEO = "ejecutivo";
    private String OFFICE_TEPIC = "caef3a64-7d1f-11ea-af3e-28f659da398e";
    private String OFFICE_GDL = "e0f1a2fc-7d1f-11ea-af3e-28f659da398e";

    public LoginControllerTest() {
    }

    @Before
    public void init() {
        User user = new User("", CEO);
        Office office = new Office(OFFICE_GDL);
        ubo = new UserByOffice(user, office);
        controller = new LoginController();
    }

    @Test
    public void getAllActiveOffice() {
        try {
            List<Office> offices = controller.getAllActiveOfficeController();

            assertFalse(offices.isEmpty());
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    public void findUserLogged() {
        try {
            assertNotNull(controller.findUserLoggedController(ubo));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

}
