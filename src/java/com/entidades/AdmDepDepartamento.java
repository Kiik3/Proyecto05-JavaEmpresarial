/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "ADM_DEP_DEPARTAMENTO", catalog = "PROYECTO04_ADMINISTRACION_EMPLEADOS", schema = "")
@NamedQueries({
    @NamedQuery(name = "AdmDepDepartamento.findAll", query = "SELECT a FROM AdmDepDepartamento a"),
    @NamedQuery(name = "AdmDepDepartamento.findByDepId", query = "SELECT a FROM AdmDepDepartamento a WHERE a.depId = :depId"),
    @NamedQuery(name = "AdmDepDepartamento.findByDepNombre", query = "SELECT a FROM AdmDepDepartamento a WHERE a.depNombre = :depNombre")})
public class AdmDepDepartamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DEP_ID", nullable = false)
    private Integer depId;
    @Column(name = "DEP_NOMBRE", length = 20)
    private String depNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admDepDepartamento", fetch = FetchType.LAZY)
    private List<AdmPuePuestoTrabajo> admPuePuestoTrabajoList;

    public AdmDepDepartamento() {
    }

    public AdmDepDepartamento(Integer depId) {
        this.depId = depId;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getDepNombre() {
        return depNombre;
    }

    public void setDepNombre(String depNombre) {
        this.depNombre = depNombre;
    }

    public List<AdmPuePuestoTrabajo> getAdmPuePuestoTrabajoList() {
        return admPuePuestoTrabajoList;
    }

    public void setAdmPuePuestoTrabajoList(List<AdmPuePuestoTrabajo> admPuePuestoTrabajoList) {
        this.admPuePuestoTrabajoList = admPuePuestoTrabajoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depId != null ? depId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmDepDepartamento)) {
            return false;
        }
        AdmDepDepartamento other = (AdmDepDepartamento) object;
        if ((this.depId == null && other.depId != null) || (this.depId != null && !this.depId.equals(other.depId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.AdmDepDepartamento[ depId=" + depId + " ]";
    }
    
}
