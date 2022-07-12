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
@Table(name = "APC_EXCHANGE_ENEBLED_USERS_VIEW")
public class ExchangeEnebledUsersView implements Serializable {

    private static final long serialVersionUID = -1257291916093653466L;

    @Id
    @Column(name = "id_user", length = 36)
    private String userId;

    @Column(name = "user_name", length = 51)
    private String userName;

    @Column(name = "id_office", length = 36)
    private String officeId;

    public ExchangeEnebledUsersView() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    @Override
    public String toString() {
        return "ExchangeEnebledUsersView{" + "userName=" + userName + '}';
    }

}
