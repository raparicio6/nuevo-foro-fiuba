package nuevo_foro_fiuba

class Profesor {

	String nombre
	Set<Catedra> catedras

	static hasMany = [
		catedras: Catedra
	]

    static constraints = {
    	nombre blank:false, nullable:false
    	catedras blank:false, nullable:false
    }

    Profesor(String nombre){
    	this.nombre = nombre
    	this.catedras = []
    }


}
