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
public interface TransferCfg extends GenericCfg {

    String QUERY_FIND_ALL_TRANFERS_BY_OFFICE = "findAllTransferByOffice";
    String QUERY_FIND_ALL_TRANFERS_BY_OFFICE_BETWEEN_DATES = "findAllTransferByOfficeBetweenDates";
    String QUERY_FIND_ALL_TRANFERS_BY_USER_TRANSMITTER = "findAllTransferByUserTransmitter";
    String QUERY_FIND_ALL_TRANFERS_BY_USER_RECEIVER = "findAllTransferByUserReceiver";
    String QUERY_UPDATE_TRANSFER_BY_ACTION = "updateTransferByAction";
    String QUERY_UPDATE_TRANSFER_BY_ACTIVE_STATUS = "updateTransferByActive";
    String QUERY_FIND_ALL_TRANSFER_BY_USER_ID_AND_CURDATE = "findAllTransferByUserIdAndCurdate";

    String FIELD_USER_TRANSMITTER = "userTransmitter";
    String FIELD_USER_RECEIVER = "userReceiver";
    String FIELD_OFFICE = "office";
    String FIELD_ACTION_STATUS = "actionStatus";
    String FIELD_ACTIVE_STATUS = "activeStatus";
}
