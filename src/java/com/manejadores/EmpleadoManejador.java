
package com.manejadores;

import com.controladores.EmpleadoControlador;
import com.entidades.AdmEmpEmpleado;
import com.entidades.AdmEstEstado;
import com.entidades.AdmPuePuestoTrabajo;
import com.entidades.AdmPuePuestoTrabajoPK;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utilidades.Utilidades;

/**
 *
 * @author Enrique Ochoa
 */

@ManagedBean
@ViewScoped
public class EmpleadoManejador extends AbastractoManejador<AdmEmpEmpleado>{
    
    private List<AdmEmpEmpleado> empleados = new ArrayList<AdmEmpEmpleado>();
    private List<AdmEmpEmpleado> jefes = new ArrayList<AdmEmpEmpleado>();
    
    @PostConstruct
    @Override
    public void inicializar(){
        claseEntidad = new AdmEmpEmpleado();
        claseEntidad.setEmpIdJefe(new AdmEmpEmpleado());
        claseEntidad.setPueId(new AdmPuePuestoTrabajo(new AdmPuePuestoTrabajoPK()));
        claseEntidadControlador = new EmpleadoControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
        empleados = claseEntidadControlador.encontrarEntidades();
        jefes = claseEntidadControlador.encontrarJefes();
    }
    
    @Override
    public void nuevaEntidad(){
        claseEntidad = new AdmEmpEmpleado();
        claseEntidad.setEmpIdJefe(new AdmEmpEmpleado());
        claseEntidad.setEmpIdJefe(new AdmEmpEmpleado());
        claseEntidad.setPueId(new AdmPuePuestoTrabajo(new AdmPuePuestoTrabajoPK()));
        claseEntidadControlador = new EmpleadoControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
    }
    
    @Override
    public void insertar(){
        AdmEstEstado estado = new AdmEstEstado(1);
        claseEntidad.setEstId(estado);
        int i = claseEntidad.getPueId().getAdmPuePuestoTrabajoPK().getPueId();
        AdmPuePuestoTrabajo puesto = (AdmPuePuestoTrabajo) claseEntidadControlador.encontrarPorId(i);
        claseEntidad.getPueId().getAdmPuePuestoTrabajoPK().setDepId(puesto.getAdmPuePuestoTrabajoPK().getDepId());
        
        if(claseEntidad.getEmpSalario() < puesto.getPueSalarioMinimo() || claseEntidad.getEmpSalario() > puesto.getPueSalarioMaximo()){
            Utilidades.mensajeError("El salario según el puesto escogido no debe ser menor a " + puesto.getPueSalarioMinimo() + ", ni mayor a " + puesto.getPueSalarioMaximo());
        }
        else{
            claseEntidadControlador.insertarEntidad();
            Utilidades.mensajeExito("Ingresado correctamente");
            inicializar();
            
        }
        
        
        
    }
//    
//    public void actualizar(int id, AdmEmpEmpleado empleado){        
//        empleado.setRolId((AdmRolRol) claseEntidadControlador.encontrarPorId(id));
//        claseEntidadControlador.actualizarEntidad(empleado);
//        Utilidades.mensajeExito("Ingresado correctamente");
//        inicializar();
//    }

    public List<AdmEmpEmpleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<AdmEmpEmpleado> empleados) {
        this.empleados = empleados;
    }

    public List<AdmEmpEmpleado> getJefes() {
        return jefes;
    }

    public void setJefes(List<AdmEmpEmpleado> jefes) {
        this.jefes = jefes;
    }

}
