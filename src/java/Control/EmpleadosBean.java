package Control;

import Dao.EmpleadosDao;
import Dao.UsuarioDao;
import DaoImpl.PuestoDaoImpl;
import Model.Cargo;
import Model.Empleados;
import Model.Rol;
import Model.Usuarios;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import Dao.PuestoDao;
import DaoImpl.EmpleadosDaoImpl;
import DaoImpl.UsuarioDaoImpl;

@Named(value = "empleadosBean")
@ViewScoped
public class EmpleadosBean implements Serializable {

    private List<Empleados> empleados;
    private List<Usuarios> usuarios;
    private int idRol;
    private int idCargo;
    private List<Rol> ListaRol;
    private List<Cargo> ListaCargoPorRol;
    private Cargo selectedCargo;
    private Rol selectedRol;
    private Usuarios selectedUsuario;
    private Empleados selectedEmpleado;

    public EmpleadosBean() {
        this.empleados = new ArrayList();
        this.usuarios = new ArrayList();
        this.ListaCargoPorRol = new ArrayList();
        this.ListaRol = new ArrayList();
    }

    public List<Rol> getListaRol() {
        PuestoDao rol = new PuestoDaoImpl();
        this.ListaRol = rol.ListaRol();
        return ListaRol;
    }

    public List<Cargo> getListaCargoPorRol() {
        PuestoDao rol = new PuestoDaoImpl();
        ListaCargoPorRol = rol.ListaCargo(idRol);
        return ListaCargoPorRol;
    }

    public Rol getSelectedRol() {
        if (selectedRol == null) {
            selectedRol = new Rol();
        }
        return selectedRol;
    }

    public void setEmpleados(List<Empleados> empleados) {
        this.empleados = empleados;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public void setListaRol(List<Rol> ListaRol) {

        this.ListaRol = ListaRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public int getIdRol() {
        return idRol;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setListaCargoPorRol(List<Cargo> ListaCargoPorRol) {
        this.ListaCargoPorRol = ListaCargoPorRol;
    }

    public void setSelectedRol(Rol selectedRol) {
        this.selectedRol = selectedRol;
    }

    public Cargo getSelectedCargo() {
        if (selectedCargo == null) {
            selectedCargo = new Cargo();
        }
        return selectedCargo;
    }

    public void setSelectedCargo(Cargo selectedCargo) {
        this.selectedCargo = selectedCargo;
    }

    public List<Empleados> getEmpleados() {
        EmpleadosDao empleadoDao = new EmpleadosDaoImpl();
        this.empleados = empleadoDao.ListaEmpleados();
        return empleados;
    }

    public List<Usuarios> getUsuarios() {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        this.usuarios = usuarioDao.BuscarTodos();
        return usuarios;
    }

    public Usuarios getSelectedUsuario() {
        if (selectedUsuario == null) {
            selectedUsuario = new Usuarios();
        }
        return selectedUsuario;
    }

    public Empleados getSelectedEmpleado() {
        if (selectedEmpleado == null) {
            selectedEmpleado = new Empleados();
        }
        return selectedEmpleado;
    }

    public void setSelectedUsuario(Usuarios selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public void setSelectedEmpleado(Empleados selectedEmpleado) {
        this.selectedEmpleado = selectedEmpleado;
    }

    public Date fecha() {
        Date fecha = new Date();
        return fecha;
    }

    public void createEmpleados(ActionEvent actionEvent) {
        EmpleadosDao empleadosDao = new EmpleadosDaoImpl();
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        String msg, nombre;
        this.selectedEmpleado.setNombre(this.selectedUsuario.getNomUsuario().toLowerCase());
        nombre = this.selectedUsuario.getNomUsuario().toLowerCase().charAt(0) + this.selectedEmpleado.getApellido().toLowerCase();
        this.selectedUsuario.setNomUsuario(nombre);
        this.selectedUsuario.setEstado("true");
        if (usuarioDao.create(this.selectedUsuario)) {
            this.selectedEmpleado.setUsuarios(selectedUsuario);
            this.selectedEmpleado.setApellido(this.selectedEmpleado.getApellido().toLowerCase());
            this.selectedEmpleado.setCreaciondate(fecha());
            this.selectedEmpleado.setModificaciondate(fecha());
            if (empleadosDao.Create(this.selectedEmpleado)) {
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

    public void updateEmpleados(ActionEvent actionEvent) {
        EmpleadosDao empleadosDao = new EmpleadosDaoImpl();
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
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

    public void deleteEmpleado(ActionEvent actionEvent) {
        EmpleadosDao empleadosDao = new EmpleadosDaoImpl();
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
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

    public boolean verificarCorreo() {
        boolean flag;
        String msg;
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(this.selectedUsuario.getCorreo());
        if (mather.find() == true) {
            flag = true;
        } else {
            msg = "Error El formato de correo no es valido, no se pudo crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            flag = false;
        }
        return flag;
    }

    public boolean compararCorreo() {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        boolean flag;
        String msg;
        if (usuarioDao.buscarPorCorreo(this.selectedUsuario) != null) {
            msg = "El correo ingresado ya existe, no se puede crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }
}
