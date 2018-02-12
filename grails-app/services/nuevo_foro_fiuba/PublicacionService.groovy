package nuevo_foro_fiuba
import grails.gorm.transactions.Transactional

@Transactional
class PublicacionService {

    def serviceMethod() {}

    Publicacion crearPublicacion(String texto, Usuario usuarioCreador, Materia materiaRelacionada, Catedra catedraRelacionada){
      Publicacion publicacion = new Publicacion (texto, usuarioCreador, materiaRelacionada, catedraRelacionada)
      publicacion.save(failOnError: true)
      publicacion
    }

    Boolean usuarioEsDueñoDeLaPublicacion(Usuario usuario, Publicacion publicacion){
      usuario.esDueñoDeLaPublicacion(publicacion)
    }

    def cambiarEstado (Usuario usuario, Publicacion publicacion){
      usuario.cambiarEstado(publicacion)
    }

    def modificarTexto (Usuario usuario,def objetoAModificar, String nuevoTexto){
      objetoAModificar.modificarTexto(nuevoTexto)
    }

    def modificarMateriaPublicacion (Usuario usuario, Publicacion publicacion, Materia materia){
      usuario.modificarMateriaPublicacion(publicacion, materia)
    }

    def modificarCatedraPublicacion (Usuario usuario, Publicacion publicacion, Catedra catedra){
      usuario.modificarCatedraPublicacion(publicacion, catedra)
    }

    def modificarPromedioRequeridoParaComentar(Usuario usuario, Publicacion publicacion, Integer promedio){
      usuario.modificarPromedioRequeridoParaComentar(publicacion, promedio)
    }

    def eliminarPublicacion (Usuario usuario, Publicacion publicacionInstance){
      publicacionInstance.comentarios.collect {comentario -> comentario.comentarios.collect { subcomentario -> subcomentario.eliminar()}}
      publicacionInstance.comentarios.collect {comentario2 -> comentario2.eliminar()}
      publicacionInstance.calificaciones.collect {calificacion -> calificacion.eliminar()}
      publicacionInstance.eliminar()
    }

    def calificarPublicacion(Usuario usuario, Publicacion publicacion, Puntaje.TipoPuntaje tipo){
      def promedioCalificaciones = (usuario.getPromedioCalificaciones()).toInteger()
      Integer numeroPuntaje = promedioCalificaciones + 0**promedioCalificaciones
      Puntaje puntaje = new Puntaje (tipo, numeroPuntaje)
      puntaje.save(failOnError:true)
      Calificacion calificacion = new Calificacion(usuario, puntaje, publicacion, null)
      calificacion.save(failOnError:true)
      usuario.calificar(publicacion, calificacion)
      def usuarioCalificado = publicacion.getUsuarioCreador()
      def publicaciones = usuarioCalificado.getPublicaciones()
      def calificaciones = publicaciones.collect {publicacionInstance -> publicacionInstance.calificaciones}
      def comentarios = usuarioCalificado.getComentarios()
      calificaciones += comentarios.collect {comentarioInstance -> comentarioInstance.calificaciones}
      calificaciones = calificaciones.flatten()
      def contador = 0
      calificaciones.collect {calificacionInstance -> contador += Puntaje.TipoPuntaje.getProporcion(calificacionInstance.puntaje.tipo) * calificacionInstance.puntaje.numero}
      promedioCalificaciones = (contador/calificaciones.size()).toFloat()
      usuarioCalificado.setPromedioCalificaciones(promedioCalificaciones)
    }

    def comentarPublicacion (Usuario usuario, String textoComentario, Publicacion publicacionAComentar){
      Comentario comentario = new Comentario(textoComentario, usuario, publicacionAComentar, null)
      comentario.save()
      usuario.comentarPublicacion(comentario, publicacionAComentar)
    }

}
