package nuevo_foro_fiuba

class MensajePrivadoController {

  def mensajePrivadoService

  def index(long id) {
    def usuarioInstance = Usuario.get(id)
    // .findAll {mensajePrivado -> mensajePrivado.rolUsuario == InformacionMensajeUsuario.RolUsuarioMensaje.RECEPTOR}
    render (view: "casilla", model:[usuarioInstance:usuarioInstance, mensajes:usuarioInstance.mensajes])
  }

  def verMensaje(long id, long idUsuario, long idInformacion){
    def mensajePrivadoInstance = MensajePrivado.get(id)
    def usuarioInstance = Usuario.get(idUsuario)
    def informacionMensajeUsuario = InformacionMensajeUsuario.get(idInformacion)
    // println(informacionMensajeUsuario.toString())
    [mensaje:mensajePrivadoInstance, usuario:usuarioInstance, informacion:informacionMensajeUsuario]
  }

  def enviarMensaje (long idUsuarioCreador, long  idUsuarioReceptor, String texto, MensajePrivado mensajeAlCualResponde, String archivoAdjunto){
    def usuarioCreadorInstance = Usuario.get(idUsuarioCreador)
    println(usuarioCreadorInstance.nombre)
    def usuarioReceptorInstance = Usuario.get(idUsuarioReceptor)
    mensajePrivadoService.enviarMensaje(usuarioCreadorInstance, usuarioReceptorInstance, texto, mensajeAlCualResponde, archivoAdjunto)
    redirect (action:"index", id:idUsuarioCreador)
  }

  def crearMensaje(long id){
    def usuarioInstance = Usuario.get(id)
    [usuarioInstance:usuarioInstance, usuarios:Usuario.list()]
  }

}
