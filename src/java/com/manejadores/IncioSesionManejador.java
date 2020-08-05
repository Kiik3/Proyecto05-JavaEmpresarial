
package com.manejadores;

import com.controladores.UsuarioControlador;
import com.correo.CorreoElectronico;
import com.entidades.AdmUsuUsuario;
import com.propiedades.Encriptador;
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
    private boolean flagAutenticacion;
    Encriptador enc = new Encriptador();
    
    @PostConstruct
    public void inicializar(){
        usuario = new AdmUsuUsuario();
        usuarioEncontrado = new AdmUsuUsuario();
        usuarioControlador = new UsuarioControlador(usuario);
        flagAutenticacion = false;
        numero = 1;
        codigoAutenticacion = 0;
    }
    
    public void validacion() throws IOException{
        usuarioControlador = new UsuarioControlador(usuario);
        usuarioControlador.getEntityManager();
        usuarioEncontrado = usuarioControlador.validarUsuario(usuario);

        if(usuarioEncontrado == null || !enc.desencriptador(usuarioEncontrado.getUsuContrasena()).equals(usuario.getUsuContrasena())){
            Utilidades.mensajeError("Credenciales incorrectas");
            inicializar();
        }
        else{
            mandarCorreo();
            flagAutenticacion = true;
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
                Utilidades.redireccion("catalogos/administrador");
            }
            else if(usuarioEncontrado.getRolId().getRolId() == 3){
                Utilidades.redireccion("catalogos/usuario");
            }
            flagAutenticacion = false;
        }
        else{
            Utilidades.mensajeError("Código incorrecto");
        }
//        codigoAutenticacion = 0;
//        numero = 0;
    }
    
    public void validarSesion() throws IOException{
        System.out.println(usuario.getUsuNombre());
        if(usuarioEncontrado.getRolId()== null && numero != codigoAutenticacion){
            inicializar();
            Utilidades.redireccion("index");
        }
    }
    
    public void validarAdmin() throws IOException{
        validarSesion();
        
        if(usuarioEncontrado.getRolId().getRolId() != 1){
            Utilidades.redireccion("catalogos/usuario");
        }
    }
    
    public void validarUsuario() throws IOException{
        validarSesion();
        if(usuarioEncontrado.getRolId().getRolId() != 3){
            Utilidades.redireccion("catalogos/administrador");
        }
    }
    
    public void cerrarSesion() throws IOException{
        inicializar();
        Utilidades.redireccion("index");
    }
    
    public void confirmarSesion() throws IOException{
        if(usuarioEncontrado.getUsuNombre() != null){
            System.out.println("funciona");
            Utilidades.redireccion("confirmacion");
        }
        else{
            System.out.println("no funciona q putas");
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

    public boolean isFlagAutenticacion() {
        return flagAutenticacion;
    }

    public void setFlagAutenticacion(boolean flagAutenticacion) {
        this.flagAutenticacion = flagAutenticacion;
    }

}
