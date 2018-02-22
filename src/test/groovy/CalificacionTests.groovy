

import grails.test.mixin.TestFor
import nuevo_foro_fiuba.PromedioInsuficienteException
import spock.lang.Specification
import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import nuevo_foro_fiuba.*

@TestFor(Calificacion)
class CalificacionTests {

    Calificacion calificacion
    Usuario usuario
    Catedra catedra
    Materia materia
    Profesor profesor
    Publicacion publicacion
    Puntaje puntaje

    void setUp(){
        usuario = new Usuario(nombre: 'Homero', apellido: 'Simpson', nombreUsuario: 'HSimpson')
        materia = new Materia('texto', 'desc')
        profesor = new Profesor('Mauro')
        catedra = new Catedra(materia, profesor, 'catedra')
        publicacion = new Publicacion('un texto', usuario)
        publicacion.modificarMateria(materia)
        publicacion.modificarCatedra(catedra)
        puntaje = new Puntaje(Puntaje.TipoPuntaje.ME_GUSTA,3)
        calificacion = new Calificacion(usuario, puntaje, publicacion, null)
    }

    void tearDown() {

    }

    @Test
    void 'test se elimina una calificaion y esta se elimina correctamente' () {
        assert calificacion.estado == Calificacion.EstadoCalificacion.VIGENTE
        calificacion.eliminar()
        assert calificacion.estado == Calificacion.EstadoCalificacion.ELIMINADA
    }

}
