package nuevo_foro_fiuba

class MensajePrivado {

	enum EstadoMensajePrivado {
		vigente, eliminado
	}

	String texto
	MensajePrivado mensajeAlCualSeResponde
	Archivo archivo
	EstadoMensajePrivado estado

    static constraints = {
    	texto blank: false, nullable:false
			mensajeAlCualSeResponde blank: false, nullable:true
			archivo blank: false, nullable:true
			estado blank:false, nullable:false
		}

    MensajePrivado(String texto, MensajePrivado mensajeAlCualSeResponde, Archivo archivo){
    	this.texto = texto
			this.mensajeAlCualSeResponde = mensajeAlCualSeResponde
			this.archivo = archivo
			this.estado = EstadoMensajePrivado.vigente
    }
}
