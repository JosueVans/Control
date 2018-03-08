package Control;

import Dao.EmpleadosDao;
import Dao.NegocioDao;
import DaoImpl.EmpleadosDaoImpl;
import DaoImpl.NegocioDaoImpl;
import Model.EstadoNegocio;
import Model.Negocio;
import Model.Oportunidades;
import Model.TipoNegocio;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named(value = "negocioBean")
@RequestScoped
public class NegocioBean {

    private List<Negocio> ListaNegocio;
    private List<TipoNegocio> ListaTipoN;
    private List<EstadoNegocio> ListaEstadoN;
    private List<Oportunidades> ListaOportunidades;

    private Negocio selectedNegocio;
    private TipoNegocio selectedTipoNegocio;
    private EstadoNegocio selectedEstadoNegocio;

    public NegocioBean() {
       
        this.ListaEstadoN = new ArrayList();
        this.ListaNegocio = new ArrayList();
        this.ListaTipoN = new ArrayList();
    }

    public List<Negocio> getListaNegocio() {
        NegocioDao negocioDao = new NegocioDaoImpl();
        ListaNegocio = negocioDao.ListaNegocio();
        return ListaNegocio;
    }

    public void setListaNegocio(List<Negocio> ListaNegocio) {
        this.ListaNegocio = ListaNegocio;
    }

    public List<TipoNegocio> getListaTipoN() {
        NegocioDao negocio = new NegocioDaoImpl();
        ListaTipoN = negocio.ListaTipoNegocio();
        return ListaTipoN;
    }

    public void setListaTipoN(List<TipoNegocio> ListaTipoN) {
        this.ListaTipoN = ListaTipoN;
    }

    public List<EstadoNegocio> getListaEstadoN() {
        NegocioDao negocio = new NegocioDaoImpl();
        ListaEstadoN = negocio.ListaEstadoNegocio();
        return ListaEstadoN;
    }

    public List<Oportunidades> getListaOportunidades() {
        NegocioDao negocio = new NegocioDaoImpl();
        ListaOportunidades = negocio.ListaOportunidades();
        return ListaOportunidades;
    }

    public void setListaOportunidades(List<Oportunidades> ListaOportunidades) {
        this.ListaOportunidades = ListaOportunidades;
    }

    public void setListaEstadoN(List<EstadoNegocio> ListaEstadoN) {
        this.ListaEstadoN = ListaEstadoN;
    }

    public TipoNegocio getSelectedTipoNegocio() {
        if (selectedTipoNegocio == null) {
            selectedTipoNegocio = new TipoNegocio();
        }
        return selectedTipoNegocio;
    }

    public void setSelectedTipoNegocio(TipoNegocio selectedTipoNegocio) {
        this.selectedTipoNegocio = selectedTipoNegocio;
    }

    public EstadoNegocio getSelectedEstadoNegocio() {
        if (selectedEstadoNegocio == null) {
            selectedEstadoNegocio = new EstadoNegocio();
        }
        return selectedEstadoNegocio;
    }

    public void setSelectedEstadoNegocio(EstadoNegocio selectedEstadoNegocio) {
        this.selectedEstadoNegocio = selectedEstadoNegocio;
    }

    public Negocio getSelectedNegocio() {
        if (selectedNegocio == null) {
            selectedNegocio = new Negocio();
        }
        return selectedNegocio;
    }

    public void setSelectedNegocio(Negocio selectedNegocio) {
        this.selectedNegocio = selectedNegocio;
    }

    public Date Fecha() {
        Date fecha = new Date();
        return fecha;
    }
    
    public BigDecimal formato(BigDecimal bd){
       bd=bd.setScale(2, BigDecimal.ROUND_HALF_EVEN);
       
       return bd;
    }
    
    public void cerrar(){
    NegocioDao negocio = new NegocioDaoImpl();
    negocio.cerrar();
    }

    public String InfoUsuario() {
        int idU;
        String empleado;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = request.getSession(false);

        idU = (int) session.getAttribute("userId");
        EmpleadosDao em = new EmpleadosDaoImpl();
        empleado = em.Search(idU).getNombre();
        return empleado;
    }

    public void createNegocio(ActionEvent actionEvent) {
        NegocioDao negocioDao = new NegocioDaoImpl();
        String msg;
        BigDecimal sd;
        this.selectedNegocio.setCreadopor(InfoUsuario());
        this.selectedNegocio.setCreaciondate(Fecha());
        this.selectedNegocio.setModificadopor("Sin Modificar");
        this.selectedNegocio.setPrecioOfertaFinal(formato(this.selectedNegocio.getPrecioOfertaFinal()));
        if (negocioDao.CreateNegocio(selectedNegocio)) {
            msg = "Se creó exitosamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error, no se pudo crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void updateNegocio(ActionEvent actionEvent) {
        NegocioDao negocioDao = new NegocioDaoImpl();
        String msg;
        this.selectedNegocio.setModificaciondate(Fecha());
        this.selectedNegocio.setModificadopor(InfoUsuario());
        if (negocioDao.UpdateNegocio(selectedNegocio)) {
            msg = "Se actualizó exitosamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error, no se pudo crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void deleteNegocio(ActionEvent actionEvent) {
        NegocioDao negocioDao = new NegocioDaoImpl();
        String msg;
        if (negocioDao.DeleteNegocio(this.selectedNegocio.getIdNegocio())) {
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
