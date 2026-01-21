package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dao.AlumnoDAOImplementacion;
import model.Alumno;

class PruebaActualizarPorNia {

	@Test
	void actualizoPorNia() {
		AlumnoDAOImplementacion dao= AlumnoDAOImplementacion.getInstance();
		
		Alumno a= new Alumno(23, "Guillermo", "NOOOOO", "masc", "DAM", "Segundo", "-", LocalDate.of(1999, 01, 01));
		int filas=dao.update(a);
		assertEquals(1, filas); //una fila se actualiza
	}

}
