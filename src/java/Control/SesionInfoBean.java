package Control;

import Dao.EmpleadosDao;
import DaoImpl.EmpleadosDaoImpl;
import Model.Empleados;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named(value = "sesionInfoBean")
@RequestScoped
public class SesionInfoBean {

    Empleados empleado;
    String info;
    int id;

    public int getId() {
        return id;
    }

    public SesionInfoBean() {
 InfoUsuario();
    }

    public String getInfo() {
        InfoUsuario();
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void InfoUsuario() {
        int idU;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = request.getSession(false);
        //this.info = "Usuario " + session.getAttribute("usuario") + session.getAttribute("userId");
        idU = (int) session.getAttribute("userId");
        EmpleadosDao em = new EmpleadosDaoImpl();
        this.empleado=em.Search(idU);
        this.info=empleado.getNombre().toUpperCase().charAt(0)+ empleado.getNombre().substring(1) +" "+empleado.getApellido().toUpperCase().charAt(0)+empleado.getApellido().substring(1);
       // System.out.printf("empleado" + this.empleado.getApellido());

    }

}
