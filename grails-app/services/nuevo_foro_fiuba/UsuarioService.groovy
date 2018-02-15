package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class UsuarioService {

  final Integer PROMEDIO_INICIAL_USUARIOS = 3

  def serviceMethod() {}

  Usuario crearUsuario(String nombre, String apellido, String nombreUsuario){
    Usuario usuario = new Usuario(nombre, apellido, nombreUsuario)
    usuario.save(failOnError: true)

    // se crea una publicacion vacia para que el usuario tenga
    // un promedio inicial distinto de 0
    Publicacion publicacion = new Publicacion("", usuario, null, null)
    //orden incorrecto
    usuario.agregarPublicacion(publicacion)
    publicacion.save(failOnError: true)
    publicacion.eliminar()

    Puntaje puntaje = new Puntaje(Puntaje.TipoPuntaje.ME_GUSTA, this.PROMEDIO_INICIAL_USUARIOS)
    Calificacion calificacion = new Calificacion(usuario, puntaje, publicacion, null)
    publicacion.agregarCalificacion(calificacion)
    calificacion.save(failOnError:true)
    usuario.actualizarPromedioCalificaciones()
    usuario
  }

  def obtenerTodosLosUsuarios(){
    Usuario.list()
  }

  def obtenerCantidadDeUsuariosTotal(){
    Usuario.count()
  }

  def filtrarPorPromedio(long promedioMin, long promedioMax){
    Usuario.list().findAll {usuario -> usuario.promedioCalificaciones >= promedioMin && usuario.promedioCalificaciones <= promedioMax}
  }

  def filtrarPorMateria(ArrayList usuarios, long idMateria){
    usuarios.findAll { usuario -> getMateriaById(idMateria) in usuario.obtenerMateriasCursadas() }
  }

  def getMateriaById(long idMateria){
    Materia.get(idMateria)
  }

  def getUsuarioById(long idUsuario){
    Usuario.get(idUsuario)
  }

  def getPublicacionById(long idPublicacion){
    Publicacion.get(idPublicacion)
  }

}
