package nuevo_foro_fiuba
import grails.gorm.transactions.Transactional

@Transactional
class PublicacionService {

  def serviceMethod() {}

  // Publicacion crearPublicacion(String texto, Usuario usuarioCreador, Float promedioRequeridoParaComentar = 0, Materia materiaRelacionada = null, Catedra catedraRelacionada = null, Set <Materia> materiasNecesariasParaComentar = [], Archivo archivoAdjunto = null, Encuesta encuesta = null){
  //  Publicacion publicacion = new Publicacion (texto, usuarioCreador, promedioRequeridoParaComentar, materiaRelacionada, catedraRelacionada, materiasNecesariasParaComentar, archivoAdjunto, encuesta)
  Publicacion crearPublicacion(String texto, Usuario usuarioCreador, Materia materiaRelacionada, Catedra catedraRelacionada){
    Publicacion publicacion = new Publicacion (texto, usuarioCreador, materiaRelacionada, catedraRelacionada)
    publicacion.save(failOnError: true)
    publicacion
  }

  def obtenerPublicacionesNoEliminadas(){
    Publicacion.list().findAll {publicacionInstance -> publicacionInstance.getEstado() != Publicacion.EstadoPublicacion.ELIMINADA}
  }

  def filtrarPublicacionesPorCatedra(ArrayList publicaciones, long idCatedra){
    publicaciones.findAll {publicacion -> publicacion.catedraRelacionada.id == idCatedra}
  }

  Boolean usuarioEsDueñoDeLaPublicacion(Usuario usuario, Publicacion publicacion){
    usuario.esDueñoDeLaPublicacion(publicacion)
  }

  def obtenerComentariosNoEliminados(Publicacion publicacion){
    publicacion.obtenerComentariosNoEliminados()
  }

  def formarPublicacion(long idUsuario, long idCatedra, String texto){
    Usuario usuario = getUsuarioById(idUsuario)
    Catedra catedra = getCatedraById(idCatedra)
    Publicacion publicacion = new Publicacion (texto, usuario, catedra.materia, catedra)
    publicacion.save(failOnError:true)
    usuario.publicar(publicacion)
  }

  def cambiarEstado (long idUsuario, long idPublicacion){
    def usuario = getUsuarioById(idUsuario)
    def publicacion = getPublicacionById(idPublicacion)
    usuario.cambiarEstado(publicacion)
  }

  def modificarTextoPublicacion (long idUsuario,long idPublicacion, String nuevoTexto){
    def usuario = getUsuarioById(idUsuario)
    def publicacion = getPublicacionById(idPublicacion)
    publicacion.modificarTexto(nuevoTexto)
  }

  def modificarMateriaPublicacion (long idUsuario, long idPublicacion, long idMateria){
    def usuario = getUsuarioById(idUsuario)
    def publicacion = getPublicacionById(idPublicacion)
    def materia = getMateriaById(idMateria)
    usuario.modificarMateriaPublicacion(publicacion, materia)
  }

  def modificarCatedraPublicacion (long idUsuario, long idPublicacion, long idCatedra){
    def usuario = getUsuarioById(idUsuario)
    def publicacion = getPublicacionById(idPublicacion)
    def catedra = getCatedraById(idCatedra)
    usuario.modificarCatedraPublicacion(publicacion, catedra)
  }

  def modificarPromedioRequeridoParaComentar(long idUsuario, long idPublicacion, float promedio){
    def usuario = getUsuarioById(idUsuario)
    def publicacion = getPublicacionById(idPublicacion)
    usuario.modificarPromedioRequeridoParaComentar(publicacion, promedio)
  }

  def eliminarPublicacion (long idUsuario, long idPublicacion){
    def usuario = getUsuarioById(idUsuario)
    def publicacion = getPublicacionById(idPublicacion)
    usuario.eliminarPublicacion(publicacion)
  }

  def calificarPublicacion(long idUsuario, long idPublicacion, Puntaje.TipoPuntaje tipo){
    def usuario = getUsuarioById(idUsuario)
    def publicacion = getPublicacionById(idPublicacion)
    def promedioCalificaciones = (usuario.getPromedioCalificaciones()).toInteger()
    Integer numeroPuntaje = promedioCalificaciones + 0**promedioCalificaciones
    Puntaje puntaje = new Puntaje (tipo, numeroPuntaje)
    Calificacion calificacion = new Calificacion(usuario, puntaje, publicacion, null)
    // orden incorrecto
    usuario.calificar(publicacion, calificacion)
    calificacion.save(failOnError:true)
    publicacion.getUsuarioCreador().actualizarPromedioCalificaciones()
  }

  def comentarPublicacion (long idUsuario, String textoComentario, long idPublicacion){
    def usuario = getUsuarioById(idUsuario)
    def publicacionAComentar = getPublicacionById(idPublicacion)
    Comentario comentario = new Comentario(textoComentario, usuario, publicacionAComentar, null)
    // orden incorrecto
    usuario.comentarPublicacion(comentario, publicacionAComentar)
    comentario.save(failOnError:true)
  }

  def agregarMateriaRequeridaParaComentar(long idPublicacion, long idUsuario, long idMateria){
    def publicacion = getPublicacionById(idPublicacion)
    def usuario = getUsuarioById(idUsuario)
    def materia = getMateriaById(idMateria)
    usuario.agregarMateriaRequeridaParaComentar(publicacion, materia)
  }

  def votarOpcionEncuesta(long idPublicacion, long idUsuario, long idOpcion){
    Publicacion publicacion = getPublicacionById(idPublicacion)
    Usuario usuario = getUsuarioById(idUsuario)
    Opcion opcion = getOpcionById(idOpcion)
    Voto voto = new Voto (usuario)
    voto.save(failOnError:true)
    usuario.votarOpcion(publicacion, opcion, voto)
  }

  def getUsuarioById(long idUsuario){
    Usuario.get(idUsuario)
  }

  def getPublicacionById(long idPublicacion){
    Publicacion.get(idPublicacion)
  }

  def getMateriaById(long idMateria){
    Materia.get(idMateria)
  }

  def getCatedraById(long idCatedra){
    Catedra.get(idCatedra)
  }

  def getOpcionById(long idOpcion){
    Opcion.get(idOpcion)
  }


}
