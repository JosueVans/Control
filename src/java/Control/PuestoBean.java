package Control;

import Dao.CargoDao;
import Dao.PuestoDao;
import DaoImpl.PuestoDaoImpl;
import Model.Cargo;
import Model.Rol;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.event.RowEditEvent;

@Named(value = "puestoBean")
@ViewScoped
public class PuestoBean implements Serializable {

    private int idRol;
    private int idCargo;
    private List<Rol> ListaRol;
    private List<Cargo> ListaCargo;
    private List<Cargo> ListaCargos;
    private Cargo selectedCargo;
    private Rol selectedRol;

    public PuestoBean() {
        this.ListaRol = new ArrayList();
        this.ListaCargo = new ArrayList();
    }

    public int getIdRol() {
        return idRol;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public List<Rol> getListaRol() {
        PuestoDao puesto = new PuestoDaoImpl();
        ListaRol = puesto.ListaRol();
        return ListaRol;
    }

    public List<Cargo> getListaCargos() {
        PuestoDao puesto = new PuestoDaoImpl();
        ListaCargos = puesto.ListaCargos();
        return ListaCargos;
    }

    public void setListaCargos(List<Cargo> ListaCargos) {
        this.ListaCargos = ListaCargos;
    }

    public List<Cargo> getListaCargo() {
        PuestoDao puesto = new PuestoDaoImpl();
        ListaCargo = puesto.ListaCargo(idRol);
        return ListaCargo;
    }

    public Cargo getSelectedCargo() {
        if (selectedCargo == null) {
            selectedCargo = new Cargo();
        }
        return selectedCargo;
    }

    public Rol getSelectedRol() {
        if (selectedRol == null) {
            selectedRol = new Rol();
        }
        return selectedRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public void setListaRol(List<Rol> ListaRol) {
        this.ListaRol = ListaRol;
    }

    public void setListaCargo(List<Cargo> ListaCargo) {
        this.ListaCargo = ListaCargo;
    }

    public void setSelectedCargo(Cargo selectedCargo) {
        this.selectedCargo = selectedCargo;
    }

    public void setSelectedRol(Rol selectedRol) {
        this.selectedRol = selectedRol;
    }

    public void createRol(ActionEvent actionEvent) {
        PuestoDao rolDao = new PuestoDaoImpl();
        String msg;
        if (rolDao.CreateRol(selectedRol)) {
            msg = "Se creó exitosamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error, no se pudo crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void onEditEventRol(RowEditEvent event) {
        PuestoDao rolDao = new PuestoDaoImpl();
        String msg;
        System.out.printf("rol " +((Rol) event.getObject()).getIdRol());
         System.out.printf("rol nombre" +((Rol) event.getObject()).getNombre());
          System.out.printf("rol " +this.selectedRol.getNombre());
         rolDao.UpdateRol((Rol) event.getObject());
         //  rolDao.UpdateRol(this.selectedRol);
        //this.selectedRol.setIdRol(((Rol) event.getObject()).getIdRol());
        //this.selectedRol.setNombre(((Rol) event.getObject()).getNombre());
        /*if (rolDao.UpdateRol(selectedRol)) {
                msg = "Se actualizó exitosamente el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                msg = "Error, no se pudo crear el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }*/
    }

    public void onCancel() {
        String msg;
        msg = "Se cancelo";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
    }

    
         public void updateRol(ActionEvent actionEvent) {
         PuestoDao rolDao = new PuestoDaoImpl();
        
        String msg;
      
            if (rolDao.UpdateRol(selectedRol)) {
                msg = "Se actualizó exitosamente el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                msg = "Error, no se pudo crear el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
       
    }
    public void deleteRol(ActionEvent actionEvent) {
        PuestoDao rolDao = new PuestoDaoImpl();
        String msg;
        System.out.printf("rol " + this.selectedRol.getIdRol());
        if (rolDao.DeleteRol(this.selectedRol.getIdRol())) {
            msg = "Se Eliminó exitosamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error, no se pudo eliminar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void createCargo(ActionEvent actionEvent) {
        PuestoDao cargoDao = new PuestoDaoImpl();
        String msg;
        if (cargoDao.CreateCargo(this.selectedCargo)) {
            msg = "Se creó exitosamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error, no se pudo crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    /* public void updateCargo(ActionEvent actionEvent) {
        EmpleadosDao empleadosDao = new EmpleadosDaoImpl();
        UsuarioDao usuarioDao = new UsuarioDao();
        String msg;
        if (usuarioDao.update(this.selectedEmpleado.getUsuarios())) {
            this.selectedEmpleado.setModificaciondate(fecha());
            if (empleadosDao.Update(this.selectedEmpleado)) {
                msg = "Se actualizó exitosamente el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                msg = "Error, no se pudo crear el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public void deleteCargo(ActionEvent actionEvent) {
        EmpleadosDao empleadosDao = new EmpleadosDaoImpl();
        UsuarioDao usuarioDao = new UsuarioDao();
        String msg;
        this.selectedUsuario = this.selectedEmpleado.getUsuarios();
        if (empleadosDao.Delete(this.selectedEmpleado.getIdEmpleado())) {
            if (usuarioDao.elimina(this.selectedUsuario.getUserId())) {
                msg = "Se Eliminó exitosamente el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                msg = "Error, no se pudo eliminar el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }
     */
}
