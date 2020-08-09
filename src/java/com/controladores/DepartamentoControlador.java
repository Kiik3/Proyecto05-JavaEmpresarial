
package com.controladores;

import com.conexionBD.ConexionBD;
import com.entidades.AdmDepDepartamento;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Enrique Ochoa
 */
public class DepartamentoControlador extends AbstractoControlador<AdmDepDepartamento>{
    
    //En el constructor se define la entidad
    public DepartamentoControlador(AdmDepDepartamento departamento){
        super.ClaseEntidad = departamento;
    }
    
    @Override
    public EntityManager getEntityManager() {
        return ConexionBD.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    public AdmDepDepartamento encontrarPorId(int id) {
        em = getEntityManager();
        AdmDepDepartamento depEncontrado = new AdmDepDepartamento();
        try {
            Query q = em.createQuery("SELECT u FROM AdmDepDepartamento u WHERE u.depId = :id");
            q.setParameter("id", id);
            depEncontrado = (AdmDepDepartamento) q.getSingleResult();
            
            return depEncontrado;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally{
            em.close();
        }
    }
    
}
