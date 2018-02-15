package ''

import grails.test.mixin.*
import org.junit.*

@TestFor(CommentarioTests)
class ComentarioTests {

    Comentario comentario

    void setup() {
        comentario = new Comentario(texto: 'un comentario', usuarioCreador: new Usuario(), publicacionComentada: new Publicacion())
    }

    void tearDown() {
    }

    void 'test modifico el texto del comentario y este resulta modificado'() {
        assert comentario.texto == 'un comentario'
        comentario.modificarTexto('otro comentario')
        assert comentario.texto == 'otro comentario'
    }
}