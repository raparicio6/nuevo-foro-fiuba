package nuevo_foro_fiuba
import nuevo_foro_fiuba.Publicacion.EstadoPublicacion

import grails.gorm.transactions.Transactional

@Transactional
class PublicacionService {

    def serviceMethod() {}

    Publicacion crearPublicacion(String texto, Usuario usuarioCreador, Materia materiaRelacionada, Catedra catedraRelacionada){
      Publicacion publicacion = new Publicacion (texto, usuarioCreador, materiaRelacionada, catedraRelacionada)
      publicacion.save(failOnError: true)
      publicacion
    }

    def adjuntarArchivo(Publicacion publicacion, Archivo archivoAdjunto){
        publicacion.setArchivoAdjunto(archivoAdjunto)
    }

    def cerrarPublicacion (Publicacion publicacion){
      publicacion.setEstado(EstadoPublicacion.cerrada)
    }

    def abrirPublicacion (Publicacion publicacion){
      publicacion.setEstado(EstadoPublicacion.abierta)
    }

    def agregarComentario (Publicacion publicacion, Comentario comentario){
      publicacion.setComentarios(publicacion.getComentarios() + [comentario])
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
