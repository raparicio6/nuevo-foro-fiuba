package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class UsuarioController {

  def usuarioService

  def index() {
    redirect(action: "seleccionarUsuario", max: "10")
  }

  def seleccionarUsuario(Integer max){
    params.max = Math.min(max ?: 10, 100)
    [usuarioInstanceList: Usuario.list(params), usuarioInstanceTotal: Usuario.count()]
  }

  def inicioUsuario(long id){
    def usuarioInstance = Usuario.get(id)
    [usuarioInstance: usuarioInstance]
  }

  def listaUsuarios(long id, Integer max, Integer promedioMin, Integer promedioMax, long idMateria){
    if (!promedioMin)
       promedioMin=0
    if (!promedioMax)
       promedioMax=5
    def usuarios = Usuario.list().findAll {usuario -> usuario.promedioCalificaciones >= promedioMin && usuario.promedioCalificaciones <= promedioMax}
    if (idMateria)
      usuarios= usuarios.findAll { usuario -> idMateria in usuarioService.materiasCursadas(usuario) }
    Usuario usuarioInstance = Usuario.get(id)
    params.max = Math.min(max ?: 10, 100)
    [usuarioInstanceList: usuarios, usuarioInstanceTotal: usuarios.size() , usuarioInstance: usuarioInstance, materias: Materia.list(), catedras: Catedra.list()]
  }

  def verMateriasCursadas (long id){
    def usuarioInstance = Usuario.get(id)
    def cursadas = usuarioInstance.cursadas
    [usuario:usuarioInstance, cursadas:cursadas]
  }

}
