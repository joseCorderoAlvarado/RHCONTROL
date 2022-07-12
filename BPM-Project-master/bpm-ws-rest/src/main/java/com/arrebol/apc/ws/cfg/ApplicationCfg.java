/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.cfg;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@ApplicationPath("/resource")
public class ApplicationCfg extends ResourceConfig {

    public ApplicationCfg() {
        packages(buildPackages())
                .register(MoxyJsonFeature.class)
                .register(JsonMoxyConfigurationContextResolver.class)
                .register(MultiPartFeature.class);
    }

    private String buildPackages() {
        StringBuilder builder = new StringBuilder();

        builder.append("org.glassfish.jersey.examples.multipart;");
        builder.append("com.arrebol.apc.ws.login;");
        builder.append("com.arrebol.apc.ws.preferences;");
        builder.append("com.arrebol.apc.ws.customer;");
        builder.append("com.arrebol.apc.ws.search;");
        builder.append("com.arrebol.apc.ws.loan;");
        builder.append("com.arrebol.apc.ws.cash;");
        builder.append("com.arrebol.apc.ws.exchange;");
        builder.append("com.arrebol.apc.ws.otherexpense;");
        builder.append("com.arrebol.apc.ws.reports;");
        builder.append("com.arrebol.apc.ws.gasoline;");
        builder.append("com.arrebol.apc.ws.person;");
        builder.append("com.arrebol.apc.ws.route;");

        return builder.toString();
    }
}
