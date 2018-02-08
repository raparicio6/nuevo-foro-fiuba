package nuevo_foro_fiuba
import grails.gorm.transactions.Transactional

@Transactional
class UsuarioController {

    def usuarioService
    def comentarioService
    def publicacionService

    def index() {
      redirect(action: "seleccionarUsuario", max: "10")
    }

    def seleccionarUsuario(Integer max){
      params.max = Math.min(max ?: 10, 100)
      [usuarioInstanceList: Usuario.list(params), usuarioInstanceTotal: Usuario.count()]
    }

    def inicioUsuario(long id){
      def usuarioInstance = Usuario.get(id)
      [usuarioInstance: usuarioInstance]
    }

    def crearUsuario (String nombre, String apellido, String nombreUsuario){
      usuarioService.crearInstancia(nombre, apellido, nombreUsuario)
    }

    def listaUsuarios(long id, Integer max, Integer promedioMin, Integer promedioMax, long idMateria){
      if (!promedioMin)
         promedioMin=0
      if (!promedioMax)
         promedioMax=5
      def usuarios = Usuario.list().findAll {usuario -> usuario.promedioCalificaciones >= promedioMin && usuario.promedioCalificaciones <= promedioMax}
      if (idMateria){
        usuarios= usuarios.findAll { usuario -> idMateria in usuarioService.materiasCursadas(usuario) }
      }
      Usuario usuarioInstance = Usuario.get(id)
      params.max = Math.min(max ?: 10, 100)
      [usuarioInstanceList: usuarios, usuarioInstanceTotal: usuarios.size() , usuarioInstance: usuarioInstance, materias: Materia.list(), catedras: Catedra.list()]
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//ESTOS DE ABAJO PODRIAN IR EN OTRO CONTROLADOR

    def verMaterias (long id){
      def usuarioInstance = Usuario.get(id)
      def cursadas = usuarioInstance.cursadas
      [usuario:usuarioInstance, cursadas:cursadas]
    }

    def comentar(long id, long idUsuario, long idComentario, String textoComentario){
      def usuarioLogin = Usuario.get(idUsuario)
      def publicacionInstance = Publicacion.get(id)
      def comentarioInstance = Comentario.get(idComentario)
      if (publicacionInstance){
          Comentario comentario = comentarioService.crearComentario(textoComentario, usuarioLogin, publicacionInstance, null)
          try{
            usuarioService.comentarPublicacion(usuarioLogin,comentario,publicacionInstance)
          }
          catch (PromedioInsuficienteException a){
            comentarioService.eliminarComentario(comentario)
            flash.message = "El promedio de calificaciones del usuario es insuficiente para comentar esta publicacion"
          }
          catch (PublicacionCerradaException b){
            comentarioService.eliminarComentario(comentario)
            flash.message = "No se puede comentar una publicacion cerrada"
          }
          publicacionService.agregarComentario(publicacionInstance,comentario)
          redirect(controller:"publicacion", action: "verPublicacion", id: publicacionInstance.id, params: [idUsuario:idUsuario])
      }
      else{
          Comentario comentario = comentarioService.crearComentario(textoComentario, usuarioLogin, null, comentarioInstance)
          try{
            usuarioService.comentarComentario(usuarioLogin,comentario,comentarioInstance)
          }
          catch (PublicacionCerradaException d){
            comentarioService.eliminarComentario(comentario)
            flash.message = "No se puede comentar una publicacion cerrada"
          }
          comentarioService.agregarComentario(comentarioInstance,comentario)
          redirect(controller:"comentario", action: "verComentario", id: comentarioInstance.id, params: [idUsuario:idUsuario])
      }
    }

}
