/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.views;

import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.User;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author Oscar Armando Vargas Cardenas <oscar.vargas@arrebol.com.mx>
 */
@Entity
@Immutable
@Table(name = "APC_ADVANCE_USER_DAILY_DASHBOARD_VIEW")
public class AdvanceUserDailyView implements Serializable{

    private static final long serialVersionUID = 3055231364366461069L;
    
    @Id
    @Column(name = "id", length = 36)
    private String id;
    
    @Column(name = "user_name")
    private String userName;
    
    @Column(name = "total_expected")
    private Integer totalExpected;
    
    @Column(name = "total_now")
    private Integer totalNow;
    
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
    
    @Column(name = "faltante")
    private BigDecimal faltante;
    
    @Column(name = "total_expected_week")
    private BigDecimal totalExpectedWeek;
    
    @Column(name = "total_reported_week")
    private BigDecimal totalReportedWeek;
    
    @Column(name = "total_reported_renovation_week")
    private BigDecimal totalReportedRenovationWeek;
    
    @Column(name = "total_comision_fee")
    private BigDecimal totalComisionFee;
    
    @Column(name = "colocation_approved")
    private BigDecimal colocationApproved;
    
    @Column(name = "colocation_to_delivery")
    private BigDecimal colocationToDelivery;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_user",
            referencedColumnName = "id",
            nullable = false
    )
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_office",
            referencedColumnName = "id",
            nullable = false
    )
    private Office office;
    
    @Transient
    private BigDecimal diferencePaymentWeek;
    
    @Transient
    private BigDecimal porcentajePaymentWeek;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTotalExpected() {
        return totalExpected;
    }

    public void setTotalExpected(Integer totalExpected) {
        this.totalExpected = totalExpected;
    }

    public Integer getTotalNow() {
        return totalNow;
    }

    public void setTotalNow(Integer totalNow) {
        this.totalNow = totalNow;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public BigDecimal getTotalExpectedWeek() {
        return totalExpectedWeek;
    }

    public void setTotalExpectedWeek(BigDecimal totalExpectedWeek) {
        this.totalExpectedWeek = totalExpectedWeek;
    }

    public BigDecimal getTotalReportedWeek() {
        return totalReportedWeek.add(totalReportedRenovationWeek).add(totalComisionFee);
    }

    public void setTotalReportedWeek(BigDecimal totalReportedWeek) {
        this.totalReportedWeek = totalReportedWeek;
    }

    public BigDecimal getColocationApproved() {
        return colocationApproved;
    }

    public void setColocationApproved(BigDecimal colocationApproved) {
        this.colocationApproved = colocationApproved;
    }

    public BigDecimal getColocationToDelivery() {
        return colocationToDelivery;
    }

    public void setColocationToDelivery(BigDecimal colocationToDelivery) {
        this.colocationToDelivery = colocationToDelivery;
    }

    public BigDecimal getDiferencePaymentWeek() {
        diferencePaymentWeek = (totalReportedWeek.add(totalReportedRenovationWeek).add(totalComisionFee)).subtract(totalExpectedWeek);
        return diferencePaymentWeek;
    }

    public void setDiferencePaymentWeek(BigDecimal diferencePaymentWeek) {
        this.diferencePaymentWeek = diferencePaymentWeek;
    }

    public BigDecimal getPorcentajePaymentWeek() {
        if(totalReportedWeek.compareTo(BigDecimal.ZERO) == 1)
            porcentajePaymentWeek = (faltante
                    .multiply(new BigDecimal(100))).divide(totalReportedWeek, 2, RoundingMode.HALF_UP);
        else
            porcentajePaymentWeek = BigDecimal.ZERO;
        return porcentajePaymentWeek;
    }

    public void setPorcentajePaymentWeek(BigDecimal porcentajePaymentWeek) {
        this.porcentajePaymentWeek = porcentajePaymentWeek;
    }

    public BigDecimal getTotalReportedRenovationWeek() {
        return totalReportedRenovationWeek;
    }

    public void setTotalReportedRenovationWeek(BigDecimal totalReportedRenovationWeek) {
        this.totalReportedRenovationWeek = totalReportedRenovationWeek;
    }

    public BigDecimal getTotalComisionFee() {
        return totalComisionFee;
    }

    public void setTotalComisionFee(BigDecimal totalComisionFee) {
        this.totalComisionFee = totalComisionFee;
    }

    public BigDecimal getFaltante() {
        return faltante;
    }

    public void setFaltante(BigDecimal faltante) {
        this.faltante = faltante;
    }
  
}
