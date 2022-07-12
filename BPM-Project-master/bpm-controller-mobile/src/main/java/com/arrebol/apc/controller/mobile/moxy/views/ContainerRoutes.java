/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.moxy.views;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@XmlRootElement(name = "containerRoutes")
public class ContainerRoutes {

    private List<RouteJaxb> routes;

    public ContainerRoutes() {
    }

    /**
     *
     * @param routes
     */
    public ContainerRoutes(List<RouteJaxb> routes) {
        this.routes = routes;
    }

    @XmlElement(name = "routes")
    public List<RouteJaxb> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteJaxb> routes) {
        this.routes = routes;
    }

}
