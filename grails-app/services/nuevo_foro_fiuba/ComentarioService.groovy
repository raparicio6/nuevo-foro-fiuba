package nuevo_foro_fiuba


import grails.gorm.transactions.Transactional

@Transactional
class ComentarioService {

  def serviceMethod() {}

  Comentario crearComentario(String texto, Usuario usuarioCreador,Publicacion publicacionComentada, Comentario comentarioComentado){
    Comentario comentario = new Comentario (texto, usuarioCreador, publicacionComentada, comentarioComentado)
    comentario.save(failOnError : true)
    comentario
  }

//LISTO
  def modificarTextoComentario (long idUsuario,long idComentario, String nuevoTexto){
    def usuario = getUsuarioById(idUsuario)
    def comentario = getComentarioById(idComentario)
    comentario.modificarTexto(nuevoTexto)
  }

//LISTO
  Boolean usuarioEsDueñoDelComentario(Usuario usuario, Comentario comentario){
    usuario.esDueñoDelComentario(comentario)
  }

//LISTO
  def esSubComentario(Comentario comentario){
    comentario.esSubComentario()
  }

//LISTO
  def obtenerComentariosNoEliminados(Comentario comentario){
    comentario.obtenerComentariosNoEliminados()
  }

//LISTO
  def eliminarComentario (Comentario comentario){
    comentario.eliminar()
  }

//LISTO
  def calificarComentario(long idUsuario, long idComentario, Puntaje.TipoPuntaje tipo){
    def usuario = getUsuarioById(idUsuario)
    def comentario = getComentarioById(idComentario)
    def promedioCalificaciones = (usuario.getPromedioCalificaciones()).toInteger()
    Integer numeroPuntaje = promedioCalificaciones + 0**promedioCalificaciones
    Puntaje puntaje = new Puntaje (tipo, numeroPuntaje)
    Calificacion calificacion = new Calificacion(usuario, puntaje, null, comentario)
    // orden incorrecto
    usuario.calificar(comentario, calificacion)
    calificacion.save(failOnError:true)
    comentario.getUsuarioCreador().actualizarPromedioCalificaciones()
  }

//LISTO
  def comentarComentario (long idUsuario, String textoComentario, long idComentario){
    def usuario = getUsuarioById(idUsuario)
    def comentarioAComentar = getComentarioById(idComentario)
    Comentario comentario = this.crearComentario(textoComentario, usuario, null, comentarioAComentar)
    usuario.comentarComentario(comentario, comentarioAComentar)
  }

  def getUsuarioById(long idUsuario){
    Usuario.get(idUsuario)
  }

  def getComentarioById(long idComentario){
    Comentario.get(idComentario)
  }

}
