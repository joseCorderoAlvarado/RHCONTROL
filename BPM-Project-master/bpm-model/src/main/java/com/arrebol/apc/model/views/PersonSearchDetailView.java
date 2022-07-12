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
@Table(name = "APC_PERSON_SEARCH_DETAIL_VIEW")
public class PersonSearchDetailView implements Serializable {

    private static final long serialVersionUID = 4608137238616026580L;

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "person_search", length = 103)
    private String personSearch;

    @Column(name = "thumbnail", length = 250)
    private String thumbnail;

    @Column(name = "loanStatus", length = 8)
    private String loanStatus;

    public PersonSearchDetailView() {
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    @Override
    public String toString() {
        return "PersonSearchDetailView{" + "personSearch=" + personSearch + '}';
    }

}
