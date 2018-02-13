package nuevo_foro_fiuba

class InformacionMensajeUsuario {

  enum RolUsuarioMensaje{
    EMISOR,
    RECEPTOR,
  }

  Usuario usuario
  MensajePrivado mensajePrivado
  RolUsuarioMensaje rolUsuario

  static constraints = {
    usuario nullable:false
    mensajePrivado nullable:false
    rolUsuario nullable:false
  }

  InformacionMensajeUsuario(Usuario usuario, MensajePrivado mensajePrivado, RolUsuarioMensaje rolUsuario) {
    this.usuario = usuario
    this.mensajePrivado = mensajePrivado
    this.rolUsuario = rolUsuario
  }
  
}
