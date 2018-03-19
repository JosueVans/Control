package Dao;

import Model.Usuarios;
import java.util.List;
import org.hibernate.HibernateException;

public interface UsuarioDao {
    public void IniciaSesion();
    public void ManejaException(HibernateException he);
    public Usuarios buscarPorUsuario(Usuarios usuario);
    public Usuarios buscarPorCorreo(Usuarios usuario);
    public Usuarios compararCorreo(Usuarios usuario);
    public Usuarios login(Usuarios usuario);
    public List<Usuarios> BuscarTodos() throws HibernateException;
    public boolean update(Usuarios usuario);
    public boolean create(Usuarios usuario);
    public boolean elimina(Integer id) throws HibernateException;
    
    public Usuarios Search(int id)throws HibernateException;
}
