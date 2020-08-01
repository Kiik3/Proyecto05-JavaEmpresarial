
package com.manejadores;

import com.controladores.DepartamentoControlador;
import com.entidades.AdmDepDepartamento;
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
public class DepartamentoManejador extends AbastractoManejador<AdmDepDepartamento>{
    
    private List<AdmDepDepartamento> departamentos = new ArrayList<AdmDepDepartamento>();
    
    @PostConstruct
    @Override
    public void inicializar(){
        claseEntidad = new AdmDepDepartamento();
        claseEntidadControlador = new DepartamentoControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
        departamentos = claseEntidadControlador.encontrarEntidades();
    }
    
    @Override
    public void nuevaEntidad(){
        claseEntidad = new AdmDepDepartamento();
        claseEntidadControlador = new DepartamentoControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
    }

    public List<AdmDepDepartamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<AdmDepDepartamento> departamentos) {
        this.departamentos = departamentos;
    }

}
