/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arrebol.apc.security;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Picasso
 */
public class APCSecureTest {
    
    private static final String APP_NAME = "ApoyoAProyectosComerciales";
    private static final String PASSWORD = "12345678";
    private APCSecure apcSecure;
    
    public APCSecureTest() {
    }
    
    @Before
    public void setUp() {
        this.apcSecure = new APCSecure(APP_NAME, PASSWORD);
    }

    @Test
    public void getPassword() {
        try {
            String pwd = apcSecure.getPassword();
            System.out.println(pwd);
            assertNotNull(pwd);
        } catch (Exception e) {
        }
    }
    
}
