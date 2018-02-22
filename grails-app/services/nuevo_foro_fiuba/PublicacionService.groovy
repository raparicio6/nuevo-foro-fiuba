package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional
class PublicacionService {

  def serviceMethod() {}

  final String PATH = "C:/Users/Mariano/nuevo_foro_fiuba/grails-app/files/"
  //final String PATH = "/nuevo-foro-fiuba/grails-app/files/"
  // final String PATH = "/home/rodrigo/nuevo-foro-fiuba/grails-app/files/"

  Publicacion crearPublicacion(Usuario usuarioCreador, String texto, Catedra catedraRelacionada = null, Materia materiaRelacionada = null, def materiasRequeridas = null, Float promedioCalificacionesMinimoParaComentar = 0, Encuesta encuesta = null, Archivo archivo = null){
    Publicacion publicacion = new Publicacion (texto, usuarioCreador)
    publicacion.modificarCatedra(catedraRelacionada)
    publicacion.modificarMateria(materiaRelacionada)
    if (materiasRequeridas) // para que no agregue un null en la tabla muchos a muchos
      materiasRequeridas.collect { materiaRequerida -> publicacion.agregarMateriaRequeridaParaComentar(materiaRequerida) }
    publicacion.modificarPromedioRequeridoParaComentar(promedioCalificacionesMinimoParaComentar)
    publicacion.agregarEncuesta(encuesta)
    publicacion.agregarArchivo(archivo)
    publicacion.save(failOnError: true)
    publicacion
  }

  def obtenerPublicacionesNoEliminadas(){
    this.getAllPublicaciones().findAll {publicacionInstance -> publicacionInstance.getEstado() != Publicacion.EstadoPublicacion.ELIMINADA}
  }

  def filtrarPublicacionesPorCatedra(ArrayList publicaciones, long idCatedra){
    publicaciones.findAll {publicacion -> if (publicacion.catedraRelacionada) {publicacion.catedraRelacionada.id == idCatedra}}
  }

  def obtenerComentariosNoEliminados(Publicacion publicacion){
    publicacion.obtenerComentariosNoEliminados()
  }

  Publicacion formarPublicacion(long idUsuario, String texto, long idCatedra = 0, def idsMateriasRequeridas = [], String nombreEncuesta = null, String nombreOpciones = null, Float promedioCalificacionesMinimoParaComentar = 0, MultipartFile file = null){
    def usuario = this.getUsuarioById(idUsuario)

    def catedra = null
    def materia = null
    if (idCatedra){
      catedra = this.getCatedraById(idCatedra)
      materia = catedra.materia
    }

    def archivoAdjunto = null
    if (file && !file.empty){
      archivoAdjunto = new Archivo(file.originalFilename, this.PATH)
      file.transferTo(new File(archivoAdjunto.path))
      archivoAdjunto.save(failOnError:true)
    }

    def materiasRequeridas = []
    if (idsMateriasRequeridas)
      materiasRequeridas = idsMateriasRequeridas.collect {idMateriaRequerida -> this.getMateriaById(idMateriaRequerida.toLong())}

    def encuesta = null
    if (nombreEncuesta && nombreOpciones) {
      def opcionesSeparadas = nombreOpciones.tokenize(',')
      def listaDeOpciones = opcionesSeparadas.collect { opcion -> new Opcion (opcion).save(failOnError:true) }
      encuesta = new Encuesta(nombreEncuesta, listaDeOpciones.toSet())
      encuesta.save(failOnError:true)
    }

    Publicacion publicacion = this.crearPublicacion(usuario, texto, catedra, materia, materiasRequeridas, promedioCalificacionesMinimoParaComentar, encuesta, archivoAdjunto)
    usuario.publicar(publicacion)
    publicacion
  }

  def cambiarEstado (long idPublicacion){
    def publicacion = getPublicacionById(idPublicacion)
    publicacion.cambiarEstado()
  }

  def modificarTextoPublicacion (long idPublicacion, String nuevoTexto){
    def publicacion = getPublicacionById(idPublicacion)
    publicacion.modificarTexto(nuevoTexto)
  }

  def modificarMateriaPublicacion (long idPublicacion, long idMateria){
    def publicacion = getPublicacionById(idPublicacion)
    def materia = this.getMateriaById(idMateria)
    publicacion.modificarMateria(materia)
  }

  def modificarCatedraPublicacion (long idPublicacion, long idCatedra){
    def publicacion = getPublicacionById(idPublicacion)
    def catedra = this.getCatedraById(idCatedra)
    publicacion.modificarCatedra(catedra)
    publicacion.modificarMateria(catedra.materia)
  }

  def modificarPromedioRequeridoParaComentar(long idPublicacion, Float promedio){
    def publicacion = getPublicacionById(idPublicacion)
    publicacion.modificarPromedioRequeridoParaComentar(promedio)
  }

  def eliminarPublicacion (long idPublicacion){
    def publicacion = getPublicacionById(idPublicacion)
    publicacion.eliminar()
  }

  def calificarPublicacion(long idUsuario, long idPublicacion, Puntaje.TipoPuntaje tipo){
     def usuario = this.getUsuarioById(idUsuario)
     def publicacion = getPublicacionById(idPublicacion)
     def promedioCalificaciones = usuario.getPromedioCalificaciones()
     Puntaje puntaje = new Puntaje (tipo, promedioCalificaciones)
     Calificacion calificacion = new Calificacion(usuario, puntaje, publicacion, null)
     usuario.calificar(publicacion, calificacion)
     publicacion.getUsuarioCreador().actualizarPromedioCalificaciones(calificacion)
     calificacion.save(failOnError:true)
   }

  Comentario comentarPublicacion (long idUsuario, String textoComentario, long idPublicacion){
    def usuario = this.getUsuarioById(idUsuario)
    def publicacionAComentar = getPublicacionById(idPublicacion)
    Comentario comentario = new Comentario(textoComentario, usuario, publicacionAComentar, null)
    usuario.comentarPublicacion(comentario, publicacionAComentar)
    comentario.save(failOnError:true)
    comentario
  }

  def agregarMateriaRequeridaParaComentar(long idPublicacion, long idMateria){
    def publicacion = getPublicacionById(idPublicacion)
    def materia = this.getMateriaById(idMateria)
    publicacion.agregarMateriaRequeridaParaComentar(materia)
  }

  def getPublicacionById(long idPublicacion){
    Publicacion.get(idPublicacion)
  }

  def getAllPublicaciones(){
    Publicacion.list()
  }

  def getUsuarioById(long idUsuario){
    Usuario.get(idUsuario)
  }

  def getMateriaById(long idMateria){
    Materia.get(idMateria)
  }

  def getCatedraById(long idCatedra){
    Catedra.get(idCatedra)
  }

}
