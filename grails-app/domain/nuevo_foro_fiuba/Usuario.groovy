package nuevo_foro_fiuba

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includeFields=true)
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
		cursadas: Cursada
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
		this.promedioCalificaciones = 0
	}

// ------------------------------------------------------------------------- //
	def comentarPublicacion(Comentario comentario, Publicacion publicacionAComentar){
		//no se puede comentar una publicacion cerrada
		if (publicacionAComentar.estaCerrada())
			throw new PublicacionCerradaException()
		if (publicacionAComentar.estaEliminada())
			throw new PublicacionEliminadaException()
		if (publicacionAComentar.getPromedioRequeridoParaComentar() > this.promedioCalificaciones && !this.esDueñoDeLaPublicacion(publicacionAComentar))
			throw new PromedioInsuficienteException()
		if (!this.poseeMateriasNecesariasParaComentar(publicacionAComentar) && !this.esDueñoDeLaPublicacion(publicacionAComentar))
			throw new UsuarioNoPoseeMateriasNecesariasException()
		this.comentarios << comentario
		publicacionAComentar.agregarComentario(comentario)
	}

	def poseeMateriasNecesariasParaComentar(Publicacion publicacion){
		!(false in publicacion.getMateriasNecesariasParaComentar().collect {materia -> materia in this.obtenerMateriasCursadas()})
	}

	def comentarComentario(Comentario comentario, Comentario comentarioAComentar){
		//no se puede comentar una publicacion cerrada
		if (comentarioAComentar.publicacionComentada.estaCerrada())
			throw new PublicacionCerradaException()
		this.comentarios << comentario
		comentarioAComentar.agregarComentario(comentario)
	}

	// Boolean esDueñoDeLaPublicacion(Publicacion publicacion){
	// 	(publicacion.getUsuarioCreador() == this)
	// }
	//
	// Boolean esDueñoDelComentario(Comentario comentario){
	// 	(comentario.getUsuarioCreador() == this)
	// }

	//LOS DE ARRIBA NO FUNCIONAN
	// hay que preguntar para que se pueda usar algo como lo de arriba

	def publicar(Publicacion publicacion){
		this.publicaciones << publicacion
	}

	Boolean esDueñoDeLaPublicacion(Publicacion publicacion){
		(publicacion.getUsuarioCreador() == this)
	}

	Boolean esDueñoDelComentario(Comentario comentario){
		(comentario.getUsuarioCreador() == this)
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

	def modificarPromedioRequeridoParaComentar(Publicacion publicacion, Float promedio){
		publicacion.modificarPromedioRequeridoParaComentar(promedio)
	}

	def calificar(def calificable, Calificacion calificacion){
		def calificaciones = calificable.calificaciones
		def calificacionesUsuario = calificaciones.findAll {calificacionInstance -> calificacionInstance.getUsuario() == this}
		if (calificacionesUsuario.size()>= 1)
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

	def agregarCursada(Cursada cursada){
		this.cursadas << cursada
	}

	def actualizarPromedioCalificaciones(){
		// EDITAR (rodrigo)
		def publicaciones = this.publicaciones
		def calificaciones = publicaciones.collect {publicacionInstance -> publicacionInstance.calificaciones}
		def comentarios = this.comentarios
		calificaciones += comentarios.collect {comentarioInstance -> comentarioInstance.calificaciones}
		calificaciones = calificaciones.flatten()

		def contador = 0
		def calificacionesPositivas = calificaciones.findAll { calificacion ->
			Puntaje.TipoPuntaje.getProporcion(calificacion.puntaje.tipo) > 0 }
		calificacionesPositivas.collect { calificacion ->
					def numeroPuntaje = calificacion.puntaje.numero
					(numeroPuntaje > this.promedioCalificaciones) ?
					(contador += Puntaje.TipoPuntaje.getProporcion(calificacion.puntaje.tipo) * numeroPuntaje) :
					(contador += this.promedioCalificaciones + numeroPuntaje * 0.05)
		}
		def promedioDeCalificaciones = (contador/calificacionesPositivas.size())

		def calificacionesNegativas = calificaciones.findAll { calificacion ->
			Puntaje.TipoPuntaje.getProporcion(calificacion.puntaje.tipo) < 0
		}
		calificacionesNegativas.collect {calificacion ->
				promedioDeCalificaciones += Puntaje.TipoPuntaje.getProporcion(calificacion.puntaje.tipo) * calificacion.puntaje.numero
		}

		this.setPromedioCalificaciones(promedioDeCalificaciones.toFloat())
		//siempre va a tener al menos una calificacion cuando se llame a este método, no hay división por 0
	}

	def agregarPublicacion(Publicacion publicacion){
		this.publicaciones << publicacion
	}

	def obtenerMateriasCursadas(){
		this.cursadas.collect {cursada -> cursada.catedra.materia}
	}

	def agregarMateriaRequeridaParaComentar(Publicacion publicacionInstance, Materia materiaInstance){
		publicacionInstance.agregarMateriaRequeridaParaComentar(materiaInstance)
	}

	def votarOpcion(Publicacion publicacion,Opcion opcion, Voto voto){
		if (publicacion.getUsuarioCreador() == this)
			throw new CreadorEncuestaNoPuedeVotarException()
		if (publicacion.estaCerrada())
			throw new PublicacionCerradaException()
		if (this in publicacion.encuesta.obtenerUsuariosQueVotaron())
			throw new UsuarioYaVotoException()
		opcion.agregarVoto(voto)
	}

	def eliminarPublicacion(Publicacion publicacion){
		publicacion.eliminar()
	}

}
