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
@Table(name = "ADM_REN_RENTA", catalog = "PROYECTO04_ADMINISTRACION_EMPLEADOS", schema = "")
@NamedQueries({
    @NamedQuery(name = "AdmRenRenta.findAll", query = "SELECT a FROM AdmRenRenta a"),
    @NamedQuery(name = "AdmRenRenta.findByRenId", query = "SELECT a FROM AdmRenRenta a WHERE a.renId = :renId"),
    @NamedQuery(name = "AdmRenRenta.findByRenTramo", query = "SELECT a FROM AdmRenRenta a WHERE a.renTramo = :renTramo"),
    @NamedQuery(name = "AdmRenRenta.findByRenDesde", query = "SELECT a FROM AdmRenRenta a WHERE a.renDesde = :renDesde"),
    @NamedQuery(name = "AdmRenRenta.findByRenHasta", query = "SELECT a FROM AdmRenRenta a WHERE a.renHasta = :renHasta"),
    @NamedQuery(name = "AdmRenRenta.findByRenPorcentaje", query = "SELECT a FROM AdmRenRenta a WHERE a.renPorcentaje = :renPorcentaje"),
    @NamedQuery(name = "AdmRenRenta.findByRenSobreExceso", query = "SELECT a FROM AdmRenRenta a WHERE a.renSobreExceso = :renSobreExceso"),
    @NamedQuery(name = "AdmRenRenta.findByRenCuotaFija", query = "SELECT a FROM AdmRenRenta a WHERE a.renCuotaFija = :renCuotaFija")})
public class AdmRenRenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REN_ID", nullable = false)
    private Integer renId;
    @Column(name = "REN_TRAMO", length = 20)
    private String renTramo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "REN_DESDE", precision = 22, scale = 0)
    private Double renDesde;
    @Column(name = "REN_HASTA", precision = 22, scale = 0)
    private Double renHasta;
    @Column(name = "REN_PORCENTAJE", precision = 22, scale = 0)
    private Double renPorcentaje;
    @Column(name = "REN_SOBRE_EXCESO", precision = 22, scale = 0)
    private Double renSobreExceso;
    @Column(name = "REN_CUOTA_FIJA", precision = 22, scale = 0)
    private Double renCuotaFija;

    public AdmRenRenta() {
    }

    public AdmRenRenta(Integer renId) {
        this.renId = renId;
    }

    public Integer getRenId() {
        return renId;
    }

    public void setRenId(Integer renId) {
        this.renId = renId;
    }

    public String getRenTramo() {
        return renTramo;
    }

    public void setRenTramo(String renTramo) {
        this.renTramo = renTramo;
    }

    public Double getRenDesde() {
        return renDesde;
    }

    public void setRenDesde(Double renDesde) {
        this.renDesde = renDesde;
    }

    public Double getRenHasta() {
        return renHasta;
    }

    public void setRenHasta(Double renHasta) {
        this.renHasta = renHasta;
    }

    public Double getRenPorcentaje() {
        return renPorcentaje;
    }

    public void setRenPorcentaje(Double renPorcentaje) {
        this.renPorcentaje = renPorcentaje;
    }

    public Double getRenSobreExceso() {
        return renSobreExceso;
    }

    public void setRenSobreExceso(Double renSobreExceso) {
        this.renSobreExceso = renSobreExceso;
    }

    public Double getRenCuotaFija() {
        return renCuotaFija;
    }

    public void setRenCuotaFija(Double renCuotaFija) {
        this.renCuotaFija = renCuotaFija;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (renId != null ? renId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmRenRenta)) {
            return false;
        }
        AdmRenRenta other = (AdmRenRenta) object;
        if ((this.renId == null && other.renId != null) || (this.renId != null && !this.renId.equals(other.renId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.AdmRenRenta[ renId=" + renId + " ]";
    }
    
}
