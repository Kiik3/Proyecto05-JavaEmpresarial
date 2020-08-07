
package com.manejadores;

import com.controladores.UsuarioControlador;
import com.correo.CorreoElectronico;
import com.entidades.AdmUsuUsuario;
import com.propiedades.Encriptador;
import com.propiedades.Propiedades;
import java.io.IOException;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    private boolean aviso;
    Encriptador enc = new Encriptador();
    Propiedades propiedades = new Propiedades();
    GregorianCalendar gc = new GregorianCalendar();
    private String fechaPago;
    
    @PostConstruct
    public void inicializar(){
        usuario = new AdmUsuUsuario();
        usuarioEncontrado = new AdmUsuUsuario();
        usuarioControlador = new UsuarioControlador(usuario);
        flagAutenticacion = false;
        aviso = false;
        numero = 1;
        codigoAutenticacion = 0;
    }
    
    //Validación de usuario
    public void validacion() throws IOException{
        usuarioControlador = new UsuarioControlador(usuario);
        usuarioControlador.getEntityManager();
        usuarioEncontrado = usuarioControlador.validarUsuario(usuario);
        
        //Se niega el ingreso si el usuario o contraseña son inválidas
        if(usuarioEncontrado == null || !enc.desencriptador(usuarioEncontrado.getUsuContrasena()).equals(usuario.getUsuContrasena())){
            Utilidades.mensajeError("Credenciales incorrectas");
            inicializar();
        }
        else{
            mandarCorreo();
            
        }
        
    }
    //Se envía un email para autenticación
    public void mandarCorreo(){
        numero = (int) (Math.random()*(999999-100000+1)+100000);
            try {
                correoElectronico.mandarCorreo(usuario.getUsuCorreo(), usuarioEncontrado.getRolId().getRolId(), numero);
                System.out.println("se mando");
                flagAutenticacion = true;
            } catch (EmailException ex) {
                Utilidades.mensajeError("Correo electrónico inválido" + ex.getLocalizedMessage());
                inicializar();
                System.out.println("no se mando");
            }
    }
    
    public void validarCodigo() throws IOException{

        byte fechaHoy = (byte) gc.get(GregorianCalendar.DAY_OF_MONTH); //Se obtiene la fecha de hoy
        fechaPago = propiedades.cargarFechaPla().getProperty("fecha");
        
         //Tres días anteriores al pago de planilla, se activa el pago
        if(fechaHoy == Byte.parseByte(fechaPago)-3){
            propiedades.insertarPagar("pagar", "true");
        }
        
        //Se valida que el código introducido sea el mismo al que se envió al correo
        if(numero == codigoAutenticacion){
            Utilidades.mensajeExito("Credenciales correctas");
            if(usuarioEncontrado.getRolId().getRolId() == 1){
                avisoPagoPlanilla();
                Utilidades.redireccion("catalogos/administrador");
            }
            else if(usuarioEncontrado.getRolId().getRolId() == 3){
                avisoPagoPlanilla();
                Utilidades.redireccion("catalogos/usuario");
            }
            flagAutenticacion = false;
        }
        else{
            Utilidades.mensajeError("Código incorrecto");
        }
    }
    //Valida la sesión del usuario, negando el acceso al sistema si aun no ha iniciado sesión
    public void validarSesion() throws IOException{
        System.out.println(usuario.getUsuNombre());
        if(usuarioEncontrado.getRolId()== null || numero != codigoAutenticacion){
            inicializar();
            Utilidades.redireccion("index");
        }
    }
    //Valida si es administrador para no tener acceso a las vistas de usuario rrhh
    public void validarAdmin() throws IOException{
        validarSesion();
        
        if(usuarioEncontrado.getRolId().getRolId() != 1){
            Utilidades.redireccion("catalogos/usuario");
        }
    }
    //Valida si es usuario rrhh para no tener acceso a las vistas del administrador
    public void validarUsuario() throws IOException{
        validarSesion();
        if(usuarioEncontrado.getRolId().getRolId() != 3){
            Utilidades.redireccion("catalogos/administrador");
        }
    }
    //Cierra la sesión actual
    public void cerrarSesion() throws IOException{
        inicializar();
        Utilidades.redireccion("index");
    }
    //Confirma sesión
    public void confirmarSesion() throws IOException{
        if(usuarioEncontrado.getUsuNombre() != null){
            System.out.println("funciona");
            Utilidades.redireccion("confirmacion");
        }
        else{
            System.out.println("no funciona");
        }
    }
    //Permite al administrador modificar el día de pago de planilla
    public void cambiarFecha(String fecha){
        
        if(Byte.parseByte(fecha) <= 0 || Byte.parseByte(fecha) >= 31){
            Utilidades.mensajeError("Fecha incorrecta, debe ser entre 1 y 30");
        }
        else{
            propiedades.insertarPla("fecha", fecha);
            propiedades.insertarPagar("pagar", "true");
            Utilidades.mensajeExito("Fecha de pago actualizada correctamente");
        }
    }
    //Aviso que se muestta si el día actual es pago de planilla
    public void avisoPagoPlanilla(){
        byte fechaHoy = (byte) gc.get(GregorianCalendar.DAY_OF_MONTH); //Se obtiene la fecha de hoy

        fechaPago = propiedades.cargarFechaPla().getProperty("fecha");
        boolean pagar = Boolean.valueOf(propiedades.cargarPagarPla().getProperty("pagar"));

        if(fechaHoy == Byte.parseByte(fechaPago) && pagar){
            System.out.println("Se mete a aviso");
            aviso = true;
        }
        else{
            aviso = false;
        }
    }

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

    public boolean isAviso() {
        return aviso;
    }

    public void setAviso(boolean aviso) {
        this.aviso = aviso;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

}
