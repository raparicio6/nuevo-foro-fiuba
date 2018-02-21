package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional
class MensajePrivadoService {

  def serviceMethod() {}

  final String PATH = "C:/Users/Mariano/nuevo_foro_fiuba/grails-app/files/"

  MensajePrivado crearMensaje (String texto, MensajePrivado mensajeAlCualSeResponde, Archivo archivo){
    MensajePrivado mensaje = new MensajePrivado(texto, mensajeAlCualSeResponde, archivo)
    mensaje.save(failOnError:true)
    mensaje
  }

  def obtenerInformacionMensajesRecibidos (Usuario usuario){
    usuario.getInformacionMensajes().findAll {infoMensaje -> infoMensaje.getRolUsuario() == InformacionMensajeUsuario.RolUsuarioMensaje.RECEPTOR && infoMensaje.getEstado() == InformacionMensajeUsuario.EstadoInformacionMensajeUsuario.VIGENTE }
  }

  MensajePrivado enviarMensaje(long idUsuarioCreador, long idUsuarioReceptor, String texto, long idMensajeAlCualResponde = 0, MultipartFile file = null){
    def emisor = Usuario.get(idUsuarioCreador)
    def receptor = Usuario.get(idUsuarioReceptor)
    def mensajeAlCualResponde = null
    if (idMensajeAlCualResponde)
      mensajeAlCualResponde = getMensajePrivadoById(idMensajeAlCualResponde)
    def archivo = null
    if (file && !file.empty){
       archivo = new Archivo(file.originalFilename, this.PATH + file.originalFilename)
       file.transferTo(new File(archivo.path))
       archivo.save(failOnError:true)
    }
    MensajePrivado mensaje = new MensajePrivado(texto, mensajeAlCualResponde, archivo)
    InformacionMensajeUsuario infoEmisor = new InformacionMensajeUsuario (emisor,receptor, mensaje, InformacionMensajeUsuario.RolUsuarioMensaje.EMISOR)
    InformacionMensajeUsuario infoReceptor = new InformacionMensajeUsuario (receptor,emisor, mensaje, InformacionMensajeUsuario.RolUsuarioMensaje.RECEPTOR)
    emisor.enviarMensaje(receptor, infoEmisor, infoReceptor)
    infoEmisor.save(failOnError:true)
    infoReceptor.save(failOnError:true)
    mensaje.save(failOnError:true)
    mensaje
  }

  def obtenerInformacionMensajesEnviados(Usuario usuario){
    usuario.getInformacionMensajes().findAll {infoMensaje -> infoMensaje.getRolUsuario() == InformacionMensajeUsuario.RolUsuarioMensaje.EMISOR && infoMensaje.getEstado() == InformacionMensajeUsuario.EstadoInformacionMensajeUsuario.VIGENTE}
  }


  def eliminarMensaje (long idUsuario, long idInformacionMensajeUsuario){
    def usuario = Usuario.get(idUsuario)
    def informacionMensajeUsuario = InformacionMensajeUsuario.get(idInformacionMensajeUsuario)
    usuario.eliminarInformacionMensajePrivado (informacionMensajeUsuario)
  }

  def getMensajePrivadoById(long idMensajePrivado){
    MensajePrivado.get(idMensajePrivado)
  }

}
