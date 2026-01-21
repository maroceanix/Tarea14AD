package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
			case 1: break;
			case 2: break;
			case 3: 
				insertarAlumno();
				break;
			case 4: break;
			case 5: break;
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
		 
	}
	
	private void cabeceraTablaAlumnos() {
		System.out.printf("2%s 30%s 50%s 4%s 8%s 20%s 20%s 20%s", "NIA", "NOMBRE", "APELLDIOS", "GÉNERO", "FEC. NAC.", "CICLO", "CURSO", "GRUPO");
		System.out.println();//saltito de línea
		IntStream.range(1, 100).forEach(x->System.out.print("-"));//imprime 100 guiones
		System.out.println("\n");
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