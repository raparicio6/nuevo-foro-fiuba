package nuevo_foro_fiuba

class PublicacionCerradaException extends Exception{

    final String MENSAJE = "La publicación está cerrada"

    static constraints = {
    }

    PublicacionCerradaException(){}
}
