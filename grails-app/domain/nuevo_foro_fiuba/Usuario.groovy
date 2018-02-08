package nuevo_foro_fiuba

class Usuario {

	String nombre
	String apellido
	String nombreUsuario
	Set <Publicacion> publicaciones
	Set <Comentario> comentarios
	Set <MensajePrivado> mensajes
	Set <Cursada> cursadas
	Integer promedioCalificaciones

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
		promedioCalificaciones blank:false, nullable: false
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
		this.promedioCalificaciones = 0
	}


// ------------------------------------------------------------------------- //
	def comentarPublicacion(Comentario comentario, Publicacion publicacionAComentar){
		if (publicacionAComentar.estaCerrada()){
			throw new PublicacionCerradaException()//no se puede comentar una publicacion cerrada
		}
		if (publicacionAComentar.obtenerPromedioRequeridoParaComentar() > this.promedioCalificaciones){
			throw new PromedioInsuficienteException()
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
		(publicacion.getUsuarioCreador().getId() == this.id)
	}

	Boolean esDueñoDelComentario(Comentario comentario){
		(comentario.getUsuarioCreador().getId() == this.id)
	}

	def modificarTextoPublicacion(Publicacion publicacion, String nuevoTexto){
		publicacion.modificarTexto(nuevoTexto)
	}

	def modificarTextoComentario(Comentario comentario, String nuevoTexto){
		comentario.modificarTexto(nuevoTexto)
	}

	def eliminarComentario(Comentario comentario){
		this.comentarios.removeAll {it.id==comentario.getId()}
	}

	def cerrarPublicacion(Publicacion publicacion){
		publicacion.cerrar()
	}

	def abrirPublicacion(Publicacion publicacion){
		publicacion.abrir()
	}

	def modificarMateriaPublicacion(Publicacion publicacion, Materia materia){
		publicacion.cambiarMateria(materia)
	}

	def modificarCatedraPublicacion(Publicacion publicacion, Catedra catedra){
		publicacion.cambiarCatedra(catedra)
	}

	def eliminarPublicacion(Publicacion publicacion){
		this.publicaciones.removeAll {it.id==publicacion.getId()}
	}

	def modificarPromedioRequeridoParaComentar(Publicacion publicacion, Integer promedio){
		publicacion.modificarPromedioRequeridoParaComentar(promedio)
	}

	def calificar(publicacion, calificacion){
		publicacion.agregarCalificacion(calificacion)
	}
}
