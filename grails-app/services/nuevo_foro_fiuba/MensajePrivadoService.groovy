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
    usuario.getMensajes().findAll {mensajePrivado -> mensajePrivado.getRolUsuario() == InformacionMensajeUsuario.RolUsuarioMensaje.RECEPTOR}
  }

  def enviarMensaje(long idUsuarioCreador, long idUsuarioReceptor, String texto, long idMensajeAlCualResponde, String archivoAdjunto){
    def emisor = getUsuarioById(idUsuarioCreador)
    def receptor = getUsuarioById(idUsuarioReceptor)
    def mensajeAlCualResponde = MensajePrivado.get(idMensajeAlCualResponde)
    def archivo = null // capaz está de mas el = null ya que lo es por defecto
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
  }

  def obtenerMensajesEnviados(Usuario usuario){
    usuario.getMensajes().findAll {mensajePrivado -> mensajePrivado.getRolUsuario() == InformacionMensajeUsuario.RolUsuarioMensaje.EMISOR}
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
