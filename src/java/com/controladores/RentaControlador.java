
package com.controladores;

import com.conexionBD.ConexionBD;
import com.entidades.AdmRenRenta;
import javax.persistence.EntityManager;

/**
 *
 * @author Enrique Ochoa
 */
public class RentaControlador extends AbstractoControlador<AdmRenRenta>{
    
    public RentaControlador(AdmRenRenta renta){
        super.ClaseEntidad = renta;
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
