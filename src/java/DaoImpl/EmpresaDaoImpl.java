
package DaoImpl;

import Dao.EmpresaDao;
import Model.Empresa;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmpresaDaoImpl  implements EmpresaDao{

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
    public boolean CreateEmpresa(Empresa emp) throws HibernateException {
       boolean flag;
        try {
            IniciaSesion();
            sesion.save(emp);
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
    public boolean DeleteEmpresa(int id) throws HibernateException {
       boolean flag;
        try {
            IniciaSesion();
            Empresa emp=(Empresa) sesion.load(Empresa.class, id);
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
