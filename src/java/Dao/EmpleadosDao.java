/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

//import Model.Usuarios;
import Model.Empleados;
import java.util.List;
import org.hibernate.HibernateException;

public interface EmpleadosDao {
    
    public void IniciaSesion();
    public void ManejaException(HibernateException he);
    public List<Empleados> ListaEmpleados()throws HibernateException;
    public boolean Create(Empleados em)throws HibernateException;
    public boolean Update(Empleados em)throws HibernateException;
    public boolean Delete(int id)throws HibernateException;
    
    public Empleados Search(int id)throws HibernateException;
    
}
