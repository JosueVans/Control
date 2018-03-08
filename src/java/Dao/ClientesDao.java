package Dao;

import java.util.List;
import org.hibernate.HibernateException;

public interface ClientesDao {
    public void IniciaSesion();
    public void ManejaException(HibernateException he);
     
}
