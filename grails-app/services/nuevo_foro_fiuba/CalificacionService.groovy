package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class CalificacionService {

    def serviceMethod() {}

    Calificacion crearCalificacion(Usuario usuario, Puntaje puntaje, Publicacion publicacion,Comentario comentario){
        Calificacion calificacion = new Calificacion(usuario, puntaje, publicacion, comentario)
        calificacion.save(failOnError:true)
        calificacion
    }

}
