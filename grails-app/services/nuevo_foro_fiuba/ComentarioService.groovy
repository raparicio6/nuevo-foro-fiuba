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
      comentario.comentarios.collect {subcomentario -> subcomentario.eliminar()}
      comentario.eliminar()
    }

    def calificarComentario(Usuario usuario, Comentario comentario, Puntaje.TipoPuntaje tipo){
      def promedioCalificaciones = (usuario.getPromedioCalificaciones()).toInteger()
      Integer numeroPuntaje = promedioCalificaciones + 0**promedioCalificaciones
      Puntaje puntaje = new Puntaje (tipo, numeroPuntaje)
      puntaje.save(failOnError:true)
      Calificacion calificacion = new Calificacion(usuario, puntaje, null, comentario)
      calificacion.save(failOnError:true)
      usuario.calificar(comentario, calificacion)
      def usuarioCalificado = comentario.getUsuarioCreador()
      def publicaciones = usuarioCalificado.getPublicaciones()
      def calificaciones = publicaciones.collect {publicacionInstance -> publicacionInstance.calificaciones}
      def comentarios = usuarioCalificado.getComentarios()
      calificaciones += comentarios.collect {comentarioInstance -> comentarioInstance.calificaciones}
      calificaciones = calificaciones.flatten()
      def contador = 0
      calificaciones.collect {calificacionInstance -> contador += Puntaje.TipoPuntaje.getProporcion(calificacionInstance.puntaje.tipo) * calificacionInstance.puntaje.numero}
      promedioCalificaciones = (contador/calificaciones.size()).toFloat()
      usuarioCalificado.setPromedioCalificaciones(promedioCalificaciones)
      //siempre va a tener al menos una calificacion
    }

    def comentarComentario (Usuario usuario, String textoComentario, Comentario comentarioAComentar){
      Comentario comentario = new Comentario(textoComentario, usuario, null, comentarioAComentar)
      comentario.save()
      usuario.comentarComentario(comentario, comentarioAComentar)
    }

}
