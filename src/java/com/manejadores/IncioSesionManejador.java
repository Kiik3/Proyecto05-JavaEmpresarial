
package com.manejadores;

import com.controladores.UsuarioControlador;
import com.correo.CorreoElectronico;
import com.entidades.AdmUsuUsuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.mail.EmailException;
import utilidades.Utilidades;

/**
 *
 * @author Enrique Ochoa
 */

@ManagedBean
@SessionScoped
public class IncioSesionManejador {
    
    private AdmUsuUsuario usuario;
    AdmUsuUsuario usuarioEncontrado;
    private UsuarioControlador usuarioControlador;
    private CorreoElectronico correoElectronico = new CorreoElectronico();
    private int codigoAutenticacion;
    private int numero;
    
    @PostConstruct
    public void inicializar(){
        usuario = new AdmUsuUsuario();
        usuarioControlador = new UsuarioControlador();
    }
    
    public void validacion() throws IOException{
        usuarioControlador = new UsuarioControlador();
        usuarioControlador.getEntityManager();
        usuarioEncontrado = usuarioControlador.validarUsuario(usuario);
        
        if(usuarioEncontrado == null){
            Utilidades.mensajeError("Credenciales incorrectas");
            usuario.setUsuNombre(null);
        }
        else{
            mandarCorreo();
            Utilidades.redireccion("validacion-correo.xhtml");
        }
        
    }
    
    public void mandarCorreo(){
        numero = (int) (Math.random()*(999999-100000+1)+100000);
            try {
                correoElectronico.mandarCorreo(usuario.getUsuCorreo(), usuarioEncontrado.getRolId().getRolId(), numero);
                System.out.println("se mando");
            } catch (EmailException ex) {
                Utilidades.mensajeError("Correo electrónico inválido");
                usuario.setUsuNombre(null);
                usuario.setUsuCorreo(null);
                System.out.println("no se mando");
            }
    }
    
    public void validarCodigo() throws IOException{
        if(numero == codigoAutenticacion){
            Utilidades.mensajeExito("Credenciales correctas");
            if(usuarioEncontrado.getRolId().getRolId() == 1){
                Utilidades.redireccion("catalogos/administrador.xhtml");
            }
            else if(usuarioEncontrado.getRolId().getRolId() == 3){
                Utilidades.redireccion("catalogos/usuario.xhtml");
            }
        }
        else{
            Utilidades.mensajeError("Código incorrecto");
        }
        codigoAutenticacion = 0;
        numero = 0;
    }
    
    public void validarSesion() throws IOException{
        if(usuario.getUsuNombre() == null){
            Utilidades.redireccion("../index.xhtml");
        }
    }
    
    public void cerrarSesion() throws IOException{
        usuario.setUsuNombre(null);
        usuario.setUsuCorreo(null);
        Utilidades.redireccion("../index.xhtml");
    }
    
    public void confirmarSesion() throws IOException{
        if(usuario.getUsuNombre() != null){
            System.out.println("funciona");
            Utilidades.redireccion("faces/confirmacion.xhtml");
        }
        else{
            System.out.println("no funciona");
        }
    }
//    public void destruir(){
//        usuario = null;
//    }

    public AdmUsuUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(AdmUsuUsuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioControlador getUsuarioControlador() {
        return usuarioControlador;
    }

    public void setUsuarioControlador(UsuarioControlador usuarioControlador) {
        this.usuarioControlador = usuarioControlador;
    }

    public int getCodigoAutenticacion() {
        return codigoAutenticacion;
    }

    public void setCodigoAutenticacion(int codigoAutenticacion) {
        this.codigoAutenticacion = codigoAutenticacion;
    }

}
