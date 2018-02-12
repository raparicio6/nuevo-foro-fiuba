package nuevo_foro_fiuba

class Usuario {

	String nombre
	String apellido
	String nombreUsuario
	Set <Publicacion> publicaciones
	Set <Comentario> comentarios
	Set <InformacionMensajeUsuario> mensajes
	Set <Cursada> cursadas
	Float promedioCalificaciones

	static hasMany = [
		publicaciones: Publicacion,
		comentarios: Comentario,
		mensajes: InformacionMensajeUsuario,
		cursadas: Cursada,
	]

  static constraints = {
		nombre blank: false, nullable: false
		apellido blank: false, nullable: false
		nombreUsuario blank: false, nullable: false, unique: true
		publicaciones nullable: false
		comentarios nullable: false
		cursadas nullable: false
		mensajes nullable: false
		promedioCalificaciones nullable: false, min:0F
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
		this.promedioCalificaciones = 2.5
	}

// ------------------------------------------------------------------------- //
	def comentarPublicacion(Comentario comentario, Publicacion publicacionAComentar){
		//no se puede comentar una publicacion cerrada
		if (publicacionAComentar.estaCerrada())
			throw new PublicacionCerradaException()
		if (publicacionAComentar.getPromedioRequeridoParaComentar() > this.promedioCalificaciones)
			throw new PromedioInsuficienteException()
		this.comentarios << comentario
		publicacionAComentar.agregarComentario(comentario)
	}

	def comentarComentario(Comentario comentario, Comentario comentarioAComentar){
		//no se puede comentar una publicacion cerrada
		if (comentarioAComentar.publicacionComentada.estaCerrada())
			throw new PublicacionCerradaException()
		this.comentarios << comentario
		comentarioAComentar.agregarComentario(comentario)
	}

	Boolean esDueñoDeLaPublicacion(Publicacion publicacion){
		(publicacion.getUsuarioCreador().getId() == this.id)
	}

	Boolean esDueñoDelComentario(Comentario comentario){
		(comentario.getUsuarioCreador().getId() == this.id)
	}

	def cambiarEstado(Publicacion publicacion){
		publicacion.cambiarEstado()
	}

	def modificarMateriaPublicacion(Publicacion publicacion, Materia materia){
		publicacion.cambiarMateria(materia)
	}

	def modificarCatedraPublicacion(Publicacion publicacion, Catedra catedra){
		publicacion.cambiarCatedra(catedra)
	}

	def eliminarPublicacion(Publicacion publicacion){
		this.publicaciones.removeAll {it==publicacion}
	}

	// def eliminarComentario(Comentario comentario){
	// 	this.comentarios.removeAll {it==comentario}
	// 	comentario.eliminar()
	// }

	def modificarPromedioRequeridoParaComentar(Publicacion publicacion, Integer promedio){
		publicacion.modificarPromedioRequeridoParaComentar(promedio)
	}

	def calificar(def calificable, Calificacion calificacion){
		def calificaciones = calificable.calificaciones.collect {calificacionInstance -> calificacionInstance.usuario.getId()}
		def calificacionesUsuario = calificaciones.findAll {idUsuario -> idUsuario == this.id}
		if (calificacionesUsuario.size()>1)
			throw new UsuarioYaCalificoException()
		else
			calificable.agregarCalificacion(calificacion)
	}

	def enviarMensaje(Usuario receptor, InformacionMensajeUsuario infoEmisor, InformacionMensajeUsuario infoReceptor){
		this.guardarMensaje(infoEmisor)
		receptor.guardarMensaje(infoReceptor)
	}

	def guardarMensaje(InformacionMensajeUsuario informacion){
		this.mensajes << informacion
	}

}
