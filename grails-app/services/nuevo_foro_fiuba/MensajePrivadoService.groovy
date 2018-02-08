package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class MensajePrivadoService {

    def serviceMethod() {}

    MensajePrivado crearMensaje (String texto, Usuario usuarioCreador, String usuarioReceptor, MensajePrivado mensajeAlCualSeResponde, Archivo archivo){
        MensajePrivado mensaje = new MensajePrivado(texto, usuarioCreador, receptor, mensajeAlCualSeResponde, archivo)
        mensaje.save()
        return mensaje
    }

    def crearInformacion(Usuario emisor, Usuario receptor, MensajePrivado mensajePrivado) {
        InformacionMensajeUsuario informacion = new InformacionMensajeUsuario(emisor, receptor, mensajePrivado)
        informacion.save()
    }
}
