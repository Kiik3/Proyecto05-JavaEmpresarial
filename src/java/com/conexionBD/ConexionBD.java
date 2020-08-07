
package com.conexionBD;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Enrique Ochoa
 */
public class ConexionBD {
    
    static ConexionBD con = new ConexionBD();
    static EntityManagerFactory emf;
    
    //Singleton para establecer conexión con la BD
    private ConexionBD(){
        emf = Persistence.createEntityManagerFactory("Proyecto05PU");
    }
    
    public static ConexionBD getInstance(){
        return con;
    }
    
    public EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }
}
