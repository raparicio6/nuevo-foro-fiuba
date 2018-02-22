package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class InformacionMensajeUsuarioService {

  def serviceMethod() {}

  def obtenerInformacionMensajesEnviadosUsuario(Usuario usuario){
    usuario.getInformacionMensajes().findAll {infoMensaje -> infoMensaje.getRolUsuario() == InformacionMensajeUsuario.RolUsuarioMensaje.EMISOR && infoMensaje.getEstado() == InformacionMensajeUsuario.EstadoInformacionMensajeUsuario.VIGENTE}
  }

  def obtenerInformacionMensajesRecibidosUsuario(Usuario usuario){
    usuario.getInformacionMensajes().findAll {infoMensaje -> infoMensaje.getRolUsuario() == InformacionMensajeUsuario.RolUsuarioMensaje.RECEPTOR && infoMensaje.getEstado() == InformacionMensajeUsuario.EstadoInformacionMensajeUsuario.VIGENTE }
  }

  def eliminarMensaje(long idInformacionMensajeUsuario){
    def informacionMensajeUsuario = this.getInformacionMensajeUsuarioById(idInformacionMensajeUsuario)
    informacionMensajeUsuario.eliminar()
  }

  def getInformacionMensajeUsuarioById(long idInformacionMensajeUsuario){
    InformacionMensajeUsuario.get(idInformacionMensajeUsuario)
  }

}
