/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.views;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Entity
@Immutable
@Table(name = "APC_PERSON_SEARCH_VIEW")
public class PersonSearchView implements Serializable {

    private static final long serialVersionUID = 8659713229201178299L;

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "person_search", length = 103)
    private String personSearch;

    public PersonSearchView() {
    }

    public PersonSearchView(String id, String personSearch) {
        this.id = id;
        this.personSearch = personSearch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonSearch() {
        return personSearch;
    }

    public void setPersonSearch(String personSearch) {
        this.personSearch = personSearch;
    }

    @Override
    public String toString() {
        return "PersonSearchView{" + "personSearch=" + personSearch + '}';
    }

}
