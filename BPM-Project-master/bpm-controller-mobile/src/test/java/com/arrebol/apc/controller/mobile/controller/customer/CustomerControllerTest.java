package com.arrebol.apc.controller.mobile.controller.customer;

/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */


import com.arrebol.apc.model.enums.PreferenceValue;
import com.arrebol.apc.model.views.LoanByUserView;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class CustomerControllerTest {

    public CustomerController controller;
    public static final String USER_ID = "52cbc85a-8bc9-11ea-b45c-c7b846343364";

    public CustomerControllerTest() {
    }

    @Before
    public void setUp() {
        controller = new CustomerController();
    }

    @Test
    public void findAllLoansByUserId() {
        try {
            List<LoanByUserView> results = controller.findAllLoansByUserId(PreferenceValue.ORDER_IN_LIST.toString(),USER_ID);

            results.forEach(System.out::println);

            assertFalse(results.isEmpty());
        } catch (Exception e) {
            assertFalse(true);
        }
    }

}
