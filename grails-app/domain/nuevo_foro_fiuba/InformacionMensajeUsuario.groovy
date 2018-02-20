package nuevo_foro_fiuba

class InformacionMensajeUsuario {

  enum EstadoInformacionMensajeUsuario{
    VIGENTE,
    ELIMINADA,
  }

  enum RolUsuarioMensaje{
    EMISOR,
    RECEPTOR,
  }

  Usuario usuarioQueInteractua
  Usuario usuarioConElQueSeInteractua
  MensajePrivado mensajePrivado
  RolUsuarioMensaje rolUsuario
  EstadoInformacionMensajeUsuario estado

  static constraints = {
    usuarioQueInteractua nullable:false
    usuarioConElQueSeInteractua nullable:false
    mensajePrivado nullable:false
    rolUsuario nullable:false
    estado nullable:false
  }

  InformacionMensajeUsuario(Usuario usuarioQueInteractua, Usuario usuarioConElQueSeInteractua, MensajePrivado mensajePrivado, RolUsuarioMensaje rolUsuario) {
    this.usuarioQueInteractua = usuarioQueInteractua
    this.usuarioConElQueSeInteractua = usuarioConElQueSeInteractua
    this.mensajePrivado = mensajePrivado
    this.rolUsuario = rolUsuario
    this.estado = EstadoInformacionMensajeUsuario.VIGENTE
  }

  def eliminar(){
    this.setEstado(EstadoInformacionMensajeUsuario.ELIMINADA)
  }

}
