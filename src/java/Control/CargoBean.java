/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Dao.CargoDao;
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

@Named(value = "cargoBean")
@RequestScoped
public class CargoBean {

    private Cargo selectedCargo;
    private Rol selectedRol;
    private List<SelectItem> lista, listaCargoRol, l;
    private List<Cargo> cargo;

    public Rol getSelectedRol() {
        if (selectedRol == null) {
            selectedRol = new Rol();
        }
        return selectedRol;
    }

    public void setSelectedRol(Rol selectedRol) {
        this.selectedRol = selectedRol;
    }

    public CargoBean() {
        this.cargo=new ArrayList<Cargo>();
        this.listaCargoRol=new ArrayList<SelectItem>();
        this.selectedRol=new Rol();
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

    public List<Cargo> getCargo() {
        CargoDao cargoDao=new CargoDao();
        this.cargo=cargoDao.BuscarTodos();
        return cargo;
    }

    public List<SelectItem> getListaCargoRol() {
        CargoDao cargoDao= new CargoDao();      
        List<Cargo> list =  cargoDao.BuscarTodos();
        listaCargoRol.clear();
        for (Cargo cargo: list){
            if(cargo.getRol().getIdRol()==this.selectedCargo.getRol().getIdRol()){
            SelectItem cargoItem= new SelectItem(cargo.getIdCargo(), cargo.getCargo());
            this.listaCargoRol.add(cargoItem);
           }
        
        }
        return listaCargoRol;
    }   
        public List<SelectItem> l(int rol){
        CargoDao cargoDao= new CargoDao();
        this.listaCargoRol=new ArrayList<SelectItem>();
      //Rol rol= new Rol();
       //this.selectedRol.getIdRol();
       
        List<Cargo> list =  cargoDao.BuscarTodos();
        listaCargoRol.clear();
        for (Cargo cargo: list){
            if(cargo.getRol().getIdRol()==rol){
            SelectItem cargoItem= new SelectItem(cargo.getIdCargo(), cargo.getCargo());
            this.listaCargoRol.add(cargoItem);
           }
        
        }
        
     
        return listaCargoRol;
    }

    public List<SelectItem> getListaCargos() {
        CargoDao cargoDao = new CargoDao();
        this.lista = new ArrayList<SelectItem>();
        List<Cargo> list = cargoDao.BuscarTodos();
        lista.clear();
        for (Cargo cargo : list) {
            SelectItem cargoItem = new SelectItem(cargo.getIdCargo(), cargo.getCargo());
            this.lista.add(cargoItem);
        }
        return lista;
    }
    
    

    public void createCargo(ActionEvent actionEvent) {
        CargoDao cargoDao = new CargoDao();
        String msg;
        this.selectedCargo.setCargo(this.selectedCargo.getCargo());
        this.selectedCargo.setRol(this.selectedCargo.getRol());
        //System.out.printf("el cargo" + this.selectedCargo.getCargo());
        if (compararCargo()){
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
    
    public boolean compararCargo() {
        CargoDao cargoDao = new CargoDao();
        boolean flag;
        String msg;
        if (cargoDao.buscarPorCargo(this.selectedCargo) != null) {
            msg = "El cargo ingresado ya existe, no se puede crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

}
