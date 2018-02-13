package nuevo_foro_fiuba

class Publicacion {

	enum EstadoPublicacion {
		CERRADA,
		ABIERTA,
		ELIMINADA,
	}

	String texto
	Usuario usuarioCreador
	Materia materiaRelacionada
	Catedra catedraRelacionada
	Float promedioRequeridoParaComentar
	Set <Materia> materiasNecesariasParaComentar
	Set <Comentario> comentarios
	Archivo archivoAdjunto
	EstadoPublicacion estado
	Encuesta encuesta
	Set <Calificacion> calificaciones
	Date fechaHoraCreacion

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

	Publicacion(String texto, Usuario usuarioCreador, Materia materiaRelacionada, Catedra catedraRelacionada){
		this.texto = texto
		this.usuarioCreador = usuarioCreador
		this.materiaRelacionada = materiaRelacionada
		this.catedraRelacionada = catedraRelacionada
		this.promedioRequeridoParaComentar = 0
		this.materiasNecesariasParaComentar = []
		this.comentarios = []
		this.archivoAdjunto = null
		this.estado = EstadoPublicacion.ABIERTA
		this.encuesta = null
		this.calificaciones = []
		String dia = new Date().format( 'dd/MM/yyyy hh:mm' )
		this.fechaHoraCreacion = Date.parse('dd/MM/yyyy hh:mm', dia);
	}
// ------------------------------------------------------------------------- //

	Boolean estaCerrada(){
		this.estado==EstadoPublicacion.CERRADA
	}

	Boolean estaEliminada(){
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

	def cambiarMateria(Materia materia){
		this.setMateriaRelacionada(materia)
	}

	def cambiarCatedra(Catedra catedra){
		this.setCatedraRelacionada(catedra)
	}

	def modificarPromedioRequeridoParaComentar(Float promedio){
		this.setPromedioRequeridoParaComentar(promedio)
	}

	def eliminar(){
		this.setEstado(EstadoPublicacion.ELIMINADA)
	}
}
