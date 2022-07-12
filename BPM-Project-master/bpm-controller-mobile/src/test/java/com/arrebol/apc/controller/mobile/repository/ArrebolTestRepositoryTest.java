/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository;

import com.arrebol.apc.test.ArrebolTest;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class ArrebolTestRepositoryTest {

    private ArrebolTestRepository repository;

    @Before
    public void setUp() {
        repository = new ArrebolTestRepository();
    }

    @Test
    public void save() {
        try {
            ArrebolTest arrebolTest = new ArrebolTest(new Date(), new Date());

            Assert.assertTrue(repository.saveArrebolTest(arrebolTest));
        } catch (Exception e) {
            Assert.assertFalse(true);
        }
    }
}
