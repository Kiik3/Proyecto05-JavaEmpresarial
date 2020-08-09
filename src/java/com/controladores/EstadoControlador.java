
package com.controladores;

import com.conexionBD.ConexionBD;
import com.entidades.AdmEstEstado;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Enrique Ochoa
 */
public class EstadoControlador extends AbstractoControlador<AdmEstEstado>{
    
    //En el constructor se define la entidad
    public EstadoControlador(AdmEstEstado estado){
        super.ClaseEntidad = estado;
    }
    
    @Override
    public AdmEstEstado encontrarPorId(int id) {
        em = getEntityManager();
        AdmEstEstado fechaPago = new AdmEstEstado();
        try {
            Query q = em.createQuery("SELECT u FROM AdmEstEstado u WHERE u.estId = :id");
            q.setParameter("id", id);
            fechaPago = (AdmEstEstado) q.getSingleResult();
            
            return fechaPago;
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
