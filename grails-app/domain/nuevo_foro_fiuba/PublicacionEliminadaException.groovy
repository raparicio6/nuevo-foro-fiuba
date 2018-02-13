package nuevo_foro_fiuba

class PublicacionEliminadaException extends Exception{

    static final String MENSAJE = "La publicación está eliminada"

    static constraints = {
    }

    PublicacionEliminadaException(){}
}
