
package com.manejadores;

import com.controladores.EstadoControlador;
import com.entidades.AdmEstEstado;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Enrique Ochoa
 */

@ManagedBean
@ViewScoped
public class EstadoManejador extends AbastractoManejador<AdmEstEstado>{
    
    private List<AdmEstEstado> estados = new ArrayList<AdmEstEstado>();
    
    @PostConstruct
    @Override
    public void inicializar(){
        claseEntidad = new AdmEstEstado();
        claseEntidadControlador = new EstadoControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
        estados = claseEntidadControlador.encontrarEntidades();
    }
    
    @Override
    public void nuevaEntidad(){
        claseEntidad = new AdmEstEstado();
        claseEntidadControlador = new EstadoControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
    }

    public List<AdmEstEstado> getEstados() {
        return estados;
    }

    public void setEstados(List<AdmEstEstado> estados) {
        this.estados = estados;
    }
}
