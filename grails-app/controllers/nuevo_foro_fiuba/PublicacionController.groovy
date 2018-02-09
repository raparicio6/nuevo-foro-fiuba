package nuevo_foro_fiuba
import nuevo_foro_fiuba.Puntaje.TipoPuntaje
import grails.gorm.transactions.Transactional

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
      def usuarioInstance = Usuario.get(id)
      params.max = Math.min(max ?: 10, 100)
      [publicacionInstanceList: publicaciones, publicacionInstanceTotal: publicaciones.size(), usuarioInstance: usuarioInstance, materias: Materia.list(), catedras: Catedra.list()]
    }

    def verPublicacion (long id, long idUsuario){
      def publicacionInstance = Publicacion.get(id)
      def usuarioInstance = Usuario.get(idUsuario)
      def esDueño = usuarioService.usuarioEsDueñoDeLaPublicacion(usuarioInstance, publicacionInstance)
      [publicacion: publicacionInstance, materias: Materia.list(), catedras: Catedra.list(), usuario: usuarioInstance, modificar:esDueño] //--> groovy !null==true
      // EL ATRIBUTO MODIFICAR DEFINE SI EL USUARIO QUE INGRESA A LA PUBLICACION PUEDE VER LOS BOTONES ELIMINAR,CAMBIAR ESTADO, ETC
    }

    def cambiarEstado (Long id, long idUsuario){
      def usuarioLogin = Usuario.get(idUsuario)
      def publicacionInstance = Publicacion.get(id)
      usuarioService.cambiarEstado(usuarioLogin, publicacionInstance)
      redirect(action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
    }

    def modificarTextoPublicacion(long id, String nuevoTexto, long idUsuario){
      def usuarioLogin = Usuario.get(idUsuario)
      def publicacionInstance = Publicacion.get(id)
      usuarioService.modificarTexto(usuarioLogin, publicacionInstance, nuevoTexto)
      redirect(action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
    }

    def modificarMateria(long id, long idMateria, long idUsuario){
      def usuarioLogin = Usuario.get(idUsuario)
      def publicacionInstance = Publicacion.get(id)
      def materiaInstance = Materia.get(idMateria)
      usuarioService.modificarMateriaPublicacion(usuarioLogin, publicacionInstance, materiaInstance)
      redirect (action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
    }

    def modificarCatedra(long id, long idCatedra, long idUsuario){
      def usuarioLogin = Usuario.get(idUsuario)
      def publicacionInstance = Publicacion.get(id)
      def catedraInstance = Catedra.get(idCatedra)
      usuarioService.modificarCatedraPublicacion(usuarioLogin, publicacionInstance, catedraInstance)
      redirect (action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
    }

    def modificarPromedioRequeridoParaComentar (long id, long idUsuario, Integer promedio){
      def usuarioLogin = Usuario.get(idUsuario)
      def publicacionInstance = Publicacion.get(id)
      usuarioService.modificarPromedioRequeridoParaComentar(usuarioLogin, publicacionInstance, promedio)
      redirect (action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
    }

//VER
    def eliminarPublicacion(long id, long idUsuario){
      def usuarioLogin = Usuario.get(idUsuario)
      def publicacionInstance = Publicacion.get(id)
      usuarioService.eliminarPublicacion(usuarioLogin, publicacionInstance)
      publicacionInstance.comentarios.collect {comentario -> comentarioService.eliminarComentario(comentario)}
      publicacionInstance.calificaciones.collect {calificacion -> calificacionService.eliminarCalificacion(calificacion)}
      publicacionService.eliminarPublicacion(publicacionInstance)
      redirect(action: "listaPublicaciones", max: 10, params: [id:idUsuario])
    }

    def calificarPositivo(long id, long idUsuario){
      calificarPublicacion(TipoPuntaje.meGusta, id, idUsuario)
    }

    def calificarNegativo(long id, long idUsuario){
      calificarPublicacion(TipoPuntaje.noMeGusta, id, idUsuario)
    }

    def calificarPublicacion(TipoPuntaje tipo, long id, long idUsuario){
      def publicacionInstance = Publicacion.get(id)
      def usuarioInstance = Usuario.get(idUsuario)
      def calificacion = calificacionService.crearCalificacion(usuarioInstance, puntajeService.crearPuntaje(tipo, usuarioInstance), publicacionInstance,null)
      try{
        usuarioService.calificar(usuarioInstance, publicacionInstance, calificacion)
        usuarioService.actualizarPromedioCalificaciones(publicacionInstance.usuarioCreador)
      }
      catch (UsuarioYaCalificoException e){
        calificacionService.eliminarCalificacion(calificacion)
        flash.message = "No se puede calificar dos veces la misma publicacion"
      }
      redirect (action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
    }

}
