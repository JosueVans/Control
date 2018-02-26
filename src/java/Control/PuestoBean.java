package Control;

import Dao.CargoDao;
import Dao.PuestoDao;
import DaoImpl.PuestoDaoImpl;
import Model.Cargo;
import Model.Rol;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@Named(value = "puestoBean")
@RequestScoped
public class PuestoBean {

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
        PuestoDao puesto=new PuestoDaoImpl();
        ListaCargos=puesto.ListaCargos();
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
        return selectedCargo;
    }

    public Rol getSelectedRol() {
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

    public void createCargo(ActionEvent actionEvent) {
        CargoDao cargoDao = new CargoDao();
        String msg;
        System.out.printf("el cargo" + this.selectedCargo.getCargo());

        if (cargoDao.create(this.selectedCargo)) {
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
