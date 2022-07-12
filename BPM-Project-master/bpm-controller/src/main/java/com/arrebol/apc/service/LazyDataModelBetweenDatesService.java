/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 * @param <T>
 */
public interface LazyDataModelBetweenDatesService<T> {

    /**
     *
     * @param filterBy
     * @param officeId
     * @param starDate
     * @param endDate
     * @return
     */
    long countPaginator(Map<String, Object> filterBy, String officeId, Date starDate, Date endDate);

    /**
     *
     * @param first
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @param filterBy
     * @param officeId
     * @param starDate
     * @param endDate
     * @return
     */
    List<T> lazyEntityListPaginator(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filterBy, String officeId, Date starDate, Date endDate);
}
