package nuevo_foro_fiuba
import grails.gorm.transactions.Transactional

@Transactional
class PuntajeService {

  def serviceMethod() {}

   Puntaje crearPuntaje (Puntaje.TipoPuntaje tipo, Usuario usuario){
      Float numeroPuntaje = this.calcularNumeroPuntaje(usuario)
      Puntaje puntaje = new Puntaje (tipo, numeroPuntaje)      
      puntaje
    }

    Float calcularNumeroPuntaje (Usuario usuario){
      usuario.getPromedioCalificaciones() + 0**usuario.getPromedioCalificaciones()
    }



}
