package dao;

import java.sql.SQLException;
import java.util.List;

import model.Alumno;

public interface AlumnoDAO {
	int add(Alumno alumno);
	
	Alumno getByNia(int nia);
	
	List <Alumno> getAll();
	
	int update (Alumno alumno);
	
	void delete(int nia);
	
	//interfaz libre de tratamiento de excepciones por si luego quieres hacer otra interfaz de fichero
}
