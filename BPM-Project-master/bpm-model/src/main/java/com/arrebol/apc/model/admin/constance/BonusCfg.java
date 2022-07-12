/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.admin.constance;

import com.arrebol.apc.model.core.constance.GenericCfg;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public interface BonusCfg extends GenericCfg {

    String QUERY_FIND_ALL_ACTIVE_BONUS = "findAllActiveBonus";
    String QUERY_FIND_ALL_BONUS = "findAllBonus";
    String QUERY_FIND_ALL_BONUS_BETWEEN_DATES = "findAllBonusBetweenDates";
    String QUERY_UPDATE_BONUS_BY_STATUS = "updateBonusByStatus";
   
    String FIELD_ACTIVE_STATUS = "activeStatus";
    String FIELD_OFFICE = "office";
}
