package nuevo_foro_fiuba

class Voto {

  Usuario usuario

  static constraints = {
    usuario nullable: false
  }

  Voto(Usuario usuario){
    this.usuario = usuario
  }
  
}
