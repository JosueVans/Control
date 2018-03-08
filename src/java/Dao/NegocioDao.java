
package Dao;

import Model.EstadoNegocio;
import Model.Negocio;
import Model.Oportunidades;
import Model.TipoNegocio;
import java.util.List;
import org.hibernate.HibernateException;

public interface NegocioDao {
    public void IniciaSesion();
    public void ManejaException(HibernateException he);
    public void cerrar();
    public List<Negocio> ListaNegocio()throws HibernateException;
    public List<TipoNegocio> ListaTipoNegocio()throws HibernateException;
    public List<EstadoNegocio> ListaEstadoNegocio()throws HibernateException;
    public List<Oportunidades> ListaOportunidades()throws HibernateException;
    
    public boolean CreateNegocio(Negocio neg)throws HibernateException;
    public boolean UpdateNegocio(Negocio neg)throws HibernateException;
    public boolean DeleteNegocio(int id)throws HibernateException;
    
    public boolean CreateTipoNegocio(TipoNegocio tipoNeg)throws HibernateException;
    public boolean UpdateTipoNegocio(TipoNegocio tipoNeg)throws HibernateException;
    public boolean DeleteTipoNegocio(int id)throws HibernateException;
    
    public boolean CreateEstadoNegocio(EstadoNegocio estadoNeg)throws HibernateException;
    public boolean UpdateEstadoNegocio(EstadoNegocio estadoNeg)throws HibernateException;
    public boolean DeleteEstadoNegocio(int id)throws HibernateException;
    
    
}
