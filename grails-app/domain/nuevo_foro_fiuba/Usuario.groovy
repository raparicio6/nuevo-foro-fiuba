package nuevo_foro_fiuba

class Usuario {

	String nombre
	String apellido
	String nombreUsuario
	Set <Publicacion> publicaciones
	Set <Comentario> comentarios
	Set <MensajePrivado> mensajes
	Set <Cursada> cursadas
	Integer puntajeActual


	static hasMany = [
		publicaciones: Publicacion,
		comentarios: Comentario,
		mensajes: MensajePrivado,
		cursadas: Cursada,
	]

  static constraints = {
		nombre blank: false, nullable: false
		apellido blank: false, nullable: false
		nombreUsuario blank: false, nullable: false, unique: true
		publicaciones blank:false, nullable: false
		comentarios blank:false, nullable: false
		cursadas blank:false, nullable: false
		mensajes blank:false, nullable: false
		puntajeActual blank:false, nullable: false
		}

// ------------------------------------------------------------------------- //

//CONSTRUCTOR
	Usuario(String nombre, String apellido, String nombreUsuario) {
		this.nombre = nombre
		this.apellido = apellido
		this.nombreUsuario = nombreUsuario
		this.publicaciones = []
		this.mensajes = []
		this.comentarios = []
		this.cursadas = []
		this.puntajeActual = 0
	}


// ------------------------------------------------------------------------- //
	def comentarPublicacion(Comentario comentario, Publicacion publicacionAComentar){
		if (publicacionAComentar.estaCerrada()){
			throw new PublicacionCerradaException()//no se puede comentar una publicacion cerrada
		}
		if (publicacionAComentar.obtenerPuntajeActual() > this.puntajeActual){
			throw new PuntajeInsuficienteException()
		}
		this.comentarios+=[comentario]
	}

	def comentarComentario(Comentario comentario, Comentario comentarioAComentar){
		if (comentarioAComentar.publicacionComentada.estaCerrada()){
			throw new PublicacionCerradaException() //no se puede comentar una publicacion cerrada
		}
		this.comentarios+=[comentario]
	}

	Boolean esDueñoDeLaPublicacion(Publicacion publicacion){
		return (publicacion.getUsuarioCreador().getId() == this.id)
	}

	Boolean esDueñoDelComentario(Comentario comentario){
		return (comentario.getUsuarioCreador().getId() == this.id)
	}

}
