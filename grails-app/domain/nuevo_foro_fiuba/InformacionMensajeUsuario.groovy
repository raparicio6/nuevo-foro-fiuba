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
      usuario blank:false, nullable:false
      mensajePrivado blank:false, nullable:false
      rolUsuario blank:false, nullable:false
    }

    InformacionMensajeUsuario(Usuario usuario, MensajePrivado mensajePrivado, RolUsuarioMensaje rolUsuario) {
        this.usuario = usuario
        this.mensajePrivado = mensajePrivado
        this.rolUsuario = rolUsuario
    }
}
