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
@Table(name = "APC_AVAILABLES_OWNERS_VIEW")
public class AvailablesOwnersView implements Serializable {

    private static final long serialVersionUID = -7094684754461793727L;

    @Id
    @Column(name = "id_user", length = 36)
    private String userId;

    @Column(name = "id_office", length = 36)
    private String officeId;

    @Column(name = "user_name", length = 100)
    private String userName;

    @Column(name = "full_name", length = 103)
    private String fullName;

    public AvailablesOwnersView() {
    }

    /**
     * Complete constructor.
     *
     * @param userId
     * @param officeId
     * @param userName
     * @param fullName
     */
    public AvailablesOwnersView(String userId, String officeId, String userName, String fullName) {
        this.userId = userId;
        this.officeId = officeId;
        this.userName = userName;
        this.fullName = fullName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "AvailablesOwnersView{" + "userName=" + userName + ", fullName=" + fullName + '}';
    }

}
