

import grails.test.mixin.TestFor
import nuevo_foro_fiuba.CreadorEncuestaNoPuedeVotarException
import nuevo_foro_fiuba.PromedioInsuficienteException
import nuevo_foro_fiuba.PublicacionCerradaException
import nuevo_foro_fiuba.PublicacionEliminadaException
import nuevo_foro_fiuba.Usuario
import nuevo_foro_fiuba.UsuarioNoPoseeMateriasNecesariasException
import nuevo_foro_fiuba.UsuarioYaCalificoException
import nuevo_foro_fiuba.UsuarioYaVotoException
import spock.lang.Specification
import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import nuevo_foro_fiuba.*

@TestFor(Usuario)
class UsuarioTests {

    Usuario usuario
    Publicacion publicacion
    Catedra catedra
    Materia materia
    Profesor profesor
    Comentario comentario
    Puntaje puntaje

    void setUp() {
        usuario = new Usuario(nombre: 'Homero', apellido: 'Simpson', nombreUsuario: 'HSimpson')
        materia = new Materia('texto', 'desc')
        profesor = new Profesor('Mauro')
        catedra = new Catedra(materia, profesor, 'catedra')
        publicacion = new Publicacion('un texto', usuario)
        publicacion.modificarMateria(materia)
        publicacion.modificarCatedra(catedra)
        comentario = new Comentario('comentario', usuario, publicacion, null)
        puntaje = new Puntaje(Puntaje.TipoPuntaje.ME_GUSTA,3)
    }

    void tearDown() {
    }

    @Test
    void 'test soy un usuario que quiere comentar en una publicacion pero no tiene el promedido suficiente lanza exception' () {
        publicacion.modificarPromedioRequeridoParaComentar(4)
        Usuario usuario2 = new Usuario(nombre: 'Benito', apellido: 'Camelas', promedioCalificaciones: 2)
        shouldFail(PromedioInsuficienteException) {
            assert usuario2.comentarPublicacion(comentario, publicacion)
        }
    }

    @Test
    void 'test soy un usuario que quiere comentar en una publicacion cerrada lanza exception' () {
        publicacion.cambiarEstado()
        shouldFail(PublicacionCerradaException) {
            assert usuario.comentarPublicacion(comentario, publicacion)
        }
    }

    @Test
    void 'test soy un usuario que crea una publicacion y aparezco como creador' () {
        assert usuario.esDueñoDeLaPublicacion(publicacion)
    }

    @Test
    void 'test soy un usuario que quiere comentar en una publicacion eliminada lanza exception' () {
        publicacion.eliminar()
        shouldFail(PublicacionEliminadaException) {
            assert usuario.comentarPublicacion(comentario, publicacion)
        }
    }

    @Test
    void 'test soy un usuario que califico una publicacion e intento calificarla otra vez lanza exception' () {
        publicacion.modificarPromedioRequeridoParaComentar(2)
        Usuario usuario2 = new Usuario(nombre: 'Benito', apellido: 'Camelas', promedioCalificaciones: 4)
        Calificacion calificacion = new Calificacion(usuario2, puntaje, publicacion, null)
        usuario2.calificar(publicacion, calificacion)
        Puntaje puntaje2 = new Puntaje(Puntaje.TipoPuntaje.ME_GUSTA, 3)
        Calificacion calificacion2 = new Calificacion(usuario2, puntaje2, publicacion, null)
        shouldFail(UsuarioYaCalificoException) {
            assert usuario2.calificar(publicacion, calificacion2)
        }
    }

    @Test
    void 'test soy un usuario que califico un comentario e intento calificarlo otra vez lanza exception' () {
        Usuario usuario2 = new Usuario(nombre: 'Benito', apellido: 'Camelas', promedioCalificaciones: 4)
        Calificacion calificacion = new Calificacion(usuario2, puntaje, null, comentario)
        usuario2.calificar(comentario, calificacion)
        Puntaje puntaje2 = new Puntaje(Puntaje.TipoPuntaje.ME_GUSTA, 3)
        Calificacion calificacion2 = new Calificacion(usuario2, puntaje2, null, comentario)
        shouldFail(UsuarioYaCalificoException) {
            assert usuario2.calificar(comentario, calificacion2)
        }
    }

    @Test
    void 'test soy un usuario, voto en una encuesta e intento votar de vuelta lanza exception' () {
        Opcion opcion = new Opcion('opcion')
        Set<Opcion> opciones = [opcion]
        Encuesta encuesta = new Encuesta(nombre: 'Encuesta', opciones: opciones)
        publicacion.agregarEncuesta(encuesta)
        Usuario usuario2 = new Usuario(nombre: 'Benito', apellido: 'Camelas', promedioCalificaciones:  5)
        Voto voto = new Voto(usuario2)
        usuario2.votarOpcion(publicacion, opcion, voto)
        shouldFail(UsuarioYaVotoException) {
            assert usuario2.votarOpcion(publicacion, opcion, voto)
        }
    }

    @Test
    void 'test soy un usuario, creo una encuesta e intento votar lanza exception' () {
        Voto voto = new Voto(usuario)
        Opcion opcion = new Opcion('opcion')
        Set<Opcion> opciones = [opcion]
        Encuesta encuesta = new Encuesta('encuesta', opciones)
        publicacion.agregarEncuesta(encuesta)
        shouldFail(CreadorEncuestaNoPuedeVotarException) {
            assert usuario.votarOpcion(publicacion, opcion, voto)
        }
    }

    @Test
    void 'test soy un usuario que intenta votar en una publicacion sin tener las materias necesarias lanza exception' () {
        Usuario usuario2 = new Usuario(nombre: 'Benito', apellido: 'Camelas', promedioCalificaciones: 5)
        publicacion.agregarMateriaRequeridaParaComentar(materia)
        shouldFail(UsuarioNoPoseeMateriasNecesariasException) {
            assert usuario2.comentarPublicacion(comentario, publicacion)
        }
    }

    @Test
    void 'test soy un usuario, voto en una encuesta cerrada lanza exception' () {
        Opcion opcion = new Opcion('opcion')
        Set<Opcion> opciones = [opcion]
        Encuesta encuesta = new Encuesta(nombre: 'Encuesta', opciones: opciones)
        publicacion.agregarEncuesta(encuesta)
        publicacion.cambiarEstado()
        Usuario usuario2 = new Usuario(nombre: 'Benito', apellido: 'Camelas')
        Voto voto = new Voto(usuario2)
        shouldFail(PublicacionCerradaException) {
            assert usuario2.votarOpcion(publicacion, opcion, voto)
        }
    }

    @Test
    void 'test soy un usuario, creo un comentario y aparezco como duenio' () {
        assert usuario.esDueñoDelComentario(comentario)
    }

    @Test
    void 'test soy un usuario que tiene las materias necesarias para publicar' () {
        assert usuario.poseeMateriasNecesariasParaComentar(publicacion)
    }
}
