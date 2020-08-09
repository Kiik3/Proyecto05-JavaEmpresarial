
package com.manejadores;

import com.controladores.UsuarioControlador;
import com.entidades.AdmRolRol;
import com.entidades.AdmUsuUsuario;
import com.propiedades.Encriptador;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.utilidades.Utilidades;

/**
 *
 * @author Enrique Ochoa
 */

@ManagedBean
@ViewScoped
public class UsuarioManejador extends AbastractoManejador<AdmUsuUsuario>{
    
    private List<AdmUsuUsuario> usuarios = new ArrayList<AdmUsuUsuario>();
    Encriptador enc = new Encriptador(); //Instancia para encriptar la contraseña del usuario
    
    @PostConstruct
    @Override
    public void inicializar(){
        claseEntidad = new AdmUsuUsuario();
        claseEntidadControlador = new UsuarioControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
        usuarios = claseEntidadControlador.encontrarEntidades();
    }
    
    @Override
    public void nuevaEntidad(){
        claseEntidad = new AdmUsuUsuario();
        claseEntidadControlador = new UsuarioControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
    }
    //Inserción de nuevo usuario
    public void insertar(int id){        
        claseEntidad.setRolId((AdmRolRol) claseEntidadControlador.encontrarPorId(id)); //Se ingresa al rol que pertenece
        claseEntidad.setUsuContrasena(enc.encriptador(claseEntidad.getUsuContrasena())); //Se encripta la contraseña
        claseEntidadControlador.insertarEntidad();
        Utilidades.mensajeExito("Ingresado correctamente");
        inicializar();
    }
    //Actualización de usuario
    public void actualizar(int id, AdmUsuUsuario usuario){        
        usuario.setRolId((AdmRolRol) claseEntidadControlador.encontrarPorId(id));
        usuario.setUsuContrasena(enc.encriptador(usuario.getUsuContrasena()));
        claseEntidadControlador.actualizarEntidad(usuario);
        Utilidades.mensajeExito("Actualizado correctamente");
        inicializar();
    }

    public List<AdmUsuUsuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<AdmUsuUsuario> usuarios) {
        this.usuarios = usuarios;
    }

}
