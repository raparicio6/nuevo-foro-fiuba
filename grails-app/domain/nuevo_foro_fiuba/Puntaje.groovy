package nuevo_foro_fiuba

class Puntaje {

    enum TipoPuntaje  {
      meGusta, noMeGusta
    }

    Integer numero
    TipoPuntaje tipo

    static constraints = {}

    def calcular (Usuario usuario){
      this.numero =  usuario.getPuntajeActual() + (0**usuario.getPuntajeActual())
    }

    Puntaje (TipoPuntaje tipo, Integer numero) {
        this.tipo = tipo
        this.numero = numero
    }

    static Integer getSignoTipo(TipoPuntaje tipo){
        if (tipo == TipoPuntaje.meGusta)
          return 1
        if (tipo == TipoPuntaje.noMeGusta)
          return -1
    }

}
