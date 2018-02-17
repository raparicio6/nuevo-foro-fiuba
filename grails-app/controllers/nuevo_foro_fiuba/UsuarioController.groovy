package nuevo_foro_fiuba

class UsuarioController {

  def usuarioService

  def index() {
    redirect(action: "seleccionarUsuario", max: "10")
  }

  def seleccionarUsuario(Integer max){
    params.max = Math.min(max ?: 10, 100)
    [usuarioInstanceList: usuarioService.obtenerTodosLosUsuarios(), usuarioInstanceTotal: usuarioService.obtenerCantidadDeUsuariosTotal()]
  }

  def inicioUsuario(long idUsuario){
    def usuarioInstance = usuarioService.getUsuarioById(idUsuario)
    [usuarioInstance: usuarioInstance]
  }

  def listaUsuarios(long idUsuario, Integer max, Float promedioMin, Float promedioMax, long idMateria){
    if (!promedioMin)
       promedioMin=0
    if (!promedioMax)
       promedioMax=5
    def usuarios = usuarioService.filtrarPorPromedio(promedioMin, promedioMax)
    if (idMateria)
      usuarios = usuarioService.filtrarPorMateria(usuarios, idMateria)
    Usuario usuarioInstance = usuarioService.getUsuarioById(idUsuario)
    params.max = Math.min(max ?: 10, 100)
    [usuarioInstanceList: usuarios, usuarioInstanceTotal: usuarios.size(), usuarioInstance: usuarioInstance, materias: usuarioService.getAllMaterias(), catedras: usuarioService.getAllCatedras()]
  }

  def verMateriasCursadas (long idUsuario){
    def usuarioInstance = usuarioService.getUsuarioById(idUsuario)
    def cursadas = usuarioInstance.getCursadas()
    [usuario:usuarioInstance, cursadas:cursadas]
  }

}
