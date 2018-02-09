package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class MensajePrivadoService {

    def serviceMethod() {}

    MensajePrivado crearMensaje (String texto, MensajePrivado mensajeAlCualSeResponde, Archivo archivo){
        MensajePrivado mensaje = new MensajePrivado(texto, mensajeAlCualSeResponde, archivo)
        mensaje.save(failOnError:true)
        mensaje
    }

    def crearInformacion(Usuario emisor, Usuario receptor, MensajePrivado mensajePrivado) {
        InformacionMensajeUsuario informacion = new InformacionMensajeUsuario(emisor, receptor, mensajePrivado)
        informacion.save(failOnError:true)
    }
}
