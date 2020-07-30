
package utilidades;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Enrique Ochoa
 */
public class Utilidades {
 
    public static void mensajeExito(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public static void mensajeError(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public static void redireccion(String url) throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    } 
}
