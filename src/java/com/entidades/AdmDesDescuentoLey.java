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
@Table(name = "ADM_DES_DESCUENTO_LEY", catalog = "PROYECTO04_ADMINISTRACION_EMPLEADOS", schema = "")
@NamedQueries({
    @NamedQuery(name = "AdmDesDescuentoLey.findAll", query = "SELECT a FROM AdmDesDescuentoLey a"),
    @NamedQuery(name = "AdmDesDescuentoLey.findByDesId", query = "SELECT a FROM AdmDesDescuentoLey a WHERE a.desId = :desId"),
    @NamedQuery(name = "AdmDesDescuentoLey.findByDesNombre", query = "SELECT a FROM AdmDesDescuentoLey a WHERE a.desNombre = :desNombre"),
    @NamedQuery(name = "AdmDesDescuentoLey.findByDesPorcentaje", query = "SELECT a FROM AdmDesDescuentoLey a WHERE a.desPorcentaje = :desPorcentaje")})
public class AdmDesDescuentoLey implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DES_ID", nullable = false)
    private Integer desId;
    @Column(name = "DES_NOMBRE", length = 20)
    private String desNombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DES_PORCENTAJE", precision = 22, scale = 0)
    private Double desPorcentaje;

    public AdmDesDescuentoLey() {
    }

    public AdmDesDescuentoLey(Integer desId) {
        this.desId = desId;
    }

    public Integer getDesId() {
        return desId;
    }

    public void setDesId(Integer desId) {
        this.desId = desId;
    }

    public String getDesNombre() {
        return desNombre;
    }

    public void setDesNombre(String desNombre) {
        this.desNombre = desNombre;
    }

    public Double getDesPorcentaje() {
        return desPorcentaje;
    }

    public void setDesPorcentaje(Double desPorcentaje) {
        this.desPorcentaje = desPorcentaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (desId != null ? desId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmDesDescuentoLey)) {
            return false;
        }
        AdmDesDescuentoLey other = (AdmDesDescuentoLey) object;
        if ((this.desId == null && other.desId != null) || (this.desId != null && !this.desId.equals(other.desId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.AdmDesDescuentoLey[ desId=" + desId + " ]";
    }
    
}
