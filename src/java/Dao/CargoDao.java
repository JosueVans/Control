
package Dao;
import Model.Cargo;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class CargoDao {
    
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
    public boolean create(Cargo cargo) {
        boolean flag;
        try {
            iniciaOperacion();
            sesion.save(cargo);
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
     public List<Cargo> BuscarTodos() throws HibernateException {
        List<Cargo> cargos = null;
        try {
            iniciaOperacion();
            Query query = sesion.getNamedQuery("Cargo.buscarTodos");
            cargos = query.list();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());

        } finally {
            sesion.close();
        }
        return cargos;
    }
     
      public List<Cargo> buscarCargoPorRol(int Id_Rol){
     iniciaOperacion();
     List<Cargo> cargo=null;
     Query query= sesion.getNamedQuery("Cargo.BuscarCargoPorRol");
     query.setParameter("Cargo", Id_Rol);
   cargo =query.list();
     sesion.close();
     return cargo;    
     }
     
     public Cargo buscarPorCargo(Cargo cargo) {
        iniciaOperacion();
        String sql = "FROM Cargo WHERE cargo='" + cargo.getCargo() + "'";
        Cargo us = null;
        us = (Cargo) sesion.createQuery(sql).uniqueResult();
        sesion.close();
        return us;

    }
     
    
    
}
