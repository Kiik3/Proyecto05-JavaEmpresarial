
package com.manejadores;

import com.controladores.RentaControlador;
import com.entidades.AdmRenRenta;
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
public class RentaManejador extends AbastractoManejador<AdmRenRenta>{
    
    private List<AdmRenRenta> rentas = new ArrayList<AdmRenRenta>();
    
    @PostConstruct
    @Override
    public void inicializar(){
        claseEntidad = new AdmRenRenta();
        claseEntidadControlador = new RentaControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
        rentas = claseEntidadControlador.encontrarEntidades();
    }
    
    @Override
    public void nuevaEntidad(){
        claseEntidad = new AdmRenRenta();
        claseEntidadControlador = new RentaControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
    }

    public List<AdmRenRenta> getRentas() {
        return rentas;
    }

    public void setRentas(List<AdmRenRenta> rentas) {
        this.rentas = rentas;
    }

}
