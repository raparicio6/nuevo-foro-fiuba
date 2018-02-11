package nuevo_foro_fiuba
import grails.gorm.transactions.Transactional

@Transactional
class PublicacionService {

    def serviceMethod() {}

    Publicacion crearPublicacion(String texto, Usuario usuarioCreador, Materia materiaRelacionada, Catedra catedraRelacionada){
      Publicacion publicacion = new Publicacion (texto, usuarioCreador, materiaRelacionada, catedraRelacionada)
      publicacion.save(failOnError: true)
      publicacion
    }

    def agregarComentario (Publicacion publicacion, Comentario comentario){      
      publicacion.agregarComentario(comentario)
    }

    def modificarTexto (Publicacion publicacion, String nuevoTexto){
      publicacion.setTexto(nuevoTexto)
    }

    def modificarMateria (Publicacion publicacion, Materia nuevaMateria){
      publicacion.setMateriaRelacionada(nuevaMateria)
    }

    def modificarCatedra (Publicacion publicacion, Catedra nuevaCatedra){
      publicacion.setCatedraRelacionada(nuevaCatedra)
    }

    def eliminarPublicacion (Publicacion publicacion){
      publicacion.delete(failOnError: true)
    }

    def modificarPromedioRequeridoParaComentar (Publicacion publicacion, Integer promedio){
      publicacion.setPromedioRequeridoParaComentar(promedio)
    }

}
