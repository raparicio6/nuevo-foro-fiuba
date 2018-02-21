package nuevo_foro_fiuba

class Encuesta {

	String nombre
	Set<Opcion> opciones

	static hasMany = [
		opciones: Opcion,
	]

  static constraints = {
		nombre blank:false, nullable:false
		opciones nullable: false
  }

// ------------------------------------------------------------------------- //
	Encuesta(String nombre, Set<Opcion> opciones){
		this.nombre = nombre
		this.opciones = opciones
	}

// ------------------------------------------------------------------------- //
	def obtenerUsuariosQueVotaron(){
		def todosLosVotos = this.opciones.collect {opcionInstance -> opcionInstance.getVotos() }
		todosLosVotos.flatten().collect {voto -> voto.getUsuario()}
	}
}
