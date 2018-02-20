package nuevo_foro_fiuba

import java.io.File

class MensajePrivadoController {

  def mensajePrivadoService

  def index(long idUsuario) {
    def usuarioInstance = mensajePrivadoService.getUsuarioById(idUsuario)
    def mensajesRecibidos = mensajePrivadoService.obtenerMensajesRecibidos(usuarioInstance)
    render (view:"listaMensajes", model:[usuarioInstance:usuarioInstance, mensajes:mensajesRecibidos, title:"Mensajes recibidos", enviadosRecibidos:"Recibidos", dePara:"De", mostrarResponder:true, action:"verMensajesEnviados", value:"Ver mensajes enviados"])
  }

  def verMensajesEnviados(long idUsuario){
    Usuario usuarioInstance = mensajePrivadoService.getUsuarioById(idUsuario)
    def mensajesEnviados = mensajePrivadoService.obtenerMensajesEnviados(usuarioInstance)
    render (view:"listaMensajes", model:[usuarioInstance:usuarioInstance, mensajes:mensajesEnviados, title:"Mensajes enviados", enviadosRecibidos:"Enviados", dePara:"Para", mostrarResponder:false, action:"index", value:"Volver a recibidos"])
  }

  def crearMensaje(long idUsuario){
    def usuarioInstance = mensajePrivadoService.getUsuarioById(idUsuario)
    render (view:"mensajePrivado", model:[usuario:usuarioInstance, usuarios:mensajePrivadoService.getAllUsuarios(), accion:"crear", title:"Crear mensaje"])
  }

  def verMensaje(long idMensajePrivado, long idUsuario, long idInformacion, Boolean mostrarResponder){
    def mensajePrivadoInstance = mensajePrivadoService.getMensajePrivadoById(idMensajePrivado)
    def usuarioInstance = mensajePrivadoService.getUsuarioById(idUsuario)
    def informacionMensajeUsuario = mensajePrivadoService.getInformacionMensajeUsuarioById(idInformacion)
    render (view:"mensajePrivado", model:[mensaje:mensajePrivadoInstance, usuario:usuarioInstance, informacion:informacionMensajeUsuario, accion:"ver", mostrarResponder:mostrarResponder])
  }

  def enviarMensaje (long idUsuarioCreador, long idUsuarioReceptor, String texto, long idMensajeAlCualResponde, String archivoAdjunto){
    mensajePrivadoService.enviarMensaje(idUsuarioCreador, idUsuarioReceptor, texto, idMensajeAlCualResponde, archivoAdjunto)    
    redirect (action:"verMensajesEnviados", params:[idUsuario:idUsuarioCreador])
  }

}
