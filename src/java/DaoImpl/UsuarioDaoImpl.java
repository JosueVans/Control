package DaoImpl;

import Dao.UsuarioDao;
import Model.Usuarios;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Criteria;

public class UsuarioDaoImpl implements UsuarioDao{

     private Session sesion;
    private Transaction tx;
    
    @Override
    public void IniciaSesion() {
         sesion = Utils.HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    @Override
    public void ManejaException(HibernateException he) {
       tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    @Override
    public Usuarios buscarPorUsuario(Usuarios usuario) {
       IniciaSesion();
        String sql = "FROM Usuarios WHERE Nom_Usuario='" + usuario.getNomUsuario() + "'";
        Usuarios us = null;
        us = (Usuarios) sesion.createQuery(sql).uniqueResult();
        sesion.close();
        return us;
    }

    @Override
    public Usuarios buscarPorCorreo(Usuarios usuario) {
         IniciaSesion();
        String sql = "FROM Usuarios WHERE Correo='" + usuario.getCorreo() + "'";
        Usuarios us = null;
        us = (Usuarios) sesion.createQuery(sql).uniqueResult();
        sesion.close();
        return us;

    }

    @Override
    public Usuarios compararCorreo(Usuarios usuario) {
        Usuarios us = this.buscarPorCorreo(usuario);
        if (us != null) {
            if (!usuario.getCorreo().equals(us.getCorreo())) {
                us = null;
            }
        }
        return us;
    }

    @Override
    public Usuarios login(Usuarios usuario) {
         Usuarios us = this.buscarPorUsuario(usuario);
        if (us != null) {
            if (!usuario.getPassword().equals(us.getPassword())) {
                us = null;
            }
        }
        return us;
    }

    @Override
    public List<Usuarios> BuscarTodos() throws HibernateException {
         List<Usuarios> usuarios = null;
        try {
            IniciaSesion();
            Query query = sesion.getNamedQuery("Usuarios.buscarTodos");
            usuarios = query.list();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());

        } finally {
            sesion.close();
        }
        return usuarios;
    }

    @Override
    public boolean update(Usuarios usuario) {
         boolean flag;
        try {
            IniciaSesion();
            Usuarios usrdb = (Usuarios) sesion.load(Usuarios.class, usuario.getUserId());
            usrdb.setNomUsuario(usuario.getNomUsuario());
           //usrdb.setRol(usuario.getRol());
            usrdb.setCorreo(usuario.getCorreo());
            sesion.update(usrdb);
            tx.commit();
            flag = true;
        } catch (HibernateException he) {
            flag = false;
            ManejaException(he);
            throw he;

        } finally {
            sesion.close();
        }
        return flag;
    }

    @Override
    public boolean create(Usuarios usuario) {
        boolean flag;
        try {
            IniciaSesion();
            sesion.save(usuario);
            tx.commit();
            flag = true;
        } catch (HibernateException he) {
            flag = false;
            ManejaException(he);
            throw he;

        } finally {
            sesion.close();
        }
        return flag;
    }

    @Override
    public boolean elimina(Integer id) throws HibernateException {
         boolean flag;
        try {
            IniciaSesion();
            Usuarios usuario = (Usuarios) sesion.load(Usuarios.class, id);
            sesion.delete(usuario);
            tx.commit();
            flag = true;
        } catch (HibernateException he) {
            flag = false;
            ManejaException(he);
            throw he;
        } finally {
            sesion.close();
        }
        return flag;
    }

    @Override
    public Usuarios Search(int id) throws HibernateException {
        Usuarios us=null;
       try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(Usuarios.class);
            criteria.add(Restrictions.eq("userId", id));
            us=(Usuarios) criteria.uniqueResult();            
        } catch (HibernateException he) {
            
            ManejaException(he);          
        } finally {
           // sesion.close();
        }
        return us;  
    }
    
}
