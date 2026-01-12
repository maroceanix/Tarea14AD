package dao;

import java.sql.SQLException;
import java.util.List;

import model.Alumno;

public interface AlumnoDAO {
	int add(Alumno alumno) throws Exception;
	
	Alumno getByNia(int nia) throws Exception;
	
	List <Alumno> getAll() throws Exception;
	
	int update (Alumno alumno) throws Exception;
	
	void delete(int nia) throws Exception;
}
