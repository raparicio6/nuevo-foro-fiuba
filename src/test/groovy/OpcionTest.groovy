

import grails.test.mixin.TestFor
import nuevo_foro_fiuba.PromedioInsuficienteException
import spock.lang.Specification
import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import nuevo_foro_fiuba.*

@TestFor(Opcion)
class OpcionTest {

    Opcion opcion

    void setUp(){
        opcion = new Opcion('opcion')
    }

    void tearDown() {

    }

    @Test
    void 'test se agrega un voto a una opcion y este se agrega correctamente' () {
        assert opcion.votos.isEmpty()
        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 0)
        Voto voto = new Voto(usuario)
        opcion.agregarVoto(voto)
        assert opcion.votos.contains(voto)
    }

}
