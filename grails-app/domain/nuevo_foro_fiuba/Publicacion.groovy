package nuevo_foro_fiuba

class Publicacion {

	enum EstadoPublicacion {
		CERRADA,
		ABIERTA,
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
		usuarioCreador blank: false, nullable:false
		materiaRelacionada blank:false, nullable:true
		catedraRelacionada blank:false, nullable:true
		promedioRequeridoParaComentar blank:false, nullable:false
		materiasNecesariasParaComentar blank:false, nullable:false
		comentarios blank:false, nullable:false
		archivoAdjunto blank:false, nullable:true
		estado blank: false, nullable:false
		encuesta blank:false, nullable:true
		calificaciones blank:false, nullable:false
		fechaHoraCreacion blank:false, nullable:false
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

	Float obtenerPromedioRequeridoParaComentar(){
		this.promedioRequeridoParaComentar
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
}
