package nuevo_foro_fiuba

class MensajePrivadoController {

  def mensajePrivadoService

  def index(long idUsuario) {
    def usuarioInstance = mensajePrivadoService.getUsuarioById(idUsuario)
    def mensajesRecibidos = mensajePrivadoService.obtenerMensajesRecibidos(usuarioInstance)
    render (view:"listaMensajes", model:[usuarioInstance:usuarioInstance, mensajes:mensajesRecibidos, title:"Mensajes recibidos", enviadosRecibidos:"Recibidos", dePara:"De", mostrarResponder:true, action:"verMensajesEnviados", value:"Ver mensajes enviados"])
  }

  def verMensajesEnviados(long idUsuario){
    Usuario usuarioInstance = mensajePrivadoService.getUsuarioById(idUsuario)
    def mensajesEnviados = mensajePrivadoService.obtenerMensajesEnviados(usuarioInstance)
    render (view:"listaMensajes", model:[usuarioInstance:usuarioInstance, mensajes:mensajesEnviados, title:"Mensajes enviados", enviadosRecibidos:"Enviados", dePara:"Para", mostrarResponder:false, action:"index", value:"Volver a recibidos"])
  }

  def crearMensaje(long idUsuario){
    def usuarioInstance = mensajePrivadoService.getUsuarioById(idUsuario)
    render (view:"mensajePrivado", model:[usuario:usuarioInstance, usuarios:mensajePrivadoService.getAllUsuarios(), accion:"crear", title:"Crear mensaje"])
  }

  def verMensaje(long idMensajePrivado, long idUsuario, long idInformacion, Boolean mostrarResponder){
    def mensajePrivadoInstance = mensajePrivadoService.getMensajePrivadoById(idMensajePrivado)
    def usuarioInstance = mensajePrivadoService.getUsuarioById(idUsuario)
    def informacionMensajeUsuario = mensajePrivadoService.getInformacionMensajeUsuarioById(idInformacion)
    render (view:"mensajePrivado", model:[mensaje:mensajePrivadoInstance, usuario:usuarioInstance, informacion:informacionMensajeUsuario, accion:"ver", mostrarResponder:mostrarResponder])
  }

  def enviarMensaje (long idUsuarioCreador, long idUsuarioReceptor, String texto, long idMensajeAlCualResponde){
      def file = request.getFile('file')
      mensajePrivadoService.enviarMensaje(idUsuarioCreador, idUsuarioReceptor, texto, idMensajeAlCualResponde, file)
      redirect (action:"verMensajesEnviados", params:[idUsuario:idUsuarioCreador])
  }

  def eliminarMensaje (long idUsuario, long idInformacionMensajeUsuario){
    mensajePrivadoService.eliminarMensaje(idUsuario, idInformacionMensajeUsuario)
    redirect (action:"index", params:[idUsuario:idUsuario])
  }

  def descargarArchivoAdjunto(long idArchivo) {
    Archivo archivoInstance = mensajePrivadoService.getArchivoById(idArchivo)
    response.setContentType("APPLICATION/OCTET-STREAM")
    response.setHeader("Content-Disposition", "Attachment;Filename=\"${archivoInstance.nombre}\"")
    def file = new File(archivoInstance.path)
    def fileInputStream = new FileInputStream(file)
    def outputStream = response.getOutputStream()
    byte[] buffer = new byte[4096];
    int len;
    while ((len = fileInputStream.read(buffer)) > 0) {
        outputStream.write(buffer, 0, len);
    }
    outputStream.flush()
    outputStream.close()
    fileInputStream.close()
}

}
