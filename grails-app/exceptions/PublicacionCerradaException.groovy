package nuevo_foro_fiuba

class PublicacionCerradaException extends RuntimeException{

    final String MENSAJE = "La publicación está cerrada"

    static constraints = {
    }

    PublicacionCerradaException(){}
}
