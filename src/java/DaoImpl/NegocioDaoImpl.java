
package DaoImpl;

import Dao.NegocioDao;
import Model.EstadoNegocio;
import Model.Negocio;
import Model.Oportunidades;
import Model.TipoNegocio;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class NegocioDaoImpl implements NegocioDao {

    Session sesion;
    Transaction tx;
    @Override
    public void IniciaSesion() {   
        sesion = Utils.HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    @Override
    public void ManejaException(HibernateException he) {
       tx.rollback();
        new HibernateException("Error ingresando a la capa de datos" + he);
    }

    @Override
    public List<Negocio> ListaNegocio() throws HibernateException {
      List<Negocio> neg = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(Negocio.class);
            neg = (ArrayList<Negocio>) criteria.list();
            
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
            
            //sesion.close();
        }
        return neg;
    }

    @Override
    public List<TipoNegocio> ListaTipoNegocio() throws HibernateException {
    List<TipoNegocio> tipoNeg = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(TipoNegocio.class);
            tipoNeg = (ArrayList<TipoNegocio>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
           sesion.close();
        }
        return tipoNeg;
    }

    @Override
    public List<Model.EstadoNegocio> ListaEstadoNegocio() throws HibernateException {
       List<EstadoNegocio> estadoNeg = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(EstadoNegocio.class);
           estadoNeg = (ArrayList<EstadoNegocio>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
           sesion.close();
        }
        return estadoNeg;
    }

    @Override
    public boolean CreateNegocio(Negocio neg) throws HibernateException {
        boolean flag;
        try {
            IniciaSesion();
            sesion.save(neg);
            tx.commit();
            flag = true;
        } catch (HibernateException he) {
            flag = false;
            ManejaException(he);
        } finally {
            sesion.close();
        }
        return flag;
    }

    @Override
    public boolean UpdateNegocio(Negocio ne) throws HibernateException {
      boolean flag;
        try {
            IniciaSesion();
            Negocio neg=(Negocio) sesion.load(Negocio.class, ne.getIdNegocio());
            neg.setNombreNegocio(ne.getNombreNegocio());
            neg.setModificadopor(ne.getModificadopor());
            neg.setModificaciondate(ne.getModificaciondate());
            neg.setOportunidades(ne.getOportunidades());
            neg.setTipoNegocio(ne.getTipoNegocio());
            neg.setEstadoNegocio(ne.getEstadoNegocio());
            neg.setFechaIni(ne.getFechaIni());
            neg.setFechaFin(ne.getFechaFin());
            neg.setFechaCierreNegocio(ne.getFechaCierreNegocio());
            sesion.update(neg);
            tx.commit();
            flag = true;
        } catch (HibernateException he) {
           flag = false;
            ManejaException(he);
         } finally {
            sesion.close();
        }
        return flag;
    }

    @Override
    public boolean DeleteNegocio(int id) throws HibernateException {
       boolean flag;
        try {
            IniciaSesion();
            Negocio neg=(Negocio) sesion.load(Negocio.class, id);
            sesion.delete(neg);
            tx.commit();
            flag = true;
        } catch (HibernateException he) {
            flag = false;
            ManejaException(he);          
        } finally {
            sesion.close();
        }
        return flag;
    }

    @Override
    public boolean CreateTipoNegocio(TipoNegocio tipoNeg) throws HibernateException {
      boolean flag;
        try {
            IniciaSesion();
            sesion.save(tipoNeg);
            tx.commit();
            flag = true;
        } catch (HibernateException he) {
            flag = false;
            ManejaException(he);
        } finally {
            sesion.close();
        }
        return flag;
    }

    @Override
    public boolean UpdateTipoNegocio(TipoNegocio tipoNeg) throws HibernateException {
      boolean flag;
        try {
            IniciaSesion();
            TipoNegocio tn=(TipoNegocio) sesion.load(TipoNegocio.class, tipoNeg.getIdTipoNegocio());
            tn.setTipoNegocio(tipoNeg.getTipoNegocio());
            sesion.update(tn);
            tx.commit();
            flag = true;
        } catch (HibernateException he) {
           flag = false;
            ManejaException(he);
         } finally {
            sesion.close();
        }
        return flag;
    }

    @Override
    public boolean DeleteTipoNegocio(int id) throws HibernateException {
      boolean flag;
        try {
            IniciaSesion();
            TipoNegocio tn=(TipoNegocio) sesion.load(TipoNegocio.class, id);
            sesion.delete(tn);
            tx.commit();
            flag = true;
        } catch (HibernateException he) {
            flag = false;
            ManejaException(he);          
        } finally {
            sesion.close();
        }
        return flag;
    }

    @Override
    public boolean CreateEstadoNegocio(Model.EstadoNegocio estadoNeg) throws HibernateException {
        boolean flag;
        try {
            IniciaSesion();
            sesion.save(estadoNeg);
            tx.commit();
            flag = true;
        } catch (HibernateException he) {
            flag = false;
            ManejaException(he);
        } finally {
            sesion.close();
        }
        return flag;
    }

    @Override
    public boolean UpdateEstadoNegocio(Model.EstadoNegocio estadoNeg) throws HibernateException {
      boolean flag;
        try {
            IniciaSesion();
            EstadoNegocio en=(EstadoNegocio) sesion.load(EstadoNegocio.class, estadoNeg.getIdEstadoNegocio());
            en.setEstadoNegocio(estadoNeg.getEstadoNegocio());
            sesion.update(en);
            tx.commit();
            flag = true;
        } catch (HibernateException he) {
           flag = false;
            ManejaException(he);
         } finally {
            sesion.close();
        }
        return flag;
    }

    @Override
    public boolean DeleteEstadoNegocio(int id) throws HibernateException {
       boolean flag;
        try {
            IniciaSesion();
            EstadoNegocio en=(EstadoNegocio) sesion.load(EstadoNegocio.class, id);
            sesion.delete(en);
            tx.commit();
            flag = true;
        } catch (HibernateException he) {
            flag = false;
            ManejaException(he);          
        } finally {
            sesion.close();
        }
        return flag;
    }

    @Override
    public List<Oportunidades> ListaOportunidades() throws HibernateException {
       List<Oportunidades> opor = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(Oportunidades.class);
            opor = (ArrayList<Oportunidades>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
          //  sesion.close();
        }
        return opor;
    }

    @Override
    public void cerrar() {
       sesion.close();
    }
    
}
