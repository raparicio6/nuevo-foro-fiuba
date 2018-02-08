package nuevo_foro_fiuba
import grails.gorm.transactions.Transactional
import nuevo_foro_fiuba.Puntaje.TipoPuntaje

@Transactional
class PublicacionController {

    def publicacionService
    def comentarioService
    def usuarioService
    def calificacionService
    def puntajeService

    def index() {}

    def listaPublicaciones(long id,Integer max,Integer idCatedra) {
      def publicaciones
      if (!idCatedra){
        publicaciones=Publicacion.list()
      }
      else{
        publicaciones=Publicacion.list().findAll {publicacion -> publicacion.catedraRelacionada.id == idCatedra}
      }
      Usuario usuarioInstance = Usuario.get(id)
      params.max = Math.min(max ?: 10, 100)
      [publicacionInstanceList: publicaciones, publicacionInstanceTotal: publicaciones.size(), usuarioInstance: usuarioInstance, materias: Materia.list(), catedras: Catedra.list()]
    }

    def filtrarPublicacionPorMateria(long idUsuario, long idMateria){
        flash.message=Publicacion.list().findAll{publicacion -> publicacion.materiaRelacionada.id == idMateria}
        redirect (action:"listaPublicaciones", id: idUsuario)
    }

    def verPublicacion (long id, long idUsuario){
      def publicacionInstance = Publicacion.get(id)
      def usuarioInstance = Usuario.get(idUsuario)
      if (usuarioService.usuarioEsDueñoDeLaPublicacion(usuarioInstance, publicacionInstance))
        [publicacion: publicacionInstance, materias: Materia.list(), catedras: Catedra.list(), usuario: usuarioInstance, modificar:"1"] //--> groovy !null==true
      else
        [publicacion: publicacionInstance, materias: Materia.list(), catedras: Catedra.list(), usuario: usuarioInstance]
        // EL ATRIBUTO MODIFICAR DEFINE SI EL USUARIO QUE INGRESA A LA PUBLICACION PUEDE VER LOS BOTONES ELIMINAR,CAMBIAR ESTADO, ETC
    }

    def cerrarPublicacion (Long id, long idUsuario){
      def usuarioLogin = Usuario.get(idUsuario)
      def publicacionInstance = Publicacion.get(id)
      publicacionService.cerrarPublicacion(publicacionInstance)
      redirect(action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
    }

    def reabrirPublicacion (Long id, long idUsuario){
      def usuarioLogin = Usuario.get(idUsuario)
      def publicacionInstance = Publicacion.get(id)
      publicacionService.reabrirPublicacion(publicacionInstance)
      redirect(action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
    }

    def modificarTexto(long id, String nuevoTexto, long idUsuario){
      def usuarioLogin = Usuario.get(idUsuario)
      def publicacionInstance = Publicacion.get(id)
      publicacionService.modificarTexto(publicacionInstance, nuevoTexto)
      redirect(action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
    }

    def modificarMateria(long id, long idMateria, long idUsuario){
      def usuarioLogin = Usuario.get(idUsuario)
      def publicacionInstance = Publicacion.get(id)
      def materiaInstance = Materia.get(idMateria)
      publicacionService.modificarMateria(publicacionInstance, materiaInstance)
      redirect (action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
    }

    def modificarCatedra(long id, long idCatedra, long idUsuario){
      def usuarioLogin = Usuario.get(idUsuario)
      def publicacionInstance = Publicacion.get(id)
      def catedraInstance = Catedra.get(idCatedra)
      publicacionService.modificarCatedra(publicacionInstance, catedraInstance)
      redirect (action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
    }

    def eliminarPublicacion(long id, long idUsuario){
      def usuarioLogin = Usuario.get(idUsuario)
      def publicacionInstance = Publicacion.get(id)
      publicacionInstance.comentarios.collect {comentario -> comentarioService.eliminarComentario(comentario)}
      publicacionInstance.calificaciones.collect {calificacion -> calificacionService.eliminarCalificacion(calificacion)}
      publicacionService.eliminarPublicacion(publicacionInstance)
      redirect(action: "listaPublicaciones", max: 10, params: [id:idUsuario])
    }

    def modificarPuntajeMinimo (long id, long idUsuario, Integer puntaje){
      def publicacionInstance = Publicacion.get(id)
      publicacionService.modificarPuntajeMinimo(publicacionInstance, puntaje)
      redirect (action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
    }

    def puntuarPositivo(long id, long idUsuario){
      puntuarPublicacion(TipoPuntaje.meGusta, id, idUsuario)
    }

    def puntuarNegativo(long id, long idUsuario){
      puntuarPublicacion(TipoPuntaje.noMeGusta, id, idUsuario)
    }

    def puntuarPublicacion(TipoPuntaje tipo, long id, long idUsuario){
      def publicacionInstance = Publicacion.get(id)
      def usuarioInstance = Usuario.get(idUsuario)
      def calificacion = calificacionService.crearCalificacion(usuarioInstance, puntajeService.crearPuntaje(tipo, usuarioInstance), publicacionInstance,null)
      publicacionService.agregarCalificacion(publicacionInstance, calificacion)
      usuarioService.actualizarPuntajeActual(publicacionInstance.usuarioCreador)
      redirect (action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
    }

}
