import grails.test.mixin.TestFor
import nuevo_foro_fiuba.PromedioInsuficienteException
import spock.lang.Specification
import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import nuevo_foro_fiuba.*

@TestFor(Comentario)
class ComentarioTests {

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
        comentario = new Comentario('un comentario', usuario, publicacion, null)
        puntaje = new Puntaje(Puntaje.TipoPuntaje.ME_GUSTA,3)
    }

    @Test
    void 'test modifico el texto del comentario y este resulta modificado'() {
        assert comentario.texto == 'un comentario'
        comentario.modificarTexto('otro comentario')
        assert comentario.texto == 'otro comentario'
    }

    @Test
    void 'test tengo un comentario que es eliminado y todos sus sub comentarios tambien se eliminan' () {
        Comentario comentario2 = new Comentario('un comentario2', usuario, null, comentario)
        comentario.agregarComentario(comentario2)
        comentario.eliminar()
        assert comentario.estado == Comentario.EstadoComentario.ELIMINADO
        assert comentario2.estado == Comentario.EstadoComentario.ELIMINADO
    }

    @Test
    void 'test tengo un comentario que es eliminado y los su lista de sub comentarios no eliminados aparece vacia' () {
        Comentario comentario2 = new Comentario('un comentario2', usuario, null, comentario)
        comentario.agregarComentario(comentario2)
        comentario.eliminar()
        assert !comentario.obtenerComentariosNoEliminados().contains(comentario2)
    }

    @Test
    void 'test se califica un comentario y esta aparece correctamente' () {
        assert comentario.calificaciones.isEmpty()
        Calificacion calificacion = new Calificacion(usuario, puntaje, null, comentario)
        comentario.agregarCalificacion(calificacion)
        assert comentario.calificaciones.contains(calificacion)
    }

}
