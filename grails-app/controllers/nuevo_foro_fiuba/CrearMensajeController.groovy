package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class CrearMensajeController {

    def mensajePrivadoService
    def usuarioService

    def index() {
        render view: "crearMensaje"
    }

    def crearMensaje (String texto, long idUsuarioCreador, long  idUsuarioReceptor, MensajePrivado mensajeAlCualResponde,Archivo archivo) {
        def usuarioCreador = Usuario.get(idUsuarioCreador)
        def usuarioReceptor = Usuario.get(idUsuarioReceptor)
        MensajePrivado mensaje = mensajePrivadoService.crearMensaje(texto, usuarioCreador, usuarioReceptor, mensajeAlCualResponde, archivo)
        mensajePrivadoService.crearInformacion(usuarioCreador, usuarioReceptor, mensaje)
        usuarioService.enviarMensaje(mensaje,usuarioReceptor)
    }
}
