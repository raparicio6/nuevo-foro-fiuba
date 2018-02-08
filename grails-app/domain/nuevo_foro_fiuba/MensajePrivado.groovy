package nuevo_foro_fiuba

class MensajePrivado {

	String texto
	Usuario usuarioCreador
	Usuario usuarioReceptor
	MensajePrivado mensajeAlCualSeResponde
	Archivo archivo

    static constraints = {
    	texto blank: false, nullable:false
    	usuarioCreador blank: false, nullable:false
    	usuarioReceptor blank: false, nullable:false
    	mensajeAlCualSeResponde blank: false, nullable:true
    	archivo blank: false, nullable:true
    }

    MensajePrivado (String texto, Usuario usuarioCreador, Usuario usuarioReceptor, MensajePrivado mensajeAlCualSeResponde, Archivo archivo){
    	this.texto = texto
    	this.usuarioCreador = usuarioCreador
    	this.usuarioReceptor = usuarioReceptor
    	this.mensajeAlcualSeResponde = mensajeAlCualSeResponde
    	this.archivo = archivo
    }
}
