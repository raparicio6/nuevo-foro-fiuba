package nuevo_foro_fiuba

class MensajePrivado {

	enum EstadoMensajePrivado {
		VIGENTE,
		ELIMINADO,
	}

	String texto
	MensajePrivado mensajeAlCualSeResponde
	Archivo archivo
	EstadoMensajePrivado estado

  static constraints = {
  	texto blank: true, nullable:false
		mensajeAlCualSeResponde nullable:true
		archivo nullable:true
		estado nullable:false
	}

  MensajePrivado(String texto, MensajePrivado mensajeAlCualSeResponde, Archivo archivo){
  	this.texto = texto
		this.mensajeAlCualSeResponde = mensajeAlCualSeResponde
		this.archivo = archivo
		this.estado = EstadoMensajePrivado.VIGENTE
  }
}
