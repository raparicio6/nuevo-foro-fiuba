package nuevo_foro_fiuba

class Puntaje {

    Integer numero
    Integer signo

    static constraints = {}

    def calcular (Usuario usuario){
      this.numero =  usuario.getPuntajeActual() + (0**usuario.getPuntajeActual())
    }

    Puntaje (Integer signo, Integer numero) {
        this.signo = signo
        this.numero = numero
    }
}
