
package com.controladores;

import com.conexionBD.ConexionBD;
import com.entidades.AdmDepDepartamento;
import javax.persistence.EntityManager;

/**
 *
 * @author Enrique Ochoa
 */
public class DepartamentoControlador extends AbstractoControlador<AdmDepDepartamento>{
    
    public DepartamentoControlador(AdmDepDepartamento departamento){
        super.ClaseEntidad = departamento;
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
