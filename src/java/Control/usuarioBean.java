package Control;

import Dao.UsuarioDao;
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

@Named(value = "usuarioBean")
@RequestScoped
public class usuarioBean {

    private List<Usuarios> usuarios;
    private Usuarios selectedUsuario;

    public usuarioBean() {
        this.usuarios = new ArrayList<Usuarios>();
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

    public boolean verificarCorreo() {
        boolean flag;
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(this.selectedUsuario.getCorreo());
        if (mather.find() == true) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    public void createUsuario(ActionEvent actionEvent) {
        UsuarioDao usuarioDao = new UsuarioDao();
        String msg;
        verificarCorreo();
        if (verificarCorreo()) {

           // this.selectedUsuario.setPassword(this.selectedUsuario.getNomUsuario());
            //this.selectedUsuario.setNomUsuario("admin");
            if (usuarioDao.create(this.selectedUsuario)) {
                msg = "Se creó exitosamente el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                msg = "Error, no se pudo crear el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            msg = "Error El formato de correo no es valido, no se pudo crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void updateUsuario(ActionEvent actionEvent) {
        UsuarioDao usuarioDao = new UsuarioDao();
        String msg;
        verificarCorreo();
        if (verificarCorreo()) {

            if (usuarioDao.update(this.selectedUsuario)) {
                msg = "Se modificó exitosamente el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                msg = "Error, no se pudo modificar el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            msg = "Error El formato de correo no es valido, no se pudo crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void deleteUsuario(ActionEvent actionEvent) {
        UsuarioDao usuarioDao = new UsuarioDao();
        String msg;
        
          
       // if (usuarioDao.eliminaUsuario(this.selectedUsuario.getUserId())) {
           if (usuarioDao.eliminaUsuario(this.selectedUsuario)) {
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
