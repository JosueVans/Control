package Dao;

import Model.EstadoNegocio;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EstadoNegocioDao {
   private Session sesion;
   private Transaction tx;
   private void iniciaOperacion() throws HibernateException {

        sesion = Utils.HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();

    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }
    
    public boolean create(EstadoNegocio estado) {
        boolean flag;
        try {
            iniciaOperacion();
            sesion.save(estado);
            tx.commit();
            flag = true;
        } catch (HibernateException he) {
            flag = false;
            manejaExcepcion(he);
            throw he;

        } finally {
            sesion.close();
        }
        return flag;
    }
    public boolean update(EstadoNegocio estado) {
        boolean flag;
        try {
            iniciaOperacion();
            EstadoNegocio endb = (EstadoNegocio) sesion.load(EstadoNegocio.class, estado.getIdEstadoNegocio());
            endb.setEstadoNegocio(estado.getEstadoNegocio());
          
            sesion.update(endb);
            tx.commit();
            flag = true;
        } catch (HibernateException he) {
            flag = false;
            manejaExcepcion(he);
            throw he;

        } finally {
            sesion.close();
        }
        return flag;
    }
    
    public boolean delete(Integer id) throws HibernateException {
        boolean flag;
        try {
            iniciaOperacion();
            EstadoNegocio estado = (EstadoNegocio) sesion.load(EstadoNegocio.class, id);
            sesion.delete(estado);
            tx.commit();
            flag = true;
        } catch (HibernateException he) {
            flag = false;
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
        return flag;
    }
    
     public List<EstadoNegocio> BuscarTodos() throws HibernateException {
        List<EstadoNegocio> estadoN = null;
        try {
            iniciaOperacion();
            Query query = sesion.getNamedQuery("EstadoNegocio.buscarTodos");
            estadoN= query.list();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());

        } finally {
            sesion.close();
        }
        return estadoN;
    }
    
     
     public EstadoNegocio buscarPorEstadoNegocio(String nombre){
		iniciaOperacion();
		Query query= sesion.getNamedQuery("EstadoNegocio.buscarPorNombre");
		query.setParameter("EstadoNegocio", nombre);// se refiere a la variable de la clase no de la tabla
		EstadoNegocio estado=(EstadoNegocio) query.uniqueResult();
		sesion.close();
		return estado;
		
	}
    
}
