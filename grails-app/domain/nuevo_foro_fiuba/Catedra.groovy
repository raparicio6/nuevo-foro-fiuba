package nuevo_foro_fiuba

class Catedra {

	Materia materia
	Profesor profesor
	String descripcion

    static constraints = {
		materia blank: false, nullable: false
		profesor blank: false, nullable: false
		descripcion blank: true, nullable: true
    }

	Catedra (Materia materia, Profesor profesor, String descripcion){
		this.materia = materia
		this.profesor = profesor
		this.descripcion = descripcion
	}
}
