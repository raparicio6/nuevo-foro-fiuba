
import grails.test.mixin.TestFor
import nuevo_foro_fiuba.PromedioInsuficienteException
import spock.lang.Specification
import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import nuevo_foro_fiuba.*

@TestFor(Publicacion)
class PublicacionTests {

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
    void 'test publicacion cambia de estado y confirma estar cerrada' () {
        assert !publicacion.estaCerrada()
        publicacion.cambiarEstado()
        assert publicacion.estaCerrada()
    }

    @Test
    void 'test publicacion se elimina y confirma estar eliminada' () {
        assert !publicacion.estaEliminada()
        publicacion.eliminar()
        assert publicacion.estaEliminada()
    }

    @Test
    void 'test modifico el texto de la publicacion y este resulta modificado' () {
        assert publicacion.texto == 'un texto'
        publicacion.modificarTexto('texto modificado')
        assert publicacion.texto == 'texto modificado'
    }

    @Test
    void 'test se cambia la catedra relacionada de una publicacion y esta se setea correctamente' () {
        Catedra catedra2 = new Catedra(materia, profesor, 'catedra2')
        publicacion.modificarCatedra(catedra2)
        assert publicacion.catedraRelacionada == catedra2
    }

    @Test
    void 'test se cambia la materia relacionada de una publicacion y esta se setea correctamente' () {
        Materia materia2 = new Materia('materia2', 'descripcion')
        publicacion.modificarMateria(materia2)
        assert publicacion.materiaRelacionada == materia2
    }

    @Test
    void 'test se modifica el promedio requerido para comentar sobre una publicacion y esta se modifica correctamente' () {
        assert publicacion.promedioRequeridoParaComentar == 0
        publicacion.modificarPromedioRequeridoParaComentar(4.5)
        assert publicacion.promedioRequeridoParaComentar == 4.5
    }

    @Test
    void 'test se agrega una encuesta a una publicacion y esta se agrega correctamente' () {
        assert publicacion.encuesta == null
        Opcion opcion = new Opcion('opcion')
        Set<Opcion> opciones = [opcion]
        Encuesta encuesta = new Encuesta('encuesta' , opciones)
        publicacion.agregarEncuesta(encuesta)
        assert publicacion.encuesta == encuesta
    }

    @Test
    void 'test se agrega un archivo a una publicacion y esta se agrega correctamente' () {
        assert publicacion.archivoAdjunto == null
        Archivo archivo = new Archivo('nombre', '/path')
        publicacion.agregarArchivo(archivo)
        assert publicacion.archivoAdjunto == archivo
    }

    @Test
    void 'test se agrega una calificacion y esta se agrega correctamente' () {
        assert publicacion.calificaciones.isEmpty()
        Puntaje puntaje = new Puntaje(Puntaje.TipoPuntaje.ME_GUSTA, 1)
        Calificacion calificacion = new Calificacion(usuario, puntaje, publicacion, null)
        publicacion.agregarCalificacion(calificacion)
        assert publicacion.calificaciones.contains(calificacion)
    }

    @Test
    void 'test se agrega un comentario a una publicacion y este se agrega correctamente' () {
        assert publicacion.comentarios.isEmpty()
        publicacion.agregarComentario(comentario)
        assert publicacion.comentarios.contains(comentario)
    }

    @Test
    void 'test se agrega un comentario y se elimina, este aparece correctamente eliminado' () {
        assert publicacion.comentarios.isEmpty()
        publicacion.agregarComentario(comentario)
        comentario.eliminar()
        assert !publicacion.obtenerComentariosNoEliminados().contains(comentario)
    }

}
