
package com.controladores;

import com.conexionBD.ConexionBD;
import com.entidades.AdmRolRol;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Enrique Ochoa
 */
public abstract class AbstractoControlador<T> {
    
    protected EntityManager em;
    protected T ClaseEntidad;
    protected List<T> ListaEntidad;
    
    public List<T> encontrarEntidades() {
        return encontrarEntidades(true, -1, -1);
    }

    public List<T> encontrarEntidades(int maxResults, int firstResult) {
        return encontrarEntidades(false, maxResults, firstResult);
    }

    private List<T> encontrarEntidades(boolean all, int maxResults, int firstResult) {
        em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ClaseEntidad.getClass()));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public void insertarEntidad(){
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ClaseEntidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally{
            em.close();
        }
    }
    
    public void actualizarEntidad(T entidad){
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally{
            em.close();
        }
    }
    
    public void eliminarEntidad(T entidad){
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (!em.contains(entidad)) {
                entidad = em.merge(entidad);
            }
            em.remove(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally{
            em.close();
        }
    }
    
    public List<T> encontrarJefes(){
        return ListaEntidad;
    }
    
    public List<T> encontrarPuestos(int id){
        return ListaEntidad;
    }
    
    public abstract EntityManager getEntityManager();
    
    public abstract Object encontrarPorId(int id);
 
    
}
