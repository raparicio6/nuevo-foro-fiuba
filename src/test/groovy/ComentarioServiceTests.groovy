

import grails.test.mixin.TestFor
import nuevo_foro_fiuba.PromedioInsuficienteException
import spock.lang.Specification
import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import nuevo_foro_fiuba.*

@TestFor(ComentarioService)
class ComentarioServiceTests {

    ComentarioService comentarioService

    void setUp(){
        comentarioService = new ComentarioService()
    }

    void tearDown() {

    }

    /*@Test
    void 'test creo un comentario y lo busco por id'() {
        mockDomain(Comentario)
        mockDomain(Usuario)

        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 4)
        usuario.save()
        Comentario comentario = comentarioService.crearComentario('comentario', usuario)
        assert comentarioService.getComentarioById(comentario.id) == comentario
    }

    @Test
    void 'test busco usuario por id'() {
        mockDomain(Usuario)

        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 3)
        usuario.save()
        assert comentarioService.getUsuarioById(usuario.id) == usuario
    }

    @Test
    void 'test modifico el texto de un comentario'() {
        mockDomain(Comentario)
        mockDomain(Usuario)

        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 4)
        usuario.save()
        Comentario comentario = comentarioService.crearComentario('comentario', usuario)
        assert comentario.texto == 'comentario'
        comentarioService.modificarTextoComentario(usuario.id, comentario.id, 'otro comentario')
        assert comentario.texto == 'otro comentario'
    }

    @Test
    void 'test comento un comentario y lo puedo encontrar en la lista de comentarios' () {
        mockDomain(Comentario)
        mockDomain(Usuario)

        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 4)
        usuario.save()
        Comentario comentario = comentarioService.crearComentario('comentario', usuario)
        Comentario comentario2 = comentarioService.comentarComentario(usuario.id,  'otro comentario', comentario.id)
        assert comentarioService.obtenerComentariosNoEliminados(comentario).contains(comentario2)
    }

    @Test
    void 'test comento un comentario, elimino el segundo comentario y no lo puedo encontrar en la lista de comentarios' () {
        mockDomain(Comentario)
        mockDomain(Usuario)

        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 4)
        usuario.save()
        Comentario comentario = comentarioService.crearComentario('comentario', usuario)
        Comentario comentario2 = comentarioService.comentarComentario(usuario.id,  'otro comentario', comentario.id)
        comentarioService.eliminarComentario(comentario2)
        assert !comentarioService.obtenerComentariosNoEliminados(comentario).contains(comentario2)
    }*/

}