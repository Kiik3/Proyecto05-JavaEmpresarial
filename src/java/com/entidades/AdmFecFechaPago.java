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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Enrique Ochoa
 */
@Entity
@Table(name = "ADM_FEC_FECHA_PAGO", catalog = "PROYECTO04_ADMINISTRACION_EMPLEADOS", schema = "")
@NamedQueries({
    @NamedQuery(name = "AdmFecFechaPago.findAll", query = "SELECT a FROM AdmFecFechaPago a"),
    @NamedQuery(name = "AdmFecFechaPago.findByFecId", query = "SELECT a FROM AdmFecFechaPago a WHERE a.fecId = :fecId"),
    @NamedQuery(name = "AdmFecFechaPago.findByFecPagar", query = "SELECT a FROM AdmFecFechaPago a WHERE a.fecPagar = :fecPagar"),
    @NamedQuery(name = "AdmFecFechaPago.findByFecFecha", query = "SELECT a FROM AdmFecFechaPago a WHERE a.fecFecha = :fecFecha")})
public class AdmFecFechaPago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FEC_ID", nullable = false)
    private Integer fecId;
    @Column(name = "FEC_PAGAR", length = 10)
    private String fecPagar;
    @Column(name = "FEC_FECHA")
    private Integer fecFecha;

    public AdmFecFechaPago() {
    }

    public AdmFecFechaPago(Integer fecId) {
        this.fecId = fecId;
    }

    public Integer getFecId() {
        return fecId;
    }

    public void setFecId(Integer fecId) {
        this.fecId = fecId;
    }

    public String getFecPagar() {
        return fecPagar;
    }

    public void setFecPagar(String fecPagar) {
        this.fecPagar = fecPagar;
    }

    public Integer getFecFecha() {
        return fecFecha;
    }

    public void setFecFecha(Integer fecFecha) {
        this.fecFecha = fecFecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecId != null ? fecId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmFecFechaPago)) {
            return false;
        }
        AdmFecFechaPago other = (AdmFecFechaPago) object;
        if ((this.fecId == null && other.fecId != null) || (this.fecId != null && !this.fecId.equals(other.fecId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.AdmFecFechaPago[ fecId=" + fecId + " ]";
    }
    
}
