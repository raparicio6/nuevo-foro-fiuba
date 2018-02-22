

import grails.test.mixin.TestFor
import nuevo_foro_fiuba.PromedioInsuficienteException
import spock.lang.Specification
import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import nuevo_foro_fiuba.*

@TestFor(MateriaService)
class MateriaServiceTests {

    MateriaService materiaService

    void setUp() {
        materiaService = new MateriaService()
    }

    void tearDown() {

    }

    @Test
    void 'test creo una materia y la busco por id'() {
        mockDomain(Materia)

        Materia materia = new Materia('nombre', 'desc')
        materia.save()
        assert materiaService.getMateriaById(materia.id) == materia
    }

    @Test
    void 'test busco todas las materias'() {
        mockDomain(Materia)

        Materia materia = new Materia('nombre', 'desc')
        materia.save()
        Materia materia2 = new Materia('nombre', 'desc')
        materia2.save()
        Materia materia3 = new Materia('nombre', 'desc')
        materia3.save()

        assert materiaService.getAllMaterias().size() == 3
        assert materiaService.getAllMaterias().contains(materia)
        assert materiaService.getAllMaterias().contains(materia2)
        assert materiaService.getAllMaterias().contains(materia3)
    }
}
