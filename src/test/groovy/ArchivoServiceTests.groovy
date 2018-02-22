

import grails.test.mixin.TestFor
import nuevo_foro_fiuba.PromedioInsuficienteException
import spock.lang.Specification
import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import nuevo_foro_fiuba.*

@TestFor(ArchivoService)
class ArchivoServiceTests {

    ArchivoService archivoService

    void setUp(){
        archivoService = new ArchivoService()
    }

    void tearDown() {

    }

    @Test
    void 'test creo un archivo y lo busco por id'() {
        mockDomain(Archivo)

        Archivo archivo = new Archivo('nombre', 'path')
        archivo.save()
        assert archivoService.getArchivoById(archivo.id) == archivo
    }

}