package DaoImpl;

import Dao.EmpleadosDao;
import Model.Empleados;
//import Model.Usuarios;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class EmpleadosDaoImpl implements EmpleadosDao {

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
    public List<Empleados> ListaEmpleados() throws HibernateException {

        List<Empleados> lis = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(Empleados.class);
            lis = (List<Empleados>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
           // sesion.close();
        }
        return lis;
    }

    @Override
    public boolean Create(Empleados em) throws HibernateException {

        boolean flag;
        try {
            IniciaSesion();
            sesion.save(em);
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
    public boolean Update(Empleados em) throws HibernateException {

        boolean flag;
        try {
            IniciaSesion();
            Empleados emp=(Empleados)sesion.load(Empleados.class, em.getIdEmpleado());
            emp.setNombre(em.getNombre());
            emp.setApellido(em.getApellido());
            emp.setModificaciondate(em.getModificaciondate());
            emp.setCargo(em.getCargo());
            sesion.update(emp);
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
    public boolean Delete(int id) throws HibernateException {

        boolean flag;
        try {
            IniciaSesion();
            Empleados emp=(Empleados) sesion.load(Empleados.class, id);
            sesion.delete(emp);
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
