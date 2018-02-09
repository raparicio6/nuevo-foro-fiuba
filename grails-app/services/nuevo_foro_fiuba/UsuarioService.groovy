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

    def enviarMensaje(Usuario emisor, Usuario receptor, InformacionMensajeUsuario infoEmisor, InformacionMensajeUsuario infoReceptor){
      emisor.enviarMensaje(receptor, infoEmisor, infoReceptor)
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

    def actualizarPromedioCalificaciones(Usuario usuario){
      def publicaciones = usuario.getPublicaciones()
      def calificaciones = publicaciones.collect {publicacion -> publicacion.calificaciones}
      def comentarios = usuario.getComentarios()
      calificaciones += comentarios.collect {comentario -> comentario.calificaciones}
      calificaciones = calificaciones.flatten()
      def contador = 0
      calificaciones.collect {calificacion -> contador += Puntaje.getSignoTipo(calificacion.puntaje.tipo) * calificacion.puntaje.numero}
      usuario.setPromedioCalificaciones(contador.intdiv(calificaciones.size()))
      //siempre va a tener al menos una calificacion
    }

    def modificarTexto (Usuario usuario,def objetoAModificar, String nuevoTexto){
      usuario.modificarTexto(objetoAModificar, nuevoTexto)
    }

    def eliminarComentario(Usuario usuario, Comentario comentario){
      usuario.eliminarComentario(comentario)
    }

    def cambiarEstado (Usuario usuario, Publicacion publicacion){
      usuario.cambiarEstado(publicacion)
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

    def calificar(Usuario usuario, def calificable, Calificacion calificacion){
      usuario.calificar(calificable, calificacion)
    }

}
