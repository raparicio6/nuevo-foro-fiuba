package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class ComentarioController {

    def comentarioService
    def usuarioService
    def calificacionService
    def puntajeService

    def index() {}

    def verComentario (long id, long idUsuario){
      def comentarioInstance = Comentario.get(id)
      def usuarioInstance = Usuario.get(idUsuario)
      def esDueño = usuarioService.usuarioEsDueñoDelComentario(usuarioInstance, comentarioInstance)
      def esSubComentario = comentarioService.esSubComentario(comentarioInstance)
      [comentario: comentarioInstance, usuario:usuarioInstance, modificar:esDueño, subComentario:esSubComentario]
    }

    def modificarTextoComentario(long id, long idUsuario,String nuevoTexto){
      def usuarioLogin = Usuario.get(idUsuario)
      def comentarioInstance = Comentario.get(id)
      usuarioService.modificarTexto(usuarioLogin, comentarioInstance, nuevoTexto)
      redirect(controller:"comentario", action: "verComentario", id: comentarioInstance.id,  params: [idUsuario:idUsuario])
    }

//VER
    def eliminarComentario(long id, long idUsuario){
      def usuarioLogin = Usuario.get(idUsuario)
      def comentarioInstance = Comentario.get(id)
      usuarioService.eliminarComentario(usuarioLogin, comentarioInstance)
      comentarioService.eliminarComentario(comentarioInstance)
      if (comentarioInstance.publicacionComentada){
        redirect(controller: "publicacion", action: "verPublicacion", id: comentarioInstance.publicacionComentada.id, params: [idUsuario:idUsuario])
      }
      else{
        redirect(controller:"comentario", action: "verComentario", id: comentarioInstance.comentarioComentado.id,  params: [idUsuario:idUsuario])
      }
    }

    def calificarPositivo(long id, long idUsuario){
      calificarComentario(Puntaje.TipoPuntaje.ME_GUSTA, id, idUsuario)
    }

    def calificarNegativo(long id, long idUsuario){
      calificarComentario(Puntaje.TipoPuntaje.NO_ME_GUSTA, id, idUsuario)
    }

    def calificarComentario(Puntaje.TipoPuntaje tipo, long id, long idUsuario){
      def comentarioInstance = Comentario.get(id)
      def usuarioInstance = Usuario.get(idUsuario)
      def calificacion = calificacionService.crearCalificacion(usuarioInstance, puntajeService.crearPuntaje(tipo, usuarioInstance), null,comentarioInstance)
      try{
        usuarioService.calificar(usuarioInstance, comentarioInstance, calificacion)
        usuarioService.actualizarPromedioCalificaciones(comentarioInstance.usuarioCreador)
      }
      catch (UsuarioYaCalificoException){
        calificacionService.eliminarCalificacion(calificacion)
        flash.message = UsuarioYaCalificoException.MENSAJE
      }
      redirect (action: "verComentario", id: comentarioInstance.id, params: [idUsuario:idUsuario])
    }

}
