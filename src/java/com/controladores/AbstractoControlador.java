
package com.controladores;

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
    
    //Selecciona las entidades existentes
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
    
    //Ingresa un nuevo registro a la BD
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
    
    //Actualiza un registro o lo inserta si este no existe
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
    
    //Elimina el registro
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
    
    public void actualizarNativo(T entidad){

    }
    
    //Método abstracto para obtener el Manejador de Entidades de la BD
    public abstract EntityManager getEntityManager();
    
    //Método abstracto para encontrar un solo elemento por su id
    public abstract Object encontrarPorId(int id);
 
    
}
