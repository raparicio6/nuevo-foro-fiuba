

import grails.test.mixin.TestFor
import nuevo_foro_fiuba.PromedioInsuficienteException
import spock.lang.Specification
import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import nuevo_foro_fiuba.*

@TestFor(MensajePrivadoService)
class MensajePrivadoServiceTests {

    MensajePrivadoService mensajePrivadoService

    void setUp(){
        mensajePrivadoService = new MensajePrivadoService()
    }

    void tearDown() {

    }

    @Test
    void 'test envio un mensaje privado y lo busco por id'() {
        mockDomain(MensajePrivado)
        mockDomain(InformacionMensajeUsuario)
        mockDomain(Usuario)

        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 3)
        usuario.save()
        Usuario usuario2 = new Usuario('Marge', 'Simpson', 'MSimpson', 3)
        usuario2.save()
        MensajePrivado mensaje = mensajePrivadoService.enviarMensaje(usuario.id, usuario2.id, 'un mensaje')
        assert mensajePrivadoService.getMensajePrivadoById(mensaje.id) == mensaje
    }

    @Test
    void 'test busco usuario por id'() {
        mockDomain(Usuario)

        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 3)
        usuario.save()
        assert mensajePrivadoService.getUsuarioById(usuario.id) == usuario
    }

}