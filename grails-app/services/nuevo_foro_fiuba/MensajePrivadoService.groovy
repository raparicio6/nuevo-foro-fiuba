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
    println (emisor)
    println (receptor)
    MensajePrivado mensaje = new MensajePrivado(texto, null, null)
    InformacionMensajeUsuario infoEmisor = new InformacionMensajeUsuario (receptor, mensaje, InformacionMensajeUsuario.RolUsuarioMensaje.EMISOR)
    InformacionMensajeUsuario infoReceptor = new InformacionMensajeUsuario (emisor, mensaje, InformacionMensajeUsuario.RolUsuarioMensaje.RECEPTOR)
    println (emisor.mensajes)
    println (receptor.mensajes)
    // orden incorrecto
    emisor.enviarMensaje(receptor, infoEmisor, infoReceptor)
    println (emisor.mensajes)
    println (receptor.mensajes)
    mensaje.save(failOnError:true)
    println (emisor.mensajes)
    println (receptor.mensajes)
    infoEmisor.save(failOnError:true)
    println (emisor.mensajes)
    println (receptor.mensajes)
    infoReceptor.save(failOnError:true)
    println (emisor.mensajes)
    println (receptor.mensajes)
  }

}
