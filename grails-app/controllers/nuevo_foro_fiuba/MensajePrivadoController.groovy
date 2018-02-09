package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class MensajePrivadoController {

    def mensajePrivadoService
    def usuarioService

    def index() {
        render view: "enviarMensaje"
    }

    def enviarMensaje (long idUsuarioCreador, long  idUsuarioReceptor) {
        def usuarioCreadorInstance = Usuario.get(idUsuarioCreador)
        def usuarioReceptorInstance = Usuario.get(idUsuarioReceptor)
        MensajePrivado mensaje = mensajePrivadoService.crearMensaje(texto, usuarioCreador, usuarioReceptor, mensajeAlCualResponde, archivo)
        mensajePrivadoService.crearInformacion(usuarioCreador, usuarioReceptor, mensaje)
        usuarioService.enviarMensaje(mensaje,usuarioReceptor)
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
