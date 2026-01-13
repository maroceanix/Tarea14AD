package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.AlumnoDAOImplementacion;
import model.Alumno;

class PruebaAlumnoNia {

	@Test
	void obtenerAlumnoNia() {
		AlumnoDAOImplementacion dao=AlumnoDAOImplementacion.getInstance();
		Alumno al=dao.getByNia(23);
		assertNotNull(al, "El alumno no puede ser nulo");
		System.out.println(al.toString());
		
	}

}
