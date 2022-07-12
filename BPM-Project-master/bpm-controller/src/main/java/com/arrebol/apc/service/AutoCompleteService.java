/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.service;

import java.util.List;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 * @param <T>
 */
public interface AutoCompleteService<T> {

    /**
     *
     * @param valueToSearch
     * @return
     */
    public List<T> queryLike(String valueToSearch);
}
