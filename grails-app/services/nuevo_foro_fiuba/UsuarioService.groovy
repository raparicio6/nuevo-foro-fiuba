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

    def actualizarPromedioCalificaciones(Usuario usuario){
      def publicaciones = usuario.getPublicaciones()
      def calificaciones = publicaciones.collect {publicacion -> publicacion.calificaciones}
      calificaciones = calificaciones.flatten()
      def contador = 0
      calificaciones.collect {calificacion -> contador += Puntaje.getSignoTipo(calificacion.puntaje.tipo) * calificacion.puntaje.numero}
      usuario.setPromedioCalificaciones(contador.intdiv(calificaciones.size()))
      //siempre va a tener al menos una calificacion
    }

    def modificarTextoPublicacion (Usuario usuario,Publicacion publicacion, String nuevoTexto){
      usuario.modificarTextoPublicacion(publicacion, nuevoTexto)
    }

    def modificarTextoComentario (Usuario usuario,Comentario comentario, String nuevoTexto){
      usuario.modificarTextoComentario(comentario, nuevoTexto)
    }

    def eliminarComentario(Usuario usuario, Comentario comentario){
      usuario.eliminarComentario(comentario)
    }

    def cerrarPublicacion (Usuario usuario, Publicacion publicacion){
      usuario.cerrarPublicacion(publicacion)
    }

    def abrirPublicacion (Usuario usuario, Publicacion publicacion){
      usuario.abrirPublicacion(publicacion)
    }

    def modificarMateriaPublicacion (Usuario usuario, Publicacion publicacion, Materia materia){
      usuario.modificarMateriaPublicacion(publicacion, materia)
    }

    def modificarCatedraPublicacion (Usuario usuario, Publicacion publicacion, Catedra catedra){
      usuario.modificarCatedraPublicacion(publicacion, catedra)
    }

    def eliminarPublicacion (Usuario usuario, Publicacion publicacion){
      usuario.eliminarPublicacion(publicacion)
    }

    def modificarPromedioRequeridoParaComentar(Usuario usuario, Publicacion publicacion, Integer promedio){
      usuario.modificarPromedioRequeridoParaComentar(publicacion, promedio)
    }

    def calificar(Usuario usuario, Publicacion publicacion, Calificacion calificacion){
      usuario.calificar(publicacion, calificacion)
    }

}
