/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.util;

import java.util.Map;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class FilterMap {

    static final String GLOBAL_FILTER = "globalFilter";

    /**
     *
     * @param map
     * @param hasWhere
     * @return
     */
    public static StringBuilder genericFilterByMapping(Map<String, Object> map, boolean hasWhere) {
        StringBuilder sqlQuery = new StringBuilder();
        if (null != map) {
            for (Map.Entry<String, Object> mapping : map.entrySet()) {
                if (hasWhere) {
                    sqlQuery.append(" AND ");
                    sqlQuery.append(mapping.getKey());
                    sqlQuery.append(" LIKE '%");
                    sqlQuery.append(mapping.getValue());
                    sqlQuery.append("%'  ");
                } else {
                    sqlQuery.append(" WHERE ");
                    sqlQuery.append(mapping.getKey());
                    sqlQuery.append(" LIKE '%");
                    sqlQuery.append(mapping.getValue());
                    sqlQuery.append("%'  ");
                    hasWhere = true;
                }
            }
        }
        return sqlQuery;
    }

    /**
     *
     * @param filterBy
     * @param hasWhere True is query contains where clause otherwise false
     * @return
     */
    public static StringBuilder filterByExcludeGlobalFilter(Map<String, Object> filterBy, boolean hasWhere) {
        StringBuilder sqlQuery = new StringBuilder();
        if (null != filterBy) {
            for (Map.Entry<String, Object> mapping : filterBy.entrySet()) {
                if (!GLOBAL_FILTER.equals(mapping.getKey())) {
                    if (hasWhere) {
                        sqlQuery.append(" AND ");
                        sqlQuery.append(mapping.getKey());
                        sqlQuery.append(" LIKE '%");
                        sqlQuery.append(mapping.getValue().toString());
                        sqlQuery.append("%'  ");
                    } else {
                        sqlQuery.append(" WHERE ");
                        sqlQuery.append(mapping.getKey());
                        sqlQuery.append(" LIKE '%");
                        sqlQuery.append(mapping.getValue().toString());
                        sqlQuery.append("%'  ");
                        hasWhere = true;
                    }
                }
            }
        }
        return sqlQuery;
    }
}
