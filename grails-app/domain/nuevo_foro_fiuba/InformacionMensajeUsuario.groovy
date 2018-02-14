package nuevo_foro_fiuba

class InformacionMensajeUsuario {

  enum RolUsuarioMensaje{
    EMISOR,
    RECEPTOR,
  }

  Usuario usuarioConElQueSeInteractua
  MensajePrivado mensajePrivado
  RolUsuarioMensaje rolUsuario

  static constraints = {
    usuarioConElQueSeInteractua nullable:false
    mensajePrivado nullable:false
    rolUsuario nullable:false
  }

  InformacionMensajeUsuario(Usuario usuario, MensajePrivado mensajePrivado, RolUsuarioMensaje rolUsuario) {
    this.usuarioConElQueSeInteractua = usuario
    this.mensajePrivado = mensajePrivado
    this.rolUsuario = rolUsuario
  }

}
