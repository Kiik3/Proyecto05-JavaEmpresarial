
package com.controladores;

import com.conexionBD.ConexionBD;
import com.entidades.AdmRolRol;
import com.entidades.AdmUsuUsuario;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Enrique Ochoa
 */
public class UsuarioControlador extends AbstractoControlador<AdmUsuUsuario>{
    
    public UsuarioControlador(AdmUsuUsuario usuario){
        super.ClaseEntidad = usuario;
    }
    
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
    public AdmRolRol encontrarPorId(int id){
        em = getEntityManager();
        AdmRolRol rolEncontrado = new AdmRolRol();
        try {
            Query q = em.createQuery("SELECT u FROM AdmRolRol u WHERE u.rolId = :id");
            q.setParameter("id", id);
            rolEncontrado = (AdmRolRol) q.getSingleResult();
            
            return rolEncontrado;
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
