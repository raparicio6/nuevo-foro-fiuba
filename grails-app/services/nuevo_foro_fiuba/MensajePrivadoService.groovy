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

  def enviarMensaje(Usuario emisor, Usuario receptor, String texto, MensajePrivado mensajeAlCualResponde, String archivoAdjunto){
    MensajePrivado mensaje = new MensajePrivado(texto, null, null)
    InformacionMensajeUsuario infoEmisor = new InformacionMensajeUsuario (receptor, mensaje, InformacionMensajeUsuario.RolUsuarioMensaje.EMISOR)
    InformacionMensajeUsuario infoReceptor = new InformacionMensajeUsuario (emisor, mensaje, InformacionMensajeUsuario.RolUsuarioMensaje.RECEPTOR)
    emisor.enviarMensaje(receptor, infoEmisor, infoReceptor)
    mensaje.save(failOnError:true)
    infoEmisor.save(failOnError:true)
    infoReceptor.save(failOnError:true)
  }

}
