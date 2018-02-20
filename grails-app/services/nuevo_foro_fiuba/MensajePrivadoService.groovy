package nuevo_foro_fiuba

import java.io.File
import grails.gorm.transactions.Transactional

@Transactional
class MensajePrivadoService {

  def serviceMethod() {}

  final String PATH = "/files/"

  MensajePrivado crearMensaje (String texto, MensajePrivado mensajeAlCualSeResponde, Archivo archivo){
    MensajePrivado mensaje = new MensajePrivado(texto, mensajeAlCualSeResponde, archivo)
    mensaje.save(failOnError:true)
    mensaje
  }

  def obtenerMensajesRecibidos (Usuario usuario){
    usuario.getMensajes().findAll {infoMensaje -> infoMensaje.getRolUsuario() == InformacionMensajeUsuario.RolUsuarioMensaje.RECEPTOR && infoMensaje.getEstado() == InformacionMensajeUsuario.EstadoInformacionMensajeUsuario.VIGENTE }
  }

  MensajePrivado enviarMensaje(long idUsuarioCreador, long idUsuarioReceptor, String texto, long idMensajeAlCualResponde = 0, String archivoAdjunto = null){
    def emisor = getUsuarioById(idUsuarioCreador)
    def receptor = getUsuarioById(idUsuarioReceptor)
    def mensajeAlCualResponde = null
    if (idMensajeAlCualResponde)
      mensajeAlCualResponde = getMensajePrivadoById(idMensajeAlCualResponde)
    def archivo = null
    if (archivoAdjunto)
       archivo = new Archivo(archivoAdjunto, this.PATH)
    MensajePrivado mensaje = new MensajePrivado(texto, mensajeAlCualResponde, archivo)
    InformacionMensajeUsuario infoEmisor = new InformacionMensajeUsuario (emisor,receptor, mensaje, InformacionMensajeUsuario.RolUsuarioMensaje.EMISOR)
    InformacionMensajeUsuario infoReceptor = new InformacionMensajeUsuario (receptor,emisor, mensaje, InformacionMensajeUsuario.RolUsuarioMensaje.RECEPTOR)
    emisor.enviarMensaje(receptor, infoEmisor, infoReceptor)
    if (archivo)
      archivo.save(failOnError:true)
    infoEmisor.save(failOnError:true)
    infoReceptor.save(failOnError:true)
    mensaje.save(failOnError:true)
    mensaje
  }

  def obtenerMensajesEnviados(Usuario usuario){
    usuario.getMensajes().findAll {infoMensaje -> infoMensaje.getRolUsuario() == InformacionMensajeUsuario.RolUsuarioMensaje.EMISOR && infoMensaje.getEstado() == InformacionMensajeUsuario.EstadoInformacionMensajeUsuario.VIGENTE}
  }


  def eliminarMensaje (long idUsuario, long idInformacionMensajeUsuario){
    def usuario = getUsuarioById(idUsuario)
    def informacionMensajeUsuario = getInformacionMensajeUsuarioById(idInformacionMensajeUsuario)
    usuario.eliminarMensajePrivado (informacionMensajeUsuario)
  }


  def getUsuarioById(long idUsuario){
    Usuario.get(idUsuario)
  }

  def getAllUsuarios(){
    Usuario.list()
  }

  def getMensajePrivadoById(long idMensajePrivado){
    MensajePrivado.get(idMensajePrivado)
  }

  def getInformacionMensajeUsuarioById(long idInformacionMensajeUsuario){
    InformacionMensajeUsuario.get(idInformacionMensajeUsuario)
  }

}
