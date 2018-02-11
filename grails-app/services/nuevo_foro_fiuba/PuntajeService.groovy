package nuevo_foro_fiuba
import grails.gorm.transactions.Transactional

@Transactional
class PuntajeService {

  def serviceMethod() {}

   Puntaje crearPuntaje (Puntaje.TipoPuntaje tipo, Usuario usuario){
      Puntaje puntaje = new Puntaje (tipo, 0)
      puntaje.calcular(usuario)
      puntaje.save(failOnError:true)
      puntaje
    }



}
