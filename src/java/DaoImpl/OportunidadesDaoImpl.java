package DaoImpl;

import Dao.OportunidadesDao;
import Model.Oportunidades;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class OportunidadesDaoImpl implements OportunidadesDao {

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
    public List<Oportunidades> ListaOportunidades() throws HibernateException {
        List<Oportunidades> lis = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(Oportunidades.class);
            lis = (List<Oportunidades>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
            //sesion.close();
        }
        return lis;
    }

  @Override
    public boolean CreateOportunidades(Oportunidades add) throws HibernateException {
         boolean flag;
        try {
            IniciaSesion();
            sesion.save(add);
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
    public boolean DeleteOportunidades(int id) throws HibernateException {
        boolean flag;
        try {
            IniciaSesion();
            Oportunidades del=(Oportunidades) sesion.load(Oportunidades.class, id);
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

    @Override
    public Oportunidades SearchOportunidades(int id) throws HibernateException {
        Oportunidades found = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(Oportunidades.class);
            criteria.add(Restrictions.eq("idOportunidad", id));
            found = (Oportunidades) criteria.uniqueResult();

        } catch (HibernateException he) {

            ManejaException(he);
        } 
        return found;
    }

}
