/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.service.views;

import com.arrebol.apc.model.views.EnabledUserDetailsView;
import java.util.List;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public interface EnabledUserDetailsViewService {

    /**
     *
     * @param officeId
     * @return
     */
    List<EnabledUserDetailsView> findEnabledUsersByOffice(String officeId);
}
