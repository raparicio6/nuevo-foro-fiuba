package nuevo_foro_fiuba


import grails.gorm.transactions.Transactional

@Transactional
class ComentarioService {

  def serviceMethod() {}

  Comentario crearComentario(String texto, Usuario usuarioCreador, Comentario comentarioComentado = null, Publicacion publicacionComentada = null ){
    Comentario comentario = new Comentario (texto, usuarioCreador, publicacionComentada, comentarioComentado)
    comentario.save(failOnError : true)
    comentario
  }

  def modificarTextoComentario (long idUsuario,long idComentario, String nuevoTexto){
    def usuario = this.getUsuarioById(idUsuario)
    def comentario = getComentarioById(idComentario)
    comentario.modificarTexto(nuevoTexto)
  }

  def esSubComentario(Comentario comentario){
    comentario.esSubComentario()
  }

  def obtenerComentariosNoEliminados(Comentario comentario){
    comentario.obtenerComentariosNoEliminados()
  }

  def eliminarComentario (Comentario comentario){
    comentario.eliminar()
  }

  def calificarComentario(long idUsuario, long idComentario, Puntaje.TipoPuntaje tipo){
    def usuario = this.getUsuarioById(idUsuario)
    def comentario = getComentarioById(idComentario)
    def promedioCalificaciones = usuario.getPromedioCalificaciones()
    Puntaje puntaje = new Puntaje (tipo, promedioCalificaciones)
    Calificacion calificacion = new Calificacion(usuario, puntaje, null, comentario)
    usuario.calificar(comentario, calificacion)
    comentario.getUsuarioCreador().actualizarPromedioCalificaciones(calificacion)
    calificacion.save(failOnError:true)
  }

  Comentario comentarComentario (long idUsuario, String textoComentario, long idComentario){
    def usuario = this.getUsuarioById(idUsuario)
    def comentarioAComentar = getComentarioById(idComentario)
    Comentario comentario = this.crearComentario(textoComentario, usuario, comentarioAComentar)
    usuario.comentarComentario(comentario, comentarioAComentar)
    comentario
  }

  def getComentarioById(long idComentario){
    Comentario.get(idComentario)
  }

  def getUsuarioById(long idUsuario){
    Usuario.get(idUsuario)
  }

}
