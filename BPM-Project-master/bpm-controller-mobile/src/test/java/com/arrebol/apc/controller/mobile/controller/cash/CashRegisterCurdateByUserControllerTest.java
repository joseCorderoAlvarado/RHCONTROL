/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.cash;

import com.arrebol.apc.model.views.TotalCashByCurdateView;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class CashRegisterCurdateByUserControllerTest {

    public CashRegisterCurdateByUserController controller;
    public static final String USER_ID = "52cbc85a-8bc9-11ea-b45c-c7b846343364";

    public CashRegisterCurdateByUserControllerTest() {
    }

    @Before
    public void setUp() {
        controller = new CashRegisterCurdateByUserController();
    }

    @Test
    public void findAllLoansByUserId() {
        try {
            TotalCashByCurdateView result = controller.findDailyTotalsByUserId(USER_ID);

            System.out.println("Total: " + result.getTotal());

            Assert.assertNotNull(result);
        } catch (Exception e) {
            assertFalse(true);
        }
    }

}
