
package com.controladores;

import javax.persistence.EntityManager;

/**
 *
 * @author Enrique Ochoa
 */
public abstract class AbstractoControlador<T> {
    
    protected EntityManager em = getEntityManager();
    
    protected abstract EntityManager getEntityManager();
    
}
