/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Enrique Ochoa
 */
@Entity
@Table(name = "ADM_HIS_HISTORIAL_PAGO", catalog = "PROYECTO04_ADMINISTRACION_EMPLEADOS", schema = "")
@NamedQueries({
    @NamedQuery(name = "AdmHisHistorialPago.findAll", query = "SELECT a FROM AdmHisHistorialPago a"),
    @NamedQuery(name = "AdmHisHistorialPago.findByHisId", query = "SELECT a FROM AdmHisHistorialPago a WHERE a.hisId = :hisId"),
    @NamedQuery(name = "AdmHisHistorialPago.findByHisIdEmpleado", query = "SELECT a FROM AdmHisHistorialPago a WHERE a.hisIdEmpleado = :hisIdEmpleado"),
    @NamedQuery(name = "AdmHisHistorialPago.findByHisNombreEmpleado", query = "SELECT a FROM AdmHisHistorialPago a WHERE a.hisNombreEmpleado = :hisNombreEmpleado"),
    @NamedQuery(name = "AdmHisHistorialPago.findByHisApellidoEmpleado", query = "SELECT a FROM AdmHisHistorialPago a WHERE a.hisApellidoEmpleado = :hisApellidoEmpleado"),
    @NamedQuery(name = "AdmHisHistorialPago.findByHisSalario", query = "SELECT a FROM AdmHisHistorialPago a WHERE a.hisSalario = :hisSalario"),
    @NamedQuery(name = "AdmHisHistorialPago.findByHisIsss", query = "SELECT a FROM AdmHisHistorialPago a WHERE a.hisIsss = :hisIsss"),
    @NamedQuery(name = "AdmHisHistorialPago.findByHisAfp", query = "SELECT a FROM AdmHisHistorialPago a WHERE a.hisAfp = :hisAfp"),
    @NamedQuery(name = "AdmHisHistorialPago.findByHisRenta", query = "SELECT a FROM AdmHisHistorialPago a WHERE a.hisRenta = :hisRenta"),
    @NamedQuery(name = "AdmHisHistorialPago.findByHisPago", query = "SELECT a FROM AdmHisHistorialPago a WHERE a.hisPago = :hisPago")})
public class AdmHisHistorialPago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "HIS_ID", nullable = false)
    private Integer hisId;
    @Basic(optional = false)
    @Column(name = "HIS_ID_EMPLEADO", nullable = false)
    private int hisIdEmpleado;
    @Column(name = "HIS_NOMBRE_EMPLEADO", length = 20)
    private String hisNombreEmpleado;
    @Column(name = "HIS_APELLIDO_EMPLEADO", length = 20)
    private String hisApellidoEmpleado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HIS_SALARIO", precision = 22, scale = 0)
    private Double hisSalario;
    @Column(name = "HIS_ISSS", precision = 22, scale = 0)
    private Double hisIsss;
    @Column(name = "HIS_AFP", precision = 22, scale = 0)
    private Double hisAfp;
    @Column(name = "HIS_RENTA", precision = 22, scale = 0)
    private Double hisRenta;
    @Column(name = "HIS_PAGO", precision = 22, scale = 0)
    private Double hisPago;
    @JoinColumn(name = "PLA_ID", referencedColumnName = "PLA_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private AdmPlaPlanilla plaId;

    public AdmHisHistorialPago() {
    }

    public AdmHisHistorialPago(Integer hisId) {
        this.hisId = hisId;
    }

    public AdmHisHistorialPago(Integer hisId, int hisIdEmpleado) {
        this.hisId = hisId;
        this.hisIdEmpleado = hisIdEmpleado;
    }

    public Integer getHisId() {
        return hisId;
    }

    public void setHisId(Integer hisId) {
        this.hisId = hisId;
    }

    public int getHisIdEmpleado() {
        return hisIdEmpleado;
    }

    public void setHisIdEmpleado(int hisIdEmpleado) {
        this.hisIdEmpleado = hisIdEmpleado;
    }

    public String getHisNombreEmpleado() {
        return hisNombreEmpleado;
    }

    public void setHisNombreEmpleado(String hisNombreEmpleado) {
        this.hisNombreEmpleado = hisNombreEmpleado;
    }

    public String getHisApellidoEmpleado() {
        return hisApellidoEmpleado;
    }

    public void setHisApellidoEmpleado(String hisApellidoEmpleado) {
        this.hisApellidoEmpleado = hisApellidoEmpleado;
    }

    public Double getHisSalario() {
        return hisSalario;
    }

    public void setHisSalario(Double hisSalario) {
        this.hisSalario = hisSalario;
    }

    public Double getHisIsss() {
        return hisIsss;
    }

    public void setHisIsss(Double hisIsss) {
        this.hisIsss = hisIsss;
    }

    public Double getHisAfp() {
        return hisAfp;
    }

    public void setHisAfp(Double hisAfp) {
        this.hisAfp = hisAfp;
    }

    public Double getHisRenta() {
        return hisRenta;
    }

    public void setHisRenta(Double hisRenta) {
        this.hisRenta = hisRenta;
    }

    public Double getHisPago() {
        return hisPago;
    }

    public void setHisPago(Double hisPago) {
        this.hisPago = hisPago;
    }

    public AdmPlaPlanilla getPlaId() {
        return plaId;
    }

    public void setPlaId(AdmPlaPlanilla plaId) {
        this.plaId = plaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hisId != null ? hisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmHisHistorialPago)) {
            return false;
        }
        AdmHisHistorialPago other = (AdmHisHistorialPago) object;
        if ((this.hisId == null && other.hisId != null) || (this.hisId != null && !this.hisId.equals(other.hisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.AdmHisHistorialPago[ hisId=" + hisId + " ]";
    }
    
}
