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
@Table(name = "ADM_ROL_ROL", catalog = "PROYECTO04_ADMINISTRACION_EMPLEADOS", schema = "")
@NamedQueries({
    @NamedQuery(name = "AdmRolRol.findAll", query = "SELECT a FROM AdmRolRol a"),
    @NamedQuery(name = "AdmRolRol.findByRolId", query = "SELECT a FROM AdmRolRol a WHERE a.rolId = :rolId"),
    @NamedQuery(name = "AdmRolRol.findByRolNombre", query = "SELECT a FROM AdmRolRol a WHERE a.rolNombre = :rolNombre")})
public class AdmRolRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ROL_ID", nullable = false)
    private Integer rolId;
    @Column(name = "ROL_NOMBRE", length = 20)
    private String rolNombre;
    @OneToMany(mappedBy = "rolId", fetch = FetchType.LAZY)
    private List<AdmUsuUsuario> admUsuUsuarioList;

    public AdmRolRol() {
    }

    public AdmRolRol(Integer rolId) {
        this.rolId = rolId;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public List<AdmUsuUsuario> getAdmUsuUsuarioList() {
        return admUsuUsuarioList;
    }

    public void setAdmUsuUsuarioList(List<AdmUsuUsuario> admUsuUsuarioList) {
        this.admUsuUsuarioList = admUsuUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolId != null ? rolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmRolRol)) {
            return false;
        }
        AdmRolRol other = (AdmRolRol) object;
        if ((this.rolId == null && other.rolId != null) || (this.rolId != null && !this.rolId.equals(other.rolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.AdmRolRol[ rolId=" + rolId + " ]";
    }
    
}
