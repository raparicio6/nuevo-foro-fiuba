package nuevo_foro_fiuba

import java.io.File

class MensajePrivadoController {

  def mensajePrivadoService

  def index(long idUsuario) {
    def usuarioInstance = mensajePrivadoService.getUsuarioById(idUsuario)
    def mensajes = mensajePrivadoService.obtenerMensajesRecibidos(usuarioInstance)
    render (view: "casilla", model:[usuarioInstance:usuarioInstance, mensajes:mensajes])
  }

  def verMensaje(long idMensajePrivado, long idUsuario, long idInformacion, Boolean mostrarResponder){
    def mensajePrivadoInstance = mensajePrivadoService.getMensajePrivadoById(idMensajePrivado)
    def usuarioInstance = mensajePrivadoService.getUsuarioById(idUsuario)
    def informacionMensajeUsuario = mensajePrivadoService.getInformacionMensajeUsuarioById(idInformacion)
    [mensaje:mensajePrivadoInstance, usuario:usuarioInstance, informacion:informacionMensajeUsuario, mostrarResponder:mostrarResponder]
  }

  def enviarMensaje (long idUsuarioCreador, long  idUsuarioReceptor, String texto, long idMensajeAlCualResponde, String archivoAdjunto){
    mensajePrivadoService.enviarMensaje(idUsuarioCreador, idUsuarioReceptor, texto, idMensajeAlCualResponde, archivoAdjunto)
    redirect (action:"index", params:[idUsuario:idUsuarioCreador])
  }

  def crearMensaje(long idUsuario){
    def usuarioInstance = mensajePrivadoService.getUsuarioById(idUsuario)
    [usuarioInstance:usuarioInstance, usuarios:Usuario.list()]
  }

  def verMensajesEnviados(long idUsuario){
    Usuario usuarioInstance = mensajePrivadoService.getUsuarioById(idUsuario)
    def mensajes = mensajePrivadoService.obtenerMensajesEnviados(usuarioInstance)
    render (view:"enviados", model:[usuarioInstance:usuarioInstance, mensajes:mensajes])
  }

}
