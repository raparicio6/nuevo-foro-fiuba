package nuevo_foro_fiuba

class Comentario {

	String texto
	Usuario usuarioCreador
	Publicacion publicacionComentada
	Comentario comentarioComentado
	Set <Comentario> comentarios
	Set <Calificacion> calificaciones
	String fechaHora

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
		fechaHora blank:false, nullable:false
	}
// ------------------------------------------------------------------------- //

	Comentario(String texto, Usuario usuarioCreador, Publicacion publicacionComentada, Comentario comentarioComentado){
		this.texto = texto
		this.usuarioCreador = usuarioCreador
		this.publicacionComentada = publicacionComentada
		this.comentarioComentado =  comentarioComentado
		this.comentarios = []
		this.calificaciones = []
		this.fechaHora = new Date().format( 'yyyyMMdd hh:mm:ss' )
	}
// ------------------------------------------------------------------------- //
	def modificarTexto(String nuevoTexto){
		this.setTexto(nuevoTexto)
	}
}
