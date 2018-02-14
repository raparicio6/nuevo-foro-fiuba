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

  def modificarTexto (Usuario usuario,def objetoAModificar, String nuevoTexto){
    objetoAModificar.modificarTexto(nuevoTexto)
  }

  Boolean usuarioEsDueñoDelComentario(Usuario usuario, Comentario comentario){
    usuario.esDueñoDelComentario(comentario)
  }

  def esSubComentario(Comentario comentario){
    comentario.esSubComentario()
  }

  def eliminarComentario (Comentario comentario){    
    comentario.eliminarComentarios()
    comentario.eliminar()
  }

  def calificarComentario(Usuario usuario, Comentario comentario, Puntaje.TipoPuntaje tipo){
    def promedioCalificaciones = (usuario.getPromedioCalificaciones()).toInteger()
    Integer numeroPuntaje = promedioCalificaciones + 0**promedioCalificaciones
    Puntaje puntaje = new Puntaje (tipo, numeroPuntaje)
    Calificacion calificacion = new Calificacion(usuario, puntaje, null, comentario)
    // orden incorrecto
    usuario.calificar(comentario, calificacion)
    calificacion.save(failOnError:true)
    comentario.getUsuarioCreador().actualizarPromedioCalificaciones()
  }

  def comentarComentario (Usuario usuario, String textoComentario, Comentario comentarioAComentar){
    Comentario comentario = this.crearComentario(textoComentario, usuario, null, comentarioAComentar)
    usuario.comentarComentario(comentario, comentarioAComentar)
  }

}
