package nuevo_foro_fiuba

class Puntaje {

    enum TipoPuntaje  {
      meGusta, noMeGusta
    }

    Integer numero
    TipoPuntaje tipo

    static constraints = {}
// ------------------------------------------------------------------------- //

    Puntaje(TipoPuntaje tipo, Integer numero) {
      this.tipo = tipo
      this.numero = numero
    }
// ------------------------------------------------------------------------- //
    def calcular (Usuario usuario){
      this.setNumero(usuario.getPromedioCalificaciones() + (0**usuario.getPromedioCalificaciones()))
    }

    static Integer getSignoTipo(TipoPuntaje tipo){
        if (tipo == TipoPuntaje.meGusta)
          return 1
        if (tipo == TipoPuntaje.noMeGusta)
          return -1
    }
}
