package nuevo_foro_fiuba


class Comentario {

	String texto
	Usuario usuarioCreador
	Publicacion publicacionComentada
	Comentario comentarioComentado
	Set <Comentario> comentarios
	Set <Calificacion> calificaciones
	Date fechaHoraCreacion

	static hasMany = [
		comentarios: Comentario
	]

	static constraints = {
		texto blank: true, nullable: false
		usuarioCreador blank:false, nullable:false
		publicacionComentada blank:false, nullable:true
		comentarioComentado blank:false, nullable:true
		comentarios blank:false, nullable:false
		calificaciones blank:false, nullable:false
		fechaHoraCreacion blank:false, nullable:false
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
}
