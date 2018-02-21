package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional
class PublicacionService {

  def serviceMethod() {}

  final String PATH = "C:/Users/Mariano/nuevo_foro_fiuba/grails-app/files/"

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

  Publicacion formarPublicacion(long idUsuario, long idCatedra, String texto, long idMateriaRequerida, Float promedioCalificacionesMinimoParaComentar = 0, String nombreEncuesta = null, String nombreOpciones = null, MultipartFile file = null){
    def usuario = Usuario.get(idUsuario)
    def catedra = null
    def materia = null
    if (idCatedra){
      catedra = Catedra.get(idCatedra)
      materia = catedra.materia
    }
    Publicacion publicacion = this.crearPublicacion(usuario, catedra, texto, materia)
    def materiaRequerida = null
    def archivoAdjunto = null
    if (file && !file.empty){
      archivoAdjunto = new Archivo(file.originalFilename, this.PATH + file.originalFilename)
      file.transferTo(new File(archivoAdjunto.path))
      archivoAdjunto.save(failOnError:true)
    }
    adjuntarArchivo (usuario, publicacion, archivoAdjunto )
    if (idMateriaRequerida){
      materiaRequerida = Materia.get(idMateriaRequerida)
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
    def usuario = Usuario.get(idUsuario)
    def publicacion = getPublicacionById(idPublicacion)
    usuario.cambiarEstado(publicacion)
  }

  def modificarTextoPublicacion (long idUsuario,long idPublicacion, String nuevoTexto){
    def usuario = Usuario.get(idUsuario)
    def publicacion = getPublicacionById(idPublicacion)
    publicacion.modificarTexto(nuevoTexto)
  }

  def modificarMateriaPublicacion (long idUsuario, long idPublicacion, long idMateria){
    def usuario = Usuario.get(idUsuario)
    def publicacion = getPublicacionById(idPublicacion)
    def materia = Materia.get(idMateria)
    usuario.modificarMateriaPublicacion(publicacion, materia)
  }

  def modificarCatedraPublicacion (long idUsuario, long idPublicacion, long idCatedra){
    def usuario = Usuario.get(idUsuario)
    def publicacion = getPublicacionById(idPublicacion)
    def catedra = Catedra.get(idCatedra)
    usuario.modificarCatedraPublicacion(publicacion, catedra)
    modificarMateriaPublicacion(idUsuario, idPublicacion, catedra.materia.id)
  }

  def modificarPromedioRequeridoParaComentar(long idUsuario, long idPublicacion, Float promedio){
    def usuario = Usuario.get(idUsuario)
    def publicacion = getPublicacionById(idPublicacion)
    usuario.modificarPromedioRequeridoParaComentar(publicacion, promedio)
  }

  def eliminarPublicacion (long idUsuario, long idPublicacion){
    def usuario = Usuario.get(idUsuario)
    def publicacion = getPublicacionById(idPublicacion)
    usuario.eliminarPublicacion(publicacion)
  }

  def calificarPublicacion(long idUsuario, long idPublicacion, Puntaje.TipoPuntaje tipo){
     def usuario = Usuario.get(idUsuario)
     def publicacion = getPublicacionById(idPublicacion)
     def promedioCalificaciones = usuario.getPromedioCalificaciones()
     Puntaje puntaje = new Puntaje (tipo, promedioCalificaciones)
     Calificacion calificacion = new Calificacion(usuario, puntaje, publicacion, null)
     usuario.calificar(publicacion, calificacion)
     publicacion.getUsuarioCreador().actualizarPromedioCalificaciones(calificacion)
     calificacion.save(failOnError:true)
   }

  Comentario comentarPublicacion (long idUsuario, String textoComentario, long idPublicacion){
    def usuario = Usuario.get(idUsuario)
    def publicacionAComentar = getPublicacionById(idPublicacion)
    Comentario comentario = new Comentario(textoComentario, usuario, publicacionAComentar, null)
    usuario.comentarPublicacion(comentario, publicacionAComentar)
    comentario.save(failOnError:true)
    comentario
  }

  def agregarMateriaRequeridaParaComentar(long idPublicacion, long idUsuario, long idMateria){
    def publicacion = getPublicacionById(idPublicacion)
    def usuario = Usuario.get(idUsuario)
    def materia = Materia.get(idMateria)
    usuario.agregarMateriaRequeridaParaComentar(publicacion, materia)
  }

  def votarOpcionEncuesta(long idPublicacion, long idUsuario, long idOpcion){
    Publicacion publicacion = getPublicacionById(idPublicacion)
    Usuario usuario = Usuario.get(idUsuario)
    Opcion opcion = Opcion.get(idOpcion)
    Voto voto = new Voto (usuario)
    usuario.votarOpcion(publicacion, opcion, voto)
    voto.save(failOnError:true)
  }

  def adjuntarArchivo (Usuario usuario, Publicacion publicacion, Archivo archivoAdjunto){
    usuario.adjuntarArchivo(publicacion, archivoAdjunto)
  }

  def getPublicacionById(long idPublicacion){
    Publicacion.get(idPublicacion)
  }

}
