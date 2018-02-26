
package Dao;
import Model.TipoNegocio;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class TipoNegocioDao {
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
    
    public boolean create(TipoNegocio tipo) {
        boolean flag;
        try {
            iniciaOperacion();
            sesion.save(tipo);
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
    public boolean update(TipoNegocio tipo) {
        boolean flag;
        try {
            iniciaOperacion();
            TipoNegocio tndb = (TipoNegocio) sesion.load(TipoNegocio.class, tipo.getIdTipoNegocio());
            tndb.setTipoNegocio(tipo.getTipoNegocio());
          
            sesion.update(tndb);
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
            TipoNegocio tipo = (TipoNegocio) sesion.load(TipoNegocio.class, id);
            sesion.delete(tipo);
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
    
     public List<TipoNegocio> BuscarTodos() throws HibernateException {
        List<TipoNegocio> tipoN = null;
        try {
            iniciaOperacion();
            Query query = sesion.getNamedQuery("TipoNegocio.buscarTodos");
            tipoN= query.list();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());

        } finally {
            sesion.close();
        }
        return tipoN;
    }
    
     public TipoNegocio buscarPorTipoNegocio(TipoNegocio tipoNegocio) {
        iniciaOperacion();
        String sql = "FROM TipoNegocio WHERE Tipo_Negocio='" + tipoNegocio.getTipoNegocio() + "'";
        TipoNegocio tn = null;
        tn = (TipoNegocio) sesion.createQuery(sql).uniqueResult();
        sesion.close();
        return tn;

    }
     public TipoNegocio buscarPorTipoNegocio(String nombre){
		iniciaOperacion();
		Query query= sesion.getNamedQuery("TipoNegocio.buscarPorNombre");
		query.setParameter("TipoNegocio", nombre);// se refiere a la variable de la clase no de la tabla
		TipoNegocio tipo=(TipoNegocio) query.uniqueResult();
		sesion.close();
		return tipo;
		
	}
     
    
}
