
package Dao;

import Model.Fase;
import java.util.List;
import org.hibernate.HibernateException;

public interface FaseDao {
    public void IniciaSesion();
    public void ManejaException(HibernateException he);
    public List<Fase> ListaFase() throws HibernateException;
    
    public boolean CreateFase(Fase op) throws HibernateException; 
    public boolean DeleteFase(int id) throws HibernateException;
    public Fase SearchFase(int id) throws HibernateException;
}
