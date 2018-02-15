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

  Boolean usuarioEsDueñoDeLaPublicacion(Usuario usuario, Publicacion publicacion){
    usuario.esDueñoDeLaPublicacion(publicacion)
  }

  def cambiarEstado (Usuario usuario, Publicacion publicacion){
    usuario.cambiarEstado(publicacion)
  }

  def modificarTexto (Usuario usuario,def objetoAModificar, String nuevoTexto){
    objetoAModificar.modificarTexto(nuevoTexto)
  }

  def modificarMateriaPublicacion (Usuario usuario, Publicacion publicacion, Materia materia){
    usuario.modificarMateriaPublicacion(publicacion, materia)
  }

  def modificarCatedraPublicacion (Usuario usuario, Publicacion publicacion, Catedra catedra){
    usuario.modificarCatedraPublicacion(publicacion, catedra)
  }

  def modificarPromedioRequeridoParaComentar(Usuario usuario, Publicacion publicacion, Integer promedio){
    usuario.modificarPromedioRequeridoParaComentar(publicacion, promedio)
  }

  def eliminarPublicacion (Usuario usuario, Publicacion publicacionInstance){
    publicacionInstance.eliminarComentarios()
    publicacionInstance.eliminarCalificaciones()
    publicacionInstance.eliminar()
  }

  def calificarPublicacion(Usuario usuario, Publicacion publicacion, Puntaje.TipoPuntaje tipo){
    def promedioCalificaciones = (usuario.getPromedioCalificaciones()).toInteger()
    Integer numeroPuntaje = promedioCalificaciones + 0**promedioCalificaciones
    Puntaje puntaje = new Puntaje (tipo, numeroPuntaje)
    Calificacion calificacion = new Calificacion(usuario, puntaje, publicacion, null)
    // orden incorrecto
    usuario.calificar(publicacion, calificacion)
    calificacion.save(failOnError:true)
    publicacion.getUsuarioCreador().actualizarPromedioCalificaciones()
  }

  def comentarPublicacion (Usuario usuario, String textoComentario, Publicacion publicacionAComentar){
    Comentario comentario = new Comentario(textoComentario, usuario, publicacionAComentar, null)
    // orden incorrecto
    usuario.comentarPublicacion(comentario, publicacionAComentar)
    comentario.save(failOnError:true)
  }

  def obtenerPublicacionesNoEliminadas(){
    Publicacion.list().findAll {publicacionInstance -> publicacionInstance.getEstado() != Publicacion.EstadoPublicacion.ELIMINADA}
  }

  def filtrarPublicacionesPorCatedra(ArrayList publicaciones, long idCatedra){
    publicaciones.findAll {publicacion -> publicacion.catedraRelacionada.id == idCatedra}
  }

  def agregarMateriaRequeridaParaComentar(Publicacion publicacionInstance, Usuario usuarioInstance, Materia materiaInstance){
    usuarioInstance.agregarMateriaRequeridaParaComentar(publicacionInstance, materiaInstance)
  }

  def votarOpcionEncuesta(long idPublicacion, long idUsuario, long idOpcion){
    Publicacion publicacionInstance = Publicacion.get(idPublicacion)
    Usuario usuarioInstance = Usuario.get(idUsuario)
    Opcion opcionInstance = Opcion.get(idOpcion)
    Voto voto = new Voto (usuarioInstance)
    voto.save(failOnError:true)
    usuarioInstance.votarOpcion(publicacionInstance,opcionInstance, voto)
  }

}
