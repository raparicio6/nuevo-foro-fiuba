package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class MensajePrivadoController {

  def mensajePrivadoService

  def index(long id) {
    def usuarioInstance = Usuario.get(id)
    render (view: "crearMensaje", model:[usuarioInstance:usuarioInstance, usuarios:Usuario.list()])
  }

  def enviarMensaje (long idUsuarioCreador, long  idUsuarioReceptor, String texto, MensajePrivado mensajeAlCualResponde, Archivo archivoAdjunto){
    def usuarioCreadorInstance = Usuario.get(idUsuarioCreador)
    def usuarioReceptorInstance = Usuario.get(idUsuarioReceptor)
    mensajePrivadoService.enviarMensaje(usuarioCreadorInstance, usuarioReceptorInstance, texto, mensajeAlCualResponde, archivoAdjunto)
  }

}
