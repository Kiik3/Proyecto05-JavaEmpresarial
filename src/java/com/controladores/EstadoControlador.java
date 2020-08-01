
package com.controladores;

import com.conexionBD.ConexionBD;
import com.entidades.AdmEstEstado;
import javax.persistence.EntityManager;

/**
 *
 * @author Enrique Ochoa
 */
public class EstadoControlador extends AbstractoControlador<AdmEstEstado>{
    
    public EstadoControlador(AdmEstEstado estado){
        super.ClaseEntidad = estado;
    }
    
    @Override
    public Object encontrarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntityManager getEntityManager() {
        return ConexionBD.getInstance().getEntityManagerFactory().createEntityManager();
    }
    
}
