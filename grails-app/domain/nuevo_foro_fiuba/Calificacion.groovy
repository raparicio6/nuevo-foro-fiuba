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
	Date fechaHoraCreacion

	static embedded = ['puntaje']

  static constraints = {
  	usuario nullable: false
		publicacion nullable:true
		comentario nullable:true
		puntaje nullable:false
		estado nullable:false
		fechaHoraCreacion nullable:false
	}

// ------------------------------------------------------------------------- //
	Calificacion(Usuario usuario, Puntaje puntaje, Publicacion publicacion, Comentario comentario) {
		this.usuario = usuario
		this.publicacion = publicacion
    this.comentario = comentario
		this.puntaje = puntaje
		this.estado = EstadoCalificacion.VIGENTE
		String dia = new Date().format( 'dd/MM/yyyy hh:mm' )
		this.fechaHoraCreacion = Date.parse('dd/MM/yyyy hh:mm', dia);
	}

// ------------------------------------------------------------------------- //	
	def eliminar(){
		this.setEstado(EstadoCalificacion.ELIMINADA)
	}

}
