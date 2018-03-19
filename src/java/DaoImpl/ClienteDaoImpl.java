package DaoImpl;

import Dao.ClienteDao;
import Model.CategoriaCliente;
import Model.Cliente;
import Model.Empresa;
import Model.EstadoCliente;
import Model.Pais;
import Model.RubroCliente;
import Model.SectorEconomico;
import Model.TipoCliente;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class ClienteDaoImpl implements ClienteDao {

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
        new HibernateException("Error ingresando a la capa de datos " + he);
    }

    
    
    @Override
    public List<Cliente> ListaCliente() throws HibernateException {
      List<Cliente> lis = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(Cliente.class);
            lis = (List<Cliente>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
            //sesion.close();
        }
        return lis;
    }

    @Override
    public List<TipoCliente> ListaTipoCliente() throws HibernateException {
        List<TipoCliente> lis = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(TipoCliente.class);
            lis = (List<TipoCliente>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
           sesion.close();
        }
        return lis;
    }

    @Override
    public List<CategoriaCliente> ListaCategoriaCliente() throws HibernateException {
        List<CategoriaCliente> lis = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(CategoriaCliente.class);
            lis = (List<CategoriaCliente>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
           sesion.close();
        }
        return lis;
    }

    @Override
    public List<EstadoCliente> ListaEstadoCliente() throws HibernateException {
        List<EstadoCliente> lis = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(EstadoCliente.class);
            lis = (List<EstadoCliente>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
            sesion.close();
        }
        return lis;
    }

    @Override
    public List<SectorEconomico> ListaSectorEconomico() throws HibernateException {
       List<SectorEconomico> lis = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(SectorEconomico.class);
            lis = (List<SectorEconomico>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
           sesion.close();
        }
        return lis;
    }

    @Override
    public List<RubroCliente> ListaRubroCliente() throws HibernateException {
        List<RubroCliente> lis = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(RubroCliente.class);
            lis = (List<RubroCliente>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
            sesion.close();
        }
        return lis;
    }
    
    @Override
    public List<Pais> ListaPais() throws HibernateException {
      List<Pais> lis = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(Pais.class);
            lis = (List<Pais>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
           sesion.close();
        }
        return lis;
    }
    
    @Override
    public List<Empresa> ListaEmpresa() throws HibernateException {
       List<Empresa> lis = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(Empresa.class);
            lis = (List<Empresa>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
            sesion.close();
        }
        return lis;
    }
    
    @Override
    public List<Empresa> ListaEmpresas(int id) throws HibernateException {
        List<Empresa> lis = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(Empresa.class);
            criteria.add(Restrictions.eq("pais.idPais", id));
            lis = (List<Empresa>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
            sesion.close();
        }
        return lis;
    }

    @Override
    public boolean CreateCliente(Cliente cli) throws HibernateException {
        boolean flag;
        try {
            IniciaSesion();
            sesion.save(cli);
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
    public boolean DeleteCliente(int id) throws HibernateException {
        boolean flag;
        try {
            IniciaSesion();
            Cliente del=(Cliente) sesion.load(Cliente.class, id);
            sesion.delete(del);
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

}
