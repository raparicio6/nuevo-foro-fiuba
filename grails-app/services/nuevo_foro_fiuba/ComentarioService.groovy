package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class ComentarioService {

    def serviceMethod() {}

    Comentario crearComentario(String texto, Usuario usuarioCreador,Publicacion publicacionComentada, Comentario comentarioComentado){
      Comentario comentario = new Comentario (texto, usuarioCreador, publicacionComentada, comentarioComentado)
      comentario.save(failOnError : true)
      comentario
    }

    def agregarComentario (Comentario comentarioAComentar, Comentario comentario){      
      comentarioAComentar.agregarComentario(comentario)
    }

    def modificarTexto (Comentario comentario, String nuevoTexto){
      comentario.setTexto(nuevoTexto)
    }

    def eliminarComentario (Comentario comentario){
      comentario.comentarios.collect {subcomentario -> subcomentario.delete(failOnError: true)}
      comentario.delete(failOnError: true)
    }

    def esSubComentario(Comentario comentario){
      comentario.esSubComentario()
    }
}
