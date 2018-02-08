package nuevo_foro_fiuba

class Encuesta {

	Set<Opcion> opciones

	static hasMany = [
		opciones: Opcion,
	]

  static constraints = {
	 opciones blank: false, nullable: false
  }

	Encuesta(Set<Opcion> opciones){
		this.opciones = opciones
	}
}
