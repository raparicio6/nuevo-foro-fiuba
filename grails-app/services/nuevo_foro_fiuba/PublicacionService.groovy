package nuevo_foro_fiuba
import nuevo_foro_fiuba.Publicacion.Estado

import grails.gorm.transactions.Transactional

@Transactional
class PublicacionService {


    def serviceMethod() {}

    Publicacion crearPublicacion(String texto, Usuario usuarioCreador, Materia materiaRelacionada, Catedra catedraRelacionada){
      Publicacion publicacion = new Publicacion (texto, usuarioCreador, materiaRelacionada, catedraRelacionada)
      publicacion.save(failOnError: true)
      return publicacion
    }

    def adjuntarArchivo(Publicacion publicacion, Archivo archivoAdjunto){
        publicacion.setArchivoAdjunto(archivoAdjunto)
    }

    def cerrarPublicacion (Publicacion publicacion){
      publicacion.setEstado(Estado.cerrado)
    }

    def reabrirPublicacion (Publicacion publicacion){
      publicacion.setEstado(Estado.abierto)
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

    String obtenerVista(publicacionInstance, usuarioInstance){
      return publicacionInstance.obtenerVista(usuarioInstance)
    }

    def modificarPuntajeMinimo (Publicacion publicacion, Integer puntaje){
      publicacion.setPuntajeRequeridoParaComentar(puntaje)
    }

    def agregarCalificacion (Publicacion publicacion, Calificacion calificacion){
      publicacion.setCalificaciones( publicacion.getCalificaciones() + [calificacion])
    }
}
