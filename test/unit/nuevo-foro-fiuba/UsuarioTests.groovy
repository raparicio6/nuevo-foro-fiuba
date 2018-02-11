import grails.test.mixin.*
import org.junit.*

@TestFor(Usuario)
class UsuarioTests {

    void setup() {
        usuario = new Usuario(nombre: 'Nombre1', apellido: 'Apellido1', nombreUsuario: 'Nick1')
    }

    void usuarioCreaUnaPublicacionY() {
        fail "Implement me"
    }
}