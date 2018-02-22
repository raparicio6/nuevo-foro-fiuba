package nuevo_foro_fiuba

class UsuarioController {

  def usuarioService
  def materiaService
  def catedraService

  def index() {
    redirect(action: "seleccionarUsuario")
  }

  def seleccionarUsuario(){
    [usuarioInstanceList: usuarioService.getAllUsuarios()]
  }

  def inicioUsuario(long idUsuario){
    def usuarioInstance = usuarioService.getUsuarioById(idUsuario)
    [usuarioInstance: usuarioInstance]
  }

  def listaUsuarios(long idUsuario, Float promedioMin, Float promedioMax){
    println(params.idMateria)
    if (!promedioMin)
       promedioMin=0
    if (!promedioMax)
       promedioMax=5
    def usuarios = usuarioService.filtrarPorPromedio(promedioMin, promedioMax)
    if (params.idMateria)
      usuarios = usuarioService.filtrarPorMateria(usuarios, params.idMateria)
    Usuario usuarioInstance = usuarioService.getUsuarioById(idUsuario)
    [usuarioInstanceList: usuarios, usuarioInstance: usuarioInstance, materias: materiaService.getAllMaterias(), catedras: catedraService.getAllCatedras()]
  }

  def verMateriasCursadas (long idUsuario){
    def usuarioInstance = usuarioService.getUsuarioById(idUsuario)
    def cursadas = usuarioService.getCursadas(usuarioInstance)
    [usuario:usuarioInstance, cursadas:cursadas]
  }

}
