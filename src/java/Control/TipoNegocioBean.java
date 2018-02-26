
package Control;

import Dao.TipoNegocioDao;
import Model.TipoNegocio;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@Named(value = "tipoNegocioBean")
@RequestScoped
public class TipoNegocioBean {
private TipoNegocio selectedTipoNegocio;
    private List<SelectItem> lista;
    private List<TipoNegocio> TipoNegocio;

    public TipoNegocio getSelectedTipoNegocio() {
        if (selectedTipoNegocio == null) {
            selectedTipoNegocio = new TipoNegocio();
        }
        return selectedTipoNegocio;
    }

    public void setSelectedTipoNegocio(TipoNegocio selectedTipoNegocio) {
        this.selectedTipoNegocio = selectedTipoNegocio;
    }

    

    public List<TipoNegocio> getTipoNegocio() {
         TipoNegocioDao tipoNegocioDao=new TipoNegocioDao();
       this.TipoNegocio=tipoNegocioDao.BuscarTodos();
        return TipoNegocio;
    }

   
    
    public TipoNegocioBean() {
        this.TipoNegocio=new ArrayList<TipoNegocio>();
    }
    
    
     public void createTipoNegocio(ActionEvent actionEvent) {
        TipoNegocioDao tipoNegocioDao = new TipoNegocioDao();
        String msg;
        
      if (compararTipoNegocio()){
        if (tipoNegocioDao.create(this.selectedTipoNegocio)) {
            msg = "Se creó exitosamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error, no se pudo crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
       }
    }
     
      public void updateTipoNegocio(ActionEvent actionEvent) {
        TipoNegocioDao tipoNegocioDao = new TipoNegocioDao();
        String msg;
             if (tipoNegocioDao.update(this.selectedTipoNegocio)) {
            msg = "Se actualizó exitosamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error, no se pudo crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }

    public void deleteTipoNegocio(ActionEvent actionEvent) {
        TipoNegocioDao tipoNegocioDao = new TipoNegocioDao();
        String msg;
        if (tipoNegocioDao.delete(this.selectedTipoNegocio.getIdTipoNegocio())) {
            msg = "Se Eliminó exitosamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error, no se pudo eliminar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
}
  
public boolean compararTipoNegocio() {
        TipoNegocioDao tipoNegocioDao = new TipoNegocioDao();
        boolean flag;
        String msg;
        if (tipoNegocioDao.buscarPorTipoNegocio(this.selectedTipoNegocio.getTipoNegocio()) != null) {
            msg = "El Tipo de Negocio ingresado ya existe, no se puede crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }
}
