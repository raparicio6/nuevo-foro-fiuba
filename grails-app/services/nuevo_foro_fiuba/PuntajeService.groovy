package nuevo_foro_fiuba
import nuevo_foro_fiuba.Puntaje.TipoPuntaje

import grails.gorm.transactions.Transactional

@Transactional
class PuntajeService {

  def serviceMethod() {}

   Puntaje crearPuntaje (TipoPuntaje tipo, Usuario usuario){
      Puntaje puntaje = new Puntaje (tipo, 0)
      puntaje.calcular(usuario)
      puntaje.save(failOnError:true)
      return puntaje
    }



}
