
import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.support.*
import org.junit.*
import nuevo_foro_fiuba.*

@TestFor(UsuarioService)
class UsuarioServiceTests {

    Usuario usuario

    void setUp() {
        usuario = new Usuario(nombre: 'Homero', apellido: 'Simpson', nombreUsuario: 'HSimpson')
    }

    void tearDown() {
    }


}