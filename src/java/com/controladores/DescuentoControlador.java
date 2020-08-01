
package com.controladores;

import com.conexionBD.ConexionBD;
import com.entidades.AdmDesDescuentoLey;
import javax.persistence.EntityManager;

/**
 *
 * @author Enrique Ochoa
 */
public class DescuentoControlador extends AbstractoControlador<AdmDesDescuentoLey>{
    
    public DescuentoControlador(AdmDesDescuentoLey descuento){
        super.ClaseEntidad = descuento;
    }
    
    @Override
    public EntityManager getEntityManager() {
        return ConexionBD.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    public Object encontrarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
