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
 * @author Oscar Armando Vargas Cardenas <oscar.vargas@arrebol.com.mx>
 */
public interface GoalCfg extends GenericCfg {

    String QUERY_FIND_ALL_GOALS_BY_OFFICE = "findAllGoalsByOffice";
    String QUERY_FIND_ALL_GOALS_BY_OFFICE_BETWEEN_DATES = "findAllGoalsByOfficeBetweenDates";
    String QUERY_UPDATE_GOAL_BY_STATUS = "updateGoalByStatus";

    String FIELD_OFFICE = "office";
    String FIELD_ACTIVE_STATUS = "activeStatus";
}
