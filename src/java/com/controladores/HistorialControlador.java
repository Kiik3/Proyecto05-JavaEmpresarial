
package com.controladores;

import com.conexionBD.ConexionBD;
import com.entidades.AdmHisHistorialPago;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Enrique Ochoa
 */
public class HistorialControlador extends AbstractoControlador<AdmHisHistorialPago>{
    
    //En el constructor se define la entidad
    public HistorialControlador(AdmHisHistorialPago historial){
        super.ClaseEntidad = historial;
    }
    
    //Se seleccionan los empleados que pertenecen a una misma planilla de pagos
    @Override
    public List<AdmHisHistorialPago> encontrarEntidades(){
        em = getEntityManager();
        List<AdmHisHistorialPago> lista = new ArrayList<AdmHisHistorialPago>();
        try {
            Query q = em.createQuery("SELECT DISTINCT u.hisIdEmpleado FROM AdmHisHistorialPago u");
            System.out.println(q.getResultList());
            for(Object l:q.getResultList()){
                q = em.createQuery("SELECT u FROM AdmHisHistorialPago u WHERE u.hisIdEmpleado = :id");
                q.setParameter("id", l);
                lista.add((AdmHisHistorialPago) q.getResultList().get(0));
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally{
            em.close();
        }
    }
    
    @Override
    public List<AdmHisHistorialPago> encontrarPuestos(int id){
        em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT u FROM AdmHisHistorialPago u WHERE u.hisIdEmpleado = :id");
            q.setParameter("id", id);
            System.out.println(q.getResultList());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
