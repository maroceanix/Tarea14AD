package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import dao.AlumnoDAOImplementacion;
import model.Alumno;

class PruebaObtenerAlumnos {

	@Test
	void obtenerAlumnos() {
		AlumnoDAOImplementacion dao= AlumnoDAOImplementacion.getInstance();
		List<Alumno> alumnos=dao.getAll();
		assertNotNull(alumnos, "La lista NO puede ser nula");
		for(Alumno al: alumnos) {
			System.out.println(al.toString());
		}
			
	}

}
