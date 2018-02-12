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

    def enviarMensaje(Usuario emisor, Usuario receptor, String texto, MensajePrivado mensajeAlCualResponde, Archivo archivoAdjunto){
      MensajePrivado mensaje = new MensajePrivado(texto, mensajeAlCualResponde, archivoAdjunto)
      mensaje.save(failOnError:true)
      InformacionMensajeUsuario infoEmisor = new InformacionMensajeUsuario (emisor, mensaje, InformacionMensajeUsuario.RolUsuarioMensaje.EMISOR)
      infoEmisor.save(failOnError:true)
      InformacionMensajeUsuario infoReceptor = new InformacionMensajeUsuario (receptor, mensaje, InformacionMensajeUsuario.RolUsuarioMensaje.RECEPTOR)
      infoReceptor.save(failOnError:true)
      emisor.enviarMensaje(receptor, infoEmisor, infoReceptor)
    }

}
