

import grails.test.mixin.TestFor
import nuevo_foro_fiuba.PromedioInsuficienteException
import spock.lang.Specification
import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import nuevo_foro_fiuba.*

@TestFor(InformacionMensajeUsuario)
class InformacionMensajeUsuarioTests {

    InformacionMensajeUsuario info

    void setUp(){
        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 0)
        Usuario usuario2 = new Usuario('Homero', 'Simpson', 'HSimpson', 0)
        MensajePrivado mensajePrivado = new MensajePrivado('un mensaje', null, null)
        info = new InformacionMensajeUsuario(usuario, usuario2, mensajePrivado, InformacionMensajeUsuario.RolUsuarioMensaje.EMISOR)
    }

    void tearDown() {

    }

    @Test
    void 'test se elimina una informacion y esta aparece como eliminada' () {
        assert info.estado == InformacionMensajeUsuario.EstadoInformacionMensajeUsuario.VIGENTE
        info.eliminar()
        assert info.estado == InformacionMensajeUsuario.EstadoInformacionMensajeUsuario.ELIMINADA
    }

}