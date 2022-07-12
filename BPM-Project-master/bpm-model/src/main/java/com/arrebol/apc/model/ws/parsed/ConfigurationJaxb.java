/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.ws.parsed;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@XmlRootElement(name = "configuration")
public class ConfigurationJaxb implements Serializable {

    private static final long serialVersionUID = -9152601421411768287L;

    private boolean activeButton;

    public ConfigurationJaxb() {
    }

    public ConfigurationJaxb(boolean activeButton) {
        this.activeButton = activeButton;
    }

    public boolean isActiveButton() {
        return activeButton;
    }

    public void setActiveButton(boolean activeButton) {
        this.activeButton = activeButton;
    }

}
