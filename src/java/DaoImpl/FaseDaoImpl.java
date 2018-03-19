package DaoImpl;

import Dao.FaseDao;
import Model.Fase;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class FaseDaoImpl implements FaseDao {

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
    public List<Fase> ListaFase() throws HibernateException {
        List<Fase> lis = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(Fase.class);
            lis = (List<Fase>) criteria.list();
        } catch (HibernateException he) {
            ManejaException(he);
        } finally {
            //sesion.close();
        }
        return lis;
    }

    @Override
    public boolean CreateFase(Fase add) throws HibernateException {
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
    public boolean DeleteFase(int id) throws HibernateException {
        boolean flag;
        try {
            IniciaSesion();
            Fase del = (Fase) sesion.load(Fase.class, id);
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
    public Fase SearchFase(int id) throws HibernateException {
        Fase found = null;
        try {
            IniciaSesion();
            Criteria criteria = sesion.createCriteria(Fase.class);
            criteria.add(Restrictions.eq("idFase", id));
            found = (Fase) criteria.uniqueResult();

        } catch (HibernateException he) {

            ManejaException(he);
        }
        return found;
    }

}
