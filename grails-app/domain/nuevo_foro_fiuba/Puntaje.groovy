package nuevo_foro_fiuba

class Puntaje { // VALUE OBJECT
  
    enum TipoPuntaje  {
      MEGUSTA,
      NOMEGUSTA,

      static Integer getProporcion(TipoPuntaje tipo){
          if (tipo == TipoPuntaje.MEGUSTA)
            return 1
          if (tipo == TipoPuntaje.NOMEGUSTA)
            return -1
      }
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

}
