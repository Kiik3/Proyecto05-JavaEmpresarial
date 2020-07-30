
package com.controladores;

import com.conexionBD.ConexionBD;
import com.entidades.AdmUsuUsuario;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Enrique Ochoa
 */
public class UsuarioControlador extends AbstractoControlador<AdmUsuUsuario>{
    
    public AdmUsuUsuario validarUsuario(AdmUsuUsuario usuario){
        AdmUsuUsuario usuarioEncontrado = new AdmUsuUsuario();
        
        try {
            Query q = em.createQuery("SELECT u FROM AdmUsuUsuario u WHERE u.usuNombre = :nombre");
            q.setParameter("nombre", usuario.getUsuNombre());
            usuarioEncontrado = (AdmUsuUsuario) q.getSingleResult();
            
            if(usuario.getUsuContrasena().equals(usuarioEncontrado.getUsuContrasena())){
                return usuarioEncontrado;
            }
            else{
                return null;
            }
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
