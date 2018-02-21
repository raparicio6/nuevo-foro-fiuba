package nuevo_foro_fiuba

class Comentario {

	enum EstadoComentario {
		VIGENTE,
		ELIMINADO,
	}

	String texto
	Usuario usuarioCreador
	Publicacion publicacionComentada
	Comentario comentarioComentado
	Set <Comentario> comentarios = []
	Set <Calificacion> calificaciones = []
	Date fechaHoraCreacion = Date.parse('dd/MM/yyyy hh:mm', new Date().format( 'dd/MM/yyyy hh:mm' ));
	EstadoComentario estado = EstadoComentario.VIGENTE

	static hasMany = [
		comentarios: Comentario,
		calificaciones: Calificacion
	]

	static constraints = {
		texto blank: true, nullable: false
		usuarioCreador nullable:false
		publicacionComentada nullable:true
		comentarioComentado nullable:true
		comentarios nullable:false
		calificaciones nullable:false
		fechaHoraCreacion nullable:false
		estado nullable:false
	}

// ------------------------------------------------------------------------- //
	Comentario(String texto, Usuario usuarioCreador, Publicacion publicacionComentada, Comentario comentarioComentado){
		this.texto = texto
		this.usuarioCreador = usuarioCreador
		this.publicacionComentada = publicacionComentada
		this.comentarioComentado =  comentarioComentado
	}

// ------------------------------------------------------------------------- //
	def modificarTexto(String nuevoTexto){
		this.setTexto(nuevoTexto)
	}

	def esSubComentario(){
		this.comentarioComentado
	}

	def agregarCalificacion(Calificacion calificacion){
		this.calificaciones << calificacion
	}

	def agregarComentario(Comentario comentario){
		this.comentarios << comentario
	}

	def eliminar(){
		this.comentarios.collect{subcomentario -> subcomentario.setEstado(EstadoComentario.ELIMINADO)}
		this.setEstado(EstadoComentario.ELIMINADO)
	}

	def obtenerComentariosNoEliminados(){
		this.comentarios.findAll {comentario -> comentario.getEstado() != Comentario.EstadoComentario.ELIMINADO}
	}

}
