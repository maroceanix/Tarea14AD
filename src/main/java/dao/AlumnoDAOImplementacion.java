package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import model.Alumno;
import pool.MyDataSource;

public class AlumnoDAOImplementacion implements AlumnoDAO {
	
	private static AlumnoDAOImplementacion instance;
	
	static {
		instance= new AlumnoDAOImplementacion();
	}

	
	private AlumnoDAOImplementacion() { }
	
	public static AlumnoDAOImplementacion getInstance() {
		return instance;
	}
	@Override
	public int add(Alumno alumno) throws Exception { //esto simplemente son nombres que yo decido ponerle a los métodos, add, getbynia, getall, 
		//update, etc, que me devuelva un alumno o una llsita de alumno o loq ue yo quiera. Aquí, insetación de un alumno en la bd
		String sql="INSERT INTO alumno (NIA, Nombre, Apellidos, Genero, Fecha_nacimiento, Ciclo, Curso, Grupo) VALUES"
				+ "(?, ?, ?, ?, ?, ?, ?, ?);";
		int result=0;;
		try (Connection conn=MyDataSource.getConnection();
				PreparedStatement pst= conn.prepareStatement(sql);){
			pst.setInt(1, alumno.getNia());
			pst.setString(2, alumno.getNombre());
			pst.setString(3, alumno.getApellidos());
			pst.setString(4, alumno.getGenero());
			pst.setDate(5, Date.valueOf(alumno.getFecha_nacimiento()));//método propio de date de sql que acepta localdate para pasarlo al formato sql
			pst.setString(6, alumno.getCiclo());
			pst.setString(7, alumno.getCurso());
			pst.setString(8, alumno.getGrupo());
			
			
			result=pst.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	@Override
	public Alumno getByNia(int nia) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alumno> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Alumno alumno) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(int nia) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
