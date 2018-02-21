package nuevo_foro_fiuba

class MensajePrivado {

	String texto
	MensajePrivado mensajeAlCualSeResponde
	Archivo archivo
	Date fechaHoraCreacion = Date.parse('dd/MM/yyyy hh:mm', new Date().format( 'dd/MM/yyyy hh:mm' ));


  static constraints = {
  	texto blank: true, nullable:false
		mensajeAlCualSeResponde nullable:true
		archivo nullable:true
		fechaHoraCreacion nullable:false
	}

// ------------------------------------------------------------------------- //
  MensajePrivado(String texto, MensajePrivado mensajeAlCualSeResponde, Archivo archivo){
  	this.texto = texto
		this.mensajeAlCualSeResponde = mensajeAlCualSeResponde
		this.archivo = archivo
  }

	// ------------------------------------------------------------------------- //
}
