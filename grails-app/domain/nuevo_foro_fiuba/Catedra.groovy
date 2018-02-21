package nuevo_foro_fiuba

class Catedra {

	Materia materia
	Profesor profesor
	String descripcion 

  static constraints = {
		materia  nullable: false
		profesor nullable: false
		descripcion blank: true, nullable: true
  }

// ------------------------------------------------------------------------- //
	Catedra(Materia materia, Profesor profesor, String descripcion){
		this.materia = materia
		this.profesor = profesor
		this.descripcion = descripcion
	}
}

// ------------------------------------------------------------------------- //
