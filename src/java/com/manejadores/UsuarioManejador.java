
package com.manejadores;

import com.controladores.UsuarioControlador;
import com.entidades.AdmRolRol;
import com.entidades.AdmUsuUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utilidades.Utilidades;

/**
 *
 * @author Enrique Ochoa
 */

@ManagedBean
@ViewScoped
public class UsuarioManejador extends AbastractoManejador<AdmUsuUsuario>{
    
    private List<AdmUsuUsuario> usuarios = new ArrayList<AdmUsuUsuario>();
    
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

    public void insertar(int id){        
        claseEntidad.setRolId((AdmRolRol) claseEntidadControlador.encontrarPorId(id));
        claseEntidadControlador.insertarEntidad();
        Utilidades.mensajeExito("Ingresado correctamente");
        inicializar();
    }
    
    public void actualizar(int id, AdmUsuUsuario usuario){        
        usuario.setRolId((AdmRolRol) claseEntidadControlador.encontrarPorId(id));
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
