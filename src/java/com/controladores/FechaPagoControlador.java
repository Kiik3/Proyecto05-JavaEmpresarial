
package com.controladores;

import com.conexionBD.ConexionBD;
import com.entidades.AdmFecFechaPago;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Enrique Ochoa
 */
public class FechaPagoControlador extends AbstractoControlador<AdmFecFechaPago>{

    //En el constructor se define la entidad
    public FechaPagoControlador(AdmFecFechaPago fecha){
        super.ClaseEntidad = fecha;
    }
    
    @Override
    public EntityManager getEntityManager() {
        return ConexionBD.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    public AdmFecFechaPago encontrarPorId(int id) {
        em = getEntityManager();
        AdmFecFechaPago fechaPago = new AdmFecFechaPago();
        try {
            Query q = em.createQuery("SELECT u FROM AdmFecFechaPago u WHERE u.fecId = :id");
            q.setParameter("id", 1);
            fechaPago = (AdmFecFechaPago) q.getSingleResult();
            
            return fechaPago;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally{
            em.close();
        }
    }
    
}
