package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.AlumnoDAOImplementacion;
import model.Alumno;

class PruebaInsercion {

	@Test
	void insercionAlumno() {
		Alumno a1=new Alumno(23, "Guillermo", "Hualde", "masc", "DAM", "Segundo", "-", 2001, 11, 27);
		AlumnoDAOImplementacion dao= AlumnoDAOImplementacion.getInstance();	//preguntar
		int filas =dao.add(a1);
		assertEquals(1, filas); //me dice si se ha modificado una fila porque añado un alumno solo
	}

}
