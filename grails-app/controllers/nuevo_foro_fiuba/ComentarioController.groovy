package nuevo_foro_fiuba

class ComentarioController {

  def comentarioService
  def usuarioService

  def index() {}

  def verComentario (long idComentario, long idUsuario){
    def comentarioInstance = comentarioService.getComentarioById(idComentario)
    def usuarioInstance = usuarioService.getUsuarioById(idUsuario)
    def esDueño = usuarioService.usuarioEsDueñoDelComentario(usuarioInstance, comentarioInstance)
    def esSubComentario = comentarioService.esSubComentario(comentarioInstance)
    def comentarios = comentarioService.obtenerComentariosNoEliminados(comentarioInstance)
    [comentario: comentarioInstance, usuario:usuarioInstance, modificar:esDueño, subComentario:esSubComentario, comentarios:comentarios]
  }

  def modificarTextoComentario(long idComentario, long idUsuario, String nuevoTexto){
    comentarioService.modificarTextoComentario(idUsuario, idComentario, nuevoTexto)
    redirect(controller:"comentario", action: "verComentario",  params: [idUsuario:idUsuario, idComentario:idComentario])
  }

  def eliminarComentario(long idComentario, long idUsuario){
    Comentario comentarioInstance = comentarioService.getComentarioById(idComentario)
    comentarioService.eliminarComentario(comentarioInstance)
    (comentarioInstance.publicacionComentada) ? redirect(controller: "publicacion", action: "verPublicacion", params: [idUsuario:idUsuario, idPublicacion: comentarioInstance.publicacionComentada.id]) : redirect(controller:"comentario", action: "verComentario",  params: [idUsuario:idUsuario, idComentario: comentarioInstance.comentarioComentado.id])
  }

  def calificarPositivo(long idComentario, long idUsuario){
    calificarComentario(Puntaje.TipoPuntaje.ME_GUSTA, idComentario, idUsuario)
  }

  def calificarNegativo(long idComentario, long idUsuario){
    calificarComentario(Puntaje.TipoPuntaje.NO_ME_GUSTA, idComentario, idUsuario)
  }

  def calificarComentario(Puntaje.TipoPuntaje tipo, long idComentario, long idUsuario){
    try{
      comentarioService.calificarComentario(idUsuario, idComentario, tipo)
    }
    catch (UsuarioYaCalificoException e){
      flash.message = e.MENSAJE
    }
    redirect (action: "verComentario", params: [idUsuario:idUsuario, idComentario: idComentario])
  }

  def comentar(long idComentario, long idUsuario, String textoComentario){
    try{
      comentarioService.comentarComentario(idUsuario, textoComentario, idComentario)
    }
    catch (PublicacionCerradaException e){
      flash.message = e.MENSAJE
    }
    catch (UsuarioNoPoseeMateriasNecesariasException e){
      flash.message = e.MENSAJE
    }
    redirect(controller:"comentario", action: "verComentario", params: [idUsuario:idUsuario, idComentario: idComentario])
  }

}
