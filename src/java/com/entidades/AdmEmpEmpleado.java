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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "ADM_EMP_EMPLEADO", catalog = "PROYECTO04_ADMINISTRACION_EMPLEADOS", schema = "")
@NamedQueries({
    @NamedQuery(name = "AdmEmpEmpleado.findAll", query = "SELECT a FROM AdmEmpEmpleado a"),
    @NamedQuery(name = "AdmEmpEmpleado.findByEmpId", query = "SELECT a FROM AdmEmpEmpleado a WHERE a.empId = :empId"),
    @NamedQuery(name = "AdmEmpEmpleado.findByEmpIdentificacion", query = "SELECT a FROM AdmEmpEmpleado a WHERE a.empIdentificacion = :empIdentificacion"),
    @NamedQuery(name = "AdmEmpEmpleado.findByEmpApellido", query = "SELECT a FROM AdmEmpEmpleado a WHERE a.empApellido = :empApellido"),
    @NamedQuery(name = "AdmEmpEmpleado.findByEmpFechaNacimiento", query = "SELECT a FROM AdmEmpEmpleado a WHERE a.empFechaNacimiento = :empFechaNacimiento"),
    @NamedQuery(name = "AdmEmpEmpleado.findByEmpNombre", query = "SELECT a FROM AdmEmpEmpleado a WHERE a.empNombre = :empNombre"),
    @NamedQuery(name = "AdmEmpEmpleado.findByEmpCorreo", query = "SELECT a FROM AdmEmpEmpleado a WHERE a.empCorreo = :empCorreo"),
    @NamedQuery(name = "AdmEmpEmpleado.findByEmpFechaContratacion", query = "SELECT a FROM AdmEmpEmpleado a WHERE a.empFechaContratacion = :empFechaContratacion"),
//    @NamedQuery(name = "AdmEmpEmpleado.findByDepId", query = "SELECT a FROM AdmEmpEmpleado a WHERE a.depId = :depId"),
    @NamedQuery(name = "AdmEmpEmpleado.findByEmpSalario", query = "SELECT a FROM AdmEmpEmpleado a WHERE a.empSalario = :empSalario"),
    @NamedQuery(name = "AdmEmpEmpleado.findByEmpTelefono", query = "SELECT a FROM AdmEmpEmpleado a WHERE a.empTelefono = :empTelefono")})
public class AdmEmpEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EMP_ID", nullable = false)
    private Integer empId;
    @Column(name = "EMP_IDENTIFICACION", length = 10)
    private String empIdentificacion;
    @Column(name = "EMP_APELLIDO", length = 30)
    private String empApellido;
    @Column(name = "EMP_FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date empFechaNacimiento;
    @Column(name = "EMP_NOMBRE", length = 30)
    private String empNombre;
    @Column(name = "EMP_CORREO", length = 30)
    private String empCorreo;
    @Column(name = "EMP_FECHA_CONTRATACION")
    @Temporal(TemporalType.DATE)
    private Date empFechaContratacion;
//    @Column(name = "DEP_ID")
//    private Integer depId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "EMP_SALARIO", precision = 22, scale = 0)
    private Double empSalario;
    @Column(name = "EMP_TELEFONO")
    private Integer empTelefono;
    @OneToMany(mappedBy = "empIdJefe", fetch = FetchType.LAZY)
    private List<AdmEmpEmpleado> admEmpEmpleadoList;
    @JoinColumn(name = "EMP_ID_JEFE", referencedColumnName = "EMP_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private AdmEmpEmpleado empIdJefe;
    @JoinColumn(name = "EST_ID", referencedColumnName = "EST_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private AdmEstEstado estId;
//    @JoinColumn(name = "PUE_ID", referencedColumnName = "PUE_ID")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private AdmPuePuestoTrabajo pueId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "PUE_ID", referencedColumnName = "PUE_ID"),
        @JoinColumn(name = "DEP_ID", referencedColumnName = "DEP_ID")
    })
    private AdmPuePuestoTrabajo pueId;

    public AdmEmpEmpleado() {
    }

    public AdmEmpEmpleado(Integer empId) {
        this.empId = empId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpIdentificacion() {
        return empIdentificacion;
    }

    public void setEmpIdentificacion(String empIdentificacion) {
        this.empIdentificacion = empIdentificacion;
    }

    public String getEmpApellido() {
        return empApellido;
    }

    public void setEmpApellido(String empApellido) {
        this.empApellido = empApellido;
    }

    public Date getEmpFechaNacimiento() {
        return empFechaNacimiento;
    }

    public void setEmpFechaNacimiento(Date empFechaNacimiento) {
        this.empFechaNacimiento = empFechaNacimiento;
    }

    public String getEmpNombre() {
        return empNombre;
    }

    public void setEmpNombre(String empNombre) {
        this.empNombre = empNombre;
    }

    public String getEmpCorreo() {
        return empCorreo;
    }

    public void setEmpCorreo(String empCorreo) {
        this.empCorreo = empCorreo;
    }

    public Date getEmpFechaContratacion() {
        return empFechaContratacion;
    }

    public void setEmpFechaContratacion(Date empFechaContratacion) {
        this.empFechaContratacion = empFechaContratacion;
    }

//    public Integer getDepId() {
//        return depId;
//    }
//
//    public void setDepId(Integer depId) {
//        this.depId = depId;
//    }

    public Double getEmpSalario() {
        return empSalario;
    }

    public void setEmpSalario(Double empSalario) {
        this.empSalario = empSalario;
    }

    public Integer getEmpTelefono() {
        return empTelefono;
    }

    public void setEmpTelefono(Integer empTelefono) {
        this.empTelefono = empTelefono;
    }

    public List<AdmEmpEmpleado> getAdmEmpEmpleadoList() {
        return admEmpEmpleadoList;
    }

    public void setAdmEmpEmpleadoList(List<AdmEmpEmpleado> admEmpEmpleadoList) {
        this.admEmpEmpleadoList = admEmpEmpleadoList;
    }

    public AdmEmpEmpleado getEmpIdJefe() {
        return empIdJefe;
    }

    public void setEmpIdJefe(AdmEmpEmpleado empIdJefe) {
        this.empIdJefe = empIdJefe;
    }

    public AdmEstEstado getEstId() {
        return estId;
    }

    public void setEstId(AdmEstEstado estId) {
        this.estId = estId;
    }

    public AdmPuePuestoTrabajo getPueId() {
        return pueId;
    }

    public void setPueId(AdmPuePuestoTrabajo pueId) {
        this.pueId = pueId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmEmpEmpleado)) {
            return false;
        }
        AdmEmpEmpleado other = (AdmEmpEmpleado) object;
        if ((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.AdmEmpEmpleado[ empId=" + empId + " ]";
    }
    
}
