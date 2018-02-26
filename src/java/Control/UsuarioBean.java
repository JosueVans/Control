package Control;

//import Dao.CargoDao;
import Dao.CargoDao;
import Dao.UsuarioDao;
import Model.Cargo;
import Model.Empleados;
//import Model.Cargo;
import Model.Usuarios;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import Dao.PuestoDao;
//import javax.faces.model.SelectItem;

@Named(value = "usuarioBean")
@RequestScoped
public class UsuarioBean {
/*
    private List<Usuarios> usuarios;
    
    private Usuarios selectedUsuario;
   private Cargo selectedCargo;
   private Empleados selectedEmpleado;
    // UsuarioDao usuarioDao;

    public usuarioBean() {
        this.usuarios = new ArrayList<Usuarios>();
        //this.usuarioDao = new UsuarioDao();
    }

    public List<Usuarios> getUsuarios() {
        UsuarioDao usuarioDao = new UsuarioDao();
        this.usuarios = usuarioDao.BuscarTodos();
        return usuarios;
    }

    
    public Usuarios getSelectedUsuario() {
        if (selectedUsuario == null) {
            selectedUsuario = new Usuarios();
        }
        return selectedUsuario;

    }

    public void setSelectedUsuario(Usuarios selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

   

    

    

   

    
   

   

    public void createUsuario(ActionEvent actionEvent) {
        UsuarioDao usuarioDao = new UsuarioDao();
        PuestoDao empleadosDao= new PuestoDao();
        
        this.selectedEmpleado.setUsuarios(selectedUsuario);
        this.selectedEmpleado.setCargo(selectedCargo);
       // CargoDao cargoDao = new CargoDao();
        //Cargo selectedCargo = new Cargo();
        String msg;
       // if (compararCorreo() && verificarCorreo()) {
            if (usuarioDao.create(this.selectedUsuario)) {
               
                    if(empleadosDao.create(selectedEmpleado)){
                    msg = "Se creó exitosamente el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                    }
               
            } else {
                msg = "Error, no se pudo crear el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
      //  }
    }


    /*public void updateUsuario(ActionEvent actionEvent) {
        UsuarioDao usuarioDao = new UsuarioDao();
        String msg;
       // if (verificarCorreo()) {
            if (usuarioDao.update(this.selectedUsuario)) {
                msg = "Se modificó exitosamente el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                msg = "Error, no se pudo modificar el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
    //    }
    else {
            msg = "Error El formato de correo no es valido, no se pudo crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    

    public void deleteUsuario(ActionEvent actionEvent) {
        UsuarioDao usuarioDao = new UsuarioDao();
        String msg;
        // if (usuarioDao.eliminaUsuario(this.selectedUsuario.getUserId())) {
        if (usuarioDao.elimina(this.selectedUsuario)) {
            msg = "Se Eliminó exitosamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error, no se pudo eliminar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }*/

}
