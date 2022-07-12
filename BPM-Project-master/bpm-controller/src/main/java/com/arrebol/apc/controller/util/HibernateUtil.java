/*
 * Arrebol Consultancy copyright
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class HibernateUtil {

    private static final String HIBERNATE_CFG_XML = "apc.cfg.xml";
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    // Hibernate 5:
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the ServiceRegistry from hibernate.cfg.xml
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure(HIBERNATE_CFG_XML).build();

            // Create a metadata sources using the specified service registry.
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Configuration object is used to create a SessionFactory object which in
     * turn configures Hibernate for the application using the supplied
     * configuration file and allows for a Session object to be instantiated.
     *
     * The SessionFactory is a thread safe object and used by all the threads of
     * an application.
     *
     * The SessionFactory is a heavyweight object; it is usually created during
     * application start up and kept for later use.
     *
     * You would need one SessionFactory object per database using a separate
     * configuration file.
     *
     * So, if you are using multiple databases, then you would have to create
     * multiple SessionFactory objects.
     *
     * @return SessionFactory.
     */
    public final static SessionFactory getSessionFactory() {
        if (null == sessionFactory) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

}
