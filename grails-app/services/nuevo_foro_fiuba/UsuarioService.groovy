package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class UsuarioService {

  def serviceMethod() {}

  Usuario crearUsuario(String nombre, String apellido, String nombreUsuario){
    Usuario usuario = new Usuario(nombre, apellido, nombreUsuario)
    usuario.save(failOnError: true)
    Publicacion publicacion = new Publicacion("A", usuario, null, null)
    //orden incorrecto
    usuario.agregarPublicacion(publicacion)
    publicacion.save(failOnError: true)
    publicacion.eliminar()
    def promedioCalificaciones = 3.toInteger()
    Integer numeroPuntaje = promedioCalificaciones + 0**promedioCalificaciones
    Puntaje puntaje = new Puntaje (Puntaje.TipoPuntaje.ME_GUSTA, numeroPuntaje)
    Calificacion calificacion = new Calificacion(usuario, puntaje, publicacion, null)
    //orden incorrecto
    publicacion.agregarCalificacion(calificacion)
    calificacion.save(failOnError:true)
    usuario.actualizarPromedioCalificaciones()
    usuario
  }

  def filtrarPorPromedio(ArrayList usuarios, long promedioMin, long promedioMax){
    usuarios.findAll {usuario -> usuario.promedioCalificaciones >= promedioMin && usuario.promedioCalificaciones <= promedioMax}
  }

  def filtrarPorMateria(ArrayList usuarios, long idMateria){
    usuarios.findAll { usuario -> Materia.get(idMateria) in usuario.obtenerMateriasCursadas() }
  }

}
