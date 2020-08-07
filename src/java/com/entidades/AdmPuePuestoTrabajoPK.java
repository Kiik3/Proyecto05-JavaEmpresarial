/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Enrique Ochoa
 */
@Embeddable
public class AdmPuePuestoTrabajoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "PUE_ID", nullable = false)
    private int pueId;
    @Basic(optional = false)
    @Column(name = "DEP_ID", nullable = false)
    private int depId;

    public AdmPuePuestoTrabajoPK() {
    }

    public AdmPuePuestoTrabajoPK(int pueId, int depId) {
        this.pueId = pueId;
        this.depId = depId;
    }

    public int getPueId() {
        return pueId;
    }

    public void setPueId(int pueId) {
        this.pueId = pueId;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pueId;
        hash += (int) depId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmPuePuestoTrabajoPK)) {
            return false;
        }
        AdmPuePuestoTrabajoPK other = (AdmPuePuestoTrabajoPK) object;
        if (this.pueId != other.pueId) {
            return false;
        }
        if (this.depId != other.depId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.AdmPuePuestoTrabajoPK[ pueId=" + pueId + ", depId=" + depId + " ]";
    }
    
}
