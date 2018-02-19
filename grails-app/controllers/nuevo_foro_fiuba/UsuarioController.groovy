package nuevo_foro_fiuba

class UsuarioController {

  def usuarioService

  def index() {
    redirect(action: "seleccionarUsuario")
  }

  def seleccionarUsuario(){
    [usuarioInstanceList: usuarioService.obtenerTodosLosUsuarios()]
  }

  def inicioUsuario(long idUsuario){
    def usuarioInstance = usuarioService.getUsuarioById(idUsuario)
    [usuarioInstance: usuarioInstance]
  }

  def listaUsuarios(long idUsuario, Float promedioMin, Float promedioMax, long idMateria){
    if (!promedioMin)
       promedioMin=0
    if (!promedioMax)
       promedioMax=5
    def usuarios = usuarioService.filtrarPorPromedio(promedioMin, promedioMax)
    if (idMateria)
      usuarios = usuarioService.filtrarPorMateria(usuarios, idMateria)
    Usuario usuarioInstance = usuarioService.getUsuarioById(idUsuario)
    [usuarioInstanceList: usuarios, usuarioInstance: usuarioInstance, materias: usuarioService.getAllMaterias(), catedras: usuarioService.getAllCatedras()]
  }

  def verMateriasCursadas (long idUsuario){
    def usuarioInstance = usuarioService.getUsuarioById(idUsuario)
    def cursadas = usuarioInstance.getCursadas()
    [usuario:usuarioInstance, cursadas:cursadas]
  }

}
