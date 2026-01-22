package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

import dao.AlumnoDAO;
import dao.AlumnoDAOImplementacion;
import model.Alumno;

public class Menu {
	private KeyboardReader lector;
	private AlumnoDAO dao;
	private static final String FORMATO_FECHA="dd/MM/yyyy";
	
	public Menu() {
		lector= new KeyboardReader();
		dao=AlumnoDAOImplementacion.getInstance();
	}
	public void init() {
		int opcion;
		do {
			menu();
			opcion=lector.nextInt();
			
			switch(opcion) {
			case 1:
				listarTodosAlumnos();
				break;
			case 2:
				listarPorNia();
				break;
			case 3: 
				insertarAlumno();
				break;
			case 4: 
				actualizar();
				break;
			case 5: 
				borrarAlumno();
				break;
			case 0: System.out.println("\nEstás saliendo del programa");
					break;
			default: System.err.println("\nEl número introducido no es válido\n");
			}
			
			
		} while (opcion!=0);
		
	}
	
	public void menu() {
		
		System.out.println("Sistema de Gestión de Alumnos\n"
				+ "⋅˚₊‧ ୨୧ ‧₊˚ ⋅⋅˚₊‧ ୨୧ ‧₊˚ ⋅⋅˚₊‧ ୨୧ ‧₊˚ ⋅⋅˚₊‧ ୨୧ ‧₊˚ ⋅⋅˚₊‧ ୨୧ ‧₊˚ ⋅\n"
				+ "Pulse una tecla para acceder a las siguientes opciones: \n"
				+ "1. Listado de todos los alumnos \n"
				+ "2. Listar un alumno por su NIA\n"
				+ "3. Insertar un nuevo alumno\n"
				+ "4. Actualizar/Editar un alumno\n"
				+ "5. Eliminar un alumno por su NIA"
				+ "0. Salir");
		System.out.println("\nOpción: ");
	}

	public void insertarAlumno() {
		System.out.println("Inserción un nuevo alumno\n");
		System.out.println("- - - - - - - - - - - - - - - - \n");
		System.out.println("Introduzca el número de identificación del alumno (entero): ");
		int num=lector.nextInt();
		System.out.println("Introduzca el nombre (sin apellidos) del  alumno:");
		String nombre=lector.nextLine();
		
		System.out.println("Introduzca los apellidos del alumno:");
		String apellidos=lector.nextLine();
		System.out.println("Género del alumno: ");
		String genero=lector.nextLine();
		System.out.println("Introduzca la fecha de nacimiento del alumno (dd/MM/aaaa): ");
		LocalDate fecha=lector.nextLDate();
		System.out.println("Introduzca el ciclo del alumno: ");
		String ciclo=lector.nextLine();
		System.out.println("Introduzca el curso del alumno: ");
		String curso=lector.nextLine();
		System.out.println("Introduzca el grupo del alumno: ");
		String grupo=lector.nextLine();
		
		try {
			dao.add(new Alumno(num, nombre, apellidos, genero, ciclo, curso, grupo, fecha));
			System.out.println("Alumno introducido exitosamente!!");
		} catch (Exception e) {
			System.err.println("Error al introducir el alumno, inténtelo de nuevo o póngase en contacto con nosotros.");
		}
		
	}
	
	public void listarTodosAlumnos() {
		System.out.println("LISTADO DE TODOS LOS ALUMNOS");
		System.out.println("⋅˚₊‧ ୨୧ ‧₊˚ ⋅⋅˚₊‧ ୨୧ ‧₊˚ ⋅⋅˚₊‧ ୨୧ ‧₊˚\n ");
		 cabeceraTablaAlumnos();
		 try {
			List <Alumno> listaAlumnos= dao.getAll();
			if(listaAlumnos.isEmpty()) {
				System.out.println("No hay alumnos registrados en la base de datos.");
			}else {
				cabeceraTablaAlumnos();
				listaAlumnos.forEach(this::printAlumno);
			}

		} catch (Exception e) {
			System.err.println("Error consultando la base de datos, inténtelo de nuevo o póngase en contacto con nosotros.");
		}
		 System.out.println("\n");
	}
	
	private void cabeceraTablaAlumnos() {
		System.out.printf("%s %s %s %s %s %s %s %s", "NIA", "NOMBRE", "APELLDIOS", "GÉNERO", "FEC. NAC.", "CICLO", "CURSO", "GRUPO");
		System.out.println();//saltito de línea
		IntStream.range(1, 100).forEach(x->System.out.print("-"));//imprime 100 guiones
		System.out.println("\n");
	}
	
	private void printAlumno(Alumno a) {
		System.out.printf("%s %s %s %s %s %s %s %s \n", a.getNia(), a.getNombre(), a.getApellidos(), a.getGenero(), a.getFecha_nacimiento().format(DateTimeFormatter.ofPattern(FORMATO_FECHA)), a.getCiclo(), a.getCurso(), a.getGrupo());

	}
	
	public void listarPorNia() {
		System.out.println("BÚSQUEDA DE ALUMNOS POR ID");
		System.out.println("⋅˚₊‧ ୨୧ ‧₊˚ ⋅⋅˚₊‧ ୨୧ ‧₊˚ ⋅⋅˚₊‧ ୨୧ ‧₊˚\n ");
		
		try {
			System.out.println("Introduzca el NIA del alumno que quiera buscar: ");
			int nia=lector.nextInt();
			Alumno a=dao.getByNia(nia);
			if(a==null) {
				System.out.println("No hay alumnos registrados con ese NIA.");
			}else {
				cabeceraTablaAlumnos();
				printAlumno(a);
			}
			System.out.println("\n");
		} catch (Exception e) {
			System.err.println("Error consultando la base de datos, inténtelo de nuevo o póngase en contacto con nosotros.");
		}
	}
	
