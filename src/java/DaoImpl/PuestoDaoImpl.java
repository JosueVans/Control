package DaoImpl;

import Model.Empleados;
//import Model.Usuarios;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Dao.PuestoDao;
import Model.Cargo;
import Model.Rol;
import java.util.ArrayList;
import org.hibernate.criterion.Restrictions;

public class PuestoDaoImpl implements PuestoDao {

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
    public List<Rol> ListaRol() {
        List<Rol> rol = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(Rol.class);
            rol = (ArrayList<Rol>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
           // sesion.close();
        }
        return rol;
    }
    
    @Override
    public List<Cargo> ListaCargos() {
List<Cargo> car = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(Cargo.class);
            //criteria.add(Restrictions.eq("rol.idRol", id));
            car = (ArrayList<Cargo>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
          //  sesion.close();
        }
        return car;      
    }

    @Override
    public List<Cargo> ListaCargo(int id) {
        List<Cargo> car = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(Cargo.class);
            criteria.add(Restrictions.eq("rol.idRol", id));
            car = (ArrayList<Cargo>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
          //  sesion.close();
        }
        return car;
    }

    @Override
    public boolean CreateCargo(Cargo ca) throws HibernateException {

        boolean flag;
        try {
            IniciaSesion();
            sesion.save(ca);
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
    
     public boolean CreateRol(Rol ro) throws HibernateException {

        boolean flag;
        try {
            IniciaSesion();
            sesion.save(ro);
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
    public boolean UpdateRol(Rol ro) {
      boolean flag;
        try {
            IniciaSesion();
            Rol rol=(Rol) sesion.load(Rol.class, ro.getIdRol());
            rol.setNombre(ro.getNombre());
            sesion.update(rol);
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
    public boolean DeleteRol(int id) {
       boolean flag;
        try {
            IniciaSesion();
            Rol rol=(Rol) sesion.load(Rol.class, id);
            sesion.delete(rol);
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
    public boolean UpdateCargo(Cargo ca) {
       boolean flag;
        try {
            IniciaSesion();
            Cargo cargo=(Cargo)sesion.load(Cargo.class, ca.getIdCargo());
            cargo.setCargo(ca.getCargo());
            cargo.setRol(ca.getRol());        
            sesion.update(cargo);
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
    public boolean DeleteCargo(int id) {
      boolean flag;
        try {
            IniciaSesion();
            Cargo cargo=(Cargo) sesion.load(Cargo.class, id);
            sesion.delete(cargo);
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
