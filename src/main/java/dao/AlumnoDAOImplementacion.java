package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	public int add(Alumno alumno)  { //esto simplemente son nombres que yo decido ponerle a los métodos, add, getbynia, getall, 
		//update, etc, que me devuelva un alumno o una llsita de alumno o loq ue yo quiera. Aquí, insetación de un alumno en la bd
		String sql="INSERT INTO alumno (NIA, Nombre, Apellidos, Genero, Fecha_nacimiento, Ciclo, Curso, Grupo) VALUES"
				+ "(?, ?, ?, ?, ?, ?, ?, ?);";
		int result=-1;
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
	public Alumno getByNia(int nia) {//no hago lista porque al ser clave primaria solo devuelve un único valor
		String sql="SELECT NIA, Nombre, Apellidos, Genero, Fecha_nacimiento, Ciclo, Curso, Grupo FROM alumno WHERE NIA = ?";
		Alumno result=null;
		try(Connection conn=MyDataSource.getConnection();
				PreparedStatement pst=conn.prepareStatement(sql);){
			pst.setInt(1, nia);
			try(ResultSet rs= pst.executeQuery()){
				if(rs.next()) {
					result=new Alumno();
					result.setNia(rs.getInt("NIA"));
					result.setNombre(rs.getString("Nombre"));
					result.setApellidos(rs.getString("Apellidos"));
					result.setGenero(rs.getString("Genero"));
					result.setFecha_nacimiento(rs.getDate("Fecha_nacimiento").toLocalDate());
					result.setCiclo(rs.getString("Ciclo"));
					result.setCurso(rs.getString("Curso"));
					result.setGrupo(rs.getString("Grupo"));
					
				}
			}
			
		} catch (Exception e) {
			return result;
		}
		return result;
	}

	@Override
	public List<Alumno> getAll() {
		String sql="SELECT NIA, Nombre, Apellidos, Genero, Fecha_nacimiento, Ciclo, Curso, Grupo FROM alumno";
		List <Alumno> result = new ArrayList<Alumno>();
		try(Connection conn= MyDataSource.getConnection();
				PreparedStatement pst=conn.prepareStatement(sql);
				ResultSet rs=pst.executeQuery()){
			Alumno a;
			while(rs.next()) {
				a= new Alumno();
				a.setNia(rs.getInt("NIA"));
				a.setNombre(rs.getString("Nombre"));
				a.setApellidos(rs.getString("Apellidos"));
				a.setGenero(rs.getString("Genero"));
				a.setFecha_nacimiento(rs.getDate("Fecha_nacimiento").toLocalDate());
				a.setCiclo(rs.getString("Ciclo"));
				a.setCurso(rs.getString("Curso"));
				a.setGrupo(rs.getString("Grupo"));
				
				result.add(a);
			}
			
		}catch (Exception e) {
			return null;
		}
		
		return result;
	}

	@Override
	public int update(Alumno alumno) {
		String sql="UPDATE alumno SET Nombre= ?, Apellidos= ?, Genero= ?, Fecha_nacimiento= ?, Ciclo= ?, Curso= ?, Grupo= ? WHERE NIA = ?";
		int result=-1;
		try (Connection conn= MyDataSource.getConnection();
				PreparedStatement pst=conn.prepareStatement(sql)){
			pst.setString(1, alumno.getNombre());
			pst.setString(2, alumno.getApellidos());
			pst.setString(3, alumno.getGenero());
			pst.setDate(4, Date.valueOf(alumno.getFecha_nacimiento()));
			pst.setString(5, alumno.getCiclo());
			pst.setString(6, alumno.getCurso());
			pst.setString(7, alumno.getGrupo());
			pst.setInt(8, alumno.getNia());
			result=pst.executeUpdate();
			
			
		} catch (Exception e) {
			return result;
			}
		return result;
	}

	@Override
	public void delete(int nia) {
		String sql="DELETE FROM alumnos WHERE NIA = ?";
		try(Connection conn=MyDataSource.getConnection();
				PreparedStatement pst=conn.prepareStatement(sql)){
			pst.setInt(1, nia);
			pst.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
