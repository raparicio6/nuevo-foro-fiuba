package nuevo_foro_fiuba

class InformacionMensajeUsuario {

    MensajePrivado mensajePrivado
    Usuario emisor
    Usuario receptor

    static constraints = {
    }

    InformacionMensajeUsuario (Usuario emisor, Usuario receptor, MensajePrivado mensajePrivado) {
        this.emisor = emisor
        this.receptor = receptor
        this.mensajePrivado = mensajePrivado
    }
}
