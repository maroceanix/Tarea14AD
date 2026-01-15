package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.AlumnoDAOImplementacion;

class PruebaBorrarPorNia {


	@Test
	void borrarPorNia() {
		AlumnoDAOImplementacion dao= AlumnoDAOImplementacion.getInstance();
		int filas=dao.delete(23);
		assertEquals(1, filas);
		
	}

}
