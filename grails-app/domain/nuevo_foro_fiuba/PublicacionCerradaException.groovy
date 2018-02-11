package nuevo_foro_fiuba

class PublicacionCerradaException extends Exception{

    static final String MENSAJE = "La publicación está cerrada"

    static constraints = {
    }

    PublicacionCerradaException(){}
}
