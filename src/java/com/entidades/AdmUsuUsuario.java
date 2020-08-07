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
@Table(name = "ADM_USU_USUARIO", catalog = "PROYECTO04_ADMINISTRACION_EMPLEADOS", schema = "")
@NamedQueries({
    @NamedQuery(name = "AdmUsuUsuario.findAll", query = "SELECT a FROM AdmUsuUsuario a"),
    @NamedQuery(name = "AdmUsuUsuario.findByUsuId", query = "SELECT a FROM AdmUsuUsuario a WHERE a.usuId = :usuId"),
    @NamedQuery(name = "AdmUsuUsuario.findByUsuNombre", query = "SELECT a FROM AdmUsuUsuario a WHERE a.usuNombre = :usuNombre"),
    @NamedQuery(name = "AdmUsuUsuario.findByUsuCorreo", query = "SELECT a FROM AdmUsuUsuario a WHERE a.usuCorreo = :usuCorreo"),
    @NamedQuery(name = "AdmUsuUsuario.findByUsuContrasena", query = "SELECT a FROM AdmUsuUsuario a WHERE a.usuContrasena = :usuContrasena")})
public class AdmUsuUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USU_ID", nullable = false)
    private Integer usuId;
    @Column(name = "USU_NOMBRE", length = 30)
    private String usuNombre;
    @Column(name = "USU_CORREO", length = 30)
    private String usuCorreo;
    @Column(name = "USU_CONTRASENA", length = 50)
    private String usuContrasena;
    @JoinColumn(name = "ROL_ID", referencedColumnName = "ROL_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private AdmRolRol rolId;

    public AdmUsuUsuario() {
    }

    public AdmUsuUsuario(Integer usuId) {
        this.usuId = usuId;
    }

    public Integer getUsuId() {
        return usuId;
    }

    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public String getUsuContrasena() {
        return usuContrasena;
    }

    public void setUsuContrasena(String usuContrasena) {
        this.usuContrasena = usuContrasena;
    }

    public AdmRolRol getRolId() {
        return rolId;
    }

    public void setRolId(AdmRolRol rolId) {
        this.rolId = rolId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmUsuUsuario)) {
            return false;
        }
        AdmUsuUsuario other = (AdmUsuUsuario) object;
        if ((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.AdmUsuUsuario[ usuId=" + usuId + " ]";
    }
    
}
