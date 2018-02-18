package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class UsuarioService {

  final Integer PROMEDIO_INICIAL_USUARIOS = 3

  def serviceMethod() {}

  Usuario crearUsuario(String nombre, String apellido, String nombreUsuario){
    Usuario usuario = new Usuario(nombre, apellido, nombreUsuario)

    // se crea una publicacion vacia para que el usuario tenga
    // un promedio inicial distinto de 0
    Publicacion publicacion = new Publicacion("Publicación de bienvenida!", usuario, null, null)
    publicacion.eliminar()
    usuario.agregarPublicacion(publicacion)

    // random entre 1 y 100000
    Integer decimalesASumar = Math.abs(new Random().nextInt() % 100000) + 1
    String primerPuntaje = this.PROMEDIO_INICIAL_USUARIOS.toString() + ".000" + decimalesASumar.toString()
    println(primerPuntaje)
    Float numeroPrimerPuntaje = primerPuntaje.toFloat()

    Puntaje puntaje = new Puntaje(Puntaje.TipoPuntaje.ME_GUSTA, numeroPrimerPuntaje)
    Calificacion calificacion = new Calificacion(usuario, puntaje, publicacion, null)
    publicacion.agregarCalificacion(calificacion)
    usuario.actualizarPromedioCalificaciones()

    usuario.save(failOnError: true)
    publicacion.save(failOnError: true)
    calificacion.save(failOnError:true)
    usuario
  }

  def obtenerTodosLosUsuarios(){
    Usuario.list()
  }

  def obtenerCantidadDeUsuariosTotal(){
    Usuario.count()
  }

  def filtrarPorPromedio(Float promedioMin, Float promedioMax){
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

  def getAllCatedras(){
    Catedra.list()
  }

  def getAllMaterias(){
    Materia.list()
  }

}
