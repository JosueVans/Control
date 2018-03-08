package Control;

import Dao.UsuarioDao;
//import Dao.UsuariosDaoImpl;
import Model.Usuarios;
import Utils.MyUtil;
import javax.inject.Named;
import java.awt.event.ActionEvent;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@Named(value = "loginBean")
@SessionScoped
public class loginBean implements Serializable {

    private Usuarios usuario;
    private UsuarioDao usuarioDao;
    

    

    public loginBean() {
        this.usuarioDao = new UsuarioDao();
        if (this.usuario == null) {
            this.usuario = new Usuarios();
        }
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message;
        boolean loggedIn;
        String ruta = "";
       // SesionBean rol =new SesionBean();
        this.usuario = this.usuarioDao.login(this.usuario);
        if (this.usuario != null) {
            loggedIn = true;
           
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario.getNomUsuario());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userId", this.usuario.getUserId());
                         
           //rol.rol();
            ruta = MyUtil.basePathLogin() + "vistas/inicio.xhtml";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.usuario.getNomUsuario());
          
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Usuario y Contraseña incorrectas");
            if (this.usuario == null) {
                this.usuario = new Usuarios();
            }
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("ruta", ruta);
    }

    public void logout() {
        String ruta = MyUtil.baseurl();
        //String ruta=MyUtil.basePathLogin()+"login.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(false);
        sesion.invalidate();
        context.addCallbackParam("loggedOut", true);
        context.addCallbackParam("ruta", ruta);
    }
}
