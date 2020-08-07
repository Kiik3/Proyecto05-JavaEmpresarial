/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Enrique Ochoa
 */
@Entity
@Table(name = "ADM_PLA_PLANILLA", catalog = "PROYECTO04_ADMINISTRACION_EMPLEADOS", schema = "")
@NamedQueries({
    @NamedQuery(name = "AdmPlaPlanilla.findAll", query = "SELECT a FROM AdmPlaPlanilla a"),
    @NamedQuery(name = "AdmPlaPlanilla.findByPlaId", query = "SELECT a FROM AdmPlaPlanilla a WHERE a.plaId = :plaId"),
    @NamedQuery(name = "AdmPlaPlanilla.findByPlaFecha", query = "SELECT a FROM AdmPlaPlanilla a WHERE a.plaFecha = :plaFecha"),
    @NamedQuery(name = "AdmPlaPlanilla.findByPlaTotalSalario", query = "SELECT a FROM AdmPlaPlanilla a WHERE a.plaTotalSalario = :plaTotalSalario"),
    @NamedQuery(name = "AdmPlaPlanilla.findByPlaTotalDescuento", query = "SELECT a FROM AdmPlaPlanilla a WHERE a.plaTotalDescuento = :plaTotalDescuento"),
    @NamedQuery(name = "AdmPlaPlanilla.findByPlaTotalPago", query = "SELECT a FROM AdmPlaPlanilla a WHERE a.plaTotalPago = :plaTotalPago")})
public class AdmPlaPlanilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PLA_ID", nullable = false)
    private Integer plaId;
    @Basic(optional = false)
    @Column(name = "PLA_FECHA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date plaFecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PLA_TOTAL_SALARIO", precision = 22, scale = 0)
    private Double plaTotalSalario;
    @Column(name = "PLA_TOTAL_DESCUENTO", precision = 22, scale = 0)
    private Double plaTotalDescuento;
    @Column(name = "PLA_TOTAL_PAGO", precision = 22, scale = 0)
    private Double plaTotalPago;
    @OneToMany(mappedBy = "plaId", fetch = FetchType.LAZY)
    private List<AdmHisHistorialPago> admHisHistorialPagoList;

    public AdmPlaPlanilla() {
    }

    public AdmPlaPlanilla(Integer plaId) {
        this.plaId = plaId;
    }

    public AdmPlaPlanilla(Integer plaId, Date plaFecha) {
        this.plaId = plaId;
        this.plaFecha = plaFecha;
    }

    public Integer getPlaId() {
        return plaId;
    }

    public void setPlaId(Integer plaId) {
        this.plaId = plaId;
    }

    public Date getPlaFecha() {
        return plaFecha;
    }

    public void setPlaFecha(Date plaFecha) {
        this.plaFecha = plaFecha;
    }

    public Double getPlaTotalSalario() {
        return plaTotalSalario;
    }

    public void setPlaTotalSalario(Double plaTotalSalario) {
        this.plaTotalSalario = plaTotalSalario;
    }

    public Double getPlaTotalDescuento() {
        return plaTotalDescuento;
    }

    public void setPlaTotalDescuento(Double plaTotalDescuento) {
        this.plaTotalDescuento = plaTotalDescuento;
    }

    public Double getPlaTotalPago() {
        return plaTotalPago;
    }

    public void setPlaTotalPago(Double plaTotalPago) {
        this.plaTotalPago = plaTotalPago;
    }

    public List<AdmHisHistorialPago> getAdmHisHistorialPagoList() {
        return admHisHistorialPagoList;
    }

    public void setAdmHisHistorialPagoList(List<AdmHisHistorialPago> admHisHistorialPagoList) {
        this.admHisHistorialPagoList = admHisHistorialPagoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plaId != null ? plaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmPlaPlanilla)) {
            return false;
        }
        AdmPlaPlanilla other = (AdmPlaPlanilla) object;
        if ((this.plaId == null && other.plaId != null) || (this.plaId != null && !this.plaId.equals(other.plaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.AdmPlaPlanilla[ plaId=" + plaId + " ]";
    }
    
}
