package nuevo_foro_fiuba

class PublicacionController {

  def publicacionService

  def index() {}

  def listaPublicaciones(long idUsuario,Integer max,Integer idCatedra) {
    def publicacionesNoEliminadas = publicacionService.obtenerPublicacionesNoEliminadas()
    def publicaciones = (!idCatedra) ? publicacionesNoEliminadas : publicacionService.filtrarPublicacionesPorCatedra(publicacionesNoEliminadas, idCatedra)
    def usuarioInstance = publicacionService.getUsuarioById(idUsuario)  
    params.max = Math.min(max ?: 10, 100)
    [publicacionInstanceList: publicaciones, publicacionInstanceTotal: publicaciones.size(), usuarioInstance: usuarioInstance, materias: Materia.list(), catedras: Catedra.list()]
  }

  def crearPublicacion (long idUsuario) {
    def usuarioInstance = publicacionService.getUsuarioById(idUsuario)
    [usuario: usuarioInstance]
  }

  def verPublicacion (long idPublicacion, long idUsuario){
    def publicacionInstance = publicacionService.getPublicacionById(idPublicacion)  
    def usuarioInstance = publicacionService.getUsuarioById(idUsuario)  
    def esDueño = publicacionService.usuarioEsDueñoDeLaPublicacion(usuarioInstance, publicacionInstance)
    def comentarios = publicacionService.obtenerComentariosNoEliminados(publicacionInstance)
    [publicacion: publicacionInstance, materias: Materia.list(), catedras: Catedra.list(), usuario: usuarioInstance, modificar:esDueño, comentarios:comentarios] //--> groovy !null==true
    // EL ATRIBUTO MODIFICAR DEFINE SI EL USUARIO QUE INGRESA A LA PUBLICACION PUEDE VER LOS BOTONES ELIMINAR,CAMBIAR ESTADO, ETC
  }

  def formarPublicacion (String texto, long idUsuario, long idMateria, long idCatedra) {
    def publicacion = publicacionService.crearPublicacion(texto, idUsuario, idMateria, idCatedra)
    long idPublicacion = publicacion.id()
    redirect(action: "verPublicacion", params: [idUsuario:idUsuario, idPublicacion:idPublicacion])
  }

  def cambiarEstado (long idPublicacion, long idUsuario){
    publicacionService.cambiarEstado(idUsuario, idPublicacion)
    redirect(action: "verPublicacion", params: [idUsuario:idUsuario, idPublicacion:idPublicacion])
  }

  def modificarTextoPublicacion(long idPublicacion, String nuevoTexto, long idUsuario){
    publicacionService.modificarTextoPublicacion(idUsuario, idPublicacion, nuevoTexto)
    redirect(action: "verPublicacion", params: [idUsuario:idUsuario, idPublicacion:idPublicacion])
  }

  def modificarMateria(long idPublicacion, long idMateria, long idUsuario){
    publicacionService.modificarMateriaPublicacion(idUsuario, idPublicacion, idMateria)
    redirect(action: "verPublicacion", params: [idUsuario:idUsuario, idPublicacion:idPublicacion])
  }

  def modificarCatedra(long idPublicacion, long idCatedra, long idUsuario){
    publicacionService.modificarCatedraPublicacion(idUsuario, idPublicacion, idCatedra)
    redirect(action: "verPublicacion", params: [idUsuario:idUsuario, idPublicacion:idPublicacion])
  }

  def modificarPromedioRequeridoParaComentar (long idPublicacion, long idUsuario, Integer promedio){
    publicacionService.modificarPromedioRequeridoParaComentar(idUsuario, idPublicacion, promedio)
    redirect(action: "verPublicacion", params: [idUsuario:idUsuario, idPublicacion:idPublicacion])
  }

  def eliminarPublicacion(long idPublicacion, long idUsuario){
    publicacionService.eliminarPublicacion(idUsuario, idPublicacion)
    redirect(action: "listaPublicaciones", max: 10, params: [idUsuario:idUsuario])
  }

  def calificarPositivo(long idPublicacion, long idUsuario){
    calificarPublicacion(Puntaje.TipoPuntaje.ME_GUSTA, idPublicacion, idUsuario)
  }

  def calificarNegativo(long idPublicacion, long idUsuario){
    calificarPublicacion(Puntaje.TipoPuntaje.NO_ME_GUSTA, idPublicacion, idUsuario)
  }

  def calificarPublicacion(Puntaje.TipoPuntaje tipo, long idPublicacion, long idUsuario){
    try{
      publicacionService.calificarPublicacion(idUsuario, idPublicacion, tipo)
    }
    catch (UsuarioYaCalificoException e){
      flash.message = e.MENSAJE
    }
    redirect(action: "verPublicacion", params: [idUsuario:idUsuario, idPublicacion:idPublicacion])
  }

  def comentar(long idPublicacion, long idUsuario, String textoComentario){
    try{
      publicacionService.comentarPublicacion(idUsuario, textoComentario, idPublicacion)
    }
    catch (PromedioInsuficienteException e){
      flash.message = e.MENSAJE
    }
    catch (PublicacionCerradaException e){
      flash.message = e.MENSAJE
    }
    catch (UsuarioNoPoseeMateriasNecesariasException e){
      flash.message = e.MENSAJE
    }
    redirect(controller:"publicacion", action: "verPublicacion", params: [idUsuario:idUsuario, idPublicacion:idPublicacion])
  }

  def agregarMateriaRequeridaParaComentar(long idPublicacion, long idUsuario, long idMateria){
    publicacionService.agregarMateriaRequeridaParaComentar(idPublicacion, idUsuario, idMateria)
    redirect(controller:"publicacion", action: "verPublicacion", params: [idUsuario:idUsuario, idPublicacion:idPublicacion])
  }

  def votarOpcionEncuesta(long idPublicacion, long idUsuario, long idOpcion){
    try{
      publicacionService.votarOpcionEncuesta(idPublicacion, idUsuario, idOpcion)
    }
    catch(PublicacionCerradaException e){
      flash.message = e.MENSAJE
    }
    catch(CreadorEncuestaNoPuedeVotarException e){
      flash.message = e.MENSAJE
    }
    catch(UsuarioYaVotoException e){
      flash.message = e.MENSAJE
    }
    redirect(controller:"publicacion", action: "verPublicacion", params: [idUsuario:idUsuario, idPublicacion:idPublicacion])
  }
}
