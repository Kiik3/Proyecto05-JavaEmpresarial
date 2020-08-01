
package com.manejadores;

import com.controladores.AbstractoControlador;
import java.util.ArrayList;
import java.util.List;
import utilidades.Utilidades;

/**
 *
 * @author Enrique Ochoa
 */

public abstract class AbastractoManejador<T> {
    
    protected T claseEntidad;
    protected AbstractoControlador<T> claseEntidadControlador;
    protected List<T> listaEntidad = new ArrayList<T>();
    
    public void insertar(){
        claseEntidadControlador.insertarEntidad();
        Utilidades.mensajeExito("Ingresado correctamente");
        inicializar();
    }
    
    public void actualizar(T entidad){
        System.out.println("entra a actualizar" + entidad);
        claseEntidadControlador.actualizarEntidad(entidad);
        Utilidades.mensajeExito("Actualizado correctamente");
    }
    
    public void eliminar(T entidad){
        try {
            System.out.println("entra a eliminar" + entidad);
            claseEntidadControlador.eliminarEntidad(entidad);
            Utilidades.mensajeExito("Eliminado correctamente");
            inicializar();
        } catch (Exception e) {
            Utilidades.mensajeError("No se pudo eliminar por dependencias");
        }
        
        
    }
    
    public T getClaseEntidad() {
        return claseEntidad;
    }

    public void setClaseEntidad(T claseEntidad) {
        this.claseEntidad = claseEntidad;
    }

    public List<T> getListaEntidad() {
        return listaEntidad;
    }

    public void setListaEntidad(List<T> listaEntidad) {
        this.listaEntidad = listaEntidad;
    }

    public AbstractoControlador<T> getClaseEntidadControlador() {
        return claseEntidadControlador;
    }

    public void setClaseEntidadControlador(AbstractoControlador<T> claseEntidadControlador) {
        this.claseEntidadControlador = claseEntidadControlador;
    }
    
    public abstract void inicializar();
    
    public abstract void nuevaEntidad();
    
}
