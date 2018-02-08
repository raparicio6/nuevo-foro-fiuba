package nuevo_foro_fiuba

class Encuesta {

	Set<Opcion> opciones
	Set<Usuario> usuariosQueVotaron

	static hasMany = [
		opciones: Opcion,
		usuariosQueVotaron: Usuario
	]

  static constraints = {
	 opciones blank: false, nullable: false
	 usuariosQueVotaron blank: false, nullable: false
  }

	Encuesta(Set<Opcion> opciones){
		this.opciones = opciones
		this.usuariosQueVotaron = []
	}
}
