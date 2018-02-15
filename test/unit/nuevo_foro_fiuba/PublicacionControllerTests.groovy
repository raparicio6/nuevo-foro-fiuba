package ''

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TestGenerator
import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.support.*
import org.junit.*
import main.domain.*

@TestFor(PublicacionControllerTests)
class PublicacionControllerTests extends specificacion {

    Usuario usuario

    void setUp() {
        usuario = new Usuario(nombre: 'Homero', apellido: 'Simpson', nombreUsuario: 'HSimpson')
    }

    void tearDown() {
    }


}