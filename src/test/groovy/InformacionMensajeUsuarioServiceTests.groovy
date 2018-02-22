
import grails.test.mixin.TestFor
import nuevo_foro_fiuba.PromedioInsuficienteException
import spock.lang.Specification
import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import nuevo_foro_fiuba.*

@TestFor(InformacionMensajeUsuarioService)
class InformacionMensajeUsuarioServiceTests {

    InformacionMensajeUsuarioService informacionMensajeUsuarioService

    void setUp(){
        informacionMensajeUsuarioService = new InformacionMensajeUsuarioService()
    }

    void tearDown() {

    }

    @Test
    void 'test creo una informacion y la busco por id'() {
        mockDomain(Usuario)
        mockDomain(InformacionMensajeUsuario)
        mockDomain(MensajePrivado)

        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 3)
        usuario.save()
        Usuario usuario2 = new Usuario('Marge', 'Simpson', 'MSimpson', 3)
        usuario2.save()
        MensajePrivado mensaje = new MensajePrivado('un mensaje', null, null)
        mensaje.save()
        InformacionMensajeUsuario info = new InformacionMensajeUsuario(usuario, usuario2, mensaje, InformacionMensajeUsuario.RolUsuarioMensaje.EMISOR)
        info.save()
        assert informacionMensajeUsuarioService.getInformacionMensajeUsuarioById(info.id) == info
    }

    /*@Test
    void 'test busco los mensajes enviados de un usuario'() {
        mockDomain(Usuario)
        mockDomain(InformacionMensajeUsuario)
        mockDomain(MensajePrivado)

        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 3)
        usuario.save()
        Usuario usuario2 = new Usuario('Marge', 'Simpson', 'MSimpson', 3)
        usuario2.save()
        MensajePrivado mensaje = new MensajePrivado('un mensaje', null, null)
        mensaje.save()
        InformacionMensajeUsuario info = new InformacionMensajeUsuario(usuario, usuario2, mensaje, InformacionMensajeUsuario.RolUsuarioMensaje.EMISOR)
        info.save()
        InformacionMensajeUsuario info2 = new InformacionMensajeUsuario(usuario, usuario2, mensaje, InformacionMensajeUsuario.RolUsuarioMensaje.EMISOR)
        info2.save()
        InformacionMensajeUsuario info3 = new InformacionMensajeUsuario(usuario, usuario2, mensaje, InformacionMensajeUsuario.RolUsuarioMensaje.RECEPTOR)
        info3.save()
        assert informacionMensajeUsuarioService.obtenerInformacionMensajesEnviadosUsuario(usuario).size() == 2
        assert informacionMensajeUsuarioService.obtenerInformacionMensajesEnviadosUsuario(usuario).contains(info)
        assert informacionMensajeUsuarioService.obtenerInformacionMensajesEnviadosUsuario(usuario).contains(info2)
    }

    @Test
    void 'test busco los mensajes recibidos de un usuario'() {
        mockDomain(Usuario)
        mockDomain(InformacionMensajeUsuario)
        mockDomain(MensajePrivado)

        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 3)
        usuario.save()
        Usuario usuario2 = new Usuario('Marge', 'Simpson', 'MSimpson', 3)
        usuario2.save()
        MensajePrivado mensaje = new MensajePrivado('un mensaje', null, null)
        mensaje.save()
        InformacionMensajeUsuario info = new InformacionMensajeUsuario(usuario, usuario2, mensaje, InformacionMensajeUsuario.RolUsuarioMensaje.EMISOR)
        info.save()
        InformacionMensajeUsuario info2 = new InformacionMensajeUsuario(usuario, usuario2, mensaje, InformacionMensajeUsuario.RolUsuarioMensaje.EMISOR)
        info2.save()
        InformacionMensajeUsuario info3 = new InformacionMensajeUsuario(usuario, usuario2, mensaje, InformacionMensajeUsuario.RolUsuarioMensaje.RECEPTOR)
        info3.save()
        assert informacionMensajeUsuarioService.obtenerInformacionMensajesRecibidosUsuario(usuario).size() == 1
        assert informacionMensajeUsuarioService.obtenerInformacionMensajesEnviadosUsuario(usuario).contains(info3)
    }*/

}