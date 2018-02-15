package nuevo_foro_fiuba

class Calificacion {

	enum EstadoCalificacion{
		VIGENTE,
		ELIMINADA,
	}

	Usuario usuario
	Publicacion publicacion
	Comentario comentario
	Puntaje puntaje
	EstadoCalificacion estado

	static embedded = ['puntaje']

  static constraints = {
  	usuario nullable: false
		publicacion nullable:true
		comentario nullable:true
		puntaje nullable:false
		estado nullable:false
	}

	Calificacion(Usuario usuario, Puntaje puntaje, Publicacion publicacion, Comentario comentario) {
		this.usuario = usuario
		this.publicacion = publicacion
    this.comentario = comentario
		this.puntaje = puntaje
		this.estado = EstadoCalificacion.VIGENTE
	}

	def eliminar(){
		this.setEstado(EstadoCalificacion.ELIMINADA)
	}

}
