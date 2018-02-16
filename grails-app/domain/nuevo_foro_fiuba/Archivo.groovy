package nuevo_foro_fiuba

class Archivo {

	String nombre
	String path

  static constraints = {
		nombre blank: false, nullable: false
		path blank: false, nullable: false
  }

	Archivo(String nombre, String path) {
		this.nombre = nombre
		this.path = path
	}
}
