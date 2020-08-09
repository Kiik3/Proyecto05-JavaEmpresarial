
package com.manejadores;

import com.controladores.FechaPagoControlador;
import com.controladores.HistorialControlador;
import com.controladores.PlanillaControlador;
import com.entidades.AdmFecFechaPago;
import com.entidades.AdmHisHistorialPago;
import com.entidades.AdmPlaPlanilla;
import com.propiedades.Propiedades;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
public class PlanillaManejador {
    
    private AdmPlaPlanilla planilla;
    private PlanillaControlador planillaControlador;
    
    private AdmHisHistorialPago historial;
    private HistorialControlador historialControlador;
    
    private AdmFecFechaPago admFecFechaPago;
    private FechaPagoControlador fechaPagoControlador;
    
    //Inyecciones de manejadores
    @ManagedProperty(value = "#{incioSesionManejador}")
    private IncioSesionManejador sesion;
    
    @ManagedProperty(value = "#{descuentoManejador}")
    private DescuentoManejador descuento;
    
    @ManagedProperty(value = "#{rentaManejador}")
    private RentaManejador renta;
    
    private List<AdmPlaPlanilla> planillas = new ArrayList<AdmPlaPlanilla>();
    private List<AdmHisHistorialPago> elementos;
    private boolean flagVerDetalles; //bandera que determina si se muestra el listado de planillas o el detalle de pago
    Propiedades propiedades = new Propiedades();
    
    @PostConstruct
    public void inicializar(){
        planilla = new AdmPlaPlanilla();
        elementos = new ArrayList<AdmHisHistorialPago>();
        planillaControlador = new PlanillaControlador(planilla);
        planillaControlador.getEntityManager();
        
        //Se obtienen todos los pagos de cada empleado en planilla
        elementos = planillaControlador.calcularPlanilla(descuento.getDescuentos(), renta.getRentas());
        planilla.setPlaFecha(new Date());
        planilla.setPlaTotalSalario(planillaControlador.totalSalario);
        planilla.setPlaTotalDescuento(planillaControlador.totalDescuento);
        planilla.setPlaTotalPago(planillaControlador.totalPago);
        planillas = planillaControlador.encontrarEntidades();
        
        admFecFechaPago = new AdmFecFechaPago();
        fechaPagoControlador = new FechaPagoControlador(admFecFechaPago);
        historial = new AdmHisHistorialPago();
        flagVerDetalles = false;

    }
    //Pago de planilla
    public void pagar(){
        int fechaPago = fechaPagoControlador.encontrarPorId(1).getFecFecha();
        boolean pagar = Boolean.valueOf(fechaPagoControlador.encontrarPorId(1).getFecPagar());
        //Se valida que se día de pago
        if(!pagar){
            Utilidades.mensajeError("No, se pudo pagar, fecha de pago: " + fechaPago + " de cada mes");
        }
        else{
            planillaControlador.insertarEntidad();
            System.out.println(planilla.getPlaId());
            System.out.println(elementos);
            for(AdmHisHistorialPago h:elementos){
                System.out.println(h.getHisIdEmpleado());
                h.setPlaId(planilla);
                historialControlador = new HistorialControlador(h);
                historialControlador.getEntityManager();
                historialControlador.insertarEntidad();
            }
            Utilidades.mensajeExito("Planilla pagada correctamente");
            inicializar();

            admFecFechaPago = fechaPagoControlador.encontrarPorId(1);
            
            admFecFechaPago.setFecPagar("false");
            fechaPagoControlador.actualizarEntidad(admFecFechaPago);
            sesion.avisoPagoPlanilla();
        }

    }
    //Permite ver los detalles de la planilla seleccionada
    public void verDetalles(int id) throws IOException{
        elementos = planillaControlador.verDetallesPlanilla(id);
        System.out.println(id);
        System.out.println(elementos);
        planilla = (AdmPlaPlanilla) planillaControlador.encontrarPorId(id);
        flagVerDetalles = true;
    }

    public AdmPlaPlanilla getPlanilla() {
        return planilla;
    }

    public void setPlanilla(AdmPlaPlanilla planilla) {
        this.planilla = planilla;
    }

    public PlanillaControlador getPlanillaControlador() {
        return planillaControlador;
    }

    public void setPlanillaControlador(PlanillaControlador planillaControlador) {
        this.planillaControlador = planillaControlador;
    }

    public List<AdmPlaPlanilla> getPlanillas() {
        return planillas;
    }

    public void setPlanillas(List<AdmPlaPlanilla> planillas) {
        this.planillas = planillas;
    }

    public List<AdmHisHistorialPago> getElementos() {
        return elementos;
    }

    public void setElementos(List<AdmHisHistorialPago> elementos) {
        this.elementos = elementos;
    }

    public DescuentoManejador getDescuento() {
        return descuento;
    }

    public void setDescuento(DescuentoManejador descuento) {
        this.descuento = descuento;
    }

    public RentaManejador getRenta() {
        return renta;
    }

    public void setRenta(RentaManejador renta) {
        this.renta = renta;
    }

    public boolean isFlagVerDetalles() {
        return flagVerDetalles;
    }

    public void setFlagVerDetalles(boolean flagVerDetalles) {
        this.flagVerDetalles = flagVerDetalles;
    }

    public IncioSesionManejador getSesion() {
        return sesion;
    }

    public void setSesion(IncioSesionManejador sesion) {
        this.sesion = sesion;
    }
    
}
