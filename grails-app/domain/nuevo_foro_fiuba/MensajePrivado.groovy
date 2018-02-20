package nuevo_foro_fiuba

class MensajePrivado {

	String texto
	MensajePrivado mensajeAlCualSeResponde
	Archivo archivo

  static constraints = {
  	texto blank: true, nullable:false
		mensajeAlCualSeResponde nullable:true
		archivo nullable:true
	}

  MensajePrivado(String texto, MensajePrivado mensajeAlCualSeResponde, Archivo archivo){
  	this.texto = texto
		this.mensajeAlCualSeResponde = mensajeAlCualSeResponde
		this.archivo = archivo
  }
}
