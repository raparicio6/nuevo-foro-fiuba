package nuevo_foro_fiuba

class Materia {

	String nombre
	String descripcion

  static constraints = {
		nombre blank: false, nullable: false, unique: true
		descripcion blank: true, nullable: true
  }

// ------------------------------------------------------------------------- //
	Materia(String nombre, String descripcion){
		this.nombre = nombre
		this.descripcion = descripcion
	}

// ------------------------------------------------------------------------- //	

}
