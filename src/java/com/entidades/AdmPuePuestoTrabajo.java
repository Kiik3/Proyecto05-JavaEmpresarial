/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author kik3o
 */
@Entity
@Table(name = "ADM_PUE_PUESTO_TRABAJO", catalog = "PROYECTO04_ADMINISTRACION_EMPLEADOS", schema = "")
@NamedQueries({
    @NamedQuery(name = "AdmPuePuestoTrabajo.findAll", query = "SELECT a FROM AdmPuePuestoTrabajo a"),
    @NamedQuery(name = "AdmPuePuestoTrabajo.findByPueId", query = "SELECT a FROM AdmPuePuestoTrabajo a WHERE a.admPuePuestoTrabajoPK.pueId = :pueId"),
    @NamedQuery(name = "AdmPuePuestoTrabajo.findByDepId", query = "SELECT a FROM AdmPuePuestoTrabajo a WHERE a.admPuePuestoTrabajoPK.depId = :depId"),
    @NamedQuery(name = "AdmPuePuestoTrabajo.findByPueNombre", query = "SELECT a FROM AdmPuePuestoTrabajo a WHERE a.pueNombre = :pueNombre"),
    @NamedQuery(name = "AdmPuePuestoTrabajo.findByPueSalarioMinimo", query = "SELECT a FROM AdmPuePuestoTrabajo a WHERE a.pueSalarioMinimo = :pueSalarioMinimo"),
    @NamedQuery(name = "AdmPuePuestoTrabajo.findByPueSalarioMaximo", query = "SELECT a FROM AdmPuePuestoTrabajo a WHERE a.pueSalarioMaximo = :pueSalarioMaximo")})
public class AdmPuePuestoTrabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AdmPuePuestoTrabajoPK admPuePuestoTrabajoPK;
    @Column(name = "PUE_NOMBRE", length = 20)
    private String pueNombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PUE_SALARIO_MINIMO", precision = 22, scale = 0)
    private Double pueSalarioMinimo;
    @Column(name = "PUE_SALARIO_MAXIMO", precision = 22, scale = 0)
    private Double pueSalarioMaximo;
    @JoinColumn(name = "DEP_ID", referencedColumnName = "DEP_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AdmDepDepartamento admDepDepartamento;
    @OneToMany(mappedBy = "pueId", fetch = FetchType.LAZY)
    private List<AdmEmpEmpleado> admEmpEmpleadoList;

    public AdmPuePuestoTrabajo() {
    }

    public AdmPuePuestoTrabajo(AdmPuePuestoTrabajoPK admPuePuestoTrabajoPK) {
        this.admPuePuestoTrabajoPK = admPuePuestoTrabajoPK;
    }

    public AdmPuePuestoTrabajo(int pueId, int depId) {
        this.admPuePuestoTrabajoPK = new AdmPuePuestoTrabajoPK(pueId, depId);
    }

    public AdmPuePuestoTrabajoPK getAdmPuePuestoTrabajoPK() {
        return admPuePuestoTrabajoPK;
    }

    public void setAdmPuePuestoTrabajoPK(AdmPuePuestoTrabajoPK admPuePuestoTrabajoPK) {
        this.admPuePuestoTrabajoPK = admPuePuestoTrabajoPK;
    }

    public String getPueNombre() {
        return pueNombre;
    }

    public void setPueNombre(String pueNombre) {
        this.pueNombre = pueNombre;
    }

    public Double getPueSalarioMinimo() {
        return pueSalarioMinimo;
    }

    public void setPueSalarioMinimo(Double pueSalarioMinimo) {
        this.pueSalarioMinimo = pueSalarioMinimo;
    }

    public Double getPueSalarioMaximo() {
        return pueSalarioMaximo;
    }

    public void setPueSalarioMaximo(Double pueSalarioMaximo) {
        this.pueSalarioMaximo = pueSalarioMaximo;
    }

    public AdmDepDepartamento getAdmDepDepartamento() {
        return admDepDepartamento;
    }

    public void setAdmDepDepartamento(AdmDepDepartamento admDepDepartamento) {
        this.admDepDepartamento = admDepDepartamento;
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
        hash += (admPuePuestoTrabajoPK != null ? admPuePuestoTrabajoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmPuePuestoTrabajo)) {
            return false;
        }
        AdmPuePuestoTrabajo other = (AdmPuePuestoTrabajo) object;
        if ((this.admPuePuestoTrabajoPK == null && other.admPuePuestoTrabajoPK != null) || (this.admPuePuestoTrabajoPK != null && !this.admPuePuestoTrabajoPK.equals(other.admPuePuestoTrabajoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.AdmPuePuestoTrabajo[ admPuePuestoTrabajoPK=" + admPuePuestoTrabajoPK + " ]";
    }
    
}
