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
      if (usuarioService.usuarioEsDueñoDelComentario(usuarioInstance, comentarioInstance))
        [comentario: comentarioInstance, usuario:usuarioInstance, modificar:"1"]
      else
        [comentario: comentarioInstance, usuario:usuarioInstance]
    }

    def verSubComentario (long id, long idUsuario){
      def usuarioInstance = Usuario.get(idUsuario)
      def comentarioInstance = Comentario.get(id)
      if (usuarioService.usuarioEsDueñoDelComentario(usuarioInstance, comentarioInstance))
        render (view:"verComentario", model:[comentario: comentarioInstance, usuario: usuarioInstance, subcomentario:"1", modificar:"1"])
      else
        render (view:"verComentario", model:[comentario: comentarioInstance, usuario: usuarioInstance, subcomentario:"1"])
    }

    def modificarTextoComentario(long id, long idUsuario,String nuevoTexto){
      def usuarioLogin = Usuario.get(idUsuario)
      def comentarioInstance = Comentario.get(id)
      usuarioService.modificarTextoComentario(usuarioLogin, comentarioInstance, nuevoTexto)
      // comentarioService.modificarTexto(comentarioInstance, nuevoTexto)
      if (comentarioInstance.publicacionComentada){
         redirect(controller:"comentario", action: "verComentario", id: comentarioInstance.id,  params: [idUsuario:idUsuario])
      }
      else{
         redirect(controller:"comentario", action: "verSubComentario", id: comentarioInstance.id,  params: [idUsuario:idUsuario])
      }
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
