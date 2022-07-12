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
public interface OfficeCfg extends GenericCfg {

    /**
     *
     */
    String QUERY_TUPLE_FIND_ALL_OFFICES_BY_USER = "findAllOfficesByUser";
    String QUERY_FIND_ALL_OFFICES_ACTIVES = "findAllOfficesActives";
    String UPDATE_OFFICES_BY_ID = "updateByOfficeId";

    /**
     * Fields
     */
    String FIELD_PERMISSION = "officeName";
    String FIELD_OFFICE_STATUS = "officeStatus";
    String FIELD_ADDRESS = "address";
    /**
     * Query string
     */
    String QUERY_AS_STRING_TUPLE_FIND_ALL_OFFICES_BY_USER = "SELECT \n"
            + "                o.id,\n"
            + "                o.officeName\n"
            + "            FROM Office o\n"
            + "                INNER JOIN UserByOffice ubo ON o.id = ubo.office\n"
            + "            WHERE\n"
            + "                ubo.user = :user AND\n"
            + "                ubo.userByOfficeStatus = 'ENEBLED' AND\n"
            + "                o.officeStatus = 'ENEBLED'";
}
