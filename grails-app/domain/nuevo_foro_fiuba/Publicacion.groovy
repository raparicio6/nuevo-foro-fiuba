package nuevo_foro_fiuba


class Publicacion {

	enum Estado {
		cerrado, abierto
	}

	String texto
	Usuario usuarioCreador
	Materia materiaRelacionada
	Catedra catedraRelacionada
	Integer puntajeRequeridoParaComentar
	Set <Materia> materiasNecesariasParaComentar
	Set <Comentario> comentarios
	Archivo archivoAdjunto
	Estado estado
	Encuesta encuesta
	Set <Calificacion> calificaciones


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
		puntajeRequeridoParaComentar blank:false, nullable:false
		materiasNecesariasParaComentar blank:false, nullable:false
		comentarios blank:false, nullable:false
		archivoAdjunto blank:false, nullable:true
		estado blank: false, nullable:false
		encuesta blank:false, nullable:true
		calificaciones blank:false, nullable:false
    }


	Publicacion (String texto, Usuario usuarioCreador, Materia materiaRelacionada, Catedra catedraRelacionada){
		this.texto = texto
		this.usuarioCreador = usuarioCreador
		this.materiaRelacionada = materiaRelacionada
		this.catedraRelacionada = catedraRelacionada
		puntajeRequeridoParaComentar = 0
		materiasNecesariasParaComentar = []
		this.comentarios = []
		this.archivoAdjunto = null
		this.estado = Estado.abierto
		this.encuesta = null
		this.calificaciones = []
	}

	String obtenerVista(Usuario usuario){
		if (usuario.id == this.id){
			return "verPublicacion"
		}
		return "verPublicacion2"
	}

	Integer estaCerrada(){
		if (this.estado==Estado.cerrado)
			return 1
		return 0
	}

	Integer obtenerPuntajeActual(){
		return this.puntajeRequeridoParaComentar
	}

	def cerrar(){
		this.estado=Estado.cerrado
	}

	def abrir(){
		this.estado=Estado.abierto
	}

	def agregarCalificacion(Calificacion calificacion){
		this.calificaciones += [calificacion]
	}

}
