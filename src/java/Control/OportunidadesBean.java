package Control;

import Dao.FaseDao;
import Dao.OportunidadesDao;
import DaoImpl.FaseDaoImpl;
import DaoImpl.OportunidadesDaoImpl;
import Model.Fase;
import Model.Oportunidades;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "oportunidadesBean")
@RequestScoped
public class OportunidadesBean {

    private List<Oportunidades> listaOportunidades;
    private List<Fase> listaFase;
    private Oportunidades selectedOportunidades;

    public OportunidadesBean() {
        this.listaOportunidades = new ArrayList();
        this.listaFase= new ArrayList();
    }

    public List<Oportunidades> getListaOportunidades() {
        OportunidadesDao oportunidadesDao = new OportunidadesDaoImpl();
        listaOportunidades = oportunidadesDao.ListaOportunidades();
        return listaOportunidades;
    }

    public void setListaOportunidades(List<Oportunidades> listaOportunidades) {
        this.listaOportunidades = listaOportunidades;
    }

    public List<Fase> getListaFase() {
        FaseDao faseDao = new FaseDaoImpl();
        listaFase= faseDao.ListaFase();
        return listaFase;
    }

    public void setListaFase(List<Fase> listaFase) {
        this.listaFase = listaFase;
    }

    public Oportunidades getSelectedOportunidades() {
        if (selectedOportunidades == null) {
            selectedOportunidades = new Oportunidades();
        }
        return selectedOportunidades;
    }

    public void setSelectedOportunidades(Oportunidades selectedOportunidades) {
        this.selectedOportunidades = selectedOportunidades;
    }
   
    
}
