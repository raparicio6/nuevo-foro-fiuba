import grails.test.mixin.*
import org.junit.*
import domain.*

@TestFor(Usuario)
class UsuarioTests {

    Usuario usuario

    void setup() {
        usuario = new Usuario(nombre: 'Homero', apellido: 'Simpson', nombreUsuario: 'HSimpson')
    }

    void 'test soy un usuario que quiere comentar en una publicacion pero no tiene el promedido suficiente' () {
        Publicacion publicacion = new Publicacion(usuarioCreador: usuario, promedioRequeridoParaComentar: 4)
        Comentario comentario = new Comentario()
        assert shouldFail(PromedioInsuficienteException) {
            usuario.comentarPublicacion(comentario, publicacion)
        }
    }

    void 'test soy un usuario que quiere comentar en una publicacion cerrada' () {
        Publicacion publicacion = new Publicacion(usuarioCreador: usuario, promedioRequeridoParaComentar: 4)
        Comentario comentario = new Comentario()
        usuario.cambiarEstado(publicacion)
        assert shouldFail(PublicacionCerradaException) {
            usuario.comentarPublicacion(comentario, publicacion)
        }
    }

    void 'test soy un usuario que crea una publicacion y aparezco como creador' () {
        Publicacion publicacion = new Publicacion(usuarioCreador: usuario, promedioRequeridoParaComentar: 4)
        assert shouldFail(PromedioInsuficienteException) {
            usuario.esDueñoDeLaPublicacion(publicacion)
        } == true
    }

    void 'test soy un usuario que quiere comentar en una publicacion eliminada' () {
        Publicacion publicacion = new Publicacion(usuarioCreador: usuario, promedioRequeridoParaComentar: 4)
        Comentario comentario = new Comentario()
        publicaion.eliminar()
        assert shouldFail(PublicacionEliminadaException) {
            usuario.comentarPublicacion(comentario, publicacion)
        }
    }

    void 'test soy un usuario que califico una publicacion e intento calificarla otra vez' () {
        Publicacion publicacion = new Publicacion(usuarioCreador: usuario, promedioRequeridoParaComentar: 2)
        Usuario usuario2 = new Usuario(nombre: 'Benito', apellido: 'Camelas', promedioCalificaciones: 4)
        Calificacion calificacion = new Calificacion(usuario: usuario2, puntaje: 3, publicacion: publicacion)
        usuario2.calificar(publicacion, calificacion)
        Calificacion calificacion2 = new Calificacion(usuario: usuario2, puntaje: 5, publicacion: publicacion)
        assert shouldFail(UsuarioYaCalificoException) {
            usuario2.calificar(publicacion, calificacion2)
        }
    }

    void 'test soy un usuario que califico un comentario e intento calificarlo otra vez' () {
        Comentario comentario = new Comentario()
        Usuario usuario2 = new Usuario(nombre: 'Benito', apellido: 'Camelas', promedioCalificaciones: 4)
        Calificacion calificacion = new Calificacion(usuario: usuario2, puntaje: 3, comentario: comentario)
        usuario2.calificar(publicacion, calificacion)
        Calificacion calificacion2 = new Calificacion(usuario: usuario2, puntaje: 5, comentario: comentario)
        assert shouldFail(UsuarioYaCalificoException) {
            usuario2.calificar(comentario, calificacion2)
        }
    }

}