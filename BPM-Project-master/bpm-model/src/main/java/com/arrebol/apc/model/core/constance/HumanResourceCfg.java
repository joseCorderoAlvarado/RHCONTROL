/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.core.constance;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public interface HumanResourceCfg extends GenericCfg {

    String QUERY_FIND_ALL_HRS_WITHOUT_USER = "findAllHRWithoutUser";
    String QUERY_FIND_ALL_BY_STATUS = "findAllHByStatus";
    String QUERY_FIND_ALL_IN_STATUS = "findAllHRInStatus";
    String QUERY_FIND_ALL_HR_BY_OFFICE = "findAllHRByOffice";
    String UPDATE_HR_BY_STATUS = "updateHRByStatus";

    String FIELD_FIRST_NAME = "firstName";
    String FIELD_SECOND_NAME = "secondName";
    String FIELD_LAST_NAME = "lastName";
    String FIELD_MIDDLE_NAME = "middleName";
    String FIELD_BIRTHDATE = "birthdate";
    String FIELD_AVATAR = "AVATAR";
    String FIELD_HR_TYPE = "humanResourceType";
    String FIELD_ADMISSION_DATE = "admissionDate";
    String FIELD_HR_STATUS = "humanResourceStatus";
    String FIELD_OFFICE = "office";
    String FIELD_PAYMENT = "payment";
    String FIELD_IMSS = "imss";
    String FIELD_ROLE = "roleCtlg";
    String FIELD_BONUS = "bonus";
    String FIELD_EMPLOYEE_SAVING = "employeeSaving";
}
