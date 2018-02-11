package nuevo_foro_fiuba
import java.util.UUID

class Archivo {
	String nombre
	String nombreFinal
	String path

    static constraints = {
		nombre blank: false, nullable: false
		nombreFinal blank: false, nullable: false
		path blank: false, nullable: false
    }

	Archivo(String nombre, String path) {
		this.nombre = nombre
		this.nombreFinal = UUID.randomUUID().toString()
		this.path = path
	}
}
