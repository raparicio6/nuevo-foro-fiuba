package nuevo_foro_fiuba

class Puntaje { // VALUE OBJECT

    enum TipoPuntaje  {
      ME_GUSTA,
      NO_ME_GUSTA,

      static Integer getProporcion(TipoPuntaje tipo){
          if (tipo == TipoPuntaje.ME_GUSTA)
            return 1
          if (tipo == TipoPuntaje.NO_ME_GUSTA)
            return -1
      }
    }

    Float numero
    TipoPuntaje tipo

    static constraints = {}
// ------------------------------------------------------------------------- //

    Puntaje(final TipoPuntaje tipo, final Float numero) {
      this.tipo = tipo
      this.numero = numero
    }
// ------------------------------------------------------------------------- //


}
