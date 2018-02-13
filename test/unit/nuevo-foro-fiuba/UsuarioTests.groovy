import grails.test.mixin.*
import org.junit.*

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

}