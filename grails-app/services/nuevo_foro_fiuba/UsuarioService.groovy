package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class UsuarioService {

  final Integer PROMEDIO_INICIAL_USUARIOS = 3

  def serviceMethod() {}

  Usuario crearUsuario(String nombre, String apellido, String nombreUsuario){
    // Publicacion publicacion = new Publicacion("Publicación de bienvenida!", usuario, null, null)
    // publicacion.eliminar()
    // usuario.agregarPublicacion(publicacion)

    Float primerCalificacion = this.obtenerPrimerCalificacion()
    Usuario usuario = new Usuario(nombre, apellido, nombreUsuario, primerCalificacion)
    // Puntaje puntaje = new Puntaje(Puntaje.TipoPuntaje.ME_GUSTA, numeroPrimerPuntaje)
    // Calificacion calificacion = new Calificacion(usuario, puntaje, publicacion, null)
    // publicacion.agregarCalificacion(calificacion)
    // usuario.actualizarPromedioCalificaciones()

    usuario.save(failOnError: true)
    //publicacion.save(failOnError: true)
    //calificacion.save(failOnError:true)
    usuario
  }

  Float obtenerPrimerCalificacion(){
    // random entre 500 y 1000
    Integer decimalesASumar = Math.abs(new Random().nextInt() % 1000) + 500
    String primerPuntaje = this.PROMEDIO_INICIAL_USUARIOS.toString() + ".0" + decimalesASumar.toString()
    Float numeroPrimerPuntaje = primerPuntaje.toFloat()
    numeroPrimerPuntaje
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
