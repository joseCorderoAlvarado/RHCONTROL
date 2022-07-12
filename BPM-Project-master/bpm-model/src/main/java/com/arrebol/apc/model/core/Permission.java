/*
 * Arrebol Consultancy copyright
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.core;

import com.arrebol.apc.model.enums.PermissionStatus;
import com.arrebol.apc.model.enums.PermissionType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Entity
@Table(name = "APC_PERMISSION",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"permission"})
        }
)
public class Permission implements Serializable {

    private static final long serialVersionUID = -4259020491787578065L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "permission", length = 200, nullable = false)
    private String permission;

    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @Column(name = "menu_path", length = 200, nullable = false)
    private String menuPath;

    @Column(name = "left_to_right_order", nullable = false)
    private Integer leftToRightOrder;

    @Column(name = "top_to_bottom_order", nullable = false)
    private Integer topToBottomOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "permission_type", nullable = false)
    private PermissionType permissionType;

    @Column(name = "parent_name", length = 200)
    private String parentName;

    @Enumerated(EnumType.STRING)
    @Column(name = "permission_status", nullable = false)
    private PermissionStatus permissionStatus;

    @Column(name = "created_by", nullable = false, length = 36)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    private Date createdOn;

    @Column(name = "last_updated_by", length = 36)
    private String lastUpdatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_on", length = 19)
    private Date lastUpdatedOn;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "permission",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<UserByOfficeHasPermission> userByOfficeHasPermissions;

    public Permission() {
    }

    /**
     *
     * @param id
     * @param permission
     * @param description
     * @param menuPath
     */
    public Permission(String id, String permission, String description, String menuPath) {
        this.id = id;
        this.permission = permission;
        this.description = description;
        this.menuPath = menuPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public Integer getLeftToRightOrder() {
        return leftToRightOrder;
    }

    public void setLeftToRightOrder(Integer leftToRightOrder) {
        this.leftToRightOrder = leftToRightOrder;
    }

    public Integer getTopToBottomOrder() {
        return topToBottomOrder;
    }

    public void setTopToBottomOrder(Integer topToBottomOrder) {
        this.topToBottomOrder = topToBottomOrder;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public PermissionStatus getPermissionStatus() {
        return permissionStatus;
    }

    public void setPermissionStatus(PermissionStatus permissionStatus) {
        this.permissionStatus = permissionStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public List<UserByOfficeHasPermission> getUserByOfficeHasPermissions() {
        return userByOfficeHasPermissions;
    }

    public void setUserByOfficeHasPermissions(List<UserByOfficeHasPermission> userByOfficeHasPermissions) {
        this.userByOfficeHasPermissions = userByOfficeHasPermissions;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Permission other = (Permission) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Permission{" + "description=" + description + '}';
    }

}
