package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class ComentarioController {

    def comentarioService
    def usuarioService

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

}
