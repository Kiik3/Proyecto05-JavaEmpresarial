
package com.controladores;

import com.conexionBD.ConexionBD;
import com.entidades.AdmDepDepartamento;
import com.entidades.AdmPuePuestoTrabajo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Enrique Ochoa
 */
public class PuestoControlador extends AbstractoControlador<AdmPuePuestoTrabajo>{
    
    public PuestoControlador(AdmPuePuestoTrabajo puesto){
        super.ClaseEntidad = puesto;
    }
    
    @Override
    public AdmDepDepartamento encontrarPorId(int id){
        em = getEntityManager();
        AdmDepDepartamento departamentoEncontrado = new AdmDepDepartamento();
        try {
            Query q = em.createQuery("SELECT u FROM AdmDepDepartamento u WHERE u.depId = :id");
            q.setParameter("id", id);
            departamentoEncontrado = (AdmDepDepartamento) q.getSingleResult();
            
            return departamentoEncontrado;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally{
            em.close();
        }
    }
    
    @Override
    public List<AdmPuePuestoTrabajo> encontrarPuestos(int id){
        em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT u FROM AdmPuePuestoTrabajo u WHERE u.admPuePuestoTrabajoPK.depId = :id");
            q.setParameter("id", id);

            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally{
            em.close();
        }
    }

    @Override
    public EntityManager getEntityManager() {
        return ConexionBD.getInstance().getEntityManagerFactory().createEntityManager();
    }
}
