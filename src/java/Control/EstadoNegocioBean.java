/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Dao.EstadoNegocioDao;
import Model.EstadoNegocio;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author oracle
 */
@Named(value = "estadoNegocioBean")
@RequestScoped
public class EstadoNegocioBean {

    private EstadoNegocio selectedEstadoNegocio;
    private List<SelectItem> lista;
    private List<EstadoNegocio> EstadoNegocio;

    public EstadoNegocioBean() {

    }

    public EstadoNegocio getSelectedEstadoNegocio() {
        if (selectedEstadoNegocio == null) {
            selectedEstadoNegocio = new EstadoNegocio();
        }
        return selectedEstadoNegocio;
    }

    public List<EstadoNegocio> getEstadoNegocio() {
        EstadoNegocioDao estadoNegocioDao = new EstadoNegocioDao();
        this.EstadoNegocio = estadoNegocioDao.BuscarTodos();
        return EstadoNegocio;
    }

    public void setSelectedEstadoNegocio(EstadoNegocio selectedEstadoNegocio) {
        this.selectedEstadoNegocio = selectedEstadoNegocio;
    }

    public void createEstadoNegocio(ActionEvent actionEvent) {
        EstadoNegocioDao estadoNegocioDao = new EstadoNegocioDao();
        String msg;

        if (compararEstadoNegocio()) {
            if (estadoNegocioDao.create(this.selectedEstadoNegocio)) {
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

    public void updateEstadoNegocio(ActionEvent actionEvent) {
        EstadoNegocioDao estadoNegocioDao = new EstadoNegocioDao();
        String msg;
        if (estadoNegocioDao.update(this.selectedEstadoNegocio)) {
            msg = "Se actualizó exitosamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error, no se pudo crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void deleteEstadoNegocio(ActionEvent actionEvent) {
        EstadoNegocioDao estadoNegocioDao = new EstadoNegocioDao();
        String msg;
        if (estadoNegocioDao.delete(this.selectedEstadoNegocio.getIdEstadoNegocio())) {
            msg = "Se Eliminó exitosamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error, no se pudo eliminar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public boolean compararEstadoNegocio() {
        EstadoNegocioDao estadoNegocioDao = new EstadoNegocioDao();
        boolean flag;
        String msg;
        if (estadoNegocioDao.buscarPorEstadoNegocio(this.selectedEstadoNegocio.getEstadoNegocio()) != null) {
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
