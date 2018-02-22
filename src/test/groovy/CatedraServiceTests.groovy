

import grails.test.mixin.TestFor
import nuevo_foro_fiuba.PromedioInsuficienteException
import spock.lang.Specification
import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import nuevo_foro_fiuba.*

@TestFor(CatedraService)
class CatedraServiceTests {

    CatedraService catedraService

    void setUp(){
        catedraService = new CatedraService()
    }

    void tearDown() {

    }

    @Test
    void 'test creo una catedra y la busco por id'() {
        mockDomain(Catedra)
        mockDomain(Materia)
        mockDomain(Profesor)

        Profesor profesor = new Profesor('profesor')
        profesor.save()
        Materia materia = new Materia('nombre', 'desc')
        materia.save()
        Catedra catedra = new Catedra(materia, profesor, 'desc')
        catedra.save()
        assert catedraService.getCatedraById(catedra.id) == catedra
    }

    @Test
    void 'test busco todas las catedras'() {
        mockDomain(Catedra)
        mockDomain(Materia)
        mockDomain(Profesor)

        Profesor profesor = new Profesor('profesor')
        profesor.save()
        Materia materia = new Materia('nombre', 'desc')
        materia.save()
        Catedra catedra = new Catedra(materia, profesor, 'desc')
        catedra.save()
        Catedra catedra2 = new Catedra(materia, profesor, 'desc')
        catedra2.save()
        Catedra catedra3 = new Catedra(materia, profesor, 'desc')
        catedra3.save()

        assert catedraService.getAllCatedras().size() == 3
        assert catedraService.getAllCatedras().contains(catedra)
        assert catedraService.getAllCatedras().contains(catedra2)
        assert catedraService.getAllCatedras().contains(catedra3)
    }

}