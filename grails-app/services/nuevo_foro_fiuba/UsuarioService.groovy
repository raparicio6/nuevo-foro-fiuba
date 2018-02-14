package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class UsuarioService {

  def serviceMethod() {}

  Usuario crearUsuario(String nombre, String apellido, String nombreUsuario){
    Usuario usuario = new Usuario(nombre, apellido, nombreUsuario)
    usuario.save(failOnError: true)
    usuario
  }

  def materiasCursadas(Usuario usuario){
    usuario.cursadas.collect {cursada -> cursada.catedra.materia.id}
    // refactorizar y moverlo para el dominio, desde aca simplemente llamar a ese
    // metodo
  }

}
