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
	Set <Comentario> comentarios
	Set <Calificacion> calificaciones
	Date fechaHoraCreacion
	EstadoComentario estado

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
		this.comentarios = []
		this.calificaciones = []
		String dia = new Date().format( 'dd/MM/yyyy hh:mm' )
		this.fechaHoraCreacion = Date.parse('dd/MM/yyyy hh:mm', dia);
		this.estado = EstadoComentario.VIGENTE
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
		this.setEstado(EstadoComentario.ELIMINADO)
	}

	def eliminarComentarios(){
		this.comentarios.collect{subcomentario -> subcomentario.eliminar()}
	}

	def obtenerComentariosNoEliminados(){
		this.comentarios.findAll {comentario -> comentario.getEstado() != Comentario.EstadoComentario.ELIMINADO}
	}

}
