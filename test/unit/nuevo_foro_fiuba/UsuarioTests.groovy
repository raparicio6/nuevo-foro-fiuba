package ''

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TestGenerator
import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.support.*
import org.junit.*
import main.domain.*

@TestFor(UsuarioTests)
class UsuarioTests extends specificacion{

    Usuario usuario

    void setUp() {
        usuario = new Usuario(nombre: 'Homero', apellido: 'Simpson', nombreUsuario: 'HSimpson')
    }

    void tearDown() {
    }

    @Test
    void 'test soy un usuario que quiere comentar en una publicacion pero no tiene el promedido suficiente lanza exception' () {
        Publicacion publicacion = new Publicacion(usuarioCreador: usuario, promedioRequeridoParaComentar: 4)
        Comentario comentario = new Comentario()
        assert shouldFail(PromedioInsuficienteException) {
            usuario.comentarPublicacion(comentario, publicacion)
        }
    }

    @Test
    void 'test soy un usuario que quiere comentar en una publicacion cerrada lanza exception' () {
        Publicacion publicacion = new Publicacion(usuarioCreador: usuario, promedioRequeridoParaComentar: 4)
        Comentario comentario = new Comentario()
        usuario.cambiarEstado(publicacion)
        assert shouldFail(PublicacionCerradaException) {
            usuario.comentarPublicacion(comentario, publicacion)
        }
    }

    @Test
    void 'test soy un usuario que crea una publicacion y aparezco como creador' () {
        Publicacion publicacion = new Publicacion(usuarioCreador: usuario, promedioRequeridoParaComentar: 4)
        assert usuario.esDueñoDeLaPublicacion(publicacion) == true
    }

    @Test
    void 'test soy un usuario que quiere comentar en una publicacion eliminada lanza exception' () {
        Publicacion publicacion = new Publicacion(usuarioCreador: usuario, promedioRequeridoParaComentar: 4)
        Comentario comentario = new Comentario()
        publicaion.eliminar()
        assert shouldFail(PublicacionEliminadaException) {
            usuario.comentarPublicacion(comentario, publicacion)
        }
    }

    @Test
    void 'test soy un usuario que califico una publicacion e intento calificarla otra vez lanza exception' () {
        Publicacion publicacion = new Publicacion(usuarioCreador: usuario, promedioRequeridoParaComentar: 2)
        Usuario usuario2 = new Usuario(nombre: 'Benito', apellido: 'Camelas', promedioCalificaciones: 4)
        Calificacion calificacion = new Calificacion(usuario: usuario2, puntaje: 3, publicacion: publicacion)
        usuario2.calificar(publicacion, calificacion)
        Calificacion calificacion2 = new Calificacion(usuario: usuario2, puntaje: 5, publicacion: publicacion)
        assert shouldFail(UsuarioYaCalificoException) {
            usuario2.calificar(publicacion, calificacion2)
        }
    }

    @Test
    void 'test soy un usuario que califico un comentario e intento calificarlo otra vez lanza exception' () {
        Comentario comentario = new Comentario()
        Usuario usuario2 = new Usuario(nombre: 'Benito', apellido: 'Camelas', promedioCalificaciones: 4)
        Calificacion calificacion = new Calificacion(usuario: usuario2, puntaje: 3, comentario: comentario)
        usuario2.calificar(publicacion, calificacion)
        Calificacion calificacion2 = new Calificacion(usuario: usuario2, puntaje: 5, comentario: comentario)
        assert shouldFail(UsuarioYaCalificoException) {
            usuario2.calificar(comentario, calificacion2)
        }
    }

    @Test
    void 'test soy un usuario, voto en una encuesta e intento votar de vuelta lanza exception' () {
        Voto voto = new Voto(usuario: usuario)
        Opcion opcion = new Opcion(nombre: 'Opcion')
        Set<Opcion> opciones = [opcion]
        Encuesta encuesta = new Encuesta(nombre: 'Encuesta', opciones: opciones)
        Publicacion publicacion = new Publicacion(usuarioCreador: usuario)
        publicacion.agregarEncuesta(encuesta)
        Usuario usuario2 = new Usuario(nombre: 'Benito', apellido: 'Camelas')
        usuario2.votarOpcion(publicacion, opcion, voto)
        assert shouldFail(UsuarioYaVotoException) {
            usuario2.votarOpcion(publicacion, opcion, voto)
        }
    }

    @Test
    void 'test soy un usuario, creo una encuesta en intento votar lanza exception' () {
        Voto voto = new Voto(usuario: usuario)
        Opcion opcion = new Opcion(nombre: 'Opcion')
        Set<Opcion> opciones = [opcion]
        Encuesta encuesta = new Encuesta(nombre: 'Encuesta', opciones: opciones)
        Publicacion publicacion = new Publicacion(usuarioCreador: usuario)
        publicacion.agregarEncuesta(encuesta)
        assert shouldFail(CreadorEncuestaNoPuedeVotarException) {
            usuario.votarOpcion(publicacion, opcion, voto)
        }
    }

    @Test
    void 'test soy un usuario que intenta votar en una publicacion sin tener las materias necesarias lanza exception' () {
        Publicacion publicacion = new Publicacion(usuarioCreador: usuario)
        Usuario usuario2 = new Usuario(nombre: 'Benito', apellido: 'Camelas')
        Materia materia = new Materia(nombre: 'materia')
        Comentario comentario = new Comentario()
        publicacion.agregarMateriaRequeridaParaComentar(materia)
        assert shouldFail(USuarioNoPoseeMateriasNecesariasException) {
            usuario2.comentarPublicacion(comentario, publicacion)
        }
    }

    @Test
    void 'test soy un usuario, voto en una encuesta cerrada lanza exception' () {
        Voto voto = new Voto(usuario: usuario)
        Opcion opcion = new Opcion(nombre: 'Opcion')
        Set<Opcion> opciones = [opcion]
        Encuesta encuesta = new Encuesta(nombre: 'Encuesta', opciones: opciones)
        Publicacion publicacion = new Publicacion(usuarioCreador: usuario)
        publicacion.agregarEncuesta(encuesta)
        publicaion.cambiarEstado()
        Usuario usuario2 = new Usuario(nombre: 'Benito', apellido: 'Camelas')
        usuario2.votarOpcion(publicacion, opcion, voto)
        assert shouldFail(PublicacionCerradaException) {
            usuario2.votarOpcion(publicacion, opcion, voto)
        }
    }
}