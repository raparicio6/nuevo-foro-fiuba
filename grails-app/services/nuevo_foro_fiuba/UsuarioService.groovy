package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class UsuarioService {

    def serviceMethod() {}

    Usuario crearUsuario(String nombre, String apellido, String nombreUsuario){
      Usuario usuario = new Usuario(nombre, apellido, nombreUsuario)
      usuario.save()
      return usuario
    }

    def enviarMensaje (MensajePrivado mensaje, Usuario usuarioReceptor){
      usuarioReceptor.setMensaje(mensaje)
    }

    def modificarPuntaje(Usuario usuario, Puntaje puntaje){
      usuario.modificarPuntaje(puntaje)
    }

    def agregarComentario (Usuario usuario, Comentario comentario){
      usuario.setComentarios(usuario.getComentarios() + [comentario])
    }

    def comentarPublicacion (Usuario usuario, Comentario comentario, Publicacion publicacionAComentar){
      usuario.comentarPublicacion(comentario, publicacionAComentar)
    }

    def comentarComentario (Usuario usuario, Comentario comentario, Comentario comentarioAComentar){
      usuario.comentarComentario(comentario, comentarioAComentar)
    }

    def materiasCursadas(Usuario usuario){
      return usuario.cursadas.collect {cursada -> return cursada.catedra.materia.id}
    }

    Boolean usuarioEsDueñoDeLaPublicacion(Usuario usuario, Publicacion publicacion){
      return usuario.esDueñoDeLaPublicacion(publicacion)
    }

    Boolean usuarioEsDueñoDelComentario(Usuario usuario, Comentario comentario){
      return usuario.esDueñoDelComentario(comentario)
    }

    def puntuarPublicacion(Usuario usuario, Publicacion publicacion, Calificacion calificacion){
      usuario.puntuarPublicacion(publicacion, calificacion)
    }

    def actualizarPuntajeActual(Usuario usuario){
      def publicaciones = usuario.getPublicaciones()
      def calificaciones = publicaciones.collect {publicacion -> publicacion.calificaciones}
      calificaciones = calificaciones.flatten()
      def contador = 0
      def puntajeAcumulado = calificaciones.collect {calificacion -> contador += Puntaje.getSignoTipo(calificacion.puntaje.tipo) * calificacion.puntaje.numero}
      usuario.setPuntajeActual(contador.intdiv(calificaciones.size()))
      //siempre va a tener al menos una calificacion
    }

}
