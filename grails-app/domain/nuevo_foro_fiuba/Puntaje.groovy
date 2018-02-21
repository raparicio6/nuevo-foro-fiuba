package nuevo_foro_fiuba

class Puntaje { // VALUE OBJECT

    enum TipoPuntaje  {
      ME_GUSTA,
      NO_ME_GUSTA,

      static Float getProporcion(TipoPuntaje tipo){
        if (tipo == TipoPuntaje.ME_GUSTA)
          return 0.05
        if (tipo == TipoPuntaje.NO_ME_GUSTA)
          return -0.05
      }
    }

    Float numero
    TipoPuntaje tipo

    static constraints = {
      numero nullable:false
      tipo nullable:false
    }

// ------------------------------------------------------------------------- //
    Puntaje(final TipoPuntaje tipo, final Float numero) {
      this.tipo = tipo
      this.numero = numero
    }
    
// ------------------------------------------------------------------------- //


}
