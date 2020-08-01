
package com.controladores;

import com.conexionBD.ConexionBD;
import com.entidades.AdmDepDepartamento;
import com.entidades.AdmEmpEmpleado;
import com.entidades.AdmPuePuestoTrabajo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Enrique Ochoa
 */
public class EmpleadoControlador extends AbstractoControlador<AdmEmpEmpleado>{
    
    public EmpleadoControlador(AdmEmpEmpleado empleado){
        super.ClaseEntidad = empleado;
    }
    
    @Override
    public List<AdmEmpEmpleado> encontrarJefes(){
        em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT u FROM AdmEmpEmpleado u WHERE u.empIdJefe IS NULL AND u.estId.estId = :id");
            q.setParameter("id", 1);

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

    @Override
    public Object encontrarPorId(int id) {
        em = getEntityManager();
        AdmPuePuestoTrabajo pueEncontrado = new AdmPuePuestoTrabajo();
        try {
            Query q = em.createQuery("SELECT u FROM AdmPuePuestoTrabajo u WHERE u.admPuePuestoTrabajoPK.pueId = :id");
            q.setParameter("id", id);
            pueEncontrado = (AdmPuePuestoTrabajo) q.getSingleResult();
            
            return pueEncontrado;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally{
            em.close();
        }
    }
    
}
