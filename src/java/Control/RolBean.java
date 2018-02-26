/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

//import Dao.RolDao;
import Dao.RolDao;
import Dao.RolDaoImpl;
import Model.Rol;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;

@Named(value = "rolBean")
@RequestScoped
public class RolBean {
private Rol selectedRol;
private List<Rol> listaRol;
private List <SelectItem> listaItemRol;

    public RolBean() {
        this.listaRol=new ArrayList<Rol>();
    }

    public Rol getSelectedRol() {
        if(selectedRol==null){
        selectedRol=new Rol();
        }
        return selectedRol;
    }

   

    public void setSelectedRol(Rol selectedRol) {
        this.selectedRol = selectedRol;
    }

    public List<Rol> getListaRol() {
       RolDao rolDao= new RolDaoImpl();
        this.listaRol=rolDao.BuscarTodos();
        return listaRol;
    }

    public List<SelectItem> getListaItemRol() {
        RolDao rolDao=new RolDaoImpl();
        this.listaItemRol= new ArrayList<SelectItem>();
        this.listaRol=rolDao.BuscarTodos();
        listaItemRol.clear();
        for (Rol rol: this.listaRol){
        SelectItem rolItem= new SelectItem(rol.getIdRol(), rol.getNombre());
        this.listaItemRol.add(rolItem);
        }     
        return listaItemRol;
    }
    
    
    
    
    
}
