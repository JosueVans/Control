/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Usuarios;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpSession;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@Named(value = "sesionBean")
@RequestScoped
public class SesionBean {

    private boolean hideUsuarios;
    private Usuarios usuario;

    public SesionBean() {
       // this.hideUsuarios = true;
    }

    public void setHideUsuarios(boolean hideUsuarios) {
        this.hideUsuarios = hideUsuarios;
    }

    public boolean isHideUsuarios() {
        return hideUsuarios;
    }

    public void auditor() {
        this.hideUsuarios=false;
        
    }

    public void rol() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = request.getSession(false);
       
        if(session.getAttribute("usuario")!="Josue"){
            
            this.hideUsuarios = true;
            setHideUsuarios(this.hideUsuarios);
            //auditor();
        
        }
        System.out.printf("nombre " + session.getAttribute("usuario"));

    }

}
