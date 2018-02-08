package nuevo_foro_fiuba

class Opcion {

	String nombre
	Set <Voto> votos

	static hasMany = [
		votos: Voto
	]

    static constraints = {
    	nombre blank:false, nullable: false
    	votos blank:false, nullable: false
    }

    Opcion(String nombre){
    	this.nombre = nombre
			this.votos = []
    }

}
