
package com.manejadores;

import com.controladores.DescuentoControlador;
import com.entidades.AdmDesDescuentoLey;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Enrique Ochoa
 */

@ManagedBean
public class DescuentoManejador extends AbastractoManejador<AdmDesDescuentoLey>{
    
    private List<AdmDesDescuentoLey> descuentos = new ArrayList<AdmDesDescuentoLey>();
    
    @PostConstruct
    @Override
    public void inicializar(){
        claseEntidad = new AdmDesDescuentoLey();
        claseEntidadControlador = new DescuentoControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
        descuentos = claseEntidadControlador.encontrarEntidades();
    }
    
    @Override
    public void nuevaEntidad(){
        claseEntidad = new AdmDesDescuentoLey();
        claseEntidadControlador = new DescuentoControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
    }

    public List<AdmDesDescuentoLey> getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(List<AdmDesDescuentoLey> descuentos) {
        this.descuentos = descuentos;
    }

}
