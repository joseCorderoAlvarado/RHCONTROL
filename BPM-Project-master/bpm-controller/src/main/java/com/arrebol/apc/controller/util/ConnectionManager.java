/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.util;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Oscar Armando Vargas Cardenas <oscar.vargas@arrebol.com.mx>
 */
public class ConnectionManager {
    
     /**
     *
     * @param session
     */
    protected void closeSession(Session session) {
        if (null != session) {
            session.close();
        }
    }

    /**
     *
     * @param transaction
     */
    protected void rollback(Transaction transaction) {
        if (null != transaction) {
            transaction.rollback();
        }
    }

    public static final String USER_ID_MANAGER_APPLICATION = "User-Id-Manager-Application";
}
