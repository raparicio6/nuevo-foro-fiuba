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
        publicacion = new Publicacion('un texto', usuario, materia, catedra)
        comentario = new Comentario('comentario', usuario, publicacion, null)
        puntaje = new Puntaje(Puntaje.TipoPuntaje.ME_GUSTA,3)
    }

    void 'test modifico el texto del comentario y este resulta modificado'() {
        assert comentario.texto == 'un comentario'
        comentario.modificarTexto('otro comentario')
        assert comentario.texto == 'otro comentario'
    }
}