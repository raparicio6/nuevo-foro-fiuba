package ''

import grails.test.mixin.*
import org.junit.*

@TestFor(PublicacionTests)
class PublicacionTests {

    Publicacion publicacion

    void setup() {

        publicacion = new Publicacion(texto: 'texto de prueba')
    }

    void tearDown() {
    }

}