package nuevo_foro_fiuba

class Calificacion {

	Usuario usuario
	Publicacion publicacion
	Comentario comentario
	Puntaje puntaje

		static embedded = ['puntaje']

    static constraints = {
    	usuario blank: false, nullable: false
			publicacion blank:false, nullable:true
			comentario blank:false, nullable:true
			puntaje blank:false, nullable:false
		}

	Calificacion(Usuario usuario, Puntaje puntaje, Publicacion publicacion, Comentario comentario) {
		this.usuario = usuario
		this.publicacion = publicacion
    this.comentario = comentario
		this.puntaje = puntaje
	}

}
