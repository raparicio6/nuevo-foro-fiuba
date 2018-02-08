package nuevo_foro_fiuba
import grails.gorm.transactions.Transactional

@Transactional
class PruebaController {

  String prueba = "Escribir nuevo mensaje"

    def usuarioService
    def mensajePrivadoService
    def comentarioService
    def puntajeService

    def index() {}

    Usuario crearUsuario (String nombre, String apellido, String nombreUsuario){
      Usuario usuario = this.usuarioService.crearUsuario(nombre, apellido, nombreUsuario)
      Puntaje puntaje = this.puntajeService.crear(usuario,0)
      this.usuarioService.modificarPuntaje(usuario, puntaje)
      return usuario
    }



    Comentario comentar (String texto, Usuario usuarioCreador, def objetoAComentar){
      Comentario comentario = this.comentarioService.crearComentario (texto, usuarioCreador, objetoComentado)
      this.usuarioService.comentar (usuarioCreador, comentario, objetoAComentar)
      return comentario
    }

    //objetoValorado puede ser una publicacion o un comentario
    //El entero valoracion es 1 (voto positivo) o -1 (voto negativo)
    //Las reglas de negocio dicen que tienen más peso las valoraciones de usuarios con puntaje alto.
    //    Defino entonces: la valoracion de un usuario suma o resta (puntaje del usuario / 10) +1
    def valorar (Usuario usuarioQueValora, def objetoValorado,Integer valoracion){
      Puntaje puntaje = this.puntajeService.crear(objetoValorado.usuarioCreador, valoracion)
      this.usuarioService.modificarPuntaje(objetoValorado.usuarioCreador, puntaje)
    }
}
