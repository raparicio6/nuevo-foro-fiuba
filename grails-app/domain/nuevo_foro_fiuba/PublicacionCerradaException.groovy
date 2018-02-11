package nuevo_foro_fiuba

class PublicacionCerradaException extends Exception{

    static final MENSAJE = "La publicación está cerrada"

    static constraints = {
    }

    PublicacionCerradaException(){}
}
