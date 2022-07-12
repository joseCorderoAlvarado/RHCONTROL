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
@Table(name = "APC_AVAILABLE_ENDORSEMENTS_VIEW")
public class AvailableEndorsementsView implements Serializable {

    private static final long serialVersionUID = -8420465639155493835L;

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "available_person", length = 103)
    private String availablePerson;

    @Column(name = "cross_signature", length = 36)
    private String crossSignature;

    public AvailableEndorsementsView() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvailablePerson() {
        return availablePerson;
    }

    public void setAvailablePerson(String availablePerson) {
        this.availablePerson = availablePerson;
    }

    public String getCrossSignature() {
        return crossSignature;
    }

    public void setCrossSignature(String crossSignature) {
        this.crossSignature = crossSignature;
    }

    @Override
    public String toString() {
        return "AvailableEndorsementsView{" + "availablePerson=" + availablePerson + '}';
    }

}
