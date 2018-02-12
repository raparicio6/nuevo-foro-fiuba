package nuevo_foro_fiuba
import grails.gorm.transactions.Transactional

@Transactional
class PuntajeService {

  def serviceMethod() {}

   Puntaje crearPuntaje (Puntaje.TipoPuntaje tipo, Usuario usuario){
      Integer numeroPuntaje = this.calcularNumeroPuntaje(usuario)
      Puntaje puntaje = new Puntaje (tipo, numeroPuntaje)
      puntaje.save(failOnError:true)
      puntaje
    }
    
}
