package nuevo_foro_fiuba
import java.util.UUID

class Archivo {

	String nombre
	String path

  static constraints = {
		nombre blank: false, nullable: false
		path blank: false, nullable: false
  }

// ------------------------------------------------------------------------- //
	Archivo(String nombre, String path) {
		this.nombre = nombre
		this.path = path + UUID.randomUUID().toString()
		// para no pisarse entre archivos ya que es muy probable repetir nombres
		// como por ejemplo "Apunte Análisis"
	}
}

// ------------------------------------------------------------------------- //
