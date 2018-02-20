package nuevo_foro_fiuba
import grails.gorm.transactions.Transactional

@Transactional
class PublicacionService {

  def serviceMethod() {}

  // Publicacion crearPublicacion(String texto, Usuario usuarioCreador, Float promedioRequeridoParaComentar = 0, Materia materiaRelacionada = null, Catedra catedraRelacionada = null, Set <Materia> materiasNecesariasParaComentar = [], Archivo archivoAdjunto = null, Encuesta encuesta = null){
  //  Publicacion publicacion = new Publicacion (texto, usuarioCreador, promedioRequeridoParaComentar, materiaRelacionada, catedraRelacionada, materiasNecesariasParaComentar, archivoAdjunto, encuesta)
  Publicacion crearPublicacion(Usuario usuarioCreador, Catedra catedraRelacionada, String texto, Materia materiaRelacionada, Materia idMateriaRequerida = null, Float promedioCalificacionesMinimoParaComentar = 0, String nombreEncuesta = null, String nombreOpciones = null){
    Publicacion publicacion = new Publicacion (texto, usuarioCreador, materiaRelacionada, catedraRelacionada)  //AMPLIAR CONSTRUCTOR
    publicacion.save(failOnError: true)
    publicacion
  }

  def obtenerPublicacionesNoEliminadas(){
    Publicacion.list().findAll {publicacionInstance -> publicacionInstance.getEstado() != Publicacion.EstadoPublicacion.ELIMINADA}
  }

  def filtrarPublicacionesPorCatedra(ArrayList publicaciones, long idCatedra){
    publicaciones.findAll {publicacion -> if (publicacion.catedraRelacionada) {publicacion.catedraRelacionada.id == idCatedra}}
  }

  Boolean usuarioEsDueñoDeLaPublicacion(Usuario usuario, Publicacion publicacion){
    usuario.esDueñoDeLaPublicacion(publicacion)
  }

  def obtenerComentariosNoEliminados(Publicacion publicacion){
    publicacion.obtenerComentariosNoEliminados()
  }

  Publicacion formarPublicacion(long idUsuario, long idCatedra, String texto, long idMateriaRequerida, Float promedioCalificacionesMinimoParaComentar = 0, String nombreEncuesta = null, String nombreOpciones = null){
    def usuario = getUsuarioById(idUsuario)
    def catedra = null
    def materia = null
    if (idCatedra){
      catedra = getCatedraById(idCatedra)
      materia = catedra.materia
    }
    Publicacion publicacion = this.crearPublicacion(usuario, catedra, texto, materia)
    def materiaRequerida = null
    if (idMateriaRequerida){
      materiaRequerida = getMateriaById(idMateriaRequerida)
      usuario.agregarMateriaRequeridaParaComentar(publicacion, materiaRequerida)
    }
    def encuesta = null
    if (nombreEncuesta && nombreOpciones) {
      def opcionesSeparadas = nombreOpciones.tokenize(',')
      def listaDeOpciones = opcionesSeparadas.collect { opcion -> new Opcion (opcion).save(failOnError:true) }
      encuesta = new Encuesta(nombreEncuesta, listaDeOpciones.toSet())
      publicacion.agregarEncuesta(encuesta)
      encuesta.save(failOnError:true)
    }
    usuario.publicar(publicacion)
    usuario.modificarPromedioRequeridoParaComentar(publicacion, promedioCalificacionesMinimoParaComentar)
    publicacion
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
    modificarMateriaPublicacion(idUsuario, idPublicacion, catedra.materia.id)
  }

  def modificarPromedioRequeridoParaComentar(long idUsuario, long idPublicacion, Float promedio){
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
     def promedioCalificaciones = usuario.getPromedioCalificaciones()
     Puntaje puntaje = new Puntaje (tipo, promedioCalificaciones)
     Calificacion calificacion = new Calificacion(usuario, puntaje, publicacion, null)
     usuario.calificar(publicacion, calificacion)
     publicacion.getUsuarioCreador().actualizarPromedioCalificaciones(calificacion)
     calificacion.save(failOnError:true)
   }

  Comentario comentarPublicacion (long idUsuario, String textoComentario, long idPublicacion){
    def usuario = getUsuarioById(idUsuario)
    def publicacionAComentar = getPublicacionById(idPublicacion)
    Comentario comentario = new Comentario(textoComentario, usuario, publicacionAComentar, null)
    usuario.comentarPublicacion(comentario, publicacionAComentar)
    comentario.save(failOnError:true)
    comentario
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
    usuario.votarOpcion(publicacion, opcion, voto)
    voto.save(failOnError:true)
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

  def getAllMaterias(){
    Materia.list()
  }

  def getCatedraById(long idCatedra){
    Catedra.get(idCatedra)
  }

  def getAllCatedras(){
    Catedra.list()
  }

  def getOpcionById(long idOpcion){
    Opcion.get(idOpcion)
  }

}
