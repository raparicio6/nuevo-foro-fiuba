package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional
import nuevo_foro_fiuba.InformacionMensajeUsuario.RolUsuario

@Transactional
class MensajePrivadoController {

    def mensajePrivadoService
    def usuarioService

    def index(long id) {
      def usuarioInstance = Usuario.get(id)
      render (view: "crearMensaje", model:[usuarioInstance:usuarioInstance, usuarios:Usuario.list()])
    }

    def enviarMensaje (long idUsuarioCreador, long  idUsuarioReceptor, String texto, MensajePrivado mensajeAlCualResponde, Archivo archivoAdjunto){
      def usuarioCreadorInstance = Usuario.get(idUsuarioCreador)
      def usuarioReceptorInstance = Usuario.get(idUsuarioReceptor)
      MensajePrivado mensajePrivado = mensajePrivadoService.crearMensaje(texto, mensajeAlCualResponde, archivoAdjunto)
      InformacionMensajeUsuario informacionEmisor = mensajePrivadoService.crearInformacion(usuarioCreadorInstance, mensaje, RolUsuario.emisor)
      InformacionMensajeUsuario informacionReceptor = mensajePrivadoService.crearInformacion(usuarioReceptorInstance, mensaje, RolUsuario.receptor)
      usuarioService.enviarMensaje(usuarioCreadorInstance, usuarioReceptorInstance, informacionEmisor, informacionReceptor)
    }

    def verMensaje (long idUsuario, long idMensaje, long idInformacion) {
        def mensajeInstance = MensajePrivado.get(idMensaje)
        def usuarioInstance = Usuario.get(idUsuario)
        def infoInstance = InformacionMensajeUsuario.get(idInformacion)
        // if ()
    }

    def listaMensajes (long idUsuario) {

    }
}
