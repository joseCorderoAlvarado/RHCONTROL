package com.arrebol.apc.controller.mobile.controller.preference;

/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
import com.arrebol.apc.model.views.LoanByUserOrderPreferenceView;
import com.arrebol.apc.model.ws.parsed.ConfigurationJaxb;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class SystemPreferenceControllerTest {

    private SystemPreferenceController controller;
    public static final String USER_ID = "52cbc85a-8bc9-11ea-b45c-c7b846343364";

    public SystemPreferenceControllerTest() {
    }

    @Before
    public void setUp() {
        controller = new SystemPreferenceController();
    }

    @Test
    public void findAllLoanByUserOrderPreference() {
        try {
            List<LoanByUserOrderPreferenceView> results = controller.findAllLoanByUserOrderPreference(USER_ID);

            results.forEach(System.out::println);

            assertFalse(results.isEmpty());
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    public void findConfigurationButton() {
        try {
            ConfigurationJaxb result = controller.findConfigurationButton(USER_ID, USER_ID);

            assertTrue(result.isActiveButton());
        } catch (Exception e) {
            assertFalse(true);
        }
    }

}
