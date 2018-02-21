
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
        publicacion = new Publicacion('un texto', usuario, materia, catedra)
        comentario = new Comentario('comentario', usuario, publicacion, null)
        puntaje = new Puntaje(Puntaje.TipoPuntaje.ME_GUSTA,3)
    }

    void tearDown() {
    }

    @Test
    void 'test publicaion cambia de estado y confirma estar cerrada' () {
        publicacion.cambiarEstado()
        assert publicacion.estaCerrada()
    }

    @Test
    void 'test publicaion se elimina y confirma estar eliminada' () {
        publicacion.eliminar()
        assert publicacion.estaEliminada()
    }

    @Test
    void 'test modifico el texto de la publicacion y este resulta modificado' () {
        assert publicacion.texto == 'un texto'
        publicacion.modificarTexto('texto modificado')
        assert publicacion.texto == 'texto modificado'
    }

}