package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

public class Menu {
	private KeyboardReader lector;
	private static final String FORMATO_FECHA="dd/MM/yyyy";
	
	public Menu() {
		lector= new KeyboardReader();
	}
	public void init() {
		int opcion;
		do {
			menu();
			opcion=lector.nextInt();
			
			switch(opcion) {
			case 1: break;
			case 2: break;
			case 3: break;
			case 4: break;
			case 5: break;
			case 0: System.out.println("\nEstás saliendo del programa");
					break;
			default: System.err.println("\nEl número introducido no es válido\n");
			}
			
			
		} while (opcion!=0);
		
	}
	
	public void menu() {
		
		System.out.println("Sitema de Gestión de Alumnos\n"
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

