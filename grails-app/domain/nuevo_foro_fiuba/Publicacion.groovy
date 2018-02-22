package nuevo_foro_fiuba

class Publicacion {

	enum EstadoPublicacion {
		CERRADA,
		ABIERTA,
		ELIMINADA,
	}

	String texto
	Usuario usuarioCreador
	Materia materiaRelacionada = null
	Catedra catedraRelacionada = null
	Float promedioRequeridoParaComentar = 0
	Set <Materia> materiasNecesariasParaComentar = []
	Set <Comentario> comentarios = []
	Archivo archivoAdjunto = null
	EstadoPublicacion estado = EstadoPublicacion.ABIERTA
	Encuesta encuesta = null
	Set <Calificacion> calificaciones = []
	Date fechaHoraCreacion = Date.parse('dd/MM/yyyy hh:mm', new Date().format( 'dd/MM/yyyy hh:mm' ));

	static hasMany = [
		comentarios: Comentario,
		materiasNecesariasParaComentar: Materia,
		calificaciones: Calificacion
	]

	static constraints = {
		texto blank: true, nullable:false
		usuarioCreador nullable:false
		materiaRelacionada nullable:true
		catedraRelacionada nullable:true
		promedioRequeridoParaComentar nullable:false
		materiasNecesariasParaComentar nullable:false
		comentarios nullable:false
		archivoAdjunto nullable:true
		estado nullable:false
		encuesta nullable:true
		calificaciones nullable:false
		fechaHoraCreacion nullable:false
  }

// ------------------------------------------------------------------------- //
	Publicacion(String texto, Usuario usuarioCreador){
		this.texto = texto
		this.usuarioCreador = usuarioCreador
	}

// ------------------------------------------------------------------------- //
	def estaCerrada(){
		this.estado==EstadoPublicacion.CERRADA
	}

	def estaEliminada(){
		this.estado==EstadoPublicacion.ELIMINADA
	}

	def cambiarEstado(){
		this.setEstado((this.estado == EstadoPublicacion.ABIERTA) ? EstadoPublicacion.CERRADA : EstadoPublicacion.ABIERTA)
	}

	def agregarCalificacion(Calificacion calificacion){
		this.calificaciones << calificacion
	}

	def agregarComentario(Comentario comentario){
		this.comentarios << comentario
	}

	def modificarTexto(String nuevoTexto){
		this.setTexto(nuevoTexto)
	}

	def modificarMateria(Materia materia){
		this.setMateriaRelacionada(materia)
	}

	def modificarCatedra(Catedra catedra){
		this.setCatedraRelacionada(catedra)
	}

	def modificarPromedioRequeridoParaComentar(Float promedio){
		this.setPromedioRequeridoParaComentar(promedio)
	}

	def eliminar(){
		this.comentarios.collect {comentario -> comentario.eliminar()}
		this.calificaciones.collect {calificacion -> calificacion.eliminar()}
		this.setEstado(EstadoPublicacion.ELIMINADA)
	}

	def obtenerComentariosNoEliminados(){
		this.comentarios.findAll {comentario -> comentario.getEstado() != Comentario.EstadoComentario.ELIMINADO}
	}

	def agregarMateriaRequeridaParaComentar(Materia materiaInstance){
		this.materiasNecesariasParaComentar << materiaInstance
	}

	def agregarEncuesta(Encuesta encuesta){
		this.setEncuesta(encuesta)
	}

	def agregarArchivo (Archivo archivoAdjunto){
		this.setArchivoAdjunto(archivoAdjunto)
	}

}
