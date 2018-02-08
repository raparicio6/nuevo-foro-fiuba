package nuevo_foro_fiuba

class InformacionMensajeUsuario {

    enum RolUsuario{
      emisor,receptor
    }

    Usuario usuario
    MensajePrivado mensajePrivado
    RolUsuario rolUsuario

    static constraints = {
      usuario blank:false, nullable:false
      mensajePrivado blank:false, nullable:false
      rolUsuario blank:false, nullable:false
    }

    InformacionMensajeUsuario(Usuario usuario, MensajePrivado mensajePrivado, RolUsuario rolUsuario) {
        this.usuario = usuario
        this.mensajePrivado = mensajePrivado
        this.rolUsuario = rolUsuario
    }
}
