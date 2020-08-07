/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
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

/**
 *
 * @author Enrique Ochoa
 */
@Entity
@Table(name = "ADM_EST_ESTADO", catalog = "PROYECTO04_ADMINISTRACION_EMPLEADOS", schema = "")
@NamedQueries({
    @NamedQuery(name = "AdmEstEstado.findAll", query = "SELECT a FROM AdmEstEstado a"),
    @NamedQuery(name = "AdmEstEstado.findByEstId", query = "SELECT a FROM AdmEstEstado a WHERE a.estId = :estId"),
    @NamedQuery(name = "AdmEstEstado.findByEstNombre", query = "SELECT a FROM AdmEstEstado a WHERE a.estNombre = :estNombre"),
    @NamedQuery(name = "AdmEstEstado.findByEstDescripcion", query = "SELECT a FROM AdmEstEstado a WHERE a.estDescripcion = :estDescripcion")})
public class AdmEstEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EST_ID", nullable = false)
    private Integer estId;
    @Basic(optional = false)
    @Column(name = "EST_NOMBRE", nullable = false, length = 20)
    private String estNombre;
    @Column(name = "EST_DESCRIPCION", length = 100)
    private String estDescripcion;
    @OneToMany(mappedBy = "estId", fetch = FetchType.LAZY)
    private List<AdmEmpEmpleado> admEmpEmpleadoList;

    public AdmEstEstado() {
    }

    public AdmEstEstado(Integer estId) {
        this.estId = estId;
    }

    public AdmEstEstado(Integer estId, String estNombre) {
        this.estId = estId;
        this.estNombre = estNombre;
    }

    public Integer getEstId() {
        return estId;
    }

    public void setEstId(Integer estId) {
        this.estId = estId;
    }

    public String getEstNombre() {
        return estNombre;
    }

    public void setEstNombre(String estNombre) {
        this.estNombre = estNombre;
    }

    public String getEstDescripcion() {
        return estDescripcion;
    }

    public void setEstDescripcion(String estDescripcion) {
        this.estDescripcion = estDescripcion;
    }

    public List<AdmEmpEmpleado> getAdmEmpEmpleadoList() {
        return admEmpEmpleadoList;
    }

    public void setAdmEmpEmpleadoList(List<AdmEmpEmpleado> admEmpEmpleadoList) {
        this.admEmpEmpleadoList = admEmpEmpleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estId != null ? estId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmEstEstado)) {
            return false;
        }
        AdmEstEstado other = (AdmEstEstado) object;
        if ((this.estId == null && other.estId != null) || (this.estId != null && !this.estId.equals(other.estId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.AdmEstEstado[ estId=" + estId + " ]";
    }
    
}
