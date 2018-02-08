package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class PuntajeService {

  def serviceMethod() {}

   Puntaje crearPuntaje (Integer signo,Usuario usuario){
      Puntaje puntaje = new Puntaje (signo, 0)
      puntaje.calcular(usuario)
      puntaje.save(failOnError:true)
      return puntaje
    }



}
