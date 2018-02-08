package nuevo_foro_fiuba

class Calificacion {

	Usuario usuario
	Puntaje puntaje
	Publicacion publicacion
	Comentario comentario

    static constraints = {
    	usuario blank: false, nullable: false
			puntaje blank:false, nullable:false
			publicacion blank:false, nullable:true
			comentario blank:false, nullable:true
		}

	Calificacion(Usuario usuario, Puntaje puntaje, Publicacion publicacion, Comentario comentario) {
		this.usuario = usuario
		this.puntaje = puntaje
    this.publicacion = publicacion
    this.comentario = comentario
	}

}
