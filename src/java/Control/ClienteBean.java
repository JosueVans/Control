package Control;

import Dao.ClienteDao;
import Dao.EmpresaDao;
import Dao.UsuarioDao;
import DaoImpl.ClienteDaoImpl;
import DaoImpl.EmpresaDaoImpl;
import DaoImpl.UsuarioDaoImpl;
import Model.CategoriaCliente;
import Model.EstadoCliente;
import Model.RubroCliente;
import Model.SectorEconomico;
import Model.TipoCliente;
import Model.Cliente;
import Model.Empresa;
import Model.Pais;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import javax.faces.view.ViewScoped;

@Named(value = "clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {

    private List<TipoCliente> ListaTipoCliente;
    private List<CategoriaCliente> ListaCategoriaCliente;
    private List<EstadoCliente> ListaEstadoCliente;
    private List<SectorEconomico> ListaSectorEconomico;
    private List<RubroCliente> ListaRubroCliente;
    private List<Cliente> ListaCliente;
    private List<Pais> ListaPais;
    private List<Empresa> listaEmpresa;
    private List<Empresa> listaEmpresaPorPais;
    private Cliente selectedCliente;
    private Empresa selectedEmpresa;
    private int idPais;

    public ClienteBean() {
        this.ListaCategoriaCliente = new ArrayList();
        this.ListaCliente = new ArrayList();
        this.ListaEstadoCliente = new ArrayList();
        this.ListaRubroCliente = new ArrayList();
        this.ListaSectorEconomico = new ArrayList();
        this.ListaTipoCliente = new ArrayList();
        this.listaEmpresa = new ArrayList();
        this.listaEmpresaPorPais = new ArrayList();
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public List<Empresa> getListaEmpresaPorPais() {
        ClienteDao clienteDao = new ClienteDaoImpl();
        listaEmpresaPorPais = clienteDao.ListaEmpresas(idPais);
        return listaEmpresaPorPais;
    }

    public void setListaEmpresaPorPais(List<Empresa> listaEmpresaPorPais) {
        this.listaEmpresaPorPais = listaEmpresaPorPais;
    }

    public List<TipoCliente> getListaTipoCliente() {
        ClienteDao clienteDao = new ClienteDaoImpl();
        ListaTipoCliente = clienteDao.ListaTipoCliente();
        return ListaTipoCliente;
    }

    public void setListaTipoCliente(List<TipoCliente> ListaTipoCliente) {
        this.ListaTipoCliente = ListaTipoCliente;
    }

    public List<CategoriaCliente> getListaCategoriaCliente() {
        ClienteDao clienteDao = new ClienteDaoImpl();
        ListaCategoriaCliente = clienteDao.ListaCategoriaCliente();
        return ListaCategoriaCliente;
    }

    public void setListaCategoriaCliente(List<CategoriaCliente> ListaCategoriaCliente) {
        this.ListaCategoriaCliente = ListaCategoriaCliente;
    }

    public List<EstadoCliente> getListaEstadoCliente() {
        ClienteDao clienteDao = new ClienteDaoImpl();
        ListaEstadoCliente = clienteDao.ListaEstadoCliente();
        return ListaEstadoCliente;
    }

    public void setListaEstadoCliente(List<EstadoCliente> ListaEstadoCliente) {
        this.ListaEstadoCliente = ListaEstadoCliente;
    }

    public List<SectorEconomico> getListaSectorEconomico() {
        ClienteDao clienteDao = new ClienteDaoImpl();
        ListaSectorEconomico = clienteDao.ListaSectorEconomico();
        return ListaSectorEconomico;
    }

    public void setListaSectorEconomico(List<SectorEconomico> ListaSectorEconomico) {
        this.ListaSectorEconomico = ListaSectorEconomico;
    }

    public List<RubroCliente> getListaRubroCliente() {
        ClienteDao clienteDao = new ClienteDaoImpl();
        ListaRubroCliente = clienteDao.ListaRubroCliente();
        return ListaRubroCliente;
    }

    public void setListaRubroCliente(List<RubroCliente> ListaRubroCliente) {
        this.ListaRubroCliente = ListaRubroCliente;
    }

    public List<Cliente> getListaCliente() {
        ClienteDao clienteDao = new ClienteDaoImpl();
        ListaCliente = clienteDao.ListaCliente();
        return ListaCliente;
    }

    public void setListaCliente(List<Cliente> ListaCliente) {
        this.ListaCliente = ListaCliente;
    }

    public List<Pais> getListaPais() {
        ClienteDao clienteDao = new ClienteDaoImpl();
        ListaPais = clienteDao.ListaPais();
        return ListaPais;
    }

    public void setListaPais(List<Pais> ListaPais) {
        this.ListaPais = ListaPais;
    }

    public List<Empresa> getListaEmpresa() {
        ClienteDao clienteDao = new ClienteDaoImpl();
        listaEmpresa = clienteDao.ListaEmpresa();
        return listaEmpresa;
    }

    public void setListaEmpresa(List<Empresa> listaEmpresa) {
        this.listaEmpresa = listaEmpresa;
    }

    public Cliente getSelectedCliente() {
        if (selectedCliente == null) {
            selectedCliente = new Cliente();
        }
        return selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    /* public Pais getSelectedPais() {
        if (selectedPais == null) {
            selectedPais = new Pais();
        }
        return selectedPais;
    }

    public void setSelectedPais(Pais selectedPais) {
        this.selectedPais = selectedPais;
    }
     */
    public Empresa getSelectedEmpresa() {
        if (selectedEmpresa == null) {
            selectedEmpresa = new Empresa();
        }
        return selectedEmpresa;
    }

    public void setSelectedEmpresa(Empresa selectedEmpresa) {
        this.selectedEmpresa = selectedEmpresa;
    }

    public List<String> completeList(String query) {
        ClienteDao clienteDao = new ClienteDaoImpl();
        List<Empresa> list = clienteDao.ListaEmpresa();
        List<String> results = new ArrayList<String>();
        for (Empresa empresa : list) {
            results.add(empresa.getNombreEmpresa());
        }

        return results;
    }

    public Date fecha() {
        Date fecha = new Date();
        return fecha;
    }

    public boolean validateEmail() {
        boolean flag;
        String msg;
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(this.selectedCliente.getEmail());
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

    public String usuarioActual() {
        int idU;
        String nombre;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = request.getSession(false);
        idU = (int) session.getAttribute("userId");
        UsuarioDao us = new UsuarioDaoImpl();
        return us.Search(idU).getNomUsuario();
    }

    public void reload() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public void createCliente(ActionEvent actionEvent) {
        ClienteDao clienteDao = new ClienteDaoImpl();
        EmpresaDao empresaDao = new EmpresaDaoImpl();
        String msg;
        this.selectedCliente.setCreadopor(usuarioActual());
        this.selectedCliente.setCreaciondate(fecha());
        // System.out.printf("id empresa" + this.selectedCliente.getEmpresa().getIdempresa());
        if (clienteDao.CreateCliente(selectedCliente)) {
            msg = "Se creó exitosamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            RequestContext context = RequestContext.getCurrentInstance();
            clean();
            context.execute("PF('dialogClienteCreate').hide()");
        } else {
            empresaDao.DeleteEmpresa(this.selectedEmpresa.getIdempresa());
            msg = "Error, no se pudo crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void deleteCliente(ActionEvent actionEvent) {
        ClienteDao clienteDao = new ClienteDaoImpl();
        EmpresaDao empresaDao = new EmpresaDaoImpl();
        String msg;
        this.selectedEmpresa = this.selectedCliente.getEmpresa();
        if (clienteDao.DeleteCliente(this.selectedCliente.getIdCliente())) {
            // if (empresaDao.DeleteEmpresa(this.selectedEmpresa.getIdempresa())) {
            msg = "Se eliminó exitosamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            clean();
        } else {
            msg = "Error, no se eliminó el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        // }
    }

    public void createEmpresa(ActionEvent actionEvent) {
        EmpresaDao empresaDao = new EmpresaDaoImpl();
        String msg;
        this.selectedEmpresa.setCreadopor(usuarioActual());
        this.selectedEmpresa.setCreaciondate(fecha());
        if (empresaDao.CreateEmpresa(selectedEmpresa)) {
            msg = "Se creó exitosamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            RequestContext context = RequestContext.getCurrentInstance();
//reset();            
            clean();
            context.execute("PF('dialogEmpresaCreate').hide()");

        } else {
            empresaDao.DeleteEmpresa(this.selectedEmpresa.getIdempresa());
            msg = "Error, no se pudo crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void clean() {
        this.selectedCliente = null;
        this.selectedEmpresa = null;
        //this.ListaPais= new ArrayList();
    }

}
