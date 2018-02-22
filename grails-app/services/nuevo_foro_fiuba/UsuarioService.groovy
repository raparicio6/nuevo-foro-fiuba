package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class UsuarioService {

  final Integer PUNTAJE_INICIAL_USUARIOS = 3

  def serviceMethod() {}

  Usuario crearUsuario(String nombre, String apellido, String nombreUsuario){
    // Publicacion publicacion = new Publicacion("Publicación de bienvenida!", usuario, null, null)
    // publicacion.eliminar()
    // usuario.agregarPublicacion(publicacion)

    Float primerPuntaje = this.obtenerPrimerPuntaje()
    Usuario usuario = new Usuario(nombre, apellido, nombreUsuario, primerPuntaje)
    // Puntaje puntaje = new Puntaje(Puntaje.TipoPuntaje.ME_GUSTA, numeroPrimerPuntaje)
    // Calificacion calificacion = new Calificacion(usuario, puntaje, publicacion, null)
    // publicacion.agregarCalificacion(calificacion)
    // usuario.actualizarPromedioCalificaciones()

    usuario.save(failOnError: true)
    //publicacion.save(failOnError: true)
    //calificacion.save(failOnError:true)
    usuario
  }

  Float obtenerPrimerPuntaje(){
    // se generan decimales aleatorios para evitar que todos los usuarios tengan
    // los mismos puntajes literalmente durante las primeras calificaciones
    // random entre 500 y 1000
    Integer decimalesASumar = Math.abs(new Random().nextInt() % 1000) + 500
    String primerPuntaje = this.PUNTAJE_INICIAL_USUARIOS.toString() + ".0" + decimalesASumar.toString()
    Float numeroPrimerPuntaje = primerPuntaje.toFloat()
    numeroPrimerPuntaje
  }

  def filtrarPorPromedio(Float promedioMin, Float promedioMax){
    this.getAllUsuarios().findAll {usuario -> usuario.promedioCalificaciones >= promedioMin && usuario.promedioCalificaciones <= promedioMax}
  }

  def filtrarPorMateria(ArrayList usuarios, def idsMaterias){
    def materiasFiltro = idsMaterias.collect {idMateria -> getMateriaById(idMateria.toLong())}
    usuarios.findAll { usuario -> usuario.cursaEstasMaterias(materiasFiltro) }
  }

  Boolean usuarioEsDueñoDeLaPublicacion(Usuario usuario, Publicacion publicacion){
    usuario.esDueñoDeLaPublicacion(publicacion)
  }

  Boolean usuarioEsDueñoDelComentario(Usuario usuario, Comentario comentario){
    usuario.esDueñoDelComentario(comentario)
  }

  def votarOpcionEncuesta(long idPublicacion, long idUsuario, long idOpcion){
    Publicacion publicacion = getPublicacionById(idPublicacion)
    Usuario usuario = this.getUsuarioById(idUsuario)
    Opcion opcion = this.getOpcionById(idOpcion)
    Voto voto = new Voto (usuario)
    usuario.votarOpcion(publicacion, opcion, voto)
    voto.save(failOnError:true)
  }

  def getCursadas(Usuario usuario){
    usuario.getCursadas()
  }

  def getPublicacionById(long idPublicacion){
    Publicacion.get(idPublicacion)
  }

  def getUsuarioById(long idUsuario){
    Usuario.get(idUsuario)
  }

  def getAllUsuarios(){
    Usuario.list()
  }

  def getMateriaById(long idMateria){
    Materia.get(idMateria)
  }

  def getOpcionById(long idOpcion){
    Opcion.get(idOpcion)
  }

}
