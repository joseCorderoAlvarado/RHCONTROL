/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.service;

import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 * @param <T>
 */
public interface LazyDataModelService<T> {

    /**
     *
     * @param filterBy
     * @return
     */
    long countPaginator(Map<String, Object> filterBy);

    /**
     *
     * @param first
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @param filterBy
     * @return
     */
    List<T> lazyEntityListPaginator(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filterBy);
}
