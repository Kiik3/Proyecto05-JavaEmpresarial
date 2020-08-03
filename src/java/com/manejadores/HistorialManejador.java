
package com.manejadores;

import com.controladores.HistorialControlador;
import com.entidades.AdmHisHistorialPago;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Enrique Ochoa
 */

@ManagedBean
@ViewScoped
public class HistorialManejador extends AbastractoManejador<AdmHisHistorialPago>{
    
    @ManagedProperty(value = "#{planillaManejador}")
    private PlanillaManejador planilla;
    
    private List<AdmHisHistorialPago> empleados = new ArrayList<AdmHisHistorialPago>();
    
    @PostConstruct
    @Override
    public void inicializar(){
        claseEntidad = new AdmHisHistorialPago();
        claseEntidadControlador = new HistorialControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
//        elementos = planilla.getPlanillas();
        empleados = claseEntidadControlador.encontrarEntidades();
    }

    @Override
    public void nuevaEntidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PlanillaManejador getPlanilla() {
        return planilla;
    }

    public void setPlanilla(PlanillaManejador planilla) {
        this.planilla = planilla;
    }

    public List<AdmHisHistorialPago> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<AdmHisHistorialPago> empleados) {
        this.empleados = empleados;
    }
    
}
