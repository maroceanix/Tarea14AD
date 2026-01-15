package model;

import java.time.LocalDate;
import java.util.Objects;

public class Alumno {
		private int nia;
		private String nombre, apellidos, genero, ciclo, curso, grupo;
		private LocalDate fecha_nacimiento;
		
		public Alumno() {
			
		}
		public Alumno(int nia, String nombre, String apellidos, String genero, String ciclo, String curso, String grupo, int anio, int mes, int dia) {
			this.nia = nia;
			this.nombre = nombre;
			this.apellidos = apellidos;
			this.genero = genero;
			this.ciclo = ciclo;
			this.curso = curso;
			this.grupo = grupo;
			this.fecha_nacimiento=LocalDate.of(anio, mes, dia);
			
			
		}
		public int getNia() {
			return nia;
		}
		public void setNia(int nia) {
			this.nia = nia;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getApellidos() {
			return apellidos;
		}
		public void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}
		public String getGenero() {
			return genero;
		}
		public void setGenero(String genero) {
			this.genero = genero;
		}
		public String getCiclo() {
			return ciclo;
		}
		public void setCiclo(String ciclo) {
			this.ciclo = ciclo;
		}
		public String getCurso() {
			return curso;
		}
		public void setCurso(String curso) {
			this.curso = curso;
		}
		public String getGrupo() {
			return grupo;
		}
		public void setGrupo(String grupo) {
			this.grupo = grupo;
		}
		public LocalDate getFecha_nacimiento() {
			return fecha_nacimiento;
		}
		public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
			this.fecha_nacimiento = fecha_nacimiento;
		}
		
//		public String convertirFechaSQL() {
//			return this.getFecha_nacimiento().getYear()+"-"+this.getFecha_nacimiento().getMonthValue()+"-"+this.getFecha_nacimiento().getDayOfMonth();
//		}
		
		@Override
		public int hashCode() {
			return Objects.hash(apellidos, ciclo, curso, fecha_nacimiento, genero, grupo, nia, nombre);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Alumno other = (Alumno) obj;
			return Objects.equals(apellidos, other.apellidos) && Objects.equals(ciclo, other.ciclo)
					&& Objects.equals(curso, other.curso) && Objects.equals(fecha_nacimiento, other.fecha_nacimiento)
					&& Objects.equals(genero, other.genero) && Objects.equals(grupo, other.grupo) && nia == other.nia
					&& Objects.equals(nombre, other.nombre);
		}
		@Override
		public String toString() {
			return "Alumno [nia=" + nia + ", nombre=" + nombre + ", apellidos=" + apellidos + ", genero=" + genero
					+ ", ciclo=" + ciclo + ", curso=" + curso + ", grupo=" + grupo + ", fecha_nacimiento="
					+ fecha_nacimiento + "]";
		}
		
		
		
}
