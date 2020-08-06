
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
    public void insertarEntidad(){
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createNativeQuery("INSERT INTO ADM_EMP_EMPLEADO"
                    + "(EMP_FECHA_CONTRATACION, PUE_ID, EMP_IDENTIFICACION, EMP_SALARIO, EMP_APELLIDO, EMP_FECHA_NACIMIENTO, EMP_NOMBRE, EMP_CORREO,"
                    + " DEP_ID, EST_ID, EMP_TELEFONO) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11)");
            
            q.setParameter(1, ClaseEntidad.getEmpFechaContratacion());
            q.setParameter(2, ClaseEntidad.getPueId().getAdmPuePuestoTrabajoPK().getPueId());
            q.setParameter(3, ClaseEntidad.getEmpIdentificacion());
            q.setParameter(4, ClaseEntidad.getEmpSalario());
            q.setParameter(5, ClaseEntidad.getEmpApellido());
            q.setParameter(6, ClaseEntidad.getEmpFechaNacimiento());
            q.setParameter(7, ClaseEntidad.getEmpNombre());
            q.setParameter(8, ClaseEntidad.getEmpCorreo());
            q.setParameter(9, ClaseEntidad.getPueId().getAdmPuePuestoTrabajoPK().getDepId());
            q.setParameter(10, ClaseEntidad.getEstId().getEstId());            
            q.setParameter(11, ClaseEntidad.getEmpTelefono());

            q.executeUpdate();
            
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
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
