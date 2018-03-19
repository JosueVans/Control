
package Dao;


import Model.Empresa;
import org.hibernate.HibernateException;

public interface EmpresaDao {
    public void IniciaSesion();
    public void ManejaException(HibernateException he);
    public boolean CreateEmpresa(Empresa emp) throws HibernateException; 
    public boolean DeleteEmpresa(int id) throws HibernateException;
}
