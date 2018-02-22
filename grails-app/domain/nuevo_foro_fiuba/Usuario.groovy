package nuevo_foro_fiuba

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includeFields=true)
class Usuario {

	String nombre
	String apellido
	String nombreUsuario
	Set <Publicacion> publicaciones = []
	Set <Comentario> comentarios = []
	Set <InformacionMensajeUsuario> informacionMensajes = []
	Set <Cursada> cursadas = []
	Float promedioCalificaciones
	// puntajeActual

	static hasMany = [
		publicaciones: Publicacion,
		comentarios: Comentario,
		informacionMensajes: InformacionMensajeUsuario,
		cursadas: Cursada
	]

  static constraints = {
		nombre blank: false, nullable: false
		apellido blank: false, nullable: false
		nombreUsuario blank: false, nullable: false, unique: true
		publicaciones nullable: false
		comentarios nullable: false
		cursadas nullable: false
		informacionMensajes nullable: false
		promedioCalificaciones nullable: false
	}

// ------------------------------------------------------------------------- //
//CONSTRUCTOR
	Usuario(String nombre, String apellido, String nombreUsuario, Float calificacionInicial) {
		this.nombre = nombre
		this.apellido = apellido
		this.nombreUsuario = nombreUsuario
		this.promedioCalificaciones = calificacionInicial
	}

// ------------------------------------------------------------------------- //
	def comentarPublicacion(Comentario comentario, Publicacion publicacionAComentar){
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
		if (comentarioAComentar.publicacionComentada.estaCerrada())
			throw new PublicacionCerradaException()
		this.comentarios << comentario
		comentarioAComentar.agregarComentario(comentario)
	}

	def publicar(Publicacion publicacion){
		this.publicaciones << publicacion
	}

	def esDueñoDeLaPublicacion(Publicacion publicacion){
		(publicacion.getUsuarioCreador() == this)
	}

	def esDueñoDelComentario(Comentario comentario){
		(comentario.getUsuarioCreador() == this)
	}

	def calificar(def calificable, Calificacion calificacion){
		def calificaciones = calificable.calificaciones
		def calificacionesUsuario = calificaciones.findAll {calificacionInstance -> calificacionInstance.getUsuario() == this}
		if (calificacionesUsuario.size() >= 1)
			throw new UsuarioYaCalificoException()
		else
			calificable.agregarCalificacion(calificacion)
	}

	def enviarMensaje(Usuario receptor, InformacionMensajeUsuario infoEmisor, InformacionMensajeUsuario infoReceptor){
		this.guardarInformacionMensaje(infoEmisor)
		receptor.guardarInformacionMensaje(infoReceptor)
	}

	def guardarInformacionMensaje(InformacionMensajeUsuario informacion){
		this.informacionMensajes << informacion
	}

	def agregarCursada(Cursada cursada){
		this.cursadas << cursada
	}

	def actualizarPromedioCalificaciones(Calificacion calificacion){
		// def publicaciones = this.publicaciones
		// def calificaciones = publicaciones.collect {publicacionInstance -> publicacionInstance.calificaciones}
		// def comentarios = this.comentarios
		// calificaciones += comentarios.collect {comentarioInstance -> comentarioInstance.calificaciones}
		// calificaciones = calificaciones.flatten()
    //
		// def contador = 0
		// def tamanio = 0
		// def calificacionesPositivas = calificaciones.findAll { calificacion ->
		// 	Puntaje.TipoPuntaje.getProporcion(calificacion.puntaje.tipo) > 0 }
		// calificacionesPositivas.each { calificacion ->
		// 			def numeroPuntaje = calificacion.puntaje.numero
		// 			if (numeroPuntaje > this.promedioCalificaciones){
		// 				contador += Puntaje.TipoPuntaje.getProporcion(calificacion.puntaje.tipo) * numeroPuntaje
		// 				tamanio += 1
		// 			}else
		// 				contador += numeroPuntaje * this.ProporcionAIncrementarEnPuntajesMenores
		// }
		// def promedioDeCalificaciones
		// if (tamanio > 0)
		// 	promedioDeCalificaciones = contador/tamanio
		// else
		// 	promedioDeCalificaciones = this.promedioCalificaciones + contador
    //
		// def calificacionesNegativas = calificaciones.findAll { calificacion ->
		// 	Puntaje.TipoPuntaje.getProporcion(calificacion.puntaje.tipo) < 0
		// }
		// calificacionesNegativas.each {calificacion ->
		// 		promedioDeCalificaciones += Puntaje.TipoPuntaje.getProporcion(calificacion.puntaje.tipo) * calificacion.puntaje.numero
		// }

		def promedio = this.promedioCalificaciones
		promedio += Puntaje.TipoPuntaje.getProporcion(calificacion.puntaje.tipo) * calificacion.puntaje.numero
		if (promedio > 5)
			promedio = 5
		if (promedio < 0)
			promedio = 0
		this.setPromedioCalificaciones(promedio.toFloat())
	}

	def agregarPublicacion(Publicacion publicacion){
		this.publicaciones << publicacion
	}

	def obtenerMateriasCursadas(){
		this.cursadas.collect {cursada -> cursada.catedra.materia}
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

	def cursaEstasMaterias(def materiasFiltro){
		return 	!(false in materiasFiltro.collect {materia -> materia in this.obtenerMateriasCursadas()})
	}

}
