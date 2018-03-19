package Dao;

import Model.Usuarios;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioDaos {

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

   
    
    public Usuarios buscarPorUsuario(Usuarios usuario) {
        iniciaOperacion();
        String sql = "FROM Usuarios WHERE Nom_Usuario='" + usuario.getNomUsuario() + "'";
        Usuarios us = null;
        us = (Usuarios) sesion.createQuery(sql).uniqueResult();
        sesion.close();
        return us;

    }
     public Usuarios buscarPorCorreo(Usuarios usuario) {
        iniciaOperacion();
        String sql = "FROM Usuarios WHERE Correo='" + usuario.getCorreo() + "'";
        Usuarios us = null;
        us = (Usuarios) sesion.createQuery(sql).uniqueResult();
        sesion.close();
        return us;

    }

public Usuarios compararCorreo(Usuarios usuario) {
        Usuarios us = this.buscarPorCorreo(usuario);
        if (us != null) {
            if (!usuario.getCorreo().equals(us.getCorreo())) {
                us = null;
            }
        }
        return us;
    }
    public Usuarios login(Usuarios usuario) {
        Usuarios us = this.buscarPorUsuario(usuario);
        if (us != null) {
            if (!usuario.getPassword().equals(us.getPassword())) {
                us = null;
            }
        }
        return us;
    }

    public List<Usuarios> BuscarTodos() throws HibernateException {
        List<Usuarios> usuarios = null;
        try {
            iniciaOperacion();
            Query query = sesion.getNamedQuery("Usuarios.buscarTodos");
            usuarios = query.list();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());

        } finally {
            sesion.close();
        }
        return usuarios;
    }

    public boolean update(Usuarios usuario) {
        boolean flag;
        try {
            iniciaOperacion();
            Usuarios usrdb = (Usuarios) sesion.load(Usuarios.class, usuario.getUserId());
            usrdb.setNomUsuario(usuario.getNomUsuario());
           //usrdb.setRol(usuario.getRol());
            usrdb.setCorreo(usuario.getCorreo());
            sesion.update(usrdb);
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

    public boolean create(Usuarios usuario) {
        boolean flag;
        try {
            iniciaOperacion();
            sesion.save(usuario);
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

   /* public boolean elimina(Usuarios usuario) throws HibernateException {
        boolean flag;
        try {
            iniciaOperacion();
            sesion.delete(usuario);
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
    }*/

     public boolean elimina(Integer id) throws HibernateException {
        boolean flag;
        try {
            iniciaOperacion();
            Usuarios usuario = (Usuarios) sesion.load(Usuarios.class, id);
            sesion.delete(usuario);
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

}