	public void actualizar(){
		System.out.println("\nACTUALIZACIÓN DE UN ALUMNO");
		System.out.println("⋅˚₊‧ ୨୧ ‧₊˚ ⋅⋅˚₊‧ ୨୧ ‧₊˚ ⋅⋅˚₊‧ ୨୧ ‧₊˚\n ");
		try {
			System.out.println("Introduzca el NIA del alumno a buscar: ");
			int id=lector.nextInt();
			Alumno a =dao.getByNia(id);
			if(a==null) {
				System.out.println("No hay alumnos registrados en la base de datos con este nia.");
			}else {
				cabeceraTablaAlumnos();
				printAlumno(a);
				System.out.println("\n");
				
				System.out.printf("Introduzca el nombre del alumno (%s): ",a.getNombre());
				String nom=lector.nextLine();
				nom=(nom.isBlank()) ? a.getNombre() :nom;
				
				System.out.printf("Introduzca los apellidos del alumno (%s): ",a.getApellidos());
				String ap=lector.nextLine();
				ap=(ap.isBlank()) ? a.getApellidos() :ap;
				
				System.out.printf("Introduzca el género del alumno (%s): ",a.getGenero());
				String gen=lector.nextLine();
				gen=(gen.isBlank()) ? a.getGenero() :gen;
				
				System.out.printf("Introduzca la fecha de nacimiento del alumno (dd/MM/aaaa) (%s): ", a.getFecha_nacimiento().format(DateTimeFormatter.ofPattern(FORMATO_FECHA)));
				String fechaString=lector.nextLine();
				LocalDate fechaNcimiento=(fechaString.isBlank()) ? a.getFecha_nacimiento(): LocalDate.parse(fechaString, DateTimeFormatter.ofPattern(FORMATO_FECHA));
				
				System.out.printf("Introduzca el ciclo del alumno (%s): ",a.getCiclo());
				String ciclo=lector.nextLine();
				ciclo=(ciclo.isBlank()) ? a.getCiclo() :ciclo;
				
				System.out.printf("Introduzca el curso del alumno (%s): ",a.getCurso());
				String curso=lector.nextLine();
				curso=(curso.isBlank()) ? a.getCurso() :curso;
				
				System.out.printf("Introduzca el grupo del alumno (%s): ",a.getGrupo());
				String grupo=lector.nextLine();
				grupo=(grupo.isBlank()) ? a.getGrupo() :grupo;
				
				a.setNombre(nom);
				a.setApellidos(ap);
				a.setGenero(gen);
				a.setFecha_nacimiento(fechaNcimiento);
				a.setCiclo(ciclo);
				a.setCurso(curso);
				a.setGrupo(grupo);
				
				dao.update(a);
				System.out.println();
				System.out.printf("Alumno con NIA %s actualizado", a.getNia());
				System.out.println();
			}
			
		} catch (Exception e) {
			System.err.println("Error actualizando la base de datos, inténtelo de nuevo o póngase en contacto con nosotros.");
		}
		
	}
	
	public void borrarAlumno() {
		System.out.println("\nELIMINACIÓN DE UN ALUMNO");
		System.out.println("⋅˚₊‧ ୨୧ ‧₊˚ ⋅⋅˚₊‧ ୨୧ ‧₊˚ ⋅⋅˚₊‧ ୨୧ ‧₊˚\n ");
		
		try {
			System.out.println("Introduzca el NIA del alumno que desee eliminar: ");
			int nia=lector.nextInt();
			
			System.out.printf("¿Está usted segur@ de que desea borrar al alumno con nia=%s? (s/n) ", nia);
			String borrar=lector.nextLine();
			if(borrar.equalsIgnoreCase("s")) {
				dao.delete(nia);
				System.out.printf("Se ha eliminado al alumno con NIA %s", nia);
			}else {
				System.out.println("Se ha cancelado la ooperación.");
			}
			System.out.println();
			
		} catch (Exception e) {
			System.err.println("Error actualizando la base de datos, inténtelo de nuevo o póngase en contacto con nosotros.");
			}
	}
	static class KeyboardReader{
		BufferedReader br;
		StringTokenizer st;
		
		public KeyboardReader() {
			br= new BufferedReader(new InputStreamReader(System.in));
		}
		
		String next() {
			while(st==null || !st.hasMoreElements()){
				try {
					st= new StringTokenizer(br.readLine());
				}catch(IOException e) {
					System.err.println("Error en lectura de teclado");
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		Double nextDouble() {
			return Double.parseDouble(next());
		}
		
		LocalDate nextLDate() {
			return LocalDate.parse(next(), DateTimeFormatter.ofPattern(FORMATO_FECHA));
		}
		
		String nextLine() {
			String cadena="";
			try {
				if(st.hasMoreElements()) {
					cadena=st.nextToken("\n");
					
				}else {
					cadena=br.readLine();
				}
				
				
			}catch(IOException e) {
				System.err.println("Error en lectura de teclado");
				e.printStackTrace();
			}
			return cadena;
		}
}

}