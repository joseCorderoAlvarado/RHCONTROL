/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.web.beans.topbar;

import com.arrebol.apc.controller.topbar.TopBarController;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.web.beans.GenericBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@ManagedBean
@ViewScoped
public class TopBarBean extends GenericBean implements Serializable {

    private static final long serialVersionUID = -410735400441974978L;
    final Logger logger = LogManager.getLogger(TopBarBean.class);

    private TopBarController controller;
    private List<Office> offices;

    @PostConstruct
    public void init() {
        try {
            loadBundlePropertyFile();

            setController(new TopBarController());
            //setLoggedUser(getController().findUserLogged(userByOffice));
            setOffices(getController().findAllOfficesByUserController(getLoggedUser().getUser().getId()));
        } catch (Exception e) {
            logger.error("Topbar init", e);
        }
    }

    public TopBarController getController() {
        return controller;
    }

    public void setController(TopBarController controller) {
        this.controller = controller;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

}
