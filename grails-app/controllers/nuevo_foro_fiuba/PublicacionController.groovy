package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class PublicacionController {

  def publicacionService

  def index() {}

  def listaPublicaciones(long id,Integer max,Integer idCatedra) {
    def publicacionesNoEliminadas = Publicacion.list().findAll {publicacionInstance -> publicacionInstance.getEstado() != Publicacion.EstadoPublicacion.ELIMINADA}
    def publicaciones
    publicaciones = (!idCatedra) ? publicacionesNoEliminadas : publicacionesNoEliminadas.findAll {publicacion -> publicacion.catedraRelacionada.id == idCatedra}
    def usuarioInstance = Usuario.get(id)
    params.max = Math.min(max ?: 10, 100)
    [publicacionInstanceList: publicaciones, publicacionInstanceTotal: publicaciones.size(), usuarioInstance: usuarioInstance, materias: Materia.list(), catedras: Catedra.list()]
  }

  def verPublicacion (long id, long idUsuario){
    def publicacionInstance = Publicacion.get(id)
    def usuarioInstance = Usuario.get(idUsuario)
    def esDueño = publicacionService.usuarioEsDueñoDeLaPublicacion(usuarioInstance, publicacionInstance)
    def comentarios = publicacionInstance.comentarios.findAll {comentario -> comentario.getEstado() != Comentario.EstadoComentario.ELIMINADO}
    [publicacion: publicacionInstance, materias: Materia.list(), catedras: Catedra.list(), usuario: usuarioInstance, modificar:esDueño, comentarios:comentarios] //--> groovy !null==true
    // EL ATRIBUTO MODIFICAR DEFINE SI EL USUARIO QUE INGRESA A LA PUBLICACION PUEDE VER LOS BOTONES ELIMINAR,CAMBIAR ESTADO, ETC
  }

  def cambiarEstado (long id, long idUsuario){
    def usuarioLogin = Usuario.get(idUsuario)
    def publicacionInstance = Publicacion.get(id)
    publicacionService.cambiarEstado(usuarioLogin, publicacionInstance)
    redirect(action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
  }

  def modificarTextoPublicacion(long id, String nuevoTexto, long idUsuario){
    def usuarioLogin = Usuario.get(idUsuario)
    def publicacionInstance = Publicacion.get(id)
    publicacionService.modificarTexto(usuarioLogin, publicacionInstance, nuevoTexto)
    redirect(action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
  }

  def modificarMateria(long id, long idMateria, long idUsuario){
    def usuarioLogin = Usuario.get(idUsuario)
    def publicacionInstance = Publicacion.get(id)
    def materiaInstance = Materia.get(idMateria)
    publicacionService.modificarMateriaPublicacion(usuarioLogin, publicacionInstance, materiaInstance)
    redirect (action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
  }

  def modificarCatedra(long id, long idCatedra, long idUsuario){
    def usuarioLogin = Usuario.get(idUsuario)
    def publicacionInstance = Publicacion.get(id)
    def catedraInstance = Catedra.get(idCatedra)
    publicacionService.modificarCatedraPublicacion(usuarioLogin, publicacionInstance, catedraInstance)
    redirect (action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
  }

  def modificarPromedioRequeridoParaComentar (long id, long idUsuario, Integer promedio){
    def usuarioLogin = Usuario.get(idUsuario)
    def publicacionInstance = Publicacion.get(id)
    publicacionService.modificarPromedioRequeridoParaComentar(usuarioLogin, publicacionInstance, promedio)
    redirect (action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
  }

//VER ???????
  def eliminarPublicacion(long id, long idUsuario){
    def usuarioLogin = Usuario.get(idUsuario)
    def publicacionInstance = Publicacion.get(id)
    publicacionService.eliminarPublicacion(usuarioLogin, publicacionInstance)
    redirect(action: "listaPublicaciones", max: 10, params: [id:idUsuario])
  }

  def calificarPositivo(long id, long idUsuario){
    calificarPublicacion(Puntaje.TipoPuntaje.ME_GUSTA, id, idUsuario)
  }

  def calificarNegativo(long id, long idUsuario){
    calificarPublicacion(Puntaje.TipoPuntaje.NO_ME_GUSTA, id, idUsuario)
  }

  def calificarPublicacion(Puntaje.TipoPuntaje tipo, long id, long idUsuario){
    def publicacionInstance = Publicacion.get(id)
    def usuarioInstance = Usuario.get(idUsuario)
    try{
      publicacionService.calificarPublicacion(usuarioInstance, publicacionInstance, tipo)
    }
    catch (UsuarioYaCalificoException e){
      flash.message = e.MENSAJE
    }
    redirect (action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
  }

  def comentar(long id, long idUsuario, long idComentario, String textoComentario){
    def usuarioLogin = Usuario.get(idUsuario)
    def publicacionInstance = Publicacion.get(id)
    try{
      publicacionService.comentarPublicacion(usuarioLogin, textoComentario, publicacionInstance)
    }
    catch (PromedioInsuficienteException e){
      flash.message = e.MENSAJE
    }
    catch (PublicacionCerradaException e){
      flash.message = e.MENSAJE
    }
    redirect(controller:"publicacion", action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
  }
}
