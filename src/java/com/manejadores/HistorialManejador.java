
package com.manejadores;

import com.controladores.HistorialControlador;
import com.entidades.AdmHisHistorialPago;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.utilidades.Utilidades;

/**
 *
 * @author Enrique Ochoa
 */

@ManagedBean
@ViewScoped
public class HistorialManejador extends AbastractoManejador<AdmHisHistorialPago>{
    
    //Inyección del manejador de Planilla
    @ManagedProperty(value = "#{planillaManejador}")
    private PlanillaManejador planilla;
    
    private List<AdmHisHistorialPago> empleados = new ArrayList<AdmHisHistorialPago>();
    private List<AdmHisHistorialPago> pagosEmpleado = new ArrayList<AdmHisHistorialPago>();
    private boolean detallePago = false; //Bandera para mostrar la lista de empleados o los detalles de pago de cada empleado
    
    @PostConstruct
    @Override
    public void inicializar(){
        claseEntidad = new AdmHisHistorialPago();
        claseEntidadControlador = new HistorialControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
        empleados = claseEntidadControlador.encontrarEntidades();
    }
    
    //Se listan los detalles de pago de un empleado
    public void historialEmpleado(AdmHisHistorialPago emp) throws IOException{
        pagosEmpleado = claseEntidadControlador.encontrarPuestos(emp.getHisIdEmpleado());
        detallePago = true;
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

    public List<AdmHisHistorialPago> getPagosEmpleado() {
        return pagosEmpleado;
    }

    public void setPagosEmpleado(List<AdmHisHistorialPago> pagosEmpleado) {
        this.pagosEmpleado = pagosEmpleado;
    }

    public boolean isDetallePago() {
        return detallePago;
    }

    public void setDetallePago(boolean detallePago) {
        this.detallePago = detallePago;
    }
    
}
