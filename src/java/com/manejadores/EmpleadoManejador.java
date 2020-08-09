
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
import com.utilidades.Utilidades;

/**
 *
 * @author Enrique Ochoa
 */

@ManagedBean
@ViewScoped
public class EmpleadoManejador extends AbastractoManejador<AdmEmpEmpleado>{
    
    private List<AdmEmpEmpleado> empleados = new ArrayList<AdmEmpEmpleado>();
    private List<AdmEmpEmpleado> jefes = new ArrayList<AdmEmpEmpleado>();
    private boolean flagEstado = true; //Bandera que determina si se muestra o no el inputText de estado
    
    @PostConstruct
    @Override
    public void inicializar(){
        claseEntidad = new AdmEmpEmpleado();
        claseEntidad.setEstId(new AdmEstEstado());
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
        claseEntidad.setEstId(new AdmEstEstado(1)); //Se inserta estado Activo a cada nuevo empleado que se ingresa
        claseEntidad.setEmpIdJefe(new AdmEmpEmpleado());
        claseEntidad.setPueId(new AdmPuePuestoTrabajo(new AdmPuePuestoTrabajoPK()));
        claseEntidadControlador = new EmpleadoControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();

        flagEstado = false;
    }
    
    @Override
    public void insertar(){
        
        //Se valida que no se ingrese un salario menor o mayor al rango ya establecido
        if(claseEntidad.getEmpSalario() < claseEntidad.getPueId().getPueSalarioMinimo() || claseEntidad.getEmpSalario() > claseEntidad.getPueId().getPueSalarioMaximo()){
            Utilidades.mensajeError("El salario según el puesto escogido no debe ser menor a " + claseEntidad.getPueId().getPueSalarioMinimo() + ", ni mayor a " + claseEntidad.getPueId().getPueSalarioMaximo());
            System.out.println("Entra a validacion");
            inicializar();
        }
        else{
            //Si el nuevo empleado no tiene jefe, se ingresa null
            if(claseEntidad.getEmpIdJefe() == null && claseEntidad.getEmpId() == null){
                claseEntidadControlador.insertarEntidad();
                Utilidades.mensajeExito("Realizado correctamente");
                inicializar();
            }
            //Si el empleado a actualizar no tiene jefe, se ingresa null
            else if(claseEntidad.getEmpIdJefe() == null ){
                claseEntidadControlador.actualizarNativo(claseEntidad);
                System.out.println("Actualizo jefe nulo");
                Utilidades.mensajeExito("Realizado correctamente");
                inicializar();
            }
            else{
                claseEntidadControlador.actualizarEntidad(claseEntidad);
                Utilidades.mensajeExito("Realizado correctamente");
                inicializar();
            }
        }
   
    }
    
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

    public boolean isFlagEstado() {
        return flagEstado;
    }

    public void setFlagEstado(boolean flagEstado) {
        this.flagEstado = flagEstado;
    }

}
