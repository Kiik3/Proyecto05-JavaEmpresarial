
package com.controladores;

import com.conexionBD.ConexionBD;
import com.entidades.AdmRolRol;
import javax.persistence.EntityManager;

/**
 *
 * @author Enrique Ochoa 
 */
public class RolControlador extends AbstractoControlador<AdmRolRol>{
    
    //En el constructor se define la entidad
    public RolControlador(AdmRolRol rol){
        super.ClaseEntidad = rol;
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
